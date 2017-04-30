package com.mvp.java.vo;

import javax.persistence.*;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ViewPerformanceDashboardQuaterWise", procedureName = "usp_ViewPerformanceDashboardQuaterWise"
                , resultClasses = SalesPerformanceQuarterlyData.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesHierarchyID", type = Integer.class)
                })
        ,@NamedStoredProcedureQuery(name = "usp_ViewPerformanceDashboardQuaterWiseResultNotMapped", procedureName = "usp_ViewPerformanceDashboardQuaterWise"
        ,parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesHierarchyID", type = Integer.class)
        })

})

// Quarter, SALESHIERARCHY_SALESQUOTA, SALES_AMOUNT
@Entity
public class SalesPerformanceQuarterlyData {
    @Id
    @Column(name = "Quarter")
    String quarter;
    @Column(name = "SALESHIERARCHY_SALESQUOTA")
    Double quota;
    @Column(name = "SALES_AMOUNT")
    Double sales;

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }
}
