package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ListReports", procedureName = "usp_ListReports"
                , resultClasses = CompensationJob.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "StartPageNumber", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "EndPageNumber", type = Integer.class)
        }) ,
        @NamedStoredProcedureQuery(name = "usp_ListReportsForPayout", procedureName = "usp_ListReportsForPayout"
                , resultClasses = CompensationJob.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "StartPageNumber", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "EndPageNumber", type = Integer.class)
                })
        ,@NamedStoredProcedureQuery(name = "usp_ViewPerformanceDashboard", procedureName = "usp_ViewPerformanceDashboard"
        , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesHierarchyID", type = Integer.class)
        })



})
@Entity

// COMPENSATION_JOB_ID, COMPENSATION_JOB_RUNDATE, COMPENSATION_JOB_CRITERIA
public class CompensationJob {
    @Id
    @Column(name = "COMPENSATION_JOB_ID")
    Integer id;
    @Column(name = "COMPENSATION_JOB_RUNDATE")
    String createDate;
    @Column(name = "COMPENSATION_JOB_CRITERIA")
    String forPeriod;
    @Column(name = "COMPENSATION_JOB_REMARKS")
    String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getForPeriod() {
        return forPeriod;
    }

    public void setForPeriod(String forPeriod) {
        this.forPeriod = forPeriod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
       return   "Report created on: " + createDate
       + " | "+ "For Period: " + forPeriod
       ;
    }
}
