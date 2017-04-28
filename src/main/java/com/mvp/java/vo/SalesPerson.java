package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_Search_SalesPerson", procedureName = "usp_Search_SalesPerson",
                resultClasses = SalesPerson.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "FName", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "LName", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "StartPageNumber", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "EndPageNumber", type = Integer.class)
                }

        )

        , @NamedStoredProcedureQuery(name = "usp_SalesHiearchy_IU", procedureName = "usp_SalesHiearchy_IU",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "FName", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "LName", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "DesigID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "RoleID", type = Integer.class)

                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "BasicSalary", type = Double.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "PaymentFreqID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "RegionID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "ReportingManagerID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanEligible", type = Boolean.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "TotalTargetCompensation", type = Double.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesQuota", type = Double.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "IsActive", type = Boolean.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "UserID", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.INOUT, name = "SalesHiearchyID", type = Integer.class)
        }

)

})

// SALESHIERARCHY_ID, SALESHIERARCHY_FNAME, SALESHIERARCHY_LNAME, DESIGNATION_ID
// , DESIGNATION_NAME, ROLE_ID, SALESHIERARCHY_BASICSALARY, PAYMENTFREQUENCY_ID
// , REGION_ID, REPORTINGMANAGER_ID, COMPENSATIONPLAN_ELIGIBLE, SALESHIERARCHY_SALESQUOTA
// , TOTAL_TARGET_COMPENSATION, SALESHIERARCHY_ACTIVE

@Entity
public class SalesPerson {

@Id
@Column(name = "SALESHIERARCHY_ID")
private Integer saleHierarchyId;

    @Column (name = "SALESHIERARCHY_FNAME")
    private String fname;

    @Column (name = "SALESHIERARCHY_LNAME")
    private String lname;

    @Column(name = "DESIGNATION_ID")
    private Integer desId;

    @Column (name = "DESIGNATION_NAME")
    private String desName;

    @Column(name = "ROLE_ID")
    private Integer rId;

    @Column(name = "REGION_ID")
    private Integer regionId;

    @Column(name = "SALESHIERARCHY_BASICSALARY")
    private Double bSalary;
    @Column(name = "PAYMENTFREQUENCY_ID")
    private Integer payFreqId;
    @Column(name = "REPORTINGMANAGER_ID")
    private Integer reportMgrId;
    @Column(name = "COMPENSATIONPLAN_ELIGIBLE")
    private Boolean planEligible;
    @Column(name = "SALESHIERARCHY_SALESQUOTA")
    private Double salesQuota;
    @Column(name = "TOTAL_TARGET_COMPENSATION")
    private Double targetComp;
    @Column(name = "SALESHIERARCHY_ACTIVE")
    private Boolean active;

    public Integer getSaleHierarchyId() {
        return saleHierarchyId;
    }

    public void setSaleHierarchyId(Integer saleHierarchyId) {
        this.saleHierarchyId = saleHierarchyId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getDesId() {
        return desId;
    }

    public void setDesId(Integer desId) {
        this.desId = desId;
    }

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName = desName;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Double getbSalary() {
        return bSalary;
    }

    public void setbSalary(Double bSalary) {
        this.bSalary = bSalary;
    }

    public Integer getPayFreqId() {
        return payFreqId;
    }

    public void setPayFreqId(Integer payFreqId) {
        this.payFreqId = payFreqId;
    }

    public Integer getReportMgrId() {
        return reportMgrId;
    }

    public void setReportMgrId(Integer reportMgrId) {
        this.reportMgrId = reportMgrId;
    }

    public Boolean getPlanEligible() {
        return planEligible;
    }

    public void setPlanEligible(Boolean planEligible) {
        this.planEligible = planEligible;
    }

    public Double getSalesQuota() {
        return salesQuota;
    }

    public void setSalesQuota(Double salesQuota) {
        this.salesQuota = salesQuota;
    }

    public Double getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(Double targetComp) {
        this.targetComp = targetComp;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        String dispStr = lname + ", " + fname + " - " + desName;
        if(this.rId == 1) //admin
            dispStr+= "*";
        return dispStr;
    }

}
