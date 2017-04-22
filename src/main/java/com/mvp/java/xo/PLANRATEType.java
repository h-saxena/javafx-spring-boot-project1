
package com.mvp.java.xo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PLAN_RATEType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PLAN_RATEType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPENSATIONRATE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ROLE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMMISSION_RATE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMPENSATIONRATE_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TIER" type="{}TIERType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PLAN_RATEType", propOrder = {
    "compensationrateid",
    "roleid",
    "commissionrate",
    "compensationrateactive",
    "tier"
})
public class PLANRATEType {

    @XmlElement(name = "COMPENSATIONRATE_ID")
    protected int compensationrateid = -1;
    @XmlElement(name = "ROLE_ID")
    protected int roleid;
    @XmlElement(name = "COMMISSION_RATE")
    protected int commissionrate;
    @XmlElement(name = "COMPENSATIONRATE_ACTIVE")
    protected int compensationrateactive;
    @XmlElement(name = "TIER", required = true)
    protected TIERType tier;

    /**
     * Gets the value of the compensationrateid property.
     * 
     */
    public int getCOMPENSATIONRATEID() {
        return compensationrateid;
    }

    /**
     * Sets the value of the compensationrateid property.
     * 
     */
    public void setCOMPENSATIONRATEID(int value) {
        this.compensationrateid = value;
    }

    /**
     * Gets the value of the roleid property.
     * 
     */
    public int getROLEID() {
        return roleid;
    }

    /**
     * Sets the value of the roleid property.
     * 
     */
    public void setROLEID(int value) {
        this.roleid = value;
    }

    /**
     * Gets the value of the commissionrate property.
     * 
     */
    public int getCOMMISSIONRATE() {
        return commissionrate;
    }

    /**
     * Sets the value of the commissionrate property.
     * 
     */
    public void setCOMMISSIONRATE(int value) {
        this.commissionrate = value;
    }

    /**
     * Gets the value of the compensationrateactive property.
     * 
     */
    public int isCOMPENSATIONRATEACTIVE() {
        return compensationrateactive;
    }

    /**
     * Sets the value of the compensationrateactive property.
     * 
     */
    public void setCOMPENSATIONRATEACTIVE(int value) {
        this.compensationrateactive = value;
    }

    /**
     * Gets the value of the tier property.
     * 
     * @return
     *     possible object is
     *     {@link TIERType }
     *     
     */
    public TIERType getTIER() {
        return tier;
    }

    /**
     * Sets the value of the tier property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIERType }
     *     
     */
    public void setTIER(TIERType value) {
        this.tier = value;
    }

}
