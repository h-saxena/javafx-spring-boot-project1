package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_Search_CompensationPlanDetailsByID", procedureName = "usp_Search_CompensationPlanDetailsByID", resultClasses = CompensationCriteria.class
                , parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanID", type = Integer.class)
                }
        )
})

// COMPENSATIONPLAN_ID, COMMISSION_RATE, ROLE_ID, ROLE_DESCRIPTION
// , CUSTOMERTYPE_ID, CUSTOMERTYPE_DESCRIPTION, COMPENSATIONTIEREDRATE_ID
// , COMPENSATION_RANGEMIN, COMPENSATION_RANGEMAX
@Entity
public class CompensationCriteria {
    public CompensationCriteria(){

    }

    public CompensationCriteria(Integer planId, Integer planRateId, Integer tieredRateId,
                                Integer roleId, String roleDesc
                                , Integer custTypeId, String custTypeDesc, Integer commRate,
                                 Double rangeMin, Double rangeMax, boolean active) {
        this.planId = planId;
        this.planRateId = planRateId;
        this.tieredRateId = tieredRateId;
        this.roleId = roleId;
        this.custTypeId = custTypeId;
        this.commRate = commRate;
        this.roleDesc = roleDesc;
        this.custTypeDesc = custTypeDesc;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        isActive = active;
    }

    @Column(name = "COMPENSATIONPLAN_ID")
    private Integer planId = -1;

    @Id
    @Column(name = "COMPENSATIONRATE_ID")
    private Integer planRateId = -1;

    @Column(name = "COMPENSATIONTIEREDRATE_ID")
    private Integer tieredRateId = -1;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "CUSTOMERTYPE_ID")
    private Integer custTypeId;


    @Column(name = "COMMISSION_RATE")
    private Integer commRate;

    @Column (name = "ROLE_DESCRIPTION")
    private String roleDesc;

    @Column (name = "CUSTOMERTYPE_DESCRIPTION")
    private String custTypeDesc;

    @Column (name = "COMPENSATION_RANGEMIN")
    private Double rangeMin;

    @Column (name = "COMPENSATION_RANGEMAX")
    private Double rangeMax;

    @Transient
    private boolean isActive = true;

    public Integer getPlanRateId() {
        return planRateId;
    }

    public void setPlanRateId(Integer planRateId) {
        this.planRateId = planRateId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCustTypeId() {
        return custTypeId;
    }

    public void setCustTypeId(Integer custTypeId) {
        this.custTypeId = custTypeId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getCommRate() {
        return commRate;
    }

    public void setCommRate(Integer commRate) {
        this.commRate = commRate;
    }

    public Integer getTieredRateId() {
        return tieredRateId;
    }

    public void setTieredRateId(Integer tieredRateId) {
        this.tieredRateId = tieredRateId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getCustTypeDesc() {
        return custTypeDesc;
    }

    public void setCustTypeDesc(String custTypeDesc) {
        this.custTypeDesc = custTypeDesc;
    }

    public Double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Double rangeMax) {
        this.rangeMax = rangeMax;
    }

    @Override
    public String toString() {
        return (getPlanRateId() == -1 ? "<New>- ":"")
                +  "Type: " + custTypeDesc
                + " | "+ "Role: " + roleDesc
                + " | "+ "Co-Rate: " + commRate
                + ((rangeMin != null) ? (" | "+ "Min: " + rangeMin) : "")
                + ((rangeMax != null) ? (" | "+ "Max: " + rangeMax) : "")
                ;
    }
}
