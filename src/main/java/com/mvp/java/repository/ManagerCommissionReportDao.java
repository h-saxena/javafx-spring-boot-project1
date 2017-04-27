package com.mvp.java.repository;

import com.mvp.java.vo.ManagerReportDetailsData;
import com.mvp.java.vo.ManagerReportsData;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ManagerCommissionReportDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<ManagerReportsData> getManagerReports (Integer jobId, Integer regionId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetSalesCompensationReportManagers");
        query.setParameter("JobID", jobId);
        query.setParameter("RegionID", regionId);
        return query.getResultList();
    }

    public List<ManagerReportDetailsData> getManagerReportDetails (Integer jobId, Integer regionId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetSalesCompensationReportDetails");
        query.setParameter("JobID", jobId);
        query.setParameter("RegionID", regionId);
        return query.getResultList();
    }

}
