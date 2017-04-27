package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetSalesCompensationReportManagers", procedureName = "usp_GetSalesCompensationReportManagers"
                , resultClasses = ManagerReportsData.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "RegionID", type = Integer.class)
        })


})
@Entity

// REGION_ID, REGION_NAME, REPORTING_MGR_ID, REPORTING_MGR_NAME, SALESHIERARCHY_SALESQUOTA, SALES_AMOUNT, COMMISSION_AMOUNT

public class ManagerReportsData {
    @Id
    @Column(name = "REPORTING_MGR_ID")
    Integer mgrId;

    @Column(name = "REGION_ID")
    Integer regionId;
    @Column(name = "REGION_NAME")
    String regName;

    @Column(name = "REPORTING_MGR_NAME")
    String mgrName;

    @Column(name = "SALES_AMOUNT")
    Double salesAmount;
    @Column(name = "SALESHIERARCHY_SALESQUOTA")
    Double salesQuotaAmount;
    @Column(name = "COMMISSION_AMOUNT")
    Double commAmount;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Double getSalesQuotaAmount() {
        return salesQuotaAmount;
    }

    public void setSalesQuotaAmount(Double salesQuotaAmount) {
        this.salesQuotaAmount = salesQuotaAmount;
    }

    public Double getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(Double commAmount) {
        this.commAmount = commAmount;
    }
}
