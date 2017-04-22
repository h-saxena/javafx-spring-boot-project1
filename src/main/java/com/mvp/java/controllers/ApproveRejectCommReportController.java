package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApproveRejectCommReportController {

    @FXML
    ComboBox<CompensationJob> cbSalesReport;


    @FXML
    TextField txtDateCreated;
    @FXML
    TextField txtForPeriod;

    @FXML
    TableView<CompensationJobData> tbCommData;


    @FXML
    TableColumn tcSalesRep;
    @FXML
    TableColumn tcOrderNum;
    @FXML
    TableColumn tcCustomer;
    @FXML
    TableColumn tcSalesAmount;
    @FXML
    TableColumn tcRate;
    @FXML
    TableColumn tcGrossComm;
    @FXML
    TableColumn tcStatus;


    @FXML
    TextArea taRemarks;

    @FXML
    Button btSave;

    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;

    public void initialize() {
        tcSalesRep.setCellValueFactory(new PropertyValueFactory("salesPersonName"));
        tcOrderNum.setCellValueFactory(new PropertyValueFactory("orderNumber"));
        tcCustomer.setCellValueFactory(new PropertyValueFactory("customerName"));
        tcSalesAmount.setCellValueFactory(new PropertyValueFactory("salesAmount"));
        tcRate.setCellValueFactory(new PropertyValueFactory("compRate"));
        tcGrossComm.setCellValueFactory(new PropertyValueFactory("commAmount"));

        tcStatus.setCellValueFactory(new PropertyValueFactory("approvalStatus"));
        tcStatus.setCellFactory(CheckBoxTableCell.forTableColumn(tcStatus));
        tcStatus.setEditable(true);
        tbCommData.setEditable(true);
    }
    public void loadUI() {
        cbSalesReport.setItems(FXCollections.observableArrayList(approveRejectCommReportDao.getSalesReports()));
    }

    public void salesReportSelected(){
        CompensationJob selectReport = cbSalesReport.getSelectionModel().getSelectedItem();
        if(selectReport == null)
          return;

        txtDateCreated.setText(selectReport.getCreateDate());
        txtForPeriod.setText(selectReport.getForPeriod());

        List<CompensationJobData> reportDataList = approveRejectCommReportDao.getSalesReportData(selectReport.getId());
        tbCommData.setItems(FXCollections.observableArrayList(reportDataList));


    }

    public void saveApprovalRejection() {
    }


}
