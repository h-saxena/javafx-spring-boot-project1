package com.mvp.java.vo;

import javax.persistence.*;
import java.util.Date;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetTasks", procedureName = "usp_GetTasks", resultClasses = Task.class,
                parameters = {
                        //@StoredProcedureParameter(mode = ParameterMode.OUT, name = "TASK_ID", type = String.class),
                        //@StoredProcedureParameter(mode = ParameterMode.OUT, name = "TASK_DESCRPITION", type = String.class)
                })
        , @NamedStoredProcedureQuery(name = "usp_TaskScheduler_IU", procedureName = "usp_TaskScheduler_IU",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskID", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskStartDate", type = Date.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskEndDate", type = Date.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskFrequency", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "TaskActive", type = Boolean.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "RecurDay", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.INOUT, name = "TaskSchedulerID", type = Integer.class)
        })
})
@Entity
public class Task {

@Id
@Column(name = "TASK_ID")
    private Integer taskId;

    @Column (name = "TASK_DESCRIPTION")
    private String taskDesc;


    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return taskDesc;
    }

}
