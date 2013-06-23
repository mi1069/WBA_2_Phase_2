    package fahrradMenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.TransformerException;

import fahrradMenu.XMLOutputFormat;

public class FahrradRequest {

	private static String server = "http://localhost:4436/verleih";

	/**
	 * Sendet einen GET-Request an verleih/users und gibt die damit alle User aus.
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static String usersGet() throws IOException, TransformerException {
		 URL url = new URL(server + "/users");
		 URLConnection httpCon = url.openConnection();
		      
		 // XML Daten einlesen
		 ByteArrayOutputStream result = new ByteArrayOutputStream();
		 InputStream input = httpCon.getInputStream();
		 byte[] buffer = new byte[1000];
		 int amount = 0;   
		                
		 // Inhalt lesen
		 while(amount != -1){
		        
		   result.write(buffer, 0, amount);
		   amount = input.read(buffer);
		 }
		 String xml = result.toString();
		 return (XMLOutputFormat.prettyFormat(xml)); 
	}
	
	/**
	 * Sendet einen POST-Request an verleih/users und fügt damit einen neuen User hinzu.
	 * @param name
	 * @param vorname
	 * @param strasse
	 * @param hausNr
	 * @param plz
	 * @param ort
	 * @param passwort
	 * @throws IOException
	 */
	
