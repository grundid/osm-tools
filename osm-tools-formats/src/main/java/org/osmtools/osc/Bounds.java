//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.09.14 um 01:10:00 PM CEST 
//


package org.osmtools.osc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für bounds complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="bounds">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="minlat" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="minlon" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="maxlat" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="maxlon" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="origin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bounds")
public class Bounds {

    @XmlAttribute(name = "minlat", required = true)
    protected float minlat;
    @XmlAttribute(name = "minlon", required = true)
    protected float minlon;
    @XmlAttribute(name = "maxlat", required = true)
    protected float maxlat;
    @XmlAttribute(name = "maxlon", required = true)
    protected float maxlon;
    @XmlAttribute(name = "origin")
    protected String origin;

    /**
     * Ruft den Wert der minlat-Eigenschaft ab.
     * 
     */
    public float getMinlat() {
        return minlat;
    }

    /**
     * Legt den Wert der minlat-Eigenschaft fest.
     * 
     */
    public void setMinlat(float value) {
        this.minlat = value;
    }

    /**
     * Ruft den Wert der minlon-Eigenschaft ab.
     * 
     */
    public float getMinlon() {
        return minlon;
    }

    /**
     * Legt den Wert der minlon-Eigenschaft fest.
     * 
     */
    public void setMinlon(float value) {
        this.minlon = value;
    }

    /**
     * Ruft den Wert der maxlat-Eigenschaft ab.
     * 
     */
    public float getMaxlat() {
        return maxlat;
    }

    /**
     * Legt den Wert der maxlat-Eigenschaft fest.
     * 
     */
    public void setMaxlat(float value) {
        this.maxlat = value;
    }

    /**
     * Ruft den Wert der maxlon-Eigenschaft ab.
     * 
     */
    public float getMaxlon() {
        return maxlon;
    }

    /**
     * Legt den Wert der maxlon-Eigenschaft fest.
     * 
     */
    public void setMaxlon(float value) {
        this.maxlon = value;
    }

    /**
     * Ruft den Wert der origin-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Legt den Wert der origin-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

}
