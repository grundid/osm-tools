//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.09.14 um 01:10:00 PM CEST 
//


package org.osm.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.osm.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.osm.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Osm }
     * 
     */
    public Osm createOsm() {
        return new Osm();
    }

    /**
     * Create an instance of {@link OsmBound }
     * 
     */
    public OsmBound createOsmBound() {
        return new OsmBound();
    }

    /**
     * Create an instance of {@link OsmNode }
     * 
     */
    public OsmNode createOsmNode() {
        return new OsmNode();
    }

    /**
     * Create an instance of {@link OsmWay }
     * 
     */
    public OsmWay createOsmWay() {
        return new OsmWay();
    }

    /**
     * Create an instance of {@link OsmRelation }
     * 
     */
    public OsmRelation createOsmRelation() {
        return new OsmRelation();
    }

    /**
     * Create an instance of {@link OsmChangeset }
     * 
     */
    public OsmChangeset createOsmChangeset() {
        return new OsmChangeset();
    }

    /**
     * Create an instance of {@link OsmUser }
     * 
     */
    public OsmUser createOsmUser() {
        return new OsmUser();
    }

    /**
     * Create an instance of {@link OsmMember }
     * 
     */
    public OsmMember createOsmMember() {
        return new OsmMember();
    }

    /**
     * Create an instance of {@link OsmTag }
     * 
     */
    public OsmTag createOsmTag() {
        return new OsmTag();
    }

    /**
     * Create an instance of {@link OsmNd }
     * 
     */
    public OsmNd createOsmNd() {
        return new OsmNd();
    }

}
