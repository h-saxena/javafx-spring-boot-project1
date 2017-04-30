package com.mvp.java.repository;

import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.SalesPerformanceQuarterlyData;
import com.mvp.java.vo.SalesPerformanceSummaryData;
import com.mvp.java.vo.SalesPersonCompensationData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class DashboardReportDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<CompensationJob> getSalesReportsDashboard () {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ListReportsDashboard");
        query.setParameter("StartPageNumber", 1);
        query.setParameter("EndPageNumber", -1);
        return query.getResultList();

    }


    public List<SalesPersonCompensationData> getSalesPersonPayoutReportData (Integer jobId, Integer saleHierarchyId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboard");
        query.setParameter("JobID", jobId);
        query.setParameter("SalesHierarchyID", saleHierarchyId);
        return query.getResultList();
    }

    public List<SalesPerformanceQuarterlyData> getSalesPerformanceQuarterlyData (Integer jobId, Integer saleHierarchyId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboardQuaterWise");
        query.setParameter("JobID", jobId);
        query.setParameter("SalesHierarchyID", saleHierarchyId);
        return query.getResultList();
    }

    public List<SalesPerformanceQuarterlyData> getSalesPerformanceQuarterlyDataNative (Integer jobId, Integer saleHierarchyId) {
        List<SalesPerformanceQuarterlyData> dataList = new ArrayList<>();

        StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboardQuaterWiseResultNotMapped");

        storedProcedure.setParameter("JobID", jobId);
        storedProcedure.setParameter("SalesHierarchyID", saleHierarchyId);

        List results = storedProcedure.getResultList();

        results.forEach(result -> {
            Object[] values = (Object[]) result;
            if(values[0] == null && values[1] == null)  {
               SalesPerformanceQuarterlyData d = new SalesPerformanceQuarterlyData();
                d.setQuarter("Total");
                d.setSales(StringUtils.isEmpty( values[2]) ? null : Double.parseDouble(values[2].toString()));
                dataList.add(d);
            }
            else if (values[0] != null && values[1] != null) {
                SalesPerformanceQuarterlyData d = new SalesPerformanceQuarterlyData();
                d.setQuarter(values[0].toString());
                d.setQuota(Double.parseDouble(values[1].toString()));
                d.setSales(Double.parseDouble(values[2].toString()));
                dataList.add(d);
            }


        });

        return dataList;
    }


    public List<SalesPerformanceSummaryData> getSalesPerformanceSummaryData (Integer jobId, Integer saleHierarchyId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboardSummary");
        query.setParameter("JobID", jobId);
        query.setParameter("SalesHierarchyID", saleHierarchyId);
        return query.getResultList();
    }

}
