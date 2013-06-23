package fahrradGuiMenuclasses;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.transform.TransformerException;

import fahrradGuiClient.*;
import fahrradMenu.*;

@SuppressWarnings("serial")
public class UserList extends JPanel {

	private ActionListener action;
	private ActionListener edit;
	private ActionListener delete;
	private String listString;
	private ArrayList<User> list = new ArrayList<User>();

    public UserList() 
    {
        init();
    }

    private void init() 
    {
    	token();
    	
    	this.setBackground(Color.WHITE);
    	
        final JButton backButton = new JButton("Zurück");     
        
        final JButton[] jbuttonEdit = new JButton[list.size()];
        for(int i=0; i < list.size();i++) {
        	jbuttonEdit[i] = new JButton("Bearbeiten");
        }
        final JButton[] jbuttonDelet = new JButton[list.size()];
        for(int i=0; i < list.size();i++) {
        	jbuttonDelet[i] = new JButton("Löschen");
        }
        
        final JLabel[] jlabel = new JLabel[list.size()]; 
        for(int i=0; i < list.size();i++) {
            jlabel[i] = new JLabel("User:" + list.get(i).getId() + " Name:" + list.get(i).getName());

        }
        
        backButton.setForeground(Color.RED);

        this.setLayout(new GridLayout(list.size()+1,3,6,3));
        
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == backButton)
                {
                             	
                	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                    cardLayout.show(Client.menu, Client.USERSMENU);
                }
            }
        };
        
        edit = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	for(int i = 0; i < list.size();i++) {
                	if (ae.getSource() == jbuttonEdit[i])
                    {
                		UserEdit userEdit = new UserEdit(list.get(i));
                        Client.menu.add(userEdit, Client.USEREDIT);
                		
					
                	   	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                        cardLayout.show(Client.menu, Client.USEREDIT);
                    }
                   }
            }
        };
        
        delete = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
               for(int i = 0; i < list.size();i++) {
            	if (ae.getSource() == jbuttonDelet[i])
                {
            	   	try {
						FahrradRequest.userDelet(list.get(i).getId());
						//Client.fenster.repaint();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
               }
            }
        };

        backButton.addActionListener(action); 
        for(int i=0; i < list.size();i++){
            jbuttonDelet[i].addActionListener(delete);
            jbuttonEdit[i].addActionListener(edit);

        }
        
        for(int i=0; i < list.size();i++) {
        	add(jlabel[i]);
        	add(jbuttonEdit[i]);
        	add(jbuttonDelet[i]);
        }

        add(backButton);
    }
    
    private void token() {
    	 String xmlName = null;
         String xmlId = null;
         String xmlVorname = null;
         String xmlStrasse = null;
         String xmlHausNr = null;
         String xmlPlz = null;
         String xmlOrt = null;
         String xmlPasswort = null;
         
     	try {
 			listString = FahrradRequest.usersGet();
 			StringTokenizer token = new StringTokenizer(listString,"\"");
 			for(int i=0; i < 9;i++) {
 				token.nextToken();
 			}
 			boolean loop = true;
 			while(loop) {
 				xmlName = token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlVorname = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlStrasse = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlHausNr = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlPlz = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}
 				
 				xmlPasswort = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlId = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlOrt = token.nextToken();
 				
 				list.add(new User(xmlId, xmlName, xmlVorname, xmlStrasse, xmlHausNr, xmlPlz, xmlOrt, xmlPasswort));
 				
 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}
 			}
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		} catch (TransformerException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
    }
}