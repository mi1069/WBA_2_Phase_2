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
import fahrradMenu.FahrradRequest;

@SuppressWarnings("serial")
public class FahrradAdd extends JPanel {

	private ActionListener action;

    public FahrradAdd() 
    {
        init();
    }

    private void init() 
    {
        final JButton saveButton = new JButton("Speichern");
        final JButton backButton = new JButton("Zurück");     
       
        final JTextField model = new JTextField();
        final JTextField hersteller = new JTextField();
        final JTextField preis = new JTextField();
        final JTextField besitzer = new JTextField();
        final JTextField rahmenNr = new JTextField();
        final JTextField bild = new JTextField();
        final JTextField standort = new JTextField();

        
        final JLabel labelModel = new JLabel("Model:");
        final JLabel labelHersteller = new JLabel("Hersteller:");
        final JLabel labelBesitzer = new JLabel("BesitzerID:");
        final JLabel labelRahmenNr = new JLabel("RahmenNr:");
        final JLabel labelBild = new JLabel("Bild URL:");
        final JLabel labelStandort = new JLabel("Standort:");
        final JLabel labelPreis = new JLabel("Preis:");

        
        model.setVisible(true);
        hersteller.setVisible(true);
        preis.setVisible(true);
        besitzer.setVisible(true);
        rahmenNr.setVisible(true);
        bild.setVisible(true);
        standort.setVisible(true);

        

        backButton.setForeground(Color.RED);

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(10,0,0,0));
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == saveButton)
                {
                	
                	try {
						FahrradRequest.fahrraederPost(besitzer.getText(), model.getText(), hersteller.getText(), rahmenNr.getText(),bild.getText(), standort.getText(),preis.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
                	
                }
                else if (ae.getSource() == backButton)
                {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERSMENU);
                }
            }
        };

        saveButton.addActionListener(action);
        backButton.addActionListener(action);

        add(labelBesitzer);
        add(besitzer);
        
        add(labelModel);
        add(model);
        
        add(labelHersteller);
        add(hersteller);
        
        add(labelRahmenNr);
        add(rahmenNr);
        
        add(labelBild);
        add(bild);
        
        add(labelStandort);
        add(standort);
        
        add(labelPreis);
        add(preis);
       
        add(saveButton);
        add(backButton);
    }
}
