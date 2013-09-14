//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.09.14 um 01:10:00 PM CEST 
//


package org.osm.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Osm_type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="Osm_type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="node"/>
 *     &lt;enumeration value="way"/>
 *     &lt;enumeration value="relation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Osm_type")
@XmlEnum
public enum OsmType {

    @XmlEnumValue("node")
    NODE("node"),
    @XmlEnumValue("way")
    WAY("way"),
    @XmlEnumValue("relation")
    RELATION("relation");
    private final String value;

    OsmType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OsmType fromValue(String v) {
        for (OsmType c: OsmType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
