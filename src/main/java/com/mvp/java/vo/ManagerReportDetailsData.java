package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetSalesCompensationReportDetails", procedureName = "usp_GetSalesCompensationReportDetails"
                , resultClasses = ManagerReportDetailsData.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "RegionID", type = Integer.class)
        })


})
@Entity

// REGION_ID, REGION_NAME, SALESHIERARCHY_ID, SALESHIERARCHY_FNAME, SALESHIERARCHY_LNAME, SALES_AMOUNT
// , ORDER_NUMBER, CUSTOMER_NAME, PRODUCT_DESCRIPTION, COMMISSION_AMOUNT, REPORTING_MGR_ID, REPORTING_MGR_NAME

// ORDER_NUMBER, , PRODUCT_DESCRIPTION, SALESHIERARCHY_FNAME, SALESHIERARCHY_LNAME, SALES_AMOUNT
// COMMISSION_AMOUNT, REPORTING_MGR_ID

public class ManagerReportDetailsData {
    @EmbeddedId
    ManagerReportDetailId detailId;

    @Column(name = "REPORTING_MGR_ID")
    Integer mgrId;

    @Column(name = "SALESHIERARCHY_FNAME")
    String fName;

    @Column(name = "SALESHIERARCHY_LNAME")
    String lNAme;

    @Column(name = "CUSTOMER_NAME")
    String customerName;

    @Column(name = "SALES_AMOUNT")
    Double salesAmount;

    @Column(name = "COMMISSION_AMOUNT")
    Double commAmount;

    public ManagerReportDetailId getDetailId() {
        return detailId;
    }

    public void setDetailId(ManagerReportDetailId detailId) {
        this.detailId = detailId;
    }

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlNAme() {
        return lNAme;
    }

    public void setlNAme(String lNAme) {
        this.lNAme = lNAme;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Double getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(Double commAmount) {
        this.commAmount = commAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesPersonName() {
        return fName + " " + lNAme;
    }

    public String getOrderNum() {
        return detailId.getOrderNum();
    }
}
