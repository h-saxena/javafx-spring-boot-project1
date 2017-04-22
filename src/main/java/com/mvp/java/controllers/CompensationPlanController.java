package com.mvp.java.controllers;

import com.mvp.java.repository.CommonsDao;
import com.mvp.java.repository.CompensationPlanDao;
import com.mvp.java.repository.SalesHierarchyDao;
import com.mvp.java.vo.*;
import com.mvp.java.xo.COMPENSATIONPLANType;
import com.mvp.java.xo.COMPENSATIONRATEType;
import com.mvp.java.xo.PLANRATEType;
import com.mvp.java.xo.TIERType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.*;

@Component
public class CompensationPlanController {

    @FXML
    Pane CompensationPlanNode;

    @FXML
    TextField txtPlanName;
    @FXML
    TextField txtCommissionPer;
    @FXML
    TextField txtRangeMin;
    @FXML
    TextField txtRangeMax;

    @FXML
    Label lblRangeMin;
    @FXML
    Label lblRangeMax;

    @FXML
    ComboBox<CompensationPlan> cbSelectPlan;

    @FXML
    ComboBox<Product> cbProduct;
    @FXML
    ComboBox<ProductType> cbProductType;
    @FXML
    ComboBox<CustomerType> cbCustomerType;
    @FXML
    ComboBox<Role> cbUserRole;


    @FXML
    CheckBox chkCreateNew;
    @FXML
    CheckBox chkPublishPlan;


    @FXML
    RadioButton rdFlat;
    @FXML
    RadioButton rdTiered;

    @FXML
    ListView<CompensationCriteria> lstCompensationCriteria;

    @FXML
    Button btAddFlat;
    @FXML
    Button btAddFarTiered;
    @FXML
    Button btRemove;
    @FXML
    Button btRemoveAll;
    @FXML
    Button btSave;


    @Autowired
    CommonsDao commonsDao;

    @Autowired
    CompensationPlanDao compensationPlanDao;

    boolean isLoadedOnce = false;

    List<CompensationCriteria> compCriteriaList = new ArrayList<>();

    public void loadUI() {
        if(! isLoadedOnce) {
            loadCompPlans();
            cbProduct.setItems(FXCollections.observableArrayList(commonsDao.getProducts()));
            cbProductType.setItems(FXCollections.observableArrayList(commonsDao.getProductTypes()));
            cbUserRole.setItems(FXCollections.observableArrayList(commonsDao.getRoles()));
            cbCustomerType.setItems(FXCollections.observableArrayList(commonsDao.getCustomerTypes()));

            chkCreateNew.setSelected(true);

        }
        isLoadedOnce = true;
    }

    private void loadCompPlans() {
        System.out.println("load Comp Plans ....");
        List<CompensationPlan> plans = compensationPlanDao.getAllCompensationPlans();
        cbSelectPlan.setItems(FXCollections.observableArrayList(plans));
        resetView(true);
    }

    public void existingPlanSelected() {
        resetView(true);

        CompensationPlan selPlan = cbSelectPlan.getSelectionModel().getSelectedItem();
        if(selPlan == null) return;

        txtPlanName.setText(selPlan.getName());

        compCriteriaList.clear();

        List<CompensationCriteria> compCriterias = compensationPlanDao.getCompensationCriteriaBy(selPlan.getPlanId());
        compCriteriaList.addAll(compCriterias);
        lstCompensationCriteria.setItems(FXCollections.observableArrayList(compCriterias));

        if(selPlan.getType().equals("F")) {
            rdFlat.setSelected(true);
            planTypeSelected(false);
        }
        else  {
            rdTiered.setSelected(true);
            planTypeSelected(true);
        }


        for(Product p : cbProduct.getItems()) {
            if(p.getId().equals(selPlan.getProdId())) {
                cbProduct.getSelectionModel().select(p);
                break;
            }
        }

        for(ProductType pt : cbProductType.getItems()) {
            if(pt.getId().equals(selPlan.getProdId())) {
                cbProductType.getSelectionModel().select(pt);
                break;
            }
        }

        chkPublishPlan.setSelected(selPlan.getPublished());
    }

