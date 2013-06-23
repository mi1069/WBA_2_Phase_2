package fahrradGuiMenuclasses;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fahrradGuiClient.*;
import fahrradMenu.*;

@SuppressWarnings("serial")
public class UserEdit extends JPanel {

	private ActionListener action;
	
	
	public User user;
	public static String editId;
	public static String editName;
	public static String editVorname;
	public static String editStrasse;
	public static String editHausNr;
	public static String editPlz;
	public static String editOrt;
	public static String editPasswort;
	
    public UserEdit() 
    {
        init();
    }

    public UserEdit(User user)  
    {
        this.user = user;
    	init();
    }
    
    private void init() 
    {
        final JButton saveButton = new JButton("Speichern");
        final JButton backButton = new JButton("Zurück");     
        
        final JTextField name = new JTextField();
        final JTextField vorname = new JTextField();
        final JTextField passwort = new JTextField();

        final JTextField strasse = new JTextField();
        final JTextField hausNr = new JTextField();
        final JTextField plz = new JTextField();
        final JTextField ort = new JTextField();
        
        final JLabel labelName = new JLabel("Name:");
        final JLabel labelVorname = new JLabel("Vorname:");
        final JLabel labelPasswort = new JLabel("Passwort:");
        final JLabel labelStrasse = new JLabel("Strasse:");
        final JLabel labelHausNr = new JLabel("HausNr:");
        final JLabel labelPlz = new JLabel("PLZ:");
        final JLabel labelOrt = new JLabel("Ort:");
        
        name.setVisible(true);
        name.setText(user.getName());
        
        vorname.setVisible(true);
        vorname.setText(user.getVorname());

        passwort.setVisible(true);
        passwort.setText(user.getPasswort());

        strasse.setVisible(true);
        strasse.setText(user.getStrasse());

        hausNr.setVisible(true);
        hausNr.setText(user.getHausNr());

        plz.setVisible(true);
        plz.setText(user.getPlz());

        ort.setVisible(true);
        ort.setText(user.getOrt());


        
        backButton.setForeground(Color.RED);
        
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(7,0,0,0));
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == saveButton)
                {
                	try {
						FahrradRequest.userPut(user.getId(), name.getText(),vorname.getText(),strasse.getText(),hausNr.getText() ,plz.getText(),ort.getText(),passwort.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
                	}
                
                else if (ae.getSource() == backButton)
                {
                	
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERLIST);
                }
            }
        };

        saveButton.addActionListener(action);
        backButton.addActionListener(action);

        add(labelName);
        add(name);
        
        add(labelVorname);
        add(vorname);
        
        add(labelPasswort);
        add(passwort);
        
        add(labelStrasse);
        add(strasse);
        
        add(labelHausNr);
        add(hausNr);
        
        add(labelPlz);
        add(plz);
        
        add(labelOrt);
        add(ort);
        
        add(saveButton);
        add(backButton);
    }
}
