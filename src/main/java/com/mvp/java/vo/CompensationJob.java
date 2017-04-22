package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ListReports", procedureName = "usp_ListReports"
                , resultClasses = CompensationJob.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "StartPageNumber", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "EndPageNumber", type = Integer.class)
        })

//        @NamedStoredProcedureQuery(name = "usp_CompensationPlan_IU", procedureName = "usp_CompensationPlan_IU"
//                ,
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanName", type = String.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanType", type = String.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "ProductID", type = Integer.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "ProductTypeID", type = Integer.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "PublishPlan", type = Boolean.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "UserID", type = String.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationDataXml", type = String.class)
//                        ,@StoredProcedureParameter(mode = ParameterMode.INOUT, name = "CompensationPlanID", type = Integer.class)
//                })

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

    @Override
    public String toString() {
       return   "Report created on: " + createDate
       + " | "+ "For Period: " + forPeriod
       ;
    }
}
