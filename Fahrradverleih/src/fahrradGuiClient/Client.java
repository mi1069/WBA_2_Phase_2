package fahrradGuiClient;

import javax.swing.*;

import java.awt.*;


import fahrradGuiMenuclasses.*;

public class Client {
	
	private static JFrame fenster;
		
	public static final String MAINMENU =  "Hauptmenue";
	
	public static final String USERSMENU = "Usersmenue";    
	public static final String USERADD = "User hinzufügen";
	public static final String USERLIST = "Userliste";
	public static final String USEREDIT = "User bearbeiten";
	
	public static final String FAHRRAEDERMENU = "Fahrraedermenue";
	public static final String FAHRRADADD = "Fahrrad hinzufügen";
	public static final String FAHRRADLIST = "Fahrradliste";
	public static final String FAHRRADEDIT = "Fahrrad bearbeiten";
	public static final String FAHRRADSEARCH ="Fahrrad suchen";

	public static final JPanel menu = new JPanel();
	
	public Client() {
		gui();
	}
	
	private static void gui() {
		//Erzeugt das Fenster
		fenster = frame("Fahrrad Verleihsystem");
		//Erzeugt das Menu
		menu.setBackground(Color.WHITE);
		menu.setLayout(new CardLayout(20,20));
		
		MainMenu main = new MainMenu();
		main.setBackground(Color.WHITE);
        menu.add(main, MAINMENU);
        
        UsersMenu users = new UsersMenu();
        menu.add(users, USERSMENU);
        UserAdd userAdd = new UserAdd();
        menu.add(userAdd, USERADD);
        UserList userList = new UserList();
        menu.add(userList, USERLIST);
        
        
        FahrraederMenu fahrraeder = new FahrraederMenu();
        menu.add(fahrraeder, FAHRRAEDERMENU);
        FahrradAdd fahrradAdd = new FahrradAdd();
        menu.add(fahrradAdd, FAHRRADADD);
        FahrradList fahrradList = new FahrradList();
        menu.add(fahrradList, FAHRRADLIST);
        FahrradList fahrradSearch = new FahrradList();
        menu.add(fahrradSearch, FAHRRADSEARCH);
		
		fenster.add(menu);
	}
	
	
	/**
	 * Erzeugt ein Fenster mit dem angegeben Titel.
	 * @param titel Titel des Fensters
	 * @return Gibt das erstellte Fenster zurŸck
	 */
	private static JFrame frame(String titel) {
		JFrame frame;
		
		frame = new JFrame(titel);
		frame.setVisible(true);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
		
		return frame;
	}
	
	  public static void main(String... args)
	    {
	        SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                gui();
	            }
	        });
	    }
	
}