	public static void usersPost(String name, String vorname, String strasse, Integer hausNr, Integer plz, String ort, String passwort) throws IOException {
		String data = "name=" + name  + "&vornme=" + vorname + "&strasse=" + strasse  + "&hausNr=" + hausNr + "&plz=" + plz +  "&ort=" + ort + "&passwort=" + passwort;

		
		URL url = new URL(server + "/users");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	//Für Swing Client
	public static void usersPost(String name, String vorname, String strasse, String hausNr, String plz, String ort, String passwort) throws IOException {
		String data = "name=" + name  + "&vornme=" + vorname + "&strasse=" + strasse  + "&hausNr=" + hausNr + "&plz=" + plz +  "&ort=" + ort + "&passwort=" + passwort;

		
		URL url = new URL(server + "/users");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen GET-Request an verleih/users/user/{id} und gibt die damit diesen User aus.
	 * @param userId Die Id des Users
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static String userGet(Integer userId) throws IOException, TransformerException {
		URL url = new URL(server + "/users/user/" + userId);
		URLConnection httpCon = url.openConnection();
		      
		// XML Daten einlesen
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		InputStream input = httpCon.getInputStream();
		byte[] buffer = new byte[1000];
		int amount = 0;   
		                
		// Inhalt lesen
		while(amount != -1){
		        
		  result.write(buffer, 0, amount);
		  amount = input.read(buffer);
		}
		String xml = result.toString();
		return XMLOutputFormat.prettyFormat(xml); 
	}
	
	/**
	 * Sendet einen POST-Request an verleih/users/user/{id} und fügt damit eine Fahrrad hinzu, 
	 * welches automatisch dem User zugeordnet wird. 
	 * @param userId Besitzer des Fahrraeder
	 * @param model
	 * @param hersteller 
	 * @param rahmenNr
	 * @param bild
	 * @param standort
	 * @param preis  
	 * @throws IOException
	 */
	public static void userPostFahrrad(Integer userId, String model, String hersteller, int rahmenNr, String bild, String standort,Float preis) throws IOException {
		String data = "model=" + model + "&hersteller=" + hersteller + "&rahmenNr=" + rahmenNr + "&bild=" + bild + "&standort=" + standort  +"&preis=" + preis;

		URL url = new URL(server + "/users/user/" + userId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet eine PUT-Request an verleih/users/user/{id} und Šndert damit die Daten eines Users.
	 * @param userid Die ID des zuverŠndenden Users
	 * @param name
	 * @param vorname
	 * @param strasse
	 * @param hausNr
	 * @param plz
	 * @param ort
	 * @param passwort
	 * @throws IOException
	 */
	public static void userPut(Integer userid, String name, String vorname, String strasse, Integer hausNr, Integer plz, String ort, String passwort) throws IOException {
		String data = "name=" + name  + "&vornme=" + vorname + "&strasse=" + strasse  + "&hausNr=" + hausNr + "&plz=" + plz +  "&ort=" + ort + "&passwort=" + passwort;
		
		URL url = new URL(server + "/users/user/" + userid);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	} 
	
	//Für Swing Client
	public static void userPut(String userid, String name, String vorname, String strasse, String hausNr, String plz, String ort, String passwort) throws IOException {
		String data = "name=" + name  + "&vornme=" + vorname + "&strasse=" + strasse  + "&hausNr=" + hausNr + "&plz=" + plz +  "&ort=" + ort + "&passwort=" + passwort;
		
		URL url = new URL(server + "/users/user/" + userid);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	} 
	
	/**
	 * Sendet einen DELETE-Request an verleih/users/user/{id} und löscht damit einen User.
	 * @param userId Die ID des zulšschenden Users
	 * @throws IOException
	 */
	public static void userDelet(Integer userId) throws IOException {
		
		URL url = new URL(server + "/users/user/" + userId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());

	}
	//Für Swing Client
	public static void userDelet(String userId) throws IOException {
		
		URL url = new URL(server + "/users/user/" + userId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());

	}

	/**
	 * Sendet einen GET-Request an verleih/fahrraeder und gibt die damit alle Fahrraeder aus.
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static String fahrraederGet() throws IOException, TransformerException {
		 URL url = new URL(server + "/fahrraeder");
		 URLConnection httpCon = url.openConnection();
		      
		 // XML Daten einlesen
		 ByteArrayOutputStream result = new ByteArrayOutputStream();
		 InputStream input = httpCon.getInputStream();
		 byte[] buffer = new byte[1000];
		 int amount = 0;   
		                
		 // Inhalt lesen
		 while(amount != -1){
		        
		   result.write(buffer, 0, amount);
		   amount = input.read(buffer);
		 }
		 String xml = result.toString();
		 return XMLOutputFormat.prettyFormat(xml); 
	}

	
	/**
	 * Sendet einen POST-Request an verleih/fahrraeder und fügt damit eine Fahrrad hinzu. 
	 * @param userId Besitzer des Fahrraeder
	 * @param model
	 * @param hersteller 
	 * @param rahmenNr
	 * @param bild
	 * @param standort
	 * @param preis
	 * @throws IOException
	 */
	public static void fahrraederPost(BigInteger besitzer, String model, String hersteller, int rahmenNr, String bild, String standort,  Float preis) throws IOException {
		String data = "userid=" + besitzer + "&model=" + model + "&hersteller=" + hersteller + "&rahmenlNr=" + rahmenNr +  "&bild=" + bild + "&standort=" + standort + "&preis=" + preis;

		URL url = new URL(server + "/fahrraeder");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	

	//Für Swing Client
	public static void fahrraederPost(String besitzer, String typ, String hersteller, String rahmenNr, String bild, String standort,String preis) throws IOException {
		String data = "userid=" + besitzer + "&typ=" + typ + "&hersteller=" + hersteller + "&rahmenNr=" + rahmenNr + "&bild=" + bild + "&standort=" + standort + "&preis=" + preis;

		URL url = new URL(server + "/fahrraeder");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen GET-Request an verleih/fahrraeder/fahrrad/{id} und gibt damit dieses Fahrrad aus.
	 * @param fahrradId Die Id des Fahrraeder
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static String fahrradGet(Integer fahrradId) throws IOException, TransformerException {
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		URLConnection httpCon = url.openConnection();
		      
		// XML Daten einlesen
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		InputStream input = httpCon.getInputStream();
		byte[] buffer = new byte[1000];
		int amount = 0;   
		                
		// Inhalt lesen
		while(amount != -1){
		        
		  result.write(buffer, 0, amount);
		  amount = input.read(buffer);
		}
		String xml = result.toString();
		return XMLOutputFormat.prettyFormat(xml); 
	}
	
	/**
	 * Sendet einen GET-Request an verleih/users/user/{id}/fahrraeder und gibt die damit alle Fahrraeder eines Users aus.
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static String fahrradGetPerUser(Integer userId) throws IOException, TransformerException {
		 URL url = new URL(server + "/users/user/" + userId + "/fahrraeder");
		 URLConnection httpCon = url.openConnection();
		      
		 // XML Daten einlesen
		 ByteArrayOutputStream result = new ByteArrayOutputStream();
		 InputStream input = httpCon.getInputStream();
		 byte[] buffer = new byte[1000];
		 int amount = 0;   
		                
		 // Inhalt lesen
		 while(amount != -1){
		        
		   result.write(buffer, 0, amount);
		   amount = input.read(buffer);
		 }
		 String xml = result.toString();
		 return XMLOutputFormat.prettyFormat(xml); 
	}

	
	/**
	 * Sendet einen PUT-Request an verleih/fahrraeder und Šndert damit die Attribute des Fahrraeder
	 * @param model
	 * @param hersteller 
	 * @param rahmenNr
	 * @param bild
	 * @param standort
	 * @param preis  
	 * @throws IOException
	 */
	public static void fahrradPut(Integer fahrradId, String model, String hersteller, int rahmenNr, String bild, String standort, Float preis) throws IOException {
		String data = "model=" + model + "&hersteller=" + hersteller + "&rahmenNr=" + rahmenNr + "&bild=" + bild + "&standort=" + standort  +"&preis=" + preis;
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	//Für Swing Client
	public static void fahrradPut(String fahrradId, String model, String hersteller, String rahmenNr, String bild, String standort, String preis) throws IOException {
		String data = "model=" + model + "&hersteller=" + hersteller + "&rahmenNr=" + rahmenNr + "&bild=" + bild + "&standort=" + standort +"&preis=" + preis;
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen DELETE-Request an verleih/fahrraeder und löscht damit das Fahrrad
	 * @param fahrradId Die ID des Fahrraeder
	 * @throws IOException
	 */
	public static void fahrradDelet(Integer fahrradId) throws IOException {
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
	}
	//Für Swing Client
	public static void fahrradDelet(String fahrradId) throws IOException {
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
	}
	
	/**
	 * Sendet einen POST-Request an verleih/fahrraeder/fahrrad/{id} und fügt damit eine Ausleihenden hinzu. 
	 * @param fahrradId Die ID des Fahrraeder
	 * @param userId DIe ID des Ausleihenden
	 * @throws IOException
	 */
	public static void fahrradPostVergebenAn(Integer fahrradId, Integer userId) throws IOException {
		String data = "vergebenAn=" + userId;

		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen PUT-Request an verleih/fahrraeder/fahrrad/{id}/vergebenAn und Šndert den Ausleihenden.
	 * @param fahrradId Die ID des Fahrraeder
	 * @param userId Die ID des neuen Ausleihenden
	 * @throws IOException
	 */
	public static void vergebenAnPut(Integer fahrradId, Integer userId) throws IOException {
		String data = "vergebenAn=" + userId;
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId + "vergebenAn");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen DELETE-Request an verleih/fahrraeder/fahrrad/{id}/vergebAn und löscht damit den Ausleihenden.
	 * @param fahrradId Die ID des Fahrraeder desssen Ausleiher gelšscht werden soll
	 * @throws IOException
	 */
	public static void vergebAnDelet(Integer fahrradId) throws IOException {
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId + "vergebenAn");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
	}
	
	
	/**
	 * Sendet einen POST-Request an verleih/fahrraeder/fahrrad/{id}/ausleih und fügt damit eine Ausleih hinzu. 
	 * @param fahrradId Die ID des Fahrraeder
	 * @throws IOException
	 */
	public static void fahrradPostAusleih(Integer fahrradId, String standort, String mietBeginn, String mietEnde) throws IOException {
		String data = "standort=" + standort+"&mietBeginn=" + mietBeginn + "&mietEnde=" + mietEnde;

		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId + "ausleih");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen PUT-Request an verleih/fahrraeder/fahrrad/{id}/ausleih und Šndert den Ausleihenden.
	 * @param fahrradId Die ID des Fahrraeder
	 * @throws IOException
	 */
	public static void ausleihPut(Integer fahrradId, String standort, String mietBeginn, String mietEnde) throws IOException {
		String data = "standort=" + standort+"&mietBeginn=" + mietBeginn + "&mietEnde=" + mietEnde;
		
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId + "vergebenAn");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(data);
		out.flush();
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
		  
		out.close();
	}
	
	/**
	 * Sendet einen DELET-Request an verleih/fahrraeder/fahrrad/{id}/ausleih und löscht damit den Ausleihenden.
	 * @param fahrradId Die ID
	 * @throws IOException
	 */
	public static void ausleihDelet(Integer fahrradId) throws IOException {
		URL url = new URL(server + "/fahrraeder/fahrrad/" + fahrradId + "ausleih");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
	
		System.out.println(httpCon.getResponseCode());
		System.out.println(httpCon.getResponseMessage());
	}
}
