package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_ViewPerformanceDashboardSummary", procedureName = "usp_ViewPerformanceDashboardSummary"
                , resultClasses = SalesPerformanceSummaryData.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "JobID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "SalesHierarchyID", type = Integer.class)
                })
})

// Summary, TOTAL_SALES_AMOUNT, TOTAL_COMMISSION_AMOUNT
@Entity
public class SalesPerformanceSummaryData {
    @Id
    @Column(name = "Summary")
    String summary;
    @Column(name = "TOTAL_SALES_AMOUNT")
    Double totalSalesAmount;
    @Column(name = "TOTAL_COMMISSION_AMOUNT")
    Double totalCommissionAmount;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(Double totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    public Double getTotalCommissionAmount() {
        return totalCommissionAmount;
    }

    public void setTotalCommissionAmount(Double totalCommissionAmount) {
        this.totalCommissionAmount = totalCommissionAmount;
    }
}
