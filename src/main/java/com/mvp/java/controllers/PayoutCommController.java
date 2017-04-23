package com.mvp.java.controllers;

import com.mvp.java.repository.PayoutCompensationReportDao;
import com.mvp.java.vo.CompensationJob;
import com.mvp.java.vo.PayoutCompensationData;
import com.mvp.java.xo.PAYOUTCOMPENSATIONSTATUSType;
import com.mvp.java.xo.PAYOUTUPDATESTATUSType;
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
public class PayoutCommController {
    @FXML
    ComboBox<CompensationJob> cbSalesReport;


    @FXML
    TextField txtDateCreated;
    @FXML
    TextField txtForPeriod;

    @FXML
    TableView<PayoutCompensationData> tbCommData;


    @FXML
    TableColumn tcPlanName;
    @FXML
    TableColumn tcSalesRep;
    @FXML
    TableColumn tcCommAmount;
    @FXML
    TableColumn tcApproved;
    @FXML
    TableColumn tcDueDiligence;
    @FXML
    TableColumn tcPaid;

    @FXML
    Button btSave;

    @Autowired
    PayoutCompensationReportDao payoutCompensationReportDao;

    public void initialize() {
        tcPlanName.setCellValueFactory(new PropertyValueFactory("planName"));
        tcSalesRep.setCellValueFactory(new PropertyValueFactory("salesPersonName"));
        tcCommAmount.setCellValueFactory(new PropertyValueFactory("commAmount"));

        tcApproved.setCellFactory(CheckBoxTableCell.forTableColumn(tcApproved));
        tcApproved.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PayoutCompensationData, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PayoutCompensationData, Boolean> param) {
                PayoutCompensationData jobData = param.getValue();
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
        tcApproved.setEditable(false);


        tcDueDiligence.setCellFactory(CheckBoxTableCell.forTableColumn(tcDueDiligence));
        tcDueDiligence.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PayoutCompensationData, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PayoutCompensationData, Boolean> param) {
                PayoutCompensationData jobData = param.getValue();
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(jobData.getDueDiligence());
                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        jobData.setDueDiligence(newValue);
                    }
                });
                return booleanProp;
            }
        });
        tcDueDiligence.setEditable(true);

        tcPaid.setCellValueFactory(new PropertyValueFactory("paid"));
        tcPaid.setCellFactory(CheckBoxTableCell.forTableColumn(tcPaid));
        tcPaid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PayoutCompensationData, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PayoutCompensationData, Boolean> param) {
                PayoutCompensationData jobData = param.getValue();
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(jobData.getPaid());
                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        jobData.setPaid(newValue);
                    }
                });
                return booleanProp;
            }
        });
        tcPaid.setEditable(true);


        tbCommData.setEditable(true);
    }

    public void loadUI() {
       cbSalesReport.setItems(FXCollections.observableArrayList(payoutCompensationReportDao.getSalesReports()));
    }

    public void salesReportSelected(){
        resetView();
        CompensationJob selectReport = cbSalesReport.getSelectionModel().getSelectedItem();
        if(selectReport == null)
            return;

        txtDateCreated.setText(selectReport.getCreateDate());
        txtForPeriod.setText(selectReport.getForPeriod());

        List<PayoutCompensationData> reportDataList = payoutCompensationReportDao.getSalesPayoutReportData(selectReport.getId());
        ObservableList<PayoutCompensationData> obList =  FXCollections.observableArrayList(reportDataList);
        tbCommData.setItems(obList);


    }

    public void saveApprovalRejection() {
        PAYOUTUPDATESTATUSType updateStatus = new PAYOUTUPDATESTATUSType();
        updateStatus.setCOMPENSATIONJOBID(cbSalesReport.getSelectionModel().getSelectedItem().getId());

        String xmlString = null;

        tbCommData.getItems().stream().forEach( compData -> {
            PAYOUTCOMPENSATIONSTATUSType compStatus = new PAYOUTCOMPENSATIONSTATUSType();
            compStatus.setCOMPENSATIONJOBDATAID(compData.getId());
            compStatus.setCOMPENSATIONJOBDATASTATUS(compData.getApprovalStatus() ? 1 : 0);
            compStatus.setCompensationjobdataduediligence(compData.getDueDiligence() ? 1 : 0);
            compStatus.setCompensationjobdatapaid(compData.getPaid() ? 1 : 0);

            updateStatus.getCOMPENSATIONSTATUS().add(compStatus);
        });

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PAYOUTUPDATESTATUSType.class);
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

            boolean success = payoutCompensationReportDao.savePayoutCompensationReport(cbSalesReport.getSelectionModel().getSelectedItem().getId(),
                    xmlString);

            if(success) {
                AppDialogHelper.showDialog("Success !", Arrays.asList("Payout Report is saved Successfully"));
                resetView();
                loadUI();
            }
            else
                AppDialogHelper.showErrorDialog(Arrays.asList("Unable to save the Payout Report"));

        }


    }


    private void resetView() {
        txtDateCreated.setText(null);
        txtForPeriod.setText(null);

        tbCommData.getItems().clear();
    }


}
