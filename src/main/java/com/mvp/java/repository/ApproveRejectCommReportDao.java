package com.mvp.java.repository;

import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    public boolean saveCompensationApprovalRejectionStatus(Integer jobId, String compStatusXml, String remarks) {

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_ApproveRejectCompensation_U");
        query.setParameter("JobID", jobId);
        query.setParameter("CompensationStatusXml", compStatusXml);
        query.setParameter("Remarks", StringUtils.isEmpty(remarks) ? "" : remarks);

        Boolean anyError = query.execute();

        return anyError?false : true;

    }

}
