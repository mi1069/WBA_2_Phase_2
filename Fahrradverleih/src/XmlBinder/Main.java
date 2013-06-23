package XmlBinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import orgExampleFahrradverleih.*;


public class Main {

	/* Versuch Klasse für Marshalling und Unmarshalling*/
	
	private static final String FahrradVerleih_XML = "./src/XML/FahrradVerleih.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		File f = new File(FahrradVerleih_XML);
		System.out.println(f.length());

		// create JAXB context and instantiate marshaller
		// setzen des Kontextes fuer marshal/unmarshall

		JAXBContext context = JAXBContext.newInstance(Collection.class);

		// unmarshal-Methode zum Auslesen aus der XML-Datei
		// get variables from our xml file, created before

		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = context.createUnmarshaller();

		Collection collect = (Collection) um.unmarshal(new FileInputStream(
				FahrradVerleih_XML));

		for (UserType users : collect.getUsers().getUser()) {
			System.out.println("Name: " + users.getName());
			System.out.println("ID: " + users.getId());

		}

		// marshal-Methode zum Schreiben in eine XML-Datei

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		FileOutputStream file = new FileOutputStream(FahrradVerleih_XML);
		m.marshal(collect, file);

	}
}
