package fahrradMenu;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import fahrradMenu.FahrradRequest;

public class FahrradMenu {
	private static Scanner in = new Scanner(System.in);
	private boolean loop = true;

	public FahrradMenu() throws IOException, TransformerException {
		hauptMenu();
		
	}
	
	/**
	 * Gibt das Hauptmenue in einer Schleife aus bis es durch "q" beendet 
	 * wird oder ein unter Menue ausgewaehlt wurde.
	 * @throws IOException 
	 * @throws TransformerException 
	 */
	private void hauptMenu() throws IOException, TransformerException {
		String input;
		
		//Menue Titel
		System.out.println("Fahrrad-Verleihsystem:");
		System.out.println("----------------------------------------\n");
		
		//Optionen
		System.out.println("user: Users bearbeiten");
		System.out.println("fahrrad: Fahrraeder bearbeiten");
		System.out.println("?: Menupunkte anzeigen");
		System.out.println("q: Beenden");
		
		//Menueausgabe-Schleife
		while(loop) {				
			input = in.next();
			
			if(input.equals("user")) {
				//Oeffnet das User Menue
				usersMenu();
			}
			else if(input.equals("fahrrad")) {
				//Oeffnet das Fahrrad Menue
				fahrraederMenu();
			}
			else if(input.equals("?")) {
				System.out.println("user: Users bearbeiten");
				System.out.println("fahrrad: Fahrraeder bearbeiten");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("user: Users bearbeiten");
				System.out.println("fahrrad: Fahrraeder bearbeiten");
				System.out.println("q: Beenden");
			}
		}
		
	}
	
