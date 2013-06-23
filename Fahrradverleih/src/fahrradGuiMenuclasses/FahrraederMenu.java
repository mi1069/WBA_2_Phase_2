package fahrradGuiMenuclasses;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fahrradGuiClient.*;

@SuppressWarnings("serial")
public class FahrraederMenu extends JPanel {

	private ActionListener action;

    public FahrraederMenu() 
    {
        init();
    }

    private void init() 
    {
        final JButton listButton = new JButton("Liste");
        final JButton addButton = new JButton("Hinzufügen");
        final JButton searchButton = new JButton("Suchen"); 
        final JButton backButton = new JButton("Zurück");     
        
        listButton.setBounds(5, 10, 20, 10);
        addButton.setLocation(10, 30);
        backButton.setForeground(Color.RED);

        this.setLayout(new GridLayout(4,2,6,4));

        
        this.setBackground(Color.WHITE);
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == listButton)
                {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.FAHRRADLIST);
                }
                else if(ae.getSource() == addButton) {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.FAHRRADADD);
                }
                
                else if(ae.getSource() == searchButton) {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.FAHRRADSEARCH);
                }
                
                else if (ae.getSource() == backButton)
                {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.MAINMENU);
                }
            }
        };

        listButton.addActionListener(action);
        addButton.addActionListener(action);
        searchButton.addActionListener(action);
        backButton.addActionListener(action);

       
        add(listButton);
        
        add(addButton);
        
        add(searchButton);
 
        add(backButton);
    }
}
