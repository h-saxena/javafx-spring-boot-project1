package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.repository.DashboardReportDao;
import com.mvp.java.vo.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesDashboardController {

    @FXML
    Pane SalesDashboardNode;

    @FXML
    TextField txtSalesRep;

    @FXML
    ComboBox<CompensationJob> cbSalesPeriod;

    @FXML
    TableView<SalesPersonCompensationData> tbSalesPersonCompData;
    @FXML
    TableView<SalesPerformanceQuarterlyData>  tbPerfQuarterly;
    @FXML
    TableView<SalesPerformanceSummaryData> tbPerfSummary;


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
    TableColumn tcSummary;
    @FXML
    TableColumn tcTotalSales;
    @FXML
    TableColumn tcTotalCompensation;


    @FXML
    TableColumn tcQuarter;
    @FXML
    TableColumn tcTarget;
    @FXML
    TableColumn tcAchieved;





    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;

    @Autowired
    DashboardReportDao dashboardReportDao;

    SalesPerson selSalesPerson;

    public void initialize() {
        tcOrderNum.setCellValueFactory(new PropertyValueFactory("orderNumber"));
        tcCustomer.setCellValueFactory(new PropertyValueFactory("customerName"));
        tcSalesAmount.setCellValueFactory(new PropertyValueFactory("salesAmount"));
        tcRate.setCellValueFactory(new PropertyValueFactory("compRate"));
        tcGrossComm.setCellValueFactory(new PropertyValueFactory("commAmount"));
        tcStatus.setCellValueFactory(new PropertyValueFactory("paidStatus"));

        tcSummary.setCellValueFactory(new PropertyValueFactory("summary"));
        tcTotalSales.setCellValueFactory(new PropertyValueFactory("totalSalesAmount"));
        tcTotalCompensation.setCellValueFactory(new PropertyValueFactory("totalCommissionAmount"));

        tcQuarter.setCellValueFactory(new PropertyValueFactory("quarter"));
        tcTarget.setCellValueFactory(new PropertyValueFactory("quota"));
        tcAchieved.setCellValueFactory(new PropertyValueFactory("sales"));

    }

    public void loadUI() {
        resetView();
        cbSalesPeriod.setItems(FXCollections.observableArrayList(dashboardReportDao.getSalesReportsDashboard()));
        //loadViewData();
    }

    public void salesPeriodSelected() {
        resetView();

        CompensationJob selCompJob = cbSalesPeriod.getSelectionModel().getSelectedItem();
        if(selCompJob == null)
            return;

        loadViewData();

    }

    public void loggedInUserChanged(SalesPerson salesPerson) {
        selSalesPerson = salesPerson;
        txtSalesRep.setText(salesPerson.getFname() + " " + salesPerson.getLname());

        loadViewData();
    }

    private void loadViewData() {
        if(SalesDashboardNode.isVisible()) {
            List<SalesPersonCompensationData> salesPersonCompensationDataList = dashboardReportDao.getSalesPersonPayoutReportData(cbSalesPeriod.getSelectionModel().getSelectedItem().getId(),
                    selSalesPerson.getSaleHierarchyId());

            List<SalesPerformanceSummaryData> salesPerformanceSummaryDataList = dashboardReportDao.getSalesPerformanceSummaryData(cbSalesPeriod.getSelectionModel().getSelectedItem().getId(),
                    selSalesPerson.getSaleHierarchyId());

            List<SalesPerformanceQuarterlyData> salesPerformanceQuarterlyDataList = dashboardReportDao.getSalesPerformanceQuarterlyData(cbSalesPeriod.getSelectionModel().getSelectedItem().getId(),
                    selSalesPerson.getSaleHierarchyId());

            tbSalesPersonCompData.setItems(FXCollections.observableArrayList(salesPersonCompensationDataList));
            tbPerfSummary.setItems(FXCollections.observableArrayList(salesPerformanceSummaryDataList));
            tbPerfQuarterly.setItems(FXCollections.observableArrayList(salesPerformanceQuarterlyDataList));

        }
    }


    private void resetView() {
        tbSalesPersonCompData.setItems(null);
        tbPerfQuarterly.setItems(null);
        tbPerfSummary.setItems(null);
    }
}
