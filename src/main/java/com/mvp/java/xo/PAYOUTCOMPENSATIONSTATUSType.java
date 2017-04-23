
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
@XmlType(name = "PAYOUTCOMPENSATION_STATUSType", propOrder = {
    "compensationjobdataid",
    "compensationjobdatastatus",
    "compensationjobdataduediligence",
    "compensationjobdatapaid"
})
public class PAYOUTCOMPENSATIONSTATUSType {

    @XmlElement(name = "COMPENSATION_JOB_DATA_ID")
    protected int compensationjobdataid;
    @XmlElement(name = "COMPENSATION_JOB_DATA_STATUS")
    protected int compensationjobdatastatus;
    @XmlElement(name = "COMPENSATION_JOB_DATA_DUE_DILIGENCE")
    protected int compensationjobdataduediligence;
    @XmlElement(name = "COMPENSATION_JOB_DATA_PAID")
    protected int compensationjobdatapaid;

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

    public int getCompensationjobdataduediligence() {
        return compensationjobdataduediligence;
    }

    public void setCompensationjobdataduediligence(int compensationjobdataduediligence) {
        this.compensationjobdataduediligence = compensationjobdataduediligence;
    }

    public int getCompensationjobdatapaid() {
        return compensationjobdatapaid;
    }

    public void setCompensationjobdatapaid(int compensationjobdatapaid) {
        this.compensationjobdatapaid = compensationjobdatapaid;
    }
}
