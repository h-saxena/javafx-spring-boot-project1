package com.mvp.java.vo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ViewReportForPayout", procedureName = "usp_ViewReportForPayout"
                , resultClasses = PayoutCompensationData.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
        })

        ,@NamedStoredProcedureQuery(name = "usp_PayoutCompensation_U", procedureName = "usp_PayoutCompensation_U"
                ,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationStatusXml", type = String.class)
                })

})
@Entity

// COMPENSATION_JOB_DATA_ID, COMPENSATION_PLAN_NAME, SALESHIERARCHY_FNAME, SALESHIERARCHY_LNAME,
// COMMISSION_AMOUNT, COMPENSATION_JOB_DATA_STATUS, COMPENSATION_JOB_DATA_DUE_DILIGENCE, COMPENSATION_JOB_DATA_PAID

public class PayoutCompensationData {
    @Id
    @Column(name = "COMPENSATION_JOB_DATA_ID")
    Integer id;

    @Column(name = "COMPENSATION_PLAN_NAME")
    String planName;

    @Column(name = "SALESHIERARCHY_FNAME")
    String firstName;
    @Column(name = "SALESHIERARCHY_LNAME")
    String lastName;

    @Column(name = "COMMISSION_AMOUNT")
    Double commAmount;

    @Column(name = "COMPENSATION_JOB_DATA_STATUS")
    Boolean approvalStatus;

    @Column(name = "COMPENSATION_JOB_DATA_DUE_DILIGENCE")
    Boolean dueDiligence;

    @Column(name = "COMPENSATION_JOB_DATA_PAID")
    Boolean paid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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

    public Double getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(Double commAmount) {
        this.commAmount = commAmount;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Boolean getDueDiligence() {
        return dueDiligence;
    }

    public void setDueDiligence(Boolean dueDiligence) {
        this.dueDiligence = dueDiligence;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getSalesPersonName() {
        return firstName + " " + lastName;
    }

}
