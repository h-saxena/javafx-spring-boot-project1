package com.mvp.java.repository;

import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import com.mvp.java.vo.PayoutCompensationData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class PayoutCompensationReportDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<CompensationJob> getSalesReports () {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ListReportsForPayout");
        query.setParameter("StartPageNumber", 1);
        query.setParameter("EndPageNumber", -1);
        return query.getResultList();

    }

    public List<PayoutCompensationData> getSalesPayoutReportData (Integer jobId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_ViewReportForPayout");
        query.setParameter("JobID", jobId);
        return query.getResultList();

    }

    public boolean savePayoutCompensationReport(Integer jobId, String compStatusXml) {

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_PayoutCompensation_U");
        query.setParameter("JobID", jobId);
        query.setParameter("CompensationStatusXml", compStatusXml);

        Boolean anyError = query.execute();

        return anyError?false : true;

    }


}
