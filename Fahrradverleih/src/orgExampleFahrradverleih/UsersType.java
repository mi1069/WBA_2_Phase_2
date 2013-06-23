//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.18 at 05:14:24 PM CEST 
//


package orgExampleFahrradverleih;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for users_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="users_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.example.org/FahrradVerleih}user_type" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "users_type", propOrder = {
    "user"
})
public class UsersType {

    @XmlElement(required = true)
    protected List<UserType> user;
    
    /********* EIGENER TEIL **********/
 	public UsersType() {
 		this.user = new ArrayList<UserType>();
 	}

    /**
     * Gets the value of the user property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the user property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserType }
     * 
     * 
     */
    public List<UserType> getUser() {
        if (user == null) {
            user = new ArrayList<UserType>();
        }
        return this.user;
    }

    /********* EIGENER TEIL **********/
	public UserType getOneUser(int index) {
		return this.user.get(index);
	}

	/********* EIGENER TEIL **********/
	public UserType getOneUserById(int userId) {
		for (int i = 0; i < this.getUserListSize(); i++) {
			UserType user = this.user.get(i);
			if(user.getId().longValue() == userId){
				return user;
			}
		}
		return null;
	}

	public void setUsers(ArrayList<UserType> users) {
		this.user = users;
	}

	/********* EIGENER TEIL **********/
	public void addUser(UserType user) {
		this.user.add(user);
	}

	/********* EIGENER TEIL **********/
	public void deleteUser(UserType user) {
		this.user.remove(user);
	}

	/********* EIGENER TEIL **********/
	public int getUserListSize() {
		return this.user.size();
	}

	/********* EIGENER TEIL **********/
	public int getLastUserId() {
		return this.user.get(this.user.size()-1).getId().intValue();	
	}

}