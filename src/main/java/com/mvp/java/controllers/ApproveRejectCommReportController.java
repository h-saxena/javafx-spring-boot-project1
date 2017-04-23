package com.mvp.java.controllers;

import com.mvp.java.repository.ApproveRejectCommReportDao;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.CompensationJobData;
import com.mvp.java.xo.COMPENSATIONSTATUSType;
import com.mvp.java.xo.UPDATESTATUSType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Arrays;
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

        //tcStatus.setCellValueFactory(new PropertyValueFactory("approvalStatus"));

        tcStatus.setCellFactory(CheckBoxTableCell.forTableColumn(tcStatus));
        tcStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompensationJobData, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CompensationJobData, Boolean> param) {
                CompensationJobData jobData = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(jobData.getApprovalStatus());
                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        jobData.setApprovalStatus(newValue);
                    }
                });
                return booleanProp;
            }
        });

        tcStatus.setEditable(true);
        tbCommData.setEditable(true);
    }
    public void loadUI() {
        cbSalesReport.setItems(FXCollections.observableArrayList(approveRejectCommReportDao.getSalesReports()));
    }

    public void salesReportSelected(){
        resetView();
        CompensationJob selectReport = cbSalesReport.getSelectionModel().getSelectedItem();
        if(selectReport == null)
          return;

        txtDateCreated.setText(selectReport.getCreateDate());
        txtForPeriod.setText(selectReport.getForPeriod());
        taRemarks.setText(selectReport.getRemarks());

        List<CompensationJobData> reportDataList = approveRejectCommReportDao.getSalesReportData(selectReport.getId());
        ObservableList<CompensationJobData> obList =  FXCollections.observableArrayList(reportDataList);
        tbCommData.setItems(obList);


    }

    public void saveApprovalRejection() {
        UPDATESTATUSType updateStatus = new UPDATESTATUSType();
        updateStatus.setCOMPENSATIONJOBID(cbSalesReport.getSelectionModel().getSelectedItem().getId());

        String xmlString = null;

        tbCommData.getItems().stream().forEach( compData -> {
            COMPENSATIONSTATUSType compStatus = new COMPENSATIONSTATUSType();
            compStatus.setCOMPENSATIONJOBDATAID(compData.getId());
            compStatus.setCOMPENSATIONJOBDATASTATUS(compData.getApprovalStatus() ? 1 : 0);
            updateStatus.getCOMPENSATIONSTATUS().add(compStatus);
        });

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UPDATESTATUSType.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter result = new StringWriter();
            jaxbMarshaller.marshal(updateStatus, result);
            xmlString = result.toString();
            xmlString = xmlString.substring(xmlString.indexOf("<UPDATE_STATUS"), xmlString.length());

        } catch (JAXBException e) {
            System.out.println("Unable to create xml: " + e.getMessage());
        }

        if(xmlString == null)
            AppDialogHelper.showErrorDialog(Arrays.asList("System internal error."));
        else {

            boolean success = approveRejectCommReportDao.saveCompensationApprovalRejectionStatus(cbSalesReport.getSelectionModel().getSelectedItem().getId(),
                    xmlString, taRemarks.getText());

            if(success) {
                AppDialogHelper.showDialog("Success !", Arrays.asList("Approve / Rejection statuses are saved Successfully"));
                resetView();
                loadUI();
            }
            else
                AppDialogHelper.showErrorDialog(Arrays.asList("Unable to save the Approve / Rejection statuses"));

        }


    }

    private void resetView() {
        txtDateCreated.setText(null);
        txtForPeriod.setText(null);

        tbCommData.getItems().clear();
        taRemarks.setText(null);

    }


}
