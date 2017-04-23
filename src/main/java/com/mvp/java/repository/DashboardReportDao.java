package com.mvp.java.repository;

import com.mvp.java.vo.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class DashboardReportDao {

    @PersistenceContext
    EntityManager entityManager;

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

    public List<SalesPerformanceSummaryData> getSalesPerformanceSummaryData (Integer jobId, Integer saleHierarchyId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboardSummary");
        query.setParameter("JobID", jobId);
        query.setParameter("SalesHierarchyID", saleHierarchyId);
        return query.getResultList();
    }

}
