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
public class UsersMenu extends JPanel {

	private ActionListener action;

    public UsersMenu() 
    {
        init();
    }

    private void init() 
    {
        final JButton listButton = new JButton("Liste");
        final JButton addButton = new JButton("Hinzufügen");
        final JButton backButton = new JButton("Zurück");     
        
        listButton.setBounds(5, 10, 20, 10);
        addButton.setLocation(10, 30);
        backButton.setForeground(Color.RED);

        this.setLayout(new GridLayout(3,2,6,3));
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints grid = new GridBagConstraints();
        
        this.setBackground(Color.WHITE);
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == listButton)
                {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERLIST);
                }
                else if(ae.getSource() == addButton) {
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERADD);
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
        backButton.addActionListener(action);

       
        add(listButton);
        
        add(addButton);
 
        add(backButton);
    }
}
