package minirestwebservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import orgExampleFahrradverleih.*;





@Path( "/verleih" )
public class FahrradService {

	private static final String FahrradVerleih_XML = "src/XML/FahrradVerleih.xml";

	/**
	 * Diese Methode überführt Java-Objektdaten in eine XML-Datei. (Das sogenannte Marshalling)
	 * @param collection, Java-Objekt für den Marshalling-Prozess.
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void xmlMarshal(Collection collect) throws JAXBException, IOException{
		//Initialisieren des Marshallers
		JAXBContext context = JAXBContext.newInstance(Collection.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		//Umwandeln der Java-Objekte und schreiben in die XML-Datei
		Writer w = null;
		try {
			w = new FileWriter(FahrradVerleih_XML);
			m.marshal(collect, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Diese Methode überführt eine XML-Datei in Java-Objektdaten. (Das sogenannte Unmarshalling)
	 * @return Java-Objekt des Unmarshalling-Prozesses.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public Collection xmlUnmarshal() throws JAXBException, FileNotFoundException{
		//initialisieren des Unmarshallers
		JAXBContext context = JAXBContext.newInstance(Collection.class);
		Unmarshaller um = context.createUnmarshaller();

		//Erzeugen der vollständigen Java-Objekte durch die XML-Datei
		return (Collection) um.unmarshal(new FileReader(FahrradVerleih_XML));
	}



	/**
	 * Gibt den kompletten Inhalt des Verleihsystems aus.
	 * @return kompletter Inhalt des Verleihsystems in Form des XML.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET 
	@Produces( "application/xml" )
	public Collection getAll() throws JAXBException, FileNotFoundException {
		//Gibt die vollständigen Inhalte der XML-Datei zurück.
		return xmlUnmarshal();
	}


	/**
	 * Gibt alle eingetragenen User des Verleihsystems aus.
	 * @return Liste aller Users in Form des XML-Schemas.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path( "/users")
	@Produces( "application/xml" )
	public Collection getUsers() throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Erstellt eine neue Instanz von Collection in der nur die User enthalten sind und gibt diese zurueck
		ObjectFactory ob = new ObjectFactory();
		Collection usersCollection = ob.createCollection();
		usersCollection.setUsers(collect.getUsers());
		return usersCollection;
	}


	/**
	 * Erzeugt einen neuen User im Verleihsystem.
	 * @param name, Name des Users.
	 * @param vorname,Vorname des Users.
	 * @param strasse, Strasse(Adresse) des Users.
	 * @param hausNr, HausNr(Adresse) des Users
	 * @param plz, Postleitzahl(Adresse) des Users
	 * @param ort, Ort(Adresse) des Users
	 * @param passwort, Passwort des Users.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@POST
	@Path( "/users")
	public void postUsers(@FormParam("name") String name, @FormParam("vorname") String vorname, @FormParam("strasse") String strasse, @FormParam("hausNr") Integer hausNr, @FormParam("plz") Integer plz, @FormParam("ort") String ort, @FormParam("passwort")String passwort) throws JAXBException, IOException {
				//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Fügt der erzeugten Collection einen neuen User hinzu
		collect.getUsers().addUser(new UserType(collect.getUsers().getLastUserId()+1, name, vorname, strasse,hausNr,plz,ort, passwort));
		//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
		xmlMarshal(collect);
	}


	/**
	 * Gibt einen bestimmten eingetragenen User des Verleihsystems aus.
	 * @param userId, ID des Users.
	 * @return alle Userspezifischen Informationen in Form des XML-Schemas.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path ( "/users/user/{userid}")
	@Produces( "application/xml")
	public Collection getOneUser(@PathParam("userid") int userId) throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return null;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//Erstellt eine neue Instanz von Collection in der nur ein bestimmter User enthalten ist und gibt diesen zurueck.
			ObjectFactory ob = new ObjectFactory();
			Collection oneUserCollection = ob.createCollection();
			UsersType users = new UsersType();
			users.addUser(user);
			oneUserCollection.setUsers(users);
			return oneUserCollection;
		}else{
			return null;
		}
	}


	/**
	 * Ändert Eigenschaften eines bestimmten Users des Verleihsystems aus.
	 * @param userId, Id des Users.
	 * @param name, Name des Users.
	 * @param vorname, Vorname des Users.
	 * @param strasse, Strasse(Adresse) des Users.
	 * @param hausNr, HausNr(Adresse) des Users
	 * @param plz, Postleitzahl(Adresse) des Users
	 * @param ort, Ort(Adresse) des Users
	 * @param passwort, Passwort des Users.
	 * @throws JAXBException
	 * @throws IOException
	 */

	@PUT
	@Path( "/users/user/{userid}")
	public void putOneUser(@PathParam("userid") int userId, @FormParam("name") String name, @FormParam("vorname") String vorname, @FormParam("strasse") String strasse, @FormParam("hausNr") Integer hausNr, @FormParam("plz") Integer plz, @FormParam("ort") String ort, @FormParam("passwort")String passwort) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe.
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//Ändert Attribute eines bestimmten Users in der Collection.
	    	user.setName(name);
	    	user.setVorname(vorname);
	    	user.setStrasse(strasse);
	    	user.setHausNr(hausNr);
	    	user.setPlz(plz);
	    	user.setOrt(ort);
	    	user.setPasswort(passwort);
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	/**
	 * Erzeugt ein neues Fahrad zu einem bestimmten User des Verleihsystems.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @param model, Model des Fahrrads.
	 * @param hersteller, Hersteller des Fahrrads.
	 * @param rahmenNr, RahmenNr des Fahrrads.
	 * @param bild, Bild von eingef¸gten Fahrrad.
	 * @param standort, Standort des Fahrrads
	 * @param preis, verlangter Preis für den Verleih.
	 * @throws JAXBException
	 * @throws IOException
	 */


	@POST
	@Path( "/users/user/{userid}")
	public void postOneUser(@PathParam("userid") Integer userId,@FormParam("model") String model, @FormParam("hersteller") String hersteller, @FormParam("rahmenNr") Integer rahmenNr, @FormParam("bild") String bild, @FormParam("standort") String standort,  @FormParam("preis") Float preis ) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//F¸gt der erzeugten Collection f¸r einen bestimmten User ein neues Fahrrad hinzu.
			collect.getFahrraeder().addFahrrad(new FahrradType(collect.getFahrraeder().getLastFahrradId()+1, userId,ModelType.fromValue(model), hersteller, rahmenNr, bild, standort, preis));
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	

	/**
	 * Löschen eines bestimmten Users des Verleihsystems.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@DELETE
	@Path( "/users/user/{userid}")
	public void deleteOneUser(@PathParam("userid") int userId) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//Löscht einen bestimmten User und seine Fahrraeder aus der Collection
			collect.getUsers().deleteUser(user);
			collect.getFahrraeder().deleteUserFahrraeder(userId);
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	/**
	 * Gibt alle Fahrraeder eines bestimmten Users des Verleihsystems aus.
	 * @param userId, Id des besitzers des Fahrraeder.
	 * @param fahrradId, Id des Fahrraeder.
	 * @param typ, Art des Fahrraeder.
	 * @param hersteller, Hersteller des Fahrraeder.
	 * @param modelNr, ModelNr des Fahrraeder.
	 * @return alle Fahrradspezifischen Informationen eines bestimmten Users in Form des XML-Schemas.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path( "/users/user/{userid}/fahrraeder")
	@Produces( "application/xml" )
	public Collection getOneUserFahrraeder(@PathParam("userid") Integer userId, @QueryParam("fahrradid") Integer fahrradId, @QueryParam("model") String model, @QueryParam("hersteller") String hersteller, @QueryParam("rahmenNr") Integer rahmenNr, @QueryParam("vergebenAn") String vergebenAn) throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe.
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return null;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//Erstellt eine neue Instanz von Collection.
			ObjectFactory ob = new ObjectFactory();
			Collection oneUserFahrradCollection = ob.createCollection();
			FahrraederType fahrradListe = new FahrraederType();
			//Entweder wird per FahrradId das gesuchte Fahrrad der Liste hinzugefügt oder
			//es werden alle anderen Attribute abgesucht, wobei alle Fahrraeder, die die Kriterien
			//erfüllen, zur nächsten Stuffe (Durchsuchung nach nächstem Attribut) kommen.
			FahrraederType fahrradListe0 = new FahrraederType();
			if(vergebenAn!=null){
				for(int i=0;i<collect.getFahrraeder().getFahrradListSize();i++){
					FahrradType fahrrad = collect.getFahrraeder().getOneFahrrad(i);
					if(fahrrad.getVergebenAn()!=null){
						fahrradListe0.addFahrrad(fahrrad);
					}
				}
			}else{
				fahrradListe0 = collect.getFahrraeder();
			}	
			
			if(fahrradId!=null){
				fahrradListe.addFahrrad(fahrradListe0.getOneFahrradById(fahrradId));
			}else{
				FahrraederType fahrradListe1 = new FahrraederType();
				for(int i=0;i<fahrradListe0.getFahrradListSize();i++){
					FahrradType fahrrad = fahrradListe0.getOneFahrrad(i);
					if(fahrrad.getBesitzerID().longValue() == userId){
						fahrradListe1.addFahrrad(fahrrad);
					}
				}
				FahrraederType fahrradListe2 = new FahrraederType();
				if(rahmenNr!=null){
					for(int i=0;i<fahrradListe1.getFahrradListSize();i++){
						FahrradType fahrrad = fahrradListe1.getOneFahrrad(i);
						if(fahrrad.getRahmenNr().longValue() == rahmenNr){
							fahrradListe2.addFahrrad(fahrrad);
						}
					}
				}else{
					fahrradListe2 = fahrradListe1;
				}
			
				FahrraederType fahrradListe3 = new FahrraederType();
				if(model!=null){
					for(int i=0;i<fahrradListe2.getFahrradListSize();i++){
						FahrradType fahrrad = fahrradListe2.getOneFahrrad(i);
						if(fahrrad.getModel().toString().equalsIgnoreCase(model)){
							fahrradListe3.addFahrrad(fahrrad);
						}
					}
				}else{
					fahrradListe3 = fahrradListe2;
				}
				if(hersteller!=null){
					for(int i=0;i<fahrradListe3.getFahrradListSize();i++){
						FahrradType fahrrad = fahrradListe3.getOneFahrrad(i);
						if(fahrrad.getHersteller().equalsIgnoreCase(hersteller)){
							fahrradListe.addFahrrad(fahrrad);
						}
					}
				}else{
					fahrradListe = fahrradListe3;
				}
			}
			//Gibt alle Fahrraeder aus die einer gültigen FahrradId entsprechen oder alle Kriterien erfüllen.
			oneUserFahrradCollection.setFahrraeder(fahrradListe);
			return oneUserFahrradCollection;
		}else{
			return null;
		}
	}

	/**
	 * Erzeugt ein neues Fahrrad für einen bestimmten User des Verleihsystems.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @param model, Model des Fahrrads.
	 * @param hersteller, Hersteller des Fahrrads.
	 * @param rahmenNr, RahmenNr des Fahrrads.
	 * @param bild, URI des Bilds zu den Fahrrads.
	 * @param standort, Standort des Fahrrads.
	 * @param preis, verlangter Preis für den Verleih.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@POST
	@Path( "/users/user/{userid}/fahrraeder")
	public void postOneUserFahrraeder(@PathParam("userid") Integer userId, @FormParam("model") String model, @FormParam("hersteller") String hersteller, @FormParam("rahmenNr") Integer rahmenNr, @FormParam("bild") String bild, @FormParam("standort") String standort,@FormParam("preis") Float preis) throws JAXBException, IOException {
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der UserListe
		if (userId>collect.getUsers().getLastUserId()||userId<=0){
			return;
		}
		//Überprüfung ob der User zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//Fügt der erzeugten Collection für einen bestimmten User ein neues Fahrrad hinzu
			collect.getFahrraeder().addFahrrad(new FahrradType(collect.getFahrraeder().getLastFahrradId()+1, userId, ModelType.fromValue(model), hersteller, rahmenNr, bild, standort,preis));
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	

	/**
	 * Gibt alle Fahrrads des Verleihsystems ensprechend der Suchkriterien aus.
	 * @param fahrradId, Id des Fahrrads.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @param model, Art des Fahrrads.
	 * @param standort, standort des Fahrrads.
	 * @param rahmenNr, RahmenNr des Fahrrads.
	 * @return Liste aller Fahrraeder entsprechend der Suchkriterien in Form des XML-Schemas. 
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path( "/fahrraeder")
	@Produces( "application/xml")
	public Collection getFahrraeder(@QueryParam("fahrradid") Integer fahrradId, @QueryParam("userid") Integer userId, @QueryParam("model") String model, @QueryParam("rahmenNr") Integer rahmenNr, @QueryParam("verfuegbar") String vergebenAn, @QueryParam("standort") String standort) throws JAXBException, FileNotFoundException {
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collection = xmlUnmarshal();
		//Erstellt eine neue Instanz von CollectionType
		ObjectFactory ob = new ObjectFactory();
		Collection fahrradsCollection = ob.createCollection();
		FahrraederType fahrradListe = new FahrraederType();
		//Entweder wird per FahrradId das gesuchte Fahrrad der Liste hinzugefügt oder
		//es werden alle anderen Attribute abgesucht, wobei alle Fahrraeder, die die Kriterien
		//erfüllen, zur nächsten Stuffe (Durchsuchung nach nächstem Attribut) kommen
		FahrraederType fahrradListe0 = new FahrraederType();
		//bei fahrrads -> alle Fahrrads
		//bei /fahrrads?vergebenAn= (kein Wert angegeben)-> alle nicht vergebenen Fahrrads
		//bei /fahrrads?vergebenAn=asd (irgendein Wert angegeben)-> alle vergebenen Fahrrads
		if(vergebenAn==null){
			fahrradListe0 = collection.getFahrraeder();
		}else if(vergebenAn.isEmpty()){
			for(int i=0;i<collection.getFahrraeder().getFahrradListSize();i++){
				FahrradType fahrrad = collection.getFahrraeder().getOneFahrrad(i);
				if(fahrrad.getVergebenAn()==null){
					fahrradListe0.addFahrrad(fahrrad);
				}
			}
		}else{
			for(int i=0;i<collection.getFahrraeder().getFahrradListSize();i++){
				FahrradType fahrrad = collection.getFahrraeder().getOneFahrrad(i);
				if(fahrrad.getVergebenAn()!=null){
					fahrradListe0.addFahrrad(fahrrad);
				}
			}
		}	
		
		if(fahrradId!=null){
			fahrradListe.addFahrrad(fahrradListe0.getOneFahrradById(fahrradId));
		}else{
			FahrraederType fahrradListe1 = new FahrraederType();
			if(rahmenNr!=null){
				for(int i=0;i<fahrradListe0.getFahrradListSize();i++){
					FahrradType fahrrad = fahrradListe0.getOneFahrrad(i);
					if(fahrrad.getRahmenNr().longValue() == rahmenNr){
						fahrradListe1.addFahrrad(fahrrad);
					}
				}
			}else{
				fahrradListe1 = fahrradListe0;
			}
			FahrraederType fahrradListe2 = new FahrraederType();
			if(userId!=null){
				for(int i=0;i<fahrradListe1.getFahrradListSize();i++){
					FahrradType fahrrad = fahrradListe1.getOneFahrrad(i);
					if(fahrrad.getBesitzerID().longValue() == userId){
						fahrradListe2.addFahrrad(fahrrad);
					}
				}
			}else{
				fahrradListe2 = fahrradListe1;

			}
			FahrraederType fahrradListe3 = new FahrraederType();
			if(model!=null){
				for(int i=0;i<fahrradListe2.getFahrradListSize();i++){
					FahrradType fahrrad = fahrradListe2.getOneFahrrad(i);
					if(fahrrad.getModel().toString().equalsIgnoreCase(model)){
						fahrradListe3.addFahrrad(fahrrad);
					}
				}
				
				
			}else{
				fahrradListe3 = fahrradListe2;
			}
			if(standort!=null){
				for(int i=0;i<fahrradListe3.getFahrradListSize();i++){
					FahrradType fahrrad = fahrradListe3.getOneFahrrad(i);
					if(fahrrad.getStandort().equalsIgnoreCase(standort)){
						fahrradListe.addFahrrad(fahrrad);
					}
				}
			}else{
				fahrradListe = fahrradListe3;
			}
		}
		//gibt das/die gesuchte/n Fahrrad/s entsprechend der Kriterien zurück
		fahrradsCollection.setFahrraeder(fahrradListe);
		return fahrradsCollection;
	}

	/**
	 * Erzeugt ein neues Fahrrad f¸r einen bestimmten User des Verleihsystems.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @param model, Model des Fahrrads.
	 * @param hersteller, Hersteller des Fahrrads.
	 * @param rahmenNr, RahmenNr des Fahrrads.
	 * @param bild, Bild von eingef¸gten Fahrrad.
	 * @param standort, Standort des Fahrrads
	 * @throws JAXBException
	 * @throws IOException
	 */
	@POST
	@Path( "/fahrraeder")
	public void postFahrraeder(@FormParam("userid") Integer userId, @FormParam("model") String model, @FormParam("hersteller") String hersteller, @FormParam("rahmenNr") Integer rahmenNr, @FormParam("bild") String bild, @FormParam("standort") String standort, @FormParam("preis") Float preis ) throws JAXBException, IOException {
		//Erzeugt ein Collectionmit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Überprüfung ob der Besiter(User) zu UserId existiert.
		UserType user = collect.getUsers().getOneUserById(userId);
		if(user!=null){
			//F¸gt der erzeugten Collection f¸r einen User ein neues Fharrad hinzu
			collect.getFahrraeder().addFahrrad(new FahrradType(collect.getFahrraeder().getLastFahrradId()+1, userId, ModelType.fromValue(model), hersteller, rahmenNr, bild, standort, preis));
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	


	/**
	 * Gibt ein bestimmtes Fahrrad des Verleihsystems aus.
	 * @param fahrradId, Id des Fahrrads.
	 * @return alle Fahrradspezifischen Informationen in Form des XML-Schemas.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path( "/fahrraeder/fahrrad/{fahrradid}")
	@Produces( "application/xml")
	public Collection getOneFahrrad(@PathParam("fahrradid") int fahrradId) throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der Liste
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return null;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Erstellt eine neue Instanz von Collection in der nur ein bestimmtes Fahrrad enthalten ist und gibt diesen zurueck
			ObjectFactory ob = new ObjectFactory();
			Collection oneFahrradCollection = ob.createCollection();
			FahrraederType fahrradListe = new FahrraederType();
			fahrradListe.addFahrrad(fahrrad);
			oneFahrradCollection.setFahrraeder(fahrradListe);
			return oneFahrradCollection;
		}else{
			return null;
		}
	}



	/**
	 * Ändert Eigenschaften eines bestimmten Users des Verleihsystems aus.
	 * @param userId, Id des besitzers des Fahrrads.
	 * @param model, Model des Fahrrads.
	 * @param hersteller, Hersteller des Fahrrads.
	 * @param rahmenNr, RahmenNr des Fahrrads.
	 * @param bild, Bild von eingef¸gten Fahrrad.
	 * @param status, Ausleih Status von Fahrrad (False-wenn zum ausleihen nicht Verf¸gbar, True-wenn Verf¸gbar
	 * @param standort, Standort des Fahrrads
	 * @param vergebenAn, Id des Users
	 * @throws JAXBException
	 * @throws IOException
	 */

	@PUT
	@Path( "/fahrraeder/fahrrad/{fahrradid}")
	public void putOneFahrrad(@PathParam("fahradid") int fahrradId, @FormParam("userid") Integer userId, @FormParam("model") String model, @FormParam("hersteller") String hersteller, @FormParam("rahmenNr") Integer rahmenNr, @FormParam("bild") String bild,  @FormParam("standort") String standort,@FormParam("preis") Float preis ) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//ƒndert die angegebenen Attribute eines bestimmten Fahrrads in der Collection
				fahrrad.setBesitzerID(userId);
				fahrrad.setModel(ModelType.fromValue(model));
				fahrrad.setHersteller(hersteller);
				fahrrad.setRahmenNr(rahmenNr);
				fahrrad.setBild(bild);
				fahrrad.setStandort(standort);
				fahrrad.setPreis(preis);
			}
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
	}



