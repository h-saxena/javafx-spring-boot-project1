package com.mvp.java.repository;

import com.mvp.java.helpers.AppConstant;
import com.mvp.java.vo.SalesPerson;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class SalesHierarchyDao {

    @PersistenceContext
    EntityManager entityManager;

    @Cacheable(cacheNames= AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<SalesPerson> getSalesPersons() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_Search_SalesPerson");
        query.setParameter("FName", "");
        query.setParameter("LName", "");
        query.setParameter("StartPageNumber", 1);
        query.setParameter("EndPageNumber", -1);
        return query.getResultList();
    }

    public List<SalesPerson> getNonCachedSalesPersons() {
        return getSalesPersons();
    }

    public boolean saveSaleHierarchy(String fname, String lname, Integer desId, Integer roleId, Double basicSal
            , Integer payFreq, Integer regId, Integer reportMgrId, Boolean compPlanEligible, Double saleQuota
            , Double totTargetComo, Boolean isActive, String userId, Integer saleHierarchyId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_SalesHiearchy_IU");
        query.setParameter("FName", fname);
        query.setParameter("LName", lname);
        query.setParameter("DesigID", desId);
        query.setParameter("RoleID", roleId);
        query.setParameter("BasicSalary", basicSal);
        query.setParameter("PaymentFreqID", payFreq);
        query.setParameter("RegionID", regId);
        query.setParameter("ReportingManagerID", reportMgrId);
        query.setParameter("CompensationPlanEligible", compPlanEligible);
        query.setParameter("TotalTargetCompensation", totTargetComo);
        query.setParameter("SalesQuota", saleQuota);
        query.setParameter("IsActive", isActive);
        query.setParameter("UserID", userId);
        query.setParameter("SalesHiearchyID", saleHierarchyId);

        Boolean anyError = query.execute();

        return anyError?false : true;
    }


}
