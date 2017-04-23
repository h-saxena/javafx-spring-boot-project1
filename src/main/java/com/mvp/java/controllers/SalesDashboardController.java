package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.SalesPerson;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesDashboardController {

    @FXML
    TextField txtSalesRep;

    @FXML
    ComboBox<CompensationJob> cbSalesPeriod;

    @Autowired
    ApproveRejectCommReportDao approveRejectCommReportDao;

    public void loadUI() {
        cbSalesPeriod.setItems(FXCollections.observableArrayList(approveRejectCommReportDao.getSalesReports()));
    }

    public void salesPeriodSelected() {

    }

    public void loggedInUserChanged(SalesPerson selUser) {
        txtSalesRep.setText(selUser.getFname() + " " + selUser.getLname());
    }
}
