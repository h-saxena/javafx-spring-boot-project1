package com.mvp.java.repository;

import com.mvp.java.vo.CompensationCriteria;
import com.mvp.java.vo.CompensationPlan;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class CompensationPlanDao {

    @PersistenceContext
    EntityManager entityManager;

   public List<CompensationPlan> getAllCompensationPlans() {
       Query query = entityManager.createNamedStoredProcedureQuery("usp_Search_ComplensationPlan");
       query.setParameter("PlanName", "");
       query.setParameter("StartPageNumber", 1);
       query.setParameter("EndPageNumber", -1);
       return query.getResultList();

   }

    public List<CompensationCriteria> getCompensationCriteriaBy(Integer planId) {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_Search_CompensationPlanDetailsByID");
        query.setParameter("CompensationPlanID", planId);
        return query.getResultList();

    }

    public boolean saveCompensationPlan(String CompensationPlanName,String CompensationPlanType,Integer ProductID,Integer ProductTypeID
            ,Boolean PublishPlan,String UserID,String CompensationDataXml,Integer CompensationPlanID) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_CompensationPlan_IU");
        query.setParameter("CompensationPlanName", CompensationPlanName);
        query.setParameter("CompensationPlanType", CompensationPlanType);
        query.setParameter("ProductID", ProductID);
        query.setParameter("ProductTypeID", ProductTypeID);
        query.setParameter("PublishPlan", PublishPlan );
        query.setParameter("UserID", UserID);
        query.setParameter("CompensationDataXml", CompensationDataXml);
        query.setParameter("CompensationPlanID", CompensationPlanID);


        Boolean anyError = query.execute();
//        if(!anyError) {
//            CompensationPlanID = (Integer) query.getOutputParameterValue("CompensationPlanID");
//        }

        return anyError?false : true;

    }
}
