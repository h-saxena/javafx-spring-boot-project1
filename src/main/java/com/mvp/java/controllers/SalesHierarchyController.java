package com.mvp.java.controllers;

import com.mvp.java.helpers.CurrencyFormatter;
import com.mvp.java.repository.CommonsDao;
import com.mvp.java.repository.SalesHierarchyDao;
import com.mvp.java.vo.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SalesHierarchyController {

    @FXML
    Pane SalesHierarchyNode;

    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtBasicSalary;
    @FXML
    TextField txtTotalTargetComp;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtSaleQuota;

    @FXML
    ComboBox cbSelectAgent;

    @FXML
    ComboBox cbDesignation;
    @FXML
    ComboBox cbRegion;
    @FXML
    ComboBox cbRole;
    @FXML
    ComboBox cbPayoutFreq;
    @FXML
    ComboBox cdReportingMgr;


    @FXML
    CheckBox chkCreateNew;
    @FXML
    CheckBox chkEligibleForCompPlan;
    @FXML
    CheckBox chkInActive;

    @Autowired
    CommonsDao commonsDao;

    @Autowired
    SalesHierarchyDao salesHierarchyDao;

    boolean isLoadedOnce = false;

    public void initialize() {
        txtBasicSalary.setTextFormatter( new CurrencyFormatter());
        txtTotalTargetComp.setTextFormatter( new CurrencyFormatter());
        txtSaleQuota.setTextFormatter( new CurrencyFormatter());
    }

        public void loadUI() {
        if(! isLoadedOnce) {
            cbSelectAgent.setItems(FXCollections.observableArrayList(salesHierarchyDao.getSalesPersons()));

            cbDesignation.setItems(FXCollections.observableArrayList(commonsDao.getDesignations()));
            cbRegion.setItems(FXCollections.observableArrayList(commonsDao.getRegions()));
            cbRole.setItems(FXCollections.observableArrayList(commonsDao.getRoles()));
            cbPayoutFreq.setItems(FXCollections.observableArrayList(commonsDao.getPayoutFrequencies()));
            cdReportingMgr.setItems(FXCollections.observableArrayList(commonsDao.getReportingManagers()));


        }
        isLoadedOnce = true;
    }

    private void loadSalesPerson() {
        cbSelectAgent.setItems(FXCollections.observableArrayList(salesHierarchyDao.getNonCachedSalesPersons()));

    }

    public void existingSalePersonSelected() {
        resetView();

        SalesPerson sp = (SalesPerson) cbSelectAgent.getSelectionModel().getSelectedItem();
        if(sp == null)
            return;

        txtFirstName.setText(sp.getFname());
        txtBasicSalary.setText(sp.getbSalary().toString());
        txtTotalTargetComp.setText(sp.getTargetComp().toString());
        txtLastName.setText(sp.getLname());
        txtSaleQuota.setText(sp.getSalesQuota().toString());

        {
            Integer desId = sp.getDesId();
            if(desId != null) {
                List<Designation> designations = cbDesignation.getItems();
                for(Designation d : designations) {
                    if(d.getId().equals(desId)) {
                        cbDesignation.getSelectionModel().select(d);
                        break;
                    }
                }
            }

        }

        {
            Integer id = sp.getRegionId();
            if(id != null) {
                List<Region> list = cbRegion.getItems();
                for(Region item : list) {
                    if(item.getId().equals(id)) {
                        cbRegion.getSelectionModel().select(item);
                        break;
                    }
                }
            }
        }

        {
            Integer id = sp.getrId();
            if(id != null) {
                List<Role> list = cbRole.getItems();
                for(Role item : list) {
                    if(item.getId().equals(id)) {
                        cbRole.getSelectionModel().select(item);
                        break;
                    }
                }
            }
        }

        {
            Integer id = sp.getPayFreqId();
            if(id != null) {
                List<PayoutFrequency> list = cbPayoutFreq.getItems();
                for(PayoutFrequency item : list) {
                    if(item.getId().equals(id)) {
                        cbPayoutFreq.getSelectionModel().select(item);
                        break;
                    }
                }
            }
        }

        {
            Integer id = sp.getReportMgrId();
            if(id != null) {
                List<ReportingManager> list = cdReportingMgr.getItems();
                for(ReportingManager item : list) {
                    if(item.getId().equals(id)) {
                        cdReportingMgr.getSelectionModel().select(item);
                        break;
                    }
                }
            }
        }

        chkEligibleForCompPlan.setSelected(sp.getPlanEligible());
        chkInActive.setSelected(!sp.getActive());

    }

    public void resetView() {
        txtFirstName.setText(null);
        txtBasicSalary.setText(null);
        txtTotalTargetComp.setText(null);
        txtLastName.setText(null);
        txtSaleQuota.setText(null);

        cbDesignation.getSelectionModel().select(null);
        cbRegion.getSelectionModel().select(null);
        cbRole.getSelectionModel().select(null);
        cbPayoutFreq.getSelectionModel().select(null);
        cdReportingMgr.getSelectionModel().select(null);

        chkCreateNew.setSelected(false);
        chkEligibleForCompPlan.setSelected(false);
        chkInActive.setSelected(false);
    }

    public void saveSaleHierarchy() {
        List<String> errorMessages = new ArrayList<String>();

        Integer selSaleHierarchyId = 0;
        { Object selItem = cbSelectAgent.getSelectionModel().getSelectedItem();
        if(selItem != null) selSaleHierarchyId = ((SalesPerson)selItem).getSaleHierarchyId();
        }

        String fname = txtFirstName.getText();
        if(StringUtils.isEmpty(fname))
            errorMessages.add("Please enter the first name.");

        String lname = txtLastName.getText();
        if(StringUtils.isEmpty(lname))
            errorMessages.add("Please enter the last name.");

        Double basicSal = null;
        try { basicSal = Double.parseDouble(txtBasicSalary.getText()); }
        catch(Exception e) {errorMessages.add("Please enter valid Basic Salary amount");}

        Double totTarComp = null;
        try { totTarComp = Double.parseDouble(txtTotalTargetComp.getText()); }
        catch(Exception e) {errorMessages.add("Please enter valid Target Compensation Amount");}

        Double saleQuota = null;
        try { saleQuota = Double.parseDouble(txtSaleQuota.getText()); }
        catch(Exception e) {errorMessages.add("Please enter valid Sale Quota Amount");}

        Integer desId = null;
        { Object selItem = cbDesignation.getSelectionModel().getSelectedItem();
            if(selItem != null) desId = ((Designation)selItem).getId();
            else  errorMessages.add("Please select Designation");
        }

        Integer regId = null;
        { Object selItem = cbRegion.getSelectionModel().getSelectedItem();
            if(selItem != null) regId = ((Region)selItem).getId();
            else  errorMessages.add("Please select Region");
        }

        Integer roleId = null;
        { Object selItem = cbRole.getSelectionModel().getSelectedItem();
            if(selItem != null) roleId = ((Role)selItem).getId();
            else  errorMessages.add("Please select Role");
        }

        Integer payFreqId = null;
        { Object selItem = cbPayoutFreq.getSelectionModel().getSelectedItem();
            if(selItem != null) payFreqId = ((PayoutFrequency)selItem).getId();
            else  errorMessages.add("Please select Payout Frequency");
        }

        Integer repMgrId = null;
        { Object selItem = cdReportingMgr.getSelectionModel().getSelectedItem();
            if(selItem != null) repMgrId = ((ReportingManager)selItem).getId();
            else  errorMessages.add("Please select Reporting Manager");
        }

        Boolean isCompPlanEligible = chkEligibleForCompPlan.isSelected();
        Boolean isActive = !chkInActive.isSelected();

        if(errorMessages.size() > 0) {
            AppDialogHelper.showErrorDialog(errorMessages);
        }
        else {

             boolean success = salesHierarchyDao.saveSaleHierarchy(fname,lname,desId,roleId,basicSal,payFreqId
                                            ,regId,repMgrId,isCompPlanEligible,saleQuota ,totTarComp, isActive
                                        , "Admin", chkCreateNew.isSelected() ? 0 : selSaleHierarchyId);
            if(success) {
                AppDialogHelper.showDialog("Success !", Arrays.asList("Sale Hierarchy saved Successfully"));
                loadSalesPerson();
            }
            else
                AppDialogHelper.showErrorDialog(Arrays.asList("Unable to save Sale Hierarchy."));
        }
    }
}