	/**
	 * Gibt das Usermenue in einer Schleife aus.
	 * Durch "z" gelangt man zurueck zum Hauptmenue.
	 * Durch "q" beendet man den Client.
	 * @throws IOException
	 * @throws TransformerException 
	 */
	private void usersMenu() throws IOException, TransformerException {
		boolean usersLoop = true;
		String input;
		
		//Titel
		System.out.println("Userverwaltung:");
		System.out.println("----------------------------------------\n");
		
		//Optionen
		System.out.println("l: Liste aller User");
		System.out.println("1: Ein User anzeigen");
		System.out.println("+: User hinzufŸgen");
		System.out.println("*: User bearbeiten");
		System.out.println("-: User löschen");
		System.out.println("?: Menüpunkte anzeigen");
		System.out.println("z: Zurück zum HauptmenŸ");
		System.out.println("q: Beenden");
		
		//Menueausgabe-Schleife
		while(usersLoop) {
			input = in.next();
			
			if(input.equals("l")) {
				System.out.println(FahrradRequest.usersGet());
			}
			else if(input.equals("1")) {
				Integer userId;
				System.out.println("Gib die ID des Users an:");
				System.out.println("id:");
				userId = in.nextInt();
				
				userMenu(userId);
			}
			else if(input.equals("+")) {
				
				String name,vorname,strasse,ort,passwort;
				int  plz,hausNr;
				
				System.out.println("Gib die name des Users an:");
				System.out.println("name:");
				name = in.next();
				
				System.out.println("Gib die Vorname des Users an:");
				System.out.println("vorname:");
				vorname = in.next();
				
				System.out.println("Gib die strasse des Users an:");
				System.out.println("strasse:");
				strasse = in.next();
				
				System.out.println("Gib die strasse Nummer des Users an:");
				System.out.println("hausNr:");
				hausNr = in.nextInt();
				
				System.out.println("Gib die PostleitZahl des Users an:");
				System.out.println("plz:");
				plz = in.nextInt();
				
				System.out.println("Gib die ort des Users an:");
				System.out.println("ort:");
				ort = in.next();
				
				System.out.println("Gib die Passwort des Users an:");
				System.out.println("passwort:");
				passwort = in.next();
				FahrradRequest.usersPost(name, vorname, strasse, hausNr, plz, ort, passwort);
			}
			else if(input.equals("*")) {
				Integer userid;
				String name,vorname,strasse,ort,passwort;
				int  plz,hausNr;
				
				System.out.println("Gib die ID des Users an der bearbeitet werden soll:");
				System.out.println("id:");
				userid = in.nextInt();
				
				System.out.println("Gib die name des Users an:");
				System.out.println("name:");
				name = in.next();
				
				System.out.println("Gib die Vorname des Users an:");
				System.out.println("vorname:");
				vorname = in.next();
				
				System.out.println("Gib die strasse des Users an:");
				System.out.println("strasse:");
				strasse = in.next();
				
				System.out.println("Gib die strasse Nummer des Users an:");
				System.out.println("hausNr:");
				hausNr = in.nextInt();
				
				System.out.println("Gib die PostleitZahl des Users an:");
				System.out.println("plz:");
				plz = in.nextInt();
				
				System.out.println("Gib die ort des Users an:");
				System.out.println("ort:");
				ort = in.next();
				
				System.out.println("Gib die Passwort des Users an:");
				System.out.println("passwort:");
				passwort = in.next();
				FahrradRequest.userPut(userid, name,vorname,strasse,hausNr, plz,ort,passwort); 
			}
			else if(input.equals("-")) {
				Integer userid;
				
				System.out.println("Gib die ID des Users an der gelšscht werden soll:");
				System.out.println("id:");
				userid = in.nextInt();
				
				FahrradRequest.userDelet(userid);
			}
			else if(input.equals("?")) {
				System.out.println("l: Liste aller User");
				System.out.println("1: Ein User anzeigen");
				System.out.println("+: User hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
			else if(input.equals("z")) {
				usersLoop = false;
				System.out.println("Fahrrad-Verleihsystem:");
				System.out.println("----------------------------------------\n");
				System.out.println("user: Users bearbeiten");
				System.out.println("fahrrad: Fahrraeder bearbeiten");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				usersLoop = false;
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("l: Liste aller User");
				System.out.println("1: Ein User anzeigen");
				System.out.println("+: User hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
		}
	}

	/**
	 * Gibt das Menue fŸr einen User in einer Schleife aus.
	 * Durch "z" gelangt man zurueck zur Userverwaltung.
	 * Durch "q" beendet man den Client.
	 * @param userId Die ID des Users
	 * @throws IOException
	 * @throws TransformerException
	 */
	private void userMenu(Integer userId) throws IOException, TransformerException {
		boolean userLoop = true;
		String input;
		
		System.out.println("User: " + userId);
		System.out.println("----------------------------------------\n");
		
		
		System.out.println(FahrradRequest.userGet(userId));
		
		System.out.println("----------------------------------------\n");
		
		//Optionen		
		System.out.println("l: Liste aller Fahrraeder des Users");
		System.out.println("+: Fahrrad hinzufŸgen");
		System.out.println("*: User bearbeiten");
		System.out.println("-: User löschen");
		System.out.println("?: Menüpunkte anzeigen");
		System.out.println("z: Zurück zur Userverwaltung");
		System.out.println("q: Beenden");
		
		//Menueschleife
		while(userLoop) {
			input = in.next();
			
			if(input.equals("+")) {
				String model;
				String hersteller,bild,standort;
				int rahmenNr;
				Float preis;
				
				System.out.println("Gib den Model des Fahrrad ein:");
				System.out.println("Mountainbike, Rennraeder, BMX, Citybike, KinderFahrraeder");
				model = in.next();
				
				while(!model.equals("Mountainbike") && !model.equals("Rennraeder") && !model.equals("BMX")&& !model.equals("Citybike")&& !model.equals("KinderFahrraeder")) {
					System.out.println("Fehlerhafter Model!");
					System.out.println("Gib den Model des Fahrrad ein:");
					System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
					model = in.next();
				}
				
				System.out.println("Gib den Hersteller des Fahrrad ein:");
				hersteller = in.next();
				
				System.out.println("Gib die rahmenNr. des Fahrrad ein:");
				rahmenNr = in.nextInt();
				
				System.out.println("Gib den Preis fŸr das Fahrrad ein:");
				preis = in.nextFloat();
				
				System.out.println("Gib die Uri zu den Bild des Fahrrad ein:");
				bild = in.next();
				
				System.out.println("Gib die Standort des Fahrrad ein:");
				standort = in.next();
				
				FahrradRequest.userPostFahrrad(userId, model, hersteller, rahmenNr, bild,standort,preis);
			}
			else if(input.equals("l")) {
				System.out.println(FahrradRequest.fahrradGetPerUser(userId));
			}
			else if(input.equals("*")) {
				String name,vorname,strasse,ort,passwort;
				int  plz,hausNr;
				

				
				System.out.println("Gib die name des Users an:");
				System.out.println("name:");
				name = in.next();
				
				System.out.println("Gib die Vorname des Users an:");
				System.out.println("vorname:");
				vorname = in.next();
				
				System.out.println("Gib die strasse des Users an:");
				System.out.println("strasse:");
				strasse = in.next();
				
				System.out.println("Gib die strasse Nummer des Users an:");
				System.out.println("hausNr:");
				hausNr = in.nextInt();
				
				System.out.println("Gib die PostleitZahl des Users an:");
				System.out.println("plz:");
				plz = in.nextInt();
				
				System.out.println("Gib die ort des Users an:");
				System.out.println("ort:");
				ort = in.next();
				
				System.out.println("Gib die Passwort des Users an:");
				System.out.println("passwort:");
				passwort = in.next();
				
				FahrradRequest.userPut(userId, name, vorname, strasse, hausNr, plz, ort, passwort ); 
			}
			else if(input.equals("-")) {
				FahrradRequest.userDelet(userId);
				userLoop = false;
				//Titel
				System.out.println("Userverwaltung:");
				System.out.println("----------------------------------------\n");
				
				//Optionen
				System.out.println("l: Liste aller User");
				System.out.println("1: Ein User anzeigen");
				System.out.println("+: User hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
				
			}
			else if(input.equals("?")) {
				System.out.println("l: Liste aller Fahrraeder des Users");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Userverwaltung");
				System.out.println("q: Beenden");
			}
			else if(input.equals("z")) {
				userLoop = false;
				//Titel
				System.out.println("Userverwaltung:");
				System.out.println("----------------------------------------\n");
				
				//Optionen
				System.out.println("l: Liste aller User");
				System.out.println("1: Ein User anzeigen");
				System.out.println("+: User hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				userLoop = false;
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("l: Liste aller Fahrraeder des Users");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Userverwaltung");
				System.out.println("q: Beenden");
			}
		}
	}
	
	/**
	 * Gibt das Fahrradmenue in ein Schleife aus.
	 * Durch "z" gelangt man zurueck zum Hauptmenue.
	 * Durch "q" beendet man den Client.
	 * @throws IOException
	 * @throws TransformerException
	 */
	private void fahrraederMenu() throws IOException, TransformerException {
		boolean fahrraederLoop = true;
		String input;
		
		//Titel
		System.out.println("Fahrradverwaltung");
		System.out.println("----------------------------------------\n");
		
		//Optionen
		System.out.println("l: Liste aller Fahrraeder");
		System.out.println("1: Ein Fahrrad anzeigen");
		System.out.println("+: Fahrrad hinzufŸgen");
		System.out.println("*: Fahrrad bearbeiten");
		System.out.println("-: Fahrrad löschen");
		System.out.println("?: Menüpunkte anzeigen");
		System.out.println("z: Zurück zum HauptmenŸ");
		System.out.println("q: Beenden");
		
		while(fahrraederLoop) {
			input = in.next();
			
			if(input.equals("l")) {
				System.out.println(FahrradRequest.fahrraederGet());
			}
			else if(input.equals("1")) {
				Integer fahrradId;
				
				System.out.println("Gib die ID des Fahrraeder an:");
				System.out.println("id:");
				fahrradId = in.nextInt();
				fahrradMenu(fahrradId);
			}
			else if(input.equals("+")) {
				BigInteger besitzer;
				String model;
				String hersteller, bild, standort;
				int rahmenNr;
				Float preis;
				
				System.out.println("Gibt den Besitzer des Fahrraeder ein:");
				besitzer = in.nextBigInteger();
				
				System.out.println("Gib den Model des Fahrrad ein:");
				System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
				model = in.next();
				
				while(!model.equals("Mountainbike") && !model.equals("Rennraeder") && !model.equals("BMX")&& !model.equals("Citybike")&& !model.equals("KinderFahrraeder")) {
					System.out.println("Fehlerhafter Model!");
					System.out.println("Gib den Model des Fahrrad ein:");
					System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
					model = in.next();
				}
				
				
				System.out.println("Gib den Hersteller des Fahrrad ein:");
				hersteller = in.next();
				
				System.out.println("Gib die RahmenNr. des Fahrrad ein:");
				rahmenNr = in.nextInt();
				
				System.out.println("Gib die Uri zu den Bild des Fahrrad ein:");
				bild = in.next();
				
				System.out.println("Gib die Standort des Fahrrad ein:");
				standort = in.next();
				
				System.out.println("Gib den Preis fŸr das Fahrrad ein:");
				preis = in.nextFloat();
				
				FahrradRequest.fahrraederPost(besitzer, model, hersteller, rahmenNr, bild, standort, preis);
			}
			else if(input.equals("*")) {
				Integer fahrradId;
				String model;
				String hersteller,bild,standort;
				int rahmenNr;
				Float preis;
				
				System.out.println("Gib die ID des Fahrraeder an das bearbeitet werden soll:");
				System.out.println("id:");
				fahrradId = in.nextInt();
				
				System.out.println("Gib den Model des Fahrrad ein:");
				System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
				model = in.next();
				
				while(!model.equals("Mountainbike") && !model.equals("Rennraeder") && !model.equals("BMX")&& !model.equals("Citybike")&& !model.equals("KinderFahrraeder")) {
					System.out.println("Fehlerhafter Model!");
					System.out.println("Gib den Model des Fahrrad ein:");
					System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
					model = in.next();
				}
				
				System.out.println("Gib den Hersteller des Fahrrad ein:");
				hersteller = in.next();
				
				System.out.println("Gib die RahmenNr. des Fahrrad ein:");
				rahmenNr = in.nextInt();
				
				System.out.println("Gib den Preis fŸr das Fahrrad ein:");
				preis = in.nextFloat();
				
				System.out.println("Gib die Uri zu den Bild des Fahrrad ein:");
				bild = in.next();
				
				System.out.println("Gib die Standort des Fahrrad ein:");
				standort = in.next();
				FahrradRequest.fahrradPut(fahrradId, model, hersteller, rahmenNr,bild, standort, preis);
			}
			else if(input.equals("-")) {
				Integer fahrradId;
				
				System.out.println("Gib die ID des Fahrraeder an der gelšscht werden soll:");
				System.out.println("id:");
				fahrradId = in.nextInt();
				
				FahrradRequest.fahrradDelet(fahrradId);
			}
			else if(input.equals("?")) {
				System.out.println("l: Liste aller Fahrraeder");
				System.out.println("1: Ein Fahrrad anzeigen");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: Fahrrad bearbeiten");
				System.out.println("-: Fahrrad löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
			else if(input.equals("z")) {
				fahrraederLoop = false;
				System.out.println("Fahrrad-Verleihsystem:");
				System.out.println("----------------------------------------\n");
				System.out.println("user: Users bearbeiten");
				System.out.println("fahrrad: Fahrraeder bearbeiten");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				fahrraederLoop = false;
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("l: Liste aller Fahrraeder");
				System.out.println("1: Ein Fahrrad anzeigen");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: Fahrrad bearbeiten");
				System.out.println("-: Fahrrad löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
		}
	}
	
	private void fahrradMenu(Integer fahrradId) throws IOException, TransformerException {
		boolean fahrradLoop = true;
		String input;
		
		System.out.println("Fahrrad: " + fahrradId);
		System.out.println("----------------------------------------\n");
		
		System.out.println(FahrradRequest.fahrradGet(fahrradId));
		
		System.out.println("----------------------------------------\n");

		//Optionen		
		System.out.println("v: Fahrrad ausleihen");
		System.out.println("*: Fahrrad bearbeiten");
		System.out.println("-: Fahrrad löschen");
		System.out.println("?: Menüpunkte anzeigen");
		System.out.println("z: Zurück zur Fahrradverwaltung");
		System.out.println("q: Beenden");
		
		//Menueschleife
		while(fahrradLoop) {
			input = in.next();
			
			if(input.equals("v")) {
				verleihMenu(fahrradId);
			}
			else if(input.equals("*")) {
				String model;
				String hersteller,bild,standort;
				int rahmenNr;
				Float preis;
				
				System.out.println("Gib den Model des Fahrrad ein:");
				System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
				model = in.next();
				
				while(!model.equals("Mountainbike") && !model.equals("Rennraeder") && !model.equals("BMX")&& !model.equals("Citybike")&& !model.equals("KinderFahrraeder")) {
					System.out.println("Fehlerhafter Model!");
					System.out.println("Gib den Model des Fahrrad ein:");
					System.out.println("Mountainbike, Rennraeder, BMX, Citybike,KinderFahrraeder");
					model = in.next();
				}
				
				
				System.out.println("Gib den Hersteller des Fahrrad ein:");
				hersteller = in.next();
				
				System.out.println("Gib die RahmenNr. des Fahrrad ein:");
				rahmenNr = in.nextInt();
				
				System.out.println("Gib den Preis fŸr das Fahrrad ein:");
				preis = in.nextFloat();
				
				System.out.println("Gib die Uri zu den Bild des Fahrrad ein:");
				bild = in.next();
				
				System.out.println("Gib die Standort des Fahrrad ein:");
				standort = in.next();
				
				FahrradRequest.fahrradPut(fahrradId, model, hersteller, rahmenNr,bild,standort, preis); 
			}
			else if(input.equals("-")) {
				FahrradRequest.fahrradDelet(fahrradId);
				fahrradLoop = false;
				//Titel
				System.out.println("Fahrradverwaltung");
				System.out.println("----------------------------------------\n");
				
				//Optionen
				System.out.println("l: Liste aller Fahrraeder");
				System.out.println("1: Ein Fahrrad anzeigen");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: Fahrrad bearbeiten");
				System.out.println("-: Fahrrad löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
				
			}
			else if(input.equals("?")) {
				System.out.println("l: Liste aller Fahrraeder des Users");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Userverwaltung");
				System.out.println("q: Beenden");
			}
			else if(input.equals("z")) {
				fahrradLoop = false;
				//Titel
				System.out.println("Userverwaltung:");
				System.out.println("----------------------------------------\n");
				
				//Optionen
				System.out.println("l: Liste aller User");
				System.out.println("1: Ein User anzeigen");
				System.out.println("+: User hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zum HauptmenŸ");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				fahrradLoop = false;
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("l: Liste aller Fahrraeder des Users");
				System.out.println("+: Fahrrad hinzufŸgen");
				System.out.println("*: User bearbeiten");
				System.out.println("-: User löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Userverwaltung");
				System.out.println("q: Beenden");
			}
		}
	}
	
	private void verleihMenu(Integer fahrradId) throws IOException, TransformerException {
		boolean verleihLoop = true;
		String input;
		
		System.out.println("Fahrrad: " + fahrradId + " auleihen");
		System.out.println("----------------------------------------\n");
		
		System.out.println(FahrradRequest.fahrradGet(fahrradId));
		
		System.out.println("----------------------------------------\n");

		//Optionen		
		System.out.println("+: Fahrrad ausleihen");
		System.out.println("-: Fahrrad zurück gegeben");
		System.out.println("?: Menüpunkte anzeigen");
		System.out.println("z: Zurück zur Fahrradverwaltung");
		System.out.println("q: Beenden");
		
		while(verleihLoop) {
			input = in.next();
			
			if(input.equals("+")) {
				Integer userId;
				String rueckgabeort, mietBeginn, mietEnde;
				
				System.out.println("Gib die ID des Ausleihenden ein:");
				System.out.println("id:");
				userId = in.nextInt();
				
				System.out.println("Gib die Rückgabeort  ein:");
				System.out.println("rueckgabeort:");
				rueckgabeort = in.next();
				
				System.out.println("Gib die Datum und Zeit wann die Miete beginnt ein:");
				System.out.println("mietBeginn:");
				mietBeginn = in.next();
				
				System.out.println("Gib die Datum und Zeit wann das Fahrrad züruck gegeben wird  ein:");
				System.out.println("mietEnde:");
				mietEnde = in.next();
				
				FahrradRequest.fahrradPostAusleih(fahrradId,rueckgabeort, mietBeginn, mietEnde);

				FahrradRequest.fahrradPostVergebenAn(fahrradId, userId);
			}
			
			else if(input.equals("*")) {
				Integer userId;
				String rueckgabeort, mietBeginn, mietEnde;
				
				System.out.println("Gib die ID des neuen Ausleihenden ein:");
				System.out.println("id:");
				userId = in.nextInt();
				
				System.out.println("Gib die Rückgabeort  ein:");
				System.out.println("rueckgabeort:");
				rueckgabeort = in.next();
				
				System.out.println("Gib die Datum und Zeit wann die Miete beginnt ein:");
				System.out.println("mietBeginn:");
				mietBeginn = in.next();
				
				System.out.println("Gib die Datum und Zeit wann das Fahrrad züruck gegeben wird  ein:");
				System.out.println("mietEnde:");
				mietEnde = in.next();
				
				FahrradRequest.ausleihPut(fahrradId,rueckgabeort, mietBeginn, mietEnde);

				FahrradRequest.vergebenAnPut(fahrradId, userId);

			}
			else if(input.equals("-")) {								
				FahrradRequest.vergebAnDelet(fahrradId);
				FahrradRequest.ausleihDelet(fahrradId);

			}
			else if(input.equals("?")) {
				System.out.println("+: Fahrrad ausleihen");
				System.out.println("-: Fahrrad zurück geben");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Fahrradverwaltung");
				System.out.println("q: Beenden");
			}
			else if(input.equals("z")) {
				verleihLoop = false;
				
				//Titel
				System.out.println("Userverwaltung:");
				System.out.println("----------------------------------------\n");
				
				//Optionen
				System.out.println("v: Fahrrad ausleihen");
				System.out.println("*: Fahrrad bearbeiten");
				System.out.println("-: Fahrrad löschen");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Fahrradverwaltung");
				System.out.println("q: Beenden");
			}
			else if(input.equals("q")) {
				verleihLoop = false;
				this.loop = false;
				System.exit(0);
			}
			else {
				System.out.println("Fehlerhafte Eingabe! Bitte versuchen Sie es erneut.");
				System.out.println("----------------------------------------\n");
				System.out.println("+: Fahrrad ausleihen");
				System.out.println("-: Fahrrad zurück geben");
				System.out.println("?: Menüpunkte anzeigen");
				System.out.println("z: Zurück zur Fahrradverwaltung");
				System.out.println("q: Beenden");
			}
		}
	}
	

}
