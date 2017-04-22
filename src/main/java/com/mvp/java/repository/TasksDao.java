package com.mvp.java.repository;

import com.mvp.java.helpers.AppConstant;
import com.mvp.java.vo.Task;
import com.mvp.java.vo.TaskSchedule;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

@Component
public class TasksDao {

    @PersistenceContext
    EntityManager entityManager;

    @Cacheable(AppConstant.CACHE_REGION_INITIAL_LOAD)
    public List<Task> getTasks() {
        System.out.println("getTask method called");
        Query query = entityManager.createNamedStoredProcedureQuery("usp_GetTasks");
        //query.setParameter("arg", arg);
        return query.getResultList();
    }

    public TaskSchedule saveSchedule(Integer taskId, Date startDate, Date endDate, String freq, String recurDays, boolean active, Integer taskSchedulerId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_TaskScheduler_IU");
        query.setParameter("TaskID", taskId);
        query.setParameter("TaskStartDate", startDate);
        query.setParameter("TaskEndDate", endDate);
        query.setParameter("TaskFrequency", freq);
        query.setParameter("TaskActive", active);
        query.setParameter("RecurDay", recurDays == null ? "": recurDays);
        query.setParameter("TaskSchedulerID", taskSchedulerId);

        boolean status = true;   // true means issue
        try {
            status = query.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(status == false) { // no issues
            return getTaskScheduleFor(taskId);
        }

        return null;
    }

    public TaskSchedule getTaskScheduleFor(Integer taskId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_Search_TaskSchedule");
        query.setParameter("TaskID", taskId);
        List results = query.getResultList();
        if(results.size() > 0)
            return (TaskSchedule)results.get(0);

        return null;
    }

    public boolean runScheduler(Date fromDate, Date tillDate) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("usp_CalculateCompensation");
        query.setParameter("PayPeriodStartDate", fromDate);
        query.setParameter("PayPeriodEndDate", tillDate);

        boolean status = true;   // true means issue
        try {
            status = query.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return status?false : true;
    }


}
