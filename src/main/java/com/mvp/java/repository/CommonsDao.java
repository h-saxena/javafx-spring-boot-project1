package com.mvp.java.repository;

import com.mvp.java.helpers.AppConstant;
import com.mvp.java.vo.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class CommonsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<Designation> getDesignations() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetDesignations");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<Region> getRegions() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetRegions");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<Role> getRoles() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetRoles");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<PayoutFrequency> getPayoutFrequencies() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetPaymentFrequencies");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<ReportingManager> getReportingManagers() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetReportingManagerList");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<Product> getProducts() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetProducts");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<ProductType> getProductTypes() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetProductTypes");
        return query.getResultList();
    }

    @Cacheable(cacheNames=AppConstant.CACHE_REGION_INITIAL_LOAD, key="#root.methodName")
    public List<CustomerType> getCustomerTypes() {
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetCustomerTypes");
        return query.getResultList();
    }

}
