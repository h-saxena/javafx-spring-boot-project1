package com.mvp.java.repository;

import com.mvp.java.vo.CompensationCriteria;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import com.mvp.java.vo.CompensationPlan;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ApproveRejectCommReportDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<CompensationJob> getSalesReports () {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ListReports");
        query.setParameter("StartPageNumber", 1);
        query.setParameter("EndPageNumber", -1);
        return query.getResultList();

    }

    public List<CompensationJobData> getSalesReportData (Integer jobId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewCompensationReportForApprovalOrRejection");
        query.setParameter("JobID", jobId);
        return query.getResultList();

    }

}
