
package com.mvp.java.xo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mvp.java.xo package. 
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

    private final static QName _COMPENSATIONPLAN_QNAME = new QName("", "COMPENSATION_PLAN");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mvp.java.xo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link COMPENSATIONPLANType }
     * 
     */
    public COMPENSATIONPLANType createCOMPENSATIONPLANType() {
        return new COMPENSATIONPLANType();
    }

    /**
     * Create an instance of {@link COMPENSATIONRATEType }
     * 
     */
    public COMPENSATIONRATEType createCOMPENSATIONRATEType() {
        return new COMPENSATIONRATEType();
    }

    /**
     * Create an instance of {@link PLANRATEType }
     * 
     */
    public PLANRATEType createPLANRATEType() {
        return new PLANRATEType();
    }

    /**
     * Create an instance of {@link TIERType }
     * 
     */
    public TIERType createTIERType() {
        return new TIERType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link COMPENSATIONPLANType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "COMPENSATION_PLAN")
    public JAXBElement<COMPENSATIONPLANType> createCOMPENSATIONPLAN(COMPENSATIONPLANType value) {
        return new JAXBElement<COMPENSATIONPLANType>(_COMPENSATIONPLAN_QNAME, COMPENSATIONPLANType.class, null, value);
    }

}