    public void resetView(boolean resetCreateNew) {
        txtPlanName.setText(null);

        txtCommissionPer.setText(null);
        txtRangeMin.setText(null);
        txtRangeMax.setText(null);

        cbProduct.getSelectionModel().select(null);
        cbProductType.getSelectionModel().select(null);
        cbCustomerType.getSelectionModel().select(null);
        cbUserRole.getSelectionModel().select(null);

        if(resetCreateNew)
            chkCreateNew.setSelected(false);

        chkPublishPlan.setSelected(false);

        rdFlat.setSelected(false);
        rdTiered.setSelected(false);

        lstCompensationCriteria.getItems().clear();

        planTypeSelected(false);

        compCriteriaList.clear();


    }

    private void planTypeSelected(boolean tieredSelected) {
        btAddFlat.setVisible(!tieredSelected);
        btAddFarTiered.setVisible(tieredSelected);

        txtRangeMin.setVisible(tieredSelected);
        txtRangeMax.setVisible(tieredSelected);
        lblRangeMin.setVisible(tieredSelected);
        lblRangeMax.setVisible(tieredSelected);

    }

    public void saveCompensationPlan() {
        // TODO ------------------------
        List<String> errorMessages = new ArrayList<String>();

        Integer planId = -1;
        CompensationPlan selPlan = cbSelectPlan.getSelectionModel().getSelectedItem();
        if(selPlan != null && !chkCreateNew.isSelected())
            planId = selPlan.getPlanId();

        String planName = txtPlanName.getText();
        if(StringUtils.isEmpty(planName))
            errorMessages.add("Please enter the Plan Name.");

        String planType = null;
        if(rdFlat.isSelected())
            planType = "F";
        else if(rdTiered.isSelected())
            planType = "T";
        if(planType == null)  errorMessages.add("Please choose Plan Type");

        Product prod = cbProduct.getSelectionModel().getSelectedItem();
        if(prod == null) errorMessages.add("Please select Product");

        ProductType productType = cbProductType.getSelectionModel().getSelectedItem();
        if(productType == null) errorMessages.add("Please choose Product type");

        Boolean isToPublishPlan = chkPublishPlan.isSelected();

        if(lstCompensationCriteria.getItems().size() == 0 && planId == -1)
            errorMessages.add("Please add atleast one Plan Rate to the plan.");

        if(errorMessages.size() > 0) {
            AppDialogHelper.showErrorDialog(errorMessages);
        }
        else {

            String compDataXML = buildCompDataXML(planId, planType);

            // TODO ---------------------
            //if(true) return;

            if(compDataXML != null) {

                boolean success = compensationPlanDao.saveCompensationPlan(planName, planType, prod.getId(), productType.getId(), isToPublishPlan
                        , "Admin", compDataXML, planId);

                if(success) {
                    AppDialogHelper.showDialog("Success !", Arrays.asList("Plan is saved Successfully"));
                    loadCompPlans();
                }
                else
                    AppDialogHelper.showErrorDialog(Arrays.asList("Unable to save Plan details"));
            }
            else {
                AppDialogHelper.showErrorDialog(Arrays.asList("Internal error: unable to convert to xml"));
            }


        }

    }

    public void addPlanCriteria() {
        // TODO ------------------------
        List<String> errorMessages = new ArrayList<String>();

        CustomerType custType = cbCustomerType.getSelectionModel().getSelectedItem();
        if(custType == null ) errorMessages.add("Please select customer type");

        Role userRole = cbUserRole.getSelectionModel().getSelectedItem();
        if(userRole == null ) errorMessages.add("Please select User Role");

        Integer commission = null;
        try { commission = Integer.parseInt(txtCommissionPer.getText()); }
        catch(Exception e) {errorMessages.add("Please enter valid Commission Value");}

        Double rangeMin = null;
        Double rangeMax = null;

        if(rdTiered.isSelected()) {
            try { rangeMin = Double.parseDouble(txtRangeMin.getText()); }
            catch(Exception e) {errorMessages.add("Please enter valid Range Min value");}

            try { rangeMax = Double.parseDouble(txtRangeMax.getText()); }
            catch(Exception e) {errorMessages.add("Please enter valid Range Max value");}
        }

        Integer planId = -1;
        if(cbSelectPlan.getSelectionModel().getSelectedItem() != null && !chkCreateNew.isSelected())
            planId =  cbSelectPlan.getSelectionModel().getSelectedItem().getPlanId();

        if(errorMessages.size() > 0) {
            AppDialogHelper.showErrorDialog(errorMessages);
        } else {

            CompensationCriteria compCriteriaToAdd = new CompensationCriteria(planId, -1, -1, userRole.getId(), userRole.getDesc(),
                    custType.getId(), custType.getDesc(), commission, rangeMin, rangeMax, true);

            compCriteriaList.add(0, compCriteriaToAdd);
            lstCompensationCriteria.getItems().add(0, compCriteriaToAdd);
            // TODO TEMP
            compCriteriaList.size();
        }
    }

