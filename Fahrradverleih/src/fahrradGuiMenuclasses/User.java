package fahrradGuiMenuclasses;

public class User {

		
		public String id;
		public String name;
		public String vorname;
		public String strasse;
		public String hausNr;
		public String plz;
		public String ort;
		public String passwort;
	
		
		public User(String id,String name,String vorname,String strasse,String hausNr,String plz,String ort,String passwort) {
		
			this.id = id;
			this.name = name;
			this.vorname = vorname;
			this.strasse = strasse;
			this.hausNr = hausNr;
			this.plz = plz;
			this.ort = ort;
			this.passwort = passwort;
		}
		
		public String getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getVorname() {
			return this.vorname;
		}
		
		public String getStrasse() {
			return this.strasse;
		}
		
		public String getHausNr() {
			return this.hausNr;
		}
		
		public String getPlz() {
			return this.plz;
		}
		
		public String getOrt() {
			return this.ort;
		}
		
		public String getPasswort() {
			return this.passwort;
		}
}
