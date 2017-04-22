package com.mvp.java.controllers;

import com.mvp.java.helpers.TaskSchedulerHelper;
import com.mvp.java.repository.TasksDao;
import com.mvp.java.vo.Task;
import com.mvp.java.vo.TaskSchedule;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TaskSchedulerController {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    List<Task> tasks;


    @FXML
    Pane TaskSchedulerNode;

    @FXML
    ComboBox tasksComboBox;

    @FXML
    ComboBox monthComboBox;

    @FXML
    CheckBox inactiveCheckbox;

    @FXML
    DatePicker startDate;

    @FXML
    DatePicker endDate;

    @FXML
    RadioButton dailyRadioButton;
    @FXML
    RadioButton weeklyRadioButton;
    @FXML
    RadioButton monthlyRadioButton;


    boolean isLoadedOnce = false;

    //@Autowired
    //TasksRepository tasksRepository;

    @Autowired
    TasksDao tasksDao;

    @Autowired
    TaskSchedulerHelper taskSchedulerHelper;

    TaskSchedule currSelTaskSchedule = null;

    public void initialize() {
    }

    public void loadUI() {
        if(!isLoadedOnce) {
            loadData();

            tasksComboBox.setItems(FXCollections.observableArrayList(tasks));

            ArrayList monthDays = new ArrayList();
            for(int i = 1 ; i < 32 ; i++) monthDays.add(i);


            monthComboBox.setItems(FXCollections.observableArrayList(monthDays));

            startDate.setDayCellFactory(dayCellFactory);
            endDate.setDayCellFactory(dayCellFactory);

        }

        isLoadedOnce = true;
    }

    // load external data
    public void loadData() {

        loadTasks();

    }

    public void taskSelectionChanged(ActionEvent ev) {
        resetView();
        currSelTaskSchedule = tasksDao.getTaskScheduleFor(((Task) tasksComboBox.getSelectionModel().getSelectedItem()).getTaskId());
        if(currSelTaskSchedule != null) {
            String recurDay = currSelTaskSchedule.getRecurDay();

            startDate.setValue(currSelTaskSchedule.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            endDate.setValue( currSelTaskSchedule.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            String freq = currSelTaskSchedule.getTaskFrequency();

            if(freq.equals("D")) {
                RadioButton rb = (RadioButton) TaskSchedulerNode.lookup("#dailyRadioButton");
                rb.setSelected(true);
            }
            else if(freq.equals("W")) {
                RadioButton rb = (RadioButton) TaskSchedulerNode.lookup("#weeklyRadioButton");
                rb.setSelected(true);

                List<String> days = Arrays.asList(recurDay.split(","));
                List<String> wkDaysNodes = Arrays.asList("blank", "sun", "mon", "tues", "wed", "thurs", "fri", "sat");
                for(String day : days) {
                    String dayStr = wkDaysNodes.get(Integer.parseInt(day));
                    CheckBox wkDayCb = (CheckBox) TaskSchedulerNode.lookup("#" + dayStr + "CheckBox");
                    wkDayCb.setSelected(true);
                }
            }
            else if(freq.equals("M")) {
                RadioButton rb = (RadioButton) TaskSchedulerNode.lookup("#monthlyRadioButton");
                rb.setSelected(true);
                monthComboBox.getSelectionModel().select(recurDay);

            }

            inactiveCheckbox.setSelected(currSelTaskSchedule.getTaskActive() ? false : true);
        }
    }

    private void resetView() {
        startDate.setValue(null);
        endDate.setValue(null);

        dailyRadioButton.setSelected(false);
        weeklyRadioButton.setSelected(false);
        monthlyRadioButton.setSelected(false);

        List<String> wkDaysNodes = Arrays.asList("sun", "mon", "tues", "wed", "thurs", "fri", "sat");
        for(String day : wkDaysNodes) {
            CheckBox wkDayCb = (CheckBox) TaskSchedulerNode.lookup("#" + day + "CheckBox");
            wkDayCb.setSelected(false);
        }

        monthComboBox.getSelectionModel().select(null);

    }

    public void saveSchedule() {
        List<String> errorMessages = new ArrayList<String>();

        Integer selectedTaskId = null;
        String startDate = null;
        String endDate = null;
        String scheduleType = null;
        String selectedDays = null;
        Boolean isActive = true;

        // ----------- Task ----------------
        Object selectedItem = tasksComboBox.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            errorMessages.add("Please select task.");
        }
        else {
            selectedTaskId = ((Task)selectedItem).getTaskId();
        }

        // ----------- Dates ---------------
        LocalDate lclStartDate = this.startDate.getValue();
        if(lclStartDate == null) {
            errorMessages.add("Please select start Date");
        }
        else {
            startDate = lclStartDate.toString();
        }

        LocalDate lclEndDate = this.endDate.getValue();
        if(lclEndDate == null) {
            errorMessages.add("Please select end Date");
        }
        else {
            endDate = lclEndDate.toString();
        }

        // ------------ Type -------------
        List<String> scheduleNodeNames = Arrays.asList("dailyRadioButton", "weeklyRadioButton", "monthlyRadioButton");
        for(String name : scheduleNodeNames) {
            RadioButton rb = (RadioButton) TaskSchedulerNode.lookup("#" + name);
            if( rb.isSelected()) {
                switch (name) {
                    case "dailyRadioButton" : scheduleType = "D"; break;
                    case "weeklyRadioButton" : scheduleType = "W"; break;
                    case "monthlyRadioButton" : scheduleType = "M"; break;
                }
            }

        }

        if(scheduleType == null) {
            errorMessages.add("Please select Schedule Type: Daily or Weekly or Monthly");
        }
        else {

            if(scheduleType.equals("W")) {
                List<String> wkDaysNodes = Arrays.asList("sun", "mon", "tues", "wed", "thurs", "fri", "sat");
                for(String wkday : wkDaysNodes) {
                    CheckBox wkDayCb = (CheckBox) TaskSchedulerNode.lookup("#" + wkday + "CheckBox");
                    if(wkDayCb.isSelected()) {
                        int day = wkDaysNodes.indexOf(wkday) + 1;
                        if(selectedDays == null)
                            selectedDays = day + "";
                        else
                            selectedDays+= ", " + day + "";
                    }


                }

                if(selectedDays == null)
                    errorMessages.add("Please select atleast one day of the week");

            }
            else if (scheduleType.equals("M")) {
                Object monthDaySelObj = monthComboBox.getSelectionModel().getSelectedItem();
                if(monthDaySelObj == null) {
                    errorMessages.add("Please select day of the month");
                }
                else {
                    selectedDays = monthDaySelObj.toString();
                }

            }
        }

        // ------------ Active or not ---------
        if(inactiveCheckbox.isSelected()){
            isActive = false;
        }

        if(errorMessages.size() > 0) {
            AppDialogHelper.showErrorDialog(errorMessages);
        }
        else {
            System.out.println("Call proc");
            try {
                Integer taskScheduleId = currSelTaskSchedule != null ? currSelTaskSchedule.getTaskSchedulerId() : 0 ;
                TaskSchedule returnedTS = tasksDao.saveSchedule(selectedTaskId,df.parse(startDate), df.parse(endDate), scheduleType, selectedDays, isActive, taskScheduleId);
                if(returnedTS == null) {
                    AppDialogHelper.showErrorDialog( Arrays.asList("Unable to save the Task Schedule"));
                }
                else {
                    currSelTaskSchedule = returnedTS;
                    AppDialogHelper.showDialog("Success" , Arrays.asList("Task Scheduled Saved Successfully"));
                }
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
        }

    }

    private void loadTasks() {
        //List<Task> tasks = tasksRepository.getTasks();
        tasks = tasksDao.getTasks();
        tasksDao.getTasks();

    }


    Callback<DatePicker, DateCell> dayCellFactory =
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(
                                    LocalDate.now())
                                    ) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };


}