    public void removePlanCriteria() {
        CompensationCriteria item = lstCompensationCriteria.getSelectionModel().getSelectedItem();
        if(item == null) {
            AppDialogHelper.showErrorDialog(Arrays.asList("Please Select a compensation Criteria to remove."));
        }
        else {
            item.setActive(false);
            lstCompensationCriteria.getItems().remove(item);
            if(item.getPlanRateId() == -1) { // which mean its newly created one
                compCriteriaList.remove(item);
            }
        }
        // TODO TEMP
        compCriteriaList.size();
    }

    public void removeAllPlanCriteria() {
        List<String> errorMessages = new ArrayList<String>();
        lstCompensationCriteria.getItems().clear();

        List<CompensationCriteria> toRemove = new ArrayList<>();
        for(CompensationCriteria c : compCriteriaList) {
            c.setActive(false);
            if(c.getPlanRateId() == -1)
                toRemove.add(c);
        }
        compCriteriaList.removeAll(toRemove);

        // TODO TEMP
        compCriteriaList.size();
    }

    public void flatOptionSelected() {
        // TODO ------------------------
        List<String> errorMessages = new ArrayList<String>();
        planTypeSelected(false);
    }

    public void tieredOptionSelected() {
        // TODO ------------------------
        List<String> errorMessages = new ArrayList<String>();
        planTypeSelected(true);

    } // createNewPlan

    public void createNewPlan() {
        resetView(false);
    }

    private String buildCompDataXML(Integer planId, String planType) {
        String xmlString = null;

        Map<Integer, COMPENSATIONRATEType> mapCustTypeIdVsCompRateObj = new HashMap<>();


        COMPENSATIONPLANType compPlan = new COMPENSATIONPLANType();
        compPlan.setCOMPENSATIONPLANID(planId);

        for (CompensationCriteria c : compCriteriaList) {
            COMPENSATIONRATEType compRate = mapCustTypeIdVsCompRateObj.get(c.getCustTypeId());
            if(compRate == null) {
                compRate = new COMPENSATIONRATEType();
                compRate.setCUSTOMERTYPEID(c.getCustTypeId());

                compPlan.getCOMPENSATIONRATE().add(compRate);
                mapCustTypeIdVsCompRateObj.put(c.getCustTypeId(), compRate);

            }

            PLANRATEType planRate = new PLANRATEType();
            planRate.setCOMPENSATIONRATEID(c.getPlanRateId());
            planRate.setCOMMISSIONRATE(c.getCommRate());
            planRate.setCOMPENSATIONRATEACTIVE(c.isActive()? 1 :0);
            planRate.setROLEID(c.getRoleId());

            if(planType.equals("T") && (c.getTieredRateId() != null)) {
                TIERType tierRate = new TIERType();
                try {
                    tierRate.setCOMPENSATIONTIEREDRATEID(c.getTieredRateId() );
                    tierRate.setCOMPENSATIONRANGEMIN(c.getRangeMin());
                    tierRate.setCOMPENSATIONRANGEMAX(c.getRangeMax());
                    planRate.setTIER(tierRate);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            compRate.getPLANRATE().add(planRate);
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(COMPENSATIONPLANType.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter result = new StringWriter();
            jaxbMarshaller.marshal(compPlan, result);
            xmlString = result.toString();
            xmlString = xmlString.substring(xmlString.indexOf("<COMPENSATION_PLAN"), xmlString.length());

        } catch (JAXBException e) {
            System.out.println("Unable to create xml: " + e.getMessage());
        }


        return xmlString;
    }


}
