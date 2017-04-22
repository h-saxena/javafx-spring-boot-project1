
package com.mvp.java.xo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TIERType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TIERType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPENSATIONTIEREDRATE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMPENSATION_RANGEMIN" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="COMPENSATION_RANGEMAX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TIERType", propOrder = {
    "compensationtieredrateid",
    "compensationrangemin",
    "compensationrangemax"
})
public class TIERType {

    @XmlElement(name = "COMPENSATIONTIEREDRATE_ID")
    protected int compensationtieredrateid = -1;
    @XmlElement(name = "COMPENSATION_RANGEMIN")
    protected double compensationrangemin;
    @XmlElement(name = "COMPENSATION_RANGEMAX")
    protected double compensationrangemax;

    /**
     * Gets the value of the compensationtieredrateid property.
     * 
     */
    public int getCOMPENSATIONTIEREDRATEID() {
        return compensationtieredrateid;
    }

    /**
     * Sets the value of the compensationtieredrateid property.
     * 
     */
    public void setCOMPENSATIONTIEREDRATEID(int value) {
        this.compensationtieredrateid = value;
    }

    /**
     * Gets the value of the compensationrangemin property.
     * 
     */
    public double getCOMPENSATIONRANGEMIN() {
        return compensationrangemin;
    }

    /**
     * Sets the value of the compensationrangemin property.
     * 
     */
    public void setCOMPENSATIONRANGEMIN(double value) {
        this.compensationrangemin = value;
    }

    /**
     * Gets the value of the compensationrangemax property.
     * 
     */
    public double getCOMPENSATIONRANGEMAX() {
        return compensationrangemax;
    }

    /**
     * Sets the value of the compensationrangemax property.
     * 
     */
    public void setCOMPENSATIONRANGEMAX(double value) {
        this.compensationrangemax = value;
    }

}
