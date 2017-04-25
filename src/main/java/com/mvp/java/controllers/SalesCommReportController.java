package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.repository.CommonsDao;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.Region;
import com.mvp.java.vo.SalesPerson;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    TableView tbManagerData;
    @FXML
    TableView tbSalesPersonData;


    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;
    @Autowired
    CommonsDao commonsDao;

    SalesPerson selectedUser;

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
        if(selectedUser.getSaleHierarchyId() == 0) {
            toggleSelectionBoxesDisability(false);

            if(justResetView) return;

            // check region not selected
            // check period is not selected

            //TODO load data

        }


    }

    private void toggleSelectionBoxesDisability(boolean isDisabled) {
        lblNotAuthorized.setVisible(isDisabled?true:false);
        cbRegion.setDisable(isDisabled);
        cbCompPeriod.setDisable(isDisabled);
    }



    private void resetView() {
        cbCompPeriod.getSelectionModel().select(null);
        cbRegion.getSelectionModel().select(null);

        tbManagerData.setItems(null);
        tbSalesPersonData.setItems(null);

    }

}
