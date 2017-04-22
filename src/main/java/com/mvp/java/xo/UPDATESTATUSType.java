
package com.mvp.java.xo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for UPDATE_STATUSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPDATE_STATUSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPENSATION_STATUS" type="{}COMPENSATION_STATUSType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="COMPENSATION_JOB_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPDATE_STATUSType", propOrder = {
    "compensationstatus"
})
@XmlRootElement(name = "UPDATE_STATUS")
public class UPDATESTATUSType {

    @XmlElement(name = "COMPENSATION_STATUS")
    protected List<COMPENSATIONSTATUSType> compensationstatus;
    @XmlAttribute(name = "COMPENSATION_JOB_ID")
    protected Integer compensationjobid;

    /**
     * Gets the value of the compensationstatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compensationstatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMPENSATIONSTATUS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMPENSATIONSTATUSType }
     * 
     * 
     */
    public List<COMPENSATIONSTATUSType> getCOMPENSATIONSTATUS() {
        if (compensationstatus == null) {
            compensationstatus = new ArrayList<COMPENSATIONSTATUSType>();
        }
        return this.compensationstatus;
    }

    /**
     * Gets the value of the compensationjobid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCOMPENSATIONJOBID() {
        return compensationjobid;
    }

    /**
     * Sets the value of the compensationjobid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCOMPENSATIONJOBID(Integer value) {
        this.compensationjobid = value;
    }

}
