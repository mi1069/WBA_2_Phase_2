package fahrradGuiMenuclasses;

public class Fahrrad {

		public String id;
		public String besitzer;
		public String model;
		public String hersteller;
		public String rahmenNr;
		public String bild;
		public String standort;
		public String preis;

		
		public Fahrrad(String id, String besitzer, String model, String hersteller, String rahmenNr, String bild, String standort, String preis) {
			this.id = id;
			this.besitzer = besitzer;
			this.model = model;
			this.hersteller = hersteller;
			this.rahmenNr = rahmenNr;
			this.bild = bild;
			this.standort = standort;
			this.preis = preis;
		}
		
		public String getId() {
			return this.id;
		}
		
		public String getBesitzer() {
			return this.besitzer;
		}
		
		public String getModel() {
			return this.model;
		}
		
		public String getHersteller() {
			return this.hersteller;
		}
		
		public String getRahmenNr() {
			return this.rahmenNr;
		}
		
		public String getBild() {
			return this.bild;
		}
		
		public String geStandort() {
			return this.standort;
		}
		
		public String getPreis() {
			return this.preis;
		}

}
