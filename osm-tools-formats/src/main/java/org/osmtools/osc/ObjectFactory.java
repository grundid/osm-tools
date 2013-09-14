//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.09.14 um 01:10:00 PM CEST 
//


package org.osmtools.osc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.osmtools.osc package. 
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

    private final static QName _OsmChange_QNAME = new QName("http://osmtools.org/osc", "osmChange");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.osmtools.osc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OsmChange }
     * 
     */
    public OsmChange createOsmChange() {
        return new OsmChange();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link Bounds }
     * 
     */
    public Bounds createBounds() {
        return new Bounds();
    }

    /**
     * Create an instance of {@link Node }
     * 
     */
    public Node createNode() {
        return new Node();
    }

    /**
     * Create an instance of {@link Nd }
     * 
     */
    public Nd createNd() {
        return new Nd();
    }

    /**
     * Create an instance of {@link Tag }
     * 
     */
    public Tag createTag() {
        return new Tag();
    }

    /**
     * Create an instance of {@link Relation }
     * 
     */
    public Relation createRelation() {
        return new Relation();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link Way }
     * 
     */
    public Way createWay() {
        return new Way();
    }

    /**
     * Create an instance of {@link Modify }
     * 
     */
    public Modify createModify() {
        return new Modify();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OsmChange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://osmtools.org/osc", name = "osmChange")
    public JAXBElement<OsmChange> createOsmChange(OsmChange value) {
        return new JAXBElement<OsmChange>(_OsmChange_QNAME, OsmChange.class, null, value);
    }

}
