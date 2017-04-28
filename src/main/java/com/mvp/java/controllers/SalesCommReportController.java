package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.repository.CommonsDao;
import com.mvp.java.repository.ManagerCommissionReportDao;
import com.mvp.java.vo.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SalesCommReportController {

    @FXML
    Label lblNotAuthorized;

    @FXML
    ComboBox<Region> cbRegion;
    @FXML
    ComboBox<CompensationJob> cbCompPeriod;

    // TODO update the types <>
    @FXML
    TableView<ManagerReportsData> tbManagerData;
    @FXML
    TableColumn tcSalesManager;
    @FXML
    TableColumn tcSalesQuota;
    @FXML
    TableColumn tcSalesAmount;
    @FXML
    TableColumn tcCommAmount;



    @FXML
    TableView<ManagerReportDetailsData> tbSalesPersonData;
    @FXML
    TableColumn tcSubSalesRep;
    @FXML
    TableColumn tcSubOrderNum;
    @FXML
    TableColumn tcSubCustomer;
    @FXML
    TableColumn tcSubSalesAmount;
    @FXML
    TableColumn tcSubCommAmount;


    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;
    @Autowired
    CommonsDao commonsDao;
    @Autowired
    ManagerCommissionReportDao commissionReportDao;

    SalesPerson selectedUser;
    Map<Integer, List<ManagerReportDetailsData>> mapMgrIdVsDetailsDataList = new HashMap<>();

    public void initialize() {
        tcSalesManager.setCellValueFactory(new PropertyValueFactory("mgrName"));
        tcSalesQuota.setCellValueFactory(new PropertyValueFactory("salesQuotaAmount"));
        tcSalesAmount.setCellValueFactory(new PropertyValueFactory("salesAmount"));
        tcCommAmount.setCellValueFactory(new PropertyValueFactory("commAmount"));

        tcSubSalesRep.setCellValueFactory(new PropertyValueFactory("salesPersonName"));
        tcSubOrderNum.setCellValueFactory(new PropertyValueFactory("orderNum"));
        tcSubCustomer.setCellValueFactory(new PropertyValueFactory("customerName"));
        tcSubSalesAmount.setCellValueFactory(new PropertyValueFactory("salesAmount"));
        tcSubCommAmount.setCellValueFactory(new PropertyValueFactory("commAmount"));

        tbManagerData.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                List<ManagerReportDetailsData> detailsDatas = mapMgrIdVsDetailsDataList.get(newSelection.getMgrId());
                if(detailsDatas != null)
                    tbSalesPersonData.setItems(FXCollections.observableArrayList(detailsDatas));
                else
                    tbSalesPersonData.setItems(null);
            }
        });
    }

    public void loadUI() {
        cbCompPeriod.setItems(FXCollections.observableArrayList(approveRejectCommReportDao.getSalesReports()));
        cbRegion.setItems(FXCollections.observableArrayList(commonsDao.getRegions()));
        loadData(true);

    }

    public void loggedInUserChanged(SalesPerson salesPerson) {
        selectedUser = salesPerson;
        loadData();

    }

    public void salesPeriodSelected() {
        loadData();
    }

    public void regionSelected() {
        loadData();
    }


    private void loadData() {
       loadData(false);
    }
    private void loadData(boolean justResetView) {
        resetView();
        toggleSelectionBoxesDisability(true);
        if(selectedUser != null && selectedUser.getrId() == 1) { // only admin can see the report
            toggleSelectionBoxesDisability(false);

            if(justResetView) return;

            CompensationJob selPeriod = cbCompPeriod.getSelectionModel().getSelectedItem();
            Region selRegion = cbRegion.getSelectionModel().getSelectedItem();

            if(selPeriod == null || selRegion == null)
                return;

            tbManagerData.setItems(FXCollections.observableArrayList(commissionReportDao.getManagerReports(selPeriod.getId(), selRegion.getId())));

            tbSalesPersonData.setItems(null);
            hashOutManagerIdVsManagerDetailsData(commissionReportDao.getManagerReportDetails(selPeriod.getId(), selRegion.getId()));
        }


    }

    private void hashOutManagerIdVsManagerDetailsData(List<ManagerReportDetailsData> managerReportDetailsDataList) {
        mapMgrIdVsDetailsDataList.clear();
        managerReportDetailsDataList.stream().forEach( d -> {
            List<ManagerReportDetailsData> detailsDatas = mapMgrIdVsDetailsDataList.get(d.getMgrId());
            if(detailsDatas == null) {
                detailsDatas = new ArrayList<>();
                mapMgrIdVsDetailsDataList.put(d.getMgrId(), detailsDatas);
            }
            detailsDatas.add(d);
        });
    }

    private void toggleSelectionBoxesDisability(boolean isDisabled) {
        lblNotAuthorized.setVisible(isDisabled?true:false);
        cbRegion.setDisable(isDisabled);
        cbCompPeriod.setDisable(isDisabled);
    }



    private void resetView() {
        mapMgrIdVsDetailsDataList.clear();

        tbManagerData.setItems(null);
        tbSalesPersonData.setItems(null);

    }

}