	/**
	 * Löschen eines bestimmten Fahrrads des Verleihsystems.
	 * @param fahrradId, Id des Fahrrads.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@DELETE
	@Path( "/fahrraeder/fahrrad/{fahrradid}")
	public void deleteOneFahrrad(@PathParam("fahrradid") int fahrradId) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der Fahrraeder
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Löscht ein bestimmtes Fahrrad aus der Collection
			collect.getFahrraeder().deleteFahrrad(fahrrad);
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	
	
	/**
	 * Gibt vergebenAn eines bestimmten  Fahrrad des Verleihsystems aus.
	 * @param fahrradId, Id desFahrrads.
	 * @return vergebenAn eines bestimmten Fahrrader in Form des XML-Schemas.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */

	@GET
	@Path( "/fahrraeder/fahrrad/{fahrradid}/vergebenAn")
	@Produces( "application/xml")
	public Collection getVergebenAn(@PathParam("fahrradid") int fahrradId) throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Erstellt eine neue Instanz von Collection in der nur die User enthalten sind und gibt diese zurueck
		ObjectFactory ob = new ObjectFactory();
		Collection vergebenAnCollection = ob.createCollection();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return null;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert..
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			
			//Erstellt eine neue Instanz von CollectionType in der nur ein bestimmtes Fahrrad enthalten ist und gibt diesen zurueck
			FahrraederType fahrradListe = new FahrraederType();
			fahrradListe.addFahrrad(fahrrad);
			vergebenAnCollection.setFahrraeder(fahrradListe);
			return vergebenAnCollection;
		}else{
			return null;
		}
	}
	
	
	/**
	 * Erzeugt ein vergebenAn eines bestimmten Fahrrad des Verleihsystems.
	 * @param fahrradID, Id des Fahrradss.
	 * @param vergebenAn, beschreibt an welchen User das Fahrrad vergeben ist/wird.
	 * @throws JAXBException
	 * @throws IOException
	 */

	@POST
	@Path( "/fahrraeder/fahrrad/{fahrradid}")
	public void postVergebenAn(@PathParam("fahrradid") int fahrradId, @FormParam("vergebenAn") Integer vergebenAn) throws JAXBException, IOException {
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Fügt der erzeugten Collection für ein bestimmtes Fahrrad ein VergebenAn hinzu
			collect.getFahrraeder().getOneFahrradById(fahrradId).setVergebenAn(new VergebenAnType(vergebenAn));
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	
	
	/**
	 * Ändert vergebenAn eines bestimmten Fahrraeder des Verleihsystems.
	 * @param fahrradId, Id des fahrraeder.
	 * @param vergebenAn, beschreibt an welchen User das Fahrrad vergeben ist/wird.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@PUT
	@Path( "/fahrraeder/fahrrad/{fahrradid}/vergebenAn")
	public void putVergebenAn(@PathParam("fahrradid") int fahrradId, @FormParam("vergebenAn") Integer vergebenAn) throws JAXBException, IOException {
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		FahrradType ausleiher = collect.getFahrraeder().getOneFahrradById(vergebenAn);
		if(fahrrad!=null && ausleiher!=null){
			//Ändert Attribute von VergebenAn für ein bestimmtes Fahrrad in der Collection
			fahrrad.setVergebenAn(new VergebenAnType(vergebenAn)); 
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	/**
	 * Löscht vergebenAn eines bestimmten Fahrrads des Verleihsystems.
	 * @param fahrradId, Id des Fahrrads.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@DELETE
	@Path( "/fahrraeder/fahrrad/{fahrradid}/vergebenAn")
	public void deleteVergebenAn(@PathParam("fahrradid") int fahrradId) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der Fahrraeder
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Löscht das VergebenAn eines bestimmten Fahrrads aus der Collection
			collect.getFahrraeder().getOneFahrradById(fahrradId).setVergebenAn(null);
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}

	/**
	 * Gibt AusleihInformation eines bestimmten  Fahrrad des Verleihsystems aus.
	 * @param fahrradId, Id des Fahrrads.
	 * @return ausleih eines bestimmten Fahrrader in Form des XML.
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	@GET
	@Path( "/fahrraeder/fahrrad/{fahrradid}/ausleih")
	@Produces( "application/xml")
	public Collection getAusleih(@PathParam("fahrradid") int fahrradId) throws JAXBException, FileNotFoundException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Erstellt eine neue Instanz von Collection in der nur die User enthalten sind und gibt diese zurueck
		ObjectFactory ob = new ObjectFactory();
		Collection ausleihcollect = ob.createCollection();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return null;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert..
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			
			//Erstellt eine neue Instanz von CollectionType in der nur ein bestimmtes Fahrrad enthalten ist und gibt diesen zurueck
			FahrradType neuesFahrrad = new FahrradType();
			neuesFahrrad.setAusleih(fahrrad.getAusleih());
			FahrraederType fahrradListe = new FahrraederType();
			fahrradListe.addFahrrad(neuesFahrrad);
			ausleihcollect.setFahrraeder(fahrradListe);
			return ausleihcollect;
		}else{
			return null;
		}
	}
	
	/**
	 * Erzeugt ein Ausleih eines bestimmten Fahrrad des Verleihsystems.
	 * @param fahrradID, Id des Fahrradss.
	 * @param rueckgabeort, gibt die Koordinaten des Rueckgabeorts
	 * @param mietBeginn, Datum und Zeit 
	 * @param mietEnde, Datum und Zeit 
	 * @throws JAXBException
	 * @throws IOException
	 */

	@POST
	@Path( "/fahrraeder/fahrrad/{fahrradid}/ausleih")// wenn @Path( "/fahrraeder/fahrrad/{fahrradid}" Fehler beim ausführen von server
	public void postAusleihOneFahrrad(@PathParam("fahrradid") int fahrradId, @FormParam("rueckgabeort") String rueckgabeOrt, @FormParam("mietBeginn")  String mietBeginn, @FormParam("mietEnde") String mietEnde) throws JAXBException, IOException {
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Fügt der erzeugten Collection für ein bestimmtes Fahrrad ein Ausleih hinzu
			collect.getFahrraeder().getOneFahrradById(fahrradId).setAusleih(new AusleihType(rueckgabeOrt, mietBeginn, mietEnde));
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	
	/**
	 * Ändert Ausleih eines bestimmten Fahrrads des Verleihsystems.
	 * @param fahrradId, Id des fahrraeder.
	 * @param rueckgabeort, gibt die Koordinaten des Rueckgabeorts
	 * @param mietBeginn, Datum und Zeit 
	 * @param mietEnde, Datum und Zeit 
	 * @throws JAXBException
	 * @throws IOException
	 */
	@PUT
	@Path( "/fahrraeder/fahrrad/{fahrradid}/ausleih")
	public void putAusleih(@PathParam("fahrradid") int fahrradId,@FormParam("rueckgabeOrt") String rueckgabeOrt, @FormParam("mietBeginn")  String mietBeginn, @FormParam("mietEnde") String mietEnde) throws JAXBException, IOException, DatatypeConfigurationException {
		
		//Erzeugt ein CollectionType mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der FahrradListe
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			fahrrad.setAusleih(new AusleihType(rueckgabeOrt, mietBeginn, mietEnde)); 
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	
	
	
	/**
	 * Löscht AusleihInformation eines bestimmten Fahrrads des Verleihsystems.
	 * @param fahrradId, Id des Fahrrads.
	 * @throws JAXBException
	 * @throws IOException
	 */
	
	@DELETE
	@Path( "/fahrraeder/fahrrad/{fahrradid}/ausleih")
	public void deleteAusleih(@PathParam("fahrradid") int fahrradId) throws JAXBException, IOException {
		//Erzeugt ein Collection mit den vollständigen Inhalten der XML-Datei.
		Collection collect = xmlUnmarshal();
		//Vermeidung von Grenzüberschreitung der Fahrraeder
		if (fahrradId>collect.getFahrraeder().getLastFahrradId()||fahrradId<=0){
			return;
		}
		//Überprüfung ob das Fahrrad zu FahrradId existiert.
		FahrradType fahrrad = collect.getFahrraeder().getOneFahrradById(fahrradId);
		if(fahrrad!=null){
			//Löscht das VergebenAn eines bestimmten Fahrrads aus der Collection
			collect.getFahrraeder().getOneFahrradById(fahrradId).setAusleih(null);
			//Schreibt die aktuallisierte Collection vollständig in die XML-Datei.
			xmlMarshal(collect);
		}
	}
	/*_______________________________-ToDO____________________________________*/

	//Rückgabeort wenn zurückgegeben = standort setzen



}