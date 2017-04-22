package com.mvp.java.controllers;

import com.mvp.java.repository.TasksDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RunSchedulerController {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    DatePicker dtFrom;

    @FXML
    DatePicker dtTill;



    @Autowired
    TasksDao tasksDao;


    public void loadUI() {
        dtFrom.setValue(null);
        dtTill.setValue(null);
    }

    public void runSchedule() {
        List<String> errorMessages = new ArrayList<String>();

        String startDate = null;
        String endDate = null;

        LocalDate lclStartDate = this.dtFrom.getValue();
        if(lclStartDate == null) {
            errorMessages.add("Please select From Date");
        }
        else {
            startDate = lclStartDate.toString();
        }

        LocalDate lclEndDate = this.dtTill.getValue();
        if(lclEndDate == null) {
            errorMessages.add("Please select Till Date");
        }
        else {
            endDate = lclEndDate.toString();
        }

        if(errorMessages.size() > 0) {
            AppDialogHelper.showErrorDialog(errorMessages);
        }else {
            boolean success = false;
            try {
                success = tasksDao.runScheduler(df.parse(startDate), df.parse(endDate));
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            if(success)
                AppDialogHelper.showDialog("Success" , Arrays.asList("Task Scheduler executed Successfully for the given period"));
            else
                AppDialogHelper.showErrorDialog( Arrays.asList("Unable to run the Task Scheduler"));
        }
    }


}
