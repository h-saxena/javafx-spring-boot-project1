
package com.mvp.java.xo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for COMPENSATION_PLANType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMPENSATION_PLANType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPENSATION_RATE" type="{}COMPENSATION_RATEType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="COMPENSATIONPLAN_ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMPENSATION_PLANType", propOrder = {
    "compensationrate"
})
@XmlRootElement(name = "COMPENSATION_PLAN")
public class COMPENSATIONPLANType {

    @XmlElement(name = "COMPENSATION_RATE")
    protected List<COMPENSATIONRATEType> compensationrate;
    @XmlAttribute(name = "COMPENSATIONPLAN_ID")
    protected Integer compensationplanid = -1;

    /**
     * Gets the value of the compensationrate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compensationrate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMPENSATIONRATE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMPENSATIONRATEType }
     * 
     * 
     */
    public List<COMPENSATIONRATEType> getCOMPENSATIONRATE() {
        if (compensationrate == null) {
            compensationrate = new ArrayList<COMPENSATIONRATEType>();
        }
        return this.compensationrate;
    }

    /**
     * Gets the value of the compensationplanid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCOMPENSATIONPLANID() {
        return compensationplanid;
    }

    /**
     * Sets the value of the compensationplanid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCOMPENSATIONPLANID(Integer value) {
        this.compensationplanid = value;
    }

}
