//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.18 at 05:14:24 PM CEST 
//


package orgExampleFahrradverleih;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="user_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="passwort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strasse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hausNr" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="plz" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="ort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user_type", propOrder = {
    "name",
    "vorname",
    "passwort",
    "strasse",
    "hausNr",
    "plz",
    "ort"
})
public class UserType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String vorname;
    @XmlElement(required = true)
    protected String passwort;
    @XmlElement(required = true)
    protected String strasse;
    @XmlElement(required = true)
    protected BigInteger hausNr;
    @XmlElement(required = true)
    protected BigInteger plz;
    @XmlElement(required = true)
    protected String ort;
    @XmlAttribute(name = "id", required = true)
    protected BigInteger id;
    
public UserType () {
    	
    }
    
    /********* EIGENER TEIL **********/
    public UserType ( Integer id, String name, String vorname, String strasse, Integer hausNr, Integer plz, String ort, String passwort) {
    	setId(new BigInteger(id.toString()));
    	setName(name);
    	setVorname(vorname);
    	setStrasse(strasse);
    	setHausNr(hausNr);
    	setPlz(plz);
    	setOrt(ort);
    	setPasswort(passwort);
    	
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the vorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets the value of the vorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVorname(String value) {
        this.vorname = value;
    }

    /**
     * Gets the value of the passwort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Sets the value of the passwort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswort(String value) {
        this.passwort = value;
    }

    /**
     * Gets the value of the strasse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Sets the value of the strasse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrasse(String value) {
        this.strasse = value;
    }

    /**
     * Gets the value of the hausNr property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHausNr() {
        return hausNr;
    }

    /**
     * Sets the value of the hausNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHausNr(Integer value) {
        this.hausNr = new BigInteger(value.toString());
    }

    /**
     * Gets the value of the plz property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPlz() {
        return plz;
    }

    /**
     * Sets the value of the plz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPlz(Integer value) {
        this.plz = new BigInteger(value.toString());;
    }

    /**
     * Gets the value of the ort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Sets the value of the ort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrt(String value) {
        this.ort = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

}
