package com.mvp.java.vo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ViewCompensationReportForApprovalOrRejection", procedureName = "usp_ViewCompensationReportForApprovalOrRejection"
                , resultClasses = CompensationJobData.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
        })

        ,@NamedStoredProcedureQuery(name = "usp_ApproveRejectCompensation_U", procedureName = "usp_ApproveRejectCompensation_U"
                ,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationStatusXml", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "Remarks", type = String.class)
                })

})
@Entity

// COMPENSATION_JOB_DATA_ID, SALESHIERARCHY_FNAME, SALESHIERARCHY_LNAME, ORDER_NUMBER, CUSTOMER_NAME,
// SALES_AMOUNT, COMPENSATION_JOB_DATA_STATUS, COMPENSATION_RATE, COMMISSION_AMOUNT

public class CompensationJobData {
    @Id
    @Column(name = "COMPENSATION_JOB_DATA_ID")
    Integer id;
    @Column(name = "SALESHIERARCHY_FNAME")
    String firstName;
    @Column(name = "SALESHIERARCHY_LNAME")
    String lastName;

    @Column(name = "ORDER_NUMBER")
    String orderNumber;
    @Column(name = "CUSTOMER_NAME")
    String customerName;
    @Column(name = "SALES_AMOUNT")
    Double salesAmount;
    @Column(name = "COMPENSATION_JOB_DATA_STATUS")
    Boolean approvalStatus;
    @Column(name = "COMPENSATION_RATE")
    Integer compRate;
    @Column(name = "COMMISSION_AMOUNT")
    Double commAmount;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    public String getSalesPersonName() {
        return firstName + " " + lastName;
    }

    //    @Override
//    public String toString() {
//       return   "Report created on: " + createDate
//       + " | "+ "For Period: " + forPeriod
//       ;
//    }
}
