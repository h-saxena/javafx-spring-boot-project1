package com.mvp.java.repository;

import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.*;
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

    @Transactional
    public void getPerformanceDashboardFor(Integer jobId, Integer salePersonId) {
        Session session = entityManager.unwrap(Session.class);

        ProcedureCall call = session
                .createStoredProcedureCall("usp_ViewPerformanceDashboard");

        call.registerParameter(1, Integer.class,
                ParameterMode.IN).bindValue(50);
        call.registerParameter(2, Integer.class,
                ParameterMode.IN).bindValue(2);

        ProcedureOutputs outputs =  call.getOutputs();



//        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_ViewPerformanceDashboard");
//        query.setParameter("JobID", jobId);
//        query.setParameter("SalesHierarchyID", salePersonId);
//
//        query.execute();
//
//        List result1 = query.getResultList();
//        List result2 = null;
//        List result3 = null;
//
//        if(query.hasMoreResults())
//            result2 = query.getResultList();
//
//        if(query.hasMoreResults())
//            result3 = query.getResultList();
//
//

    }

}
