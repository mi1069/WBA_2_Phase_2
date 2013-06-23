package minirestwebservice;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import fahrradMenu.FahrradMenu;

public class FahrradClient {

	/**
	 * Steuerung des Clients Ÿber Konsolen eingabe
	 * @param args
	 * @throws IOException
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws IOException, TransformerException {
		new FahrradMenu();
	}
	

}