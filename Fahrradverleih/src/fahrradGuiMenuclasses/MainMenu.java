package fahrradGuiMenuclasses;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import fahrradGuiClient.*;

@SuppressWarnings("serial")
public class MainMenu extends JPanel
{
    private ActionListener action;

    public MainMenu() 
    {
        init();
    }

    private void init() 
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
    	
    	final JButton usersMenuButton = new JButton("Users");
        final JButton fahrraederMenuButton = new JButton("Fahrräder");     
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == usersMenuButton)
                {
                    CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERSMENU);
                }
                else if (ae.getSource() == fahrraederMenuButton)
                {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.FAHRRAEDERMENU);
                }
            }
        };

        usersMenuButton.addActionListener(action);
        fahrraederMenuButton.addActionListener(action);
        
        grid.gridx = 0;
        grid.gridy = 0;
        add(usersMenuButton, grid);
        
        grid.gridx = 1;
        grid.gridy = 0;
        add(fahrraederMenuButton, grid);
    }
}