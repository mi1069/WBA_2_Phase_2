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
public class FahrradList extends JPanel {

	private ActionListener action;
	private ActionListener edit;
	private ActionListener delete;
	private String listString;
	private ArrayList<Fahrrad> list = new ArrayList<Fahrrad>();

    public FahrradList() 
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
            jlabel[i] = new JLabel("Fahrrad ID:" + list.get(i).getId() );

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
                    cardLayout.show(Client.menu, Client.FAHRRAEDERMENU);
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
                		FahrradEdit fahrradEdit = new FahrradEdit(list.get(i));
                        Client.menu.add(fahrradEdit, Client.FAHRRADEDIT);
                		
					
                	   	CardLayout cardLayout = (CardLayout) Client.menu.getLayout();
                        cardLayout.show(Client.menu, Client.FAHRRADEDIT);
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
						FahrradRequest.fahrradDelet(list.get(i).getId());
					} catch (IOException e) {
					
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
		String xmlPreis = null;
		String xmlId = null;
		String xmlRahmenNr = null;
		String xmlHersteller = null;
		String xmlBild = null;
		String xmlStandort = null;
		String xmlModel = null;
		String xmlBesitzer = null;
		
		try {
 			listString = FahrradRequest.fahrraederGet();
 			StringTokenizer token = new StringTokenizer(listString,"\"");
 			for(int i=0; i < 9;i++) {
 				token.nextToken();
 			}
 			boolean loop = true;
 			while(loop) {
 				xmlPreis = token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlRahmenNr = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlHersteller = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}
 				
 				xmlBild = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}
 				
 				xmlStandort = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlModel = token.nextToken();
 				
 				token.nextToken();

 				if(!token.hasMoreTokens()) {
 					loop = false;
 					break;
 				}

 				xmlBesitzer = token.nextToken();
 				
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

 				list.add(new Fahrrad(xmlId, xmlBesitzer, xmlModel, xmlHersteller, xmlRahmenNr, xmlBild , xmlStandort, xmlPreis));
 				
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
 			e1.printStackTrace();
 		} catch (TransformerException e1) {
 			e1.printStackTrace();
 		}
    }
}