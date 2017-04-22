
package com.mvp.java.xo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMPENSATION_RATEType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMPENSATION_RATEType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CUSTOMERTYPE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PLAN_RATE" type="{}PLAN_RATEType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMPENSATION_RATEType", propOrder = {
    "customertypeid",
    "planrate"
})
public class COMPENSATIONRATEType {

    @XmlElement(name = "CUSTOMERTYPE_ID")
    protected int customertypeid = -1;
    @XmlElement(name = "PLAN_RATE")
    protected List<PLANRATEType> planrate;

    /**
     * Gets the value of the customertypeid property.
     * 
     */
    public int getCUSTOMERTYPEID() {
        return customertypeid;
    }

    /**
     * Sets the value of the customertypeid property.
     * 
     */
    public void setCUSTOMERTYPEID(int value) {
        this.customertypeid = value;
    }

    /**
     * Gets the value of the planrate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planrate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPLANRATE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLANRATEType }
     * 
     * 
     */
    public List<PLANRATEType> getPLANRATE() {
        if (planrate == null) {
            planrate = new ArrayList<PLANRATEType>();
        }
        return this.planrate;
    }

}
