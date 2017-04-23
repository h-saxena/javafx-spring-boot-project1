package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ViewPerformanceDashboard", procedureName = "usp_ViewPerformanceDashboard"
                , resultClasses = SalesPersonCompensationData.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesHierarchyID", type = Integer.class)
        })


})
@Entity

// SALESHIERARCHY_ID, SALESHIERARCHY_NAME, ORDER_NUMBER, CUSTOMER_NAME, PRODUCT_DESCRIPTION, COMPENSATION_STATUS
// , SALES_AMOUNT, COMPENSATION_RATE, COMMISSION_AMOUNT, COMPENSATION_PAID

public class SalesPersonCompensationData {
    @Id
    @Column(name = "SALESHIERARCHY_ID")
    Integer id;
    @Column(name = "SALESHIERARCHY_NAME")
    String firstName;

    @Column(name = "ORDER_NUMBER")
    String orderNumber;
    @Column(name = "CUSTOMER_NAME")
    String customerName;
    @Column(name = "PRODUCT_DESCRIPTION")
    String prodDesc;
    @Column(name = "COMPENSATION_STATUS")
    String approvalStatus;
    @Column(name = "SALES_AMOUNT")
    Double salesAmount;
    @Column(name = "COMPENSATION_RATE")
    Integer compRate;
    @Column(name = "COMMISSION_AMOUNT")
    Double commAmount;
    @Column(name = "COMPENSATION_PAID")
    String paidStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getCompRate() {
        return compRate;
    }

    public void setCompRate(Integer compRate) {
        this.compRate = compRate;
    }

    public Double getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(Double commAmount) {
        this.commAmount = commAmount;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }
}
