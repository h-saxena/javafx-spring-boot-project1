package com.mvp.java.vo;

import javax.persistence.*;
import java.util.Date;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_Search_TaskSchedule", procedureName = "usp_Search_TaskSchedule",
                resultClasses = TaskSchedule.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskID", type = Integer.class)
        })
})


@Entity

public class TaskSchedule {

    @Id
    @Column(name = "TASKSCHEDULER_ID")
    private Integer taskSchedulerId;

    @Column (name = "TASK_ID")
    private String taskId;

    @Column (name = "TASKSCHEDULER_STARTDATE")
    private Date startDate;

    @Column (name = "TASKSCHEDULER_ENDDATE")
    private Date endDate;

    @Column (name = "TASKSCHEDULER_FREQUENCY")
    private String taskFrequency;

    @Column (name = "TASKSCHEDULER_ACTIVE")
    private Boolean taskActive;

    @Column (name = "RECUR_DAY")
    private String recurDay;

    public String getRecurDay() {
        return recurDay;
    }

    public void setRecurDay(String recurDay) {
        this.recurDay = recurDay;
    }

    public Integer getTaskSchedulerId() {
        return taskSchedulerId;
    }

    public void setTaskSchedulerId(Integer taskSchedulerId) {
        this.taskSchedulerId = taskSchedulerId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTaskFrequency() {
        return taskFrequency;
    }

    public void setTaskFrequency(String taskFrequency) {
        this.taskFrequency = taskFrequency;
    }

    public Boolean getTaskActive() {
        return taskActive;
    }

    public void setTaskActive(Boolean taskActive) {
        this.taskActive = taskActive;
    }
}
