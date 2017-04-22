package com.mvp.java.repository;

import com.mvp.java.vo.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository  extends JpaRepository<Task, Long> {

    @Procedure(procedureName = "usp_GetTasks")
    List<Task> getTasks();

}
