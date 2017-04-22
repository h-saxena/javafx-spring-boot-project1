
package com.mvp.java.xo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMPENSATION_STATUSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMPENSATION_STATUSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPENSATION_JOB_DATA_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMPENSATION_JOB_DATA_STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMPENSATION_STATUSType", propOrder = {
    "compensationjobdataid",
    "compensationjobdatastatus"
})
public class COMPENSATIONSTATUSType {

    @XmlElement(name = "COMPENSATION_JOB_DATA_ID")
    protected int compensationjobdataid;
    @XmlElement(name = "COMPENSATION_JOB_DATA_STATUS")
    protected int compensationjobdatastatus;

    /**
     * Gets the value of the compensationjobdataid property.
     * 
     */
    public int getCOMPENSATIONJOBDATAID() {
        return compensationjobdataid;
    }

    /**
     * Sets the value of the compensationjobdataid property.
     * 
     */
    public void setCOMPENSATIONJOBDATAID(int value) {
        this.compensationjobdataid = value;
    }

    /**
     * Gets the value of the compensationjobdatastatus property.
     * 
     */
    public int getCOMPENSATIONJOBDATASTATUS() {
        return compensationjobdatastatus;
    }

    /**
     * Sets the value of the compensationjobdatastatus property.
     * 
     */
    public void setCOMPENSATIONJOBDATASTATUS(int value) {
        this.compensationjobdatastatus = value;
    }

}
