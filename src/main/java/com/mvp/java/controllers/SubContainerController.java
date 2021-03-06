package com.mvp.java.controllers;

import com.mvp.java.repository.SalesHierarchyDao;
import com.mvp.java.vo.SalesPerson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SubContainerController {

    @FXML
    StackPane stackPaneContainer;

    @FXML
    ComboBox<SalesPerson> cbUsers;

    @FXML
    ScrollPane contentScrollPaneContainer;


    @Autowired
    TaskSchedulerController taskSchedulerController;

    @Autowired
    SalesHierarchyController salesHierarchyController;

    @Autowired
    CompensationPlanController compensationPlanController;

    @Autowired
    ApproveRejectCommReportController approveRejectCommReportController;

    @Autowired
    RunSchedulerController runSchedulerController;

    @Autowired
    PayoutCommController payoutCommController;

    @Autowired
    SalesDashboardController salesDashboardController;

    @Autowired
    SalesCommReportController salesCommReportController;

    @Autowired
    SalesHierarchyDao salesHierarchyDao;


    String currSelectedSubViewId = null;

    public void initialize() {
        hideAllSubViews();
        makeViewVisible("Demo");
        reloadUsers();
        //userSelectionChanged();
    }

    public void reloadUsers() {
        cbUsers.setItems(FXCollections.observableArrayList(salesHierarchyDao.getNonCachedSalesPersons()));
        cbUsers.getSelectionModel().selectFirst();
    }


    public void menuItemClicked(ActionEvent event) throws IOException {
        Hyperlink link = (Hyperlink)event.getSource();
        String subViewId =  link.getId();

        makeViewVisible(subViewId);
        //FXMLLoader loader = (FXMLLoader)nextNode.getScene().getUserData();


    }

    public void userSelectionChanged() {
        if(cbUsers.getSelectionModel().getSelectedItem() != null) {
            salesDashboardController.loggedInUserChanged(cbUsers.getSelectionModel().getSelectedItem());
            salesCommReportController.loggedInUserChanged(cbUsers.getSelectionModel().getSelectedItem());
        }
    }

    public SalesPerson getLoggedInUser() {
        return cbUsers.getSelectionModel().getSelectedItem();
    }

    private void makeViewVisible(String subViewId) {
        if(currSelectedSubViewId != null) {
            Node currNode = getNodeFor(currSelectedSubViewId);
            currNode.setVisible(false);
            currNode.toBack();

        }
        Node nextNode = getNodeFor(subViewId);
        nextNode.setVisible(true);
        nextNode.toFront();
        currSelectedSubViewId = subViewId;

        switch (currSelectedSubViewId) {
            case "TaskScheduler" : taskSchedulerController.loadUI(); break;
            case "SalesHierarchy" : salesHierarchyController.loadUI(); break;
            case "CompensationPlan" : compensationPlanController.loadUI(); break;
            case "ApproveRejectCommReport" : approveRejectCommReportController.loadUI(); break;
            case "RunScheduler" : runSchedulerController.loadUI(); break;
            case "PayoutComm" : payoutCommController.loadUI(); break;
            case "SalesDashboard" : salesDashboardController.loadUI(); break;
            case "SalesCommReport" : salesCommReportController.loadUI(); break;

        }

    }

    private void hideAllSubViews () {
        ObservableList<Node> children = stackPaneContainer.getChildren();

        children.forEach(node -> {
            node.setVisible(false);
            //node.toBack();

        });

    }

    private Node getNodeFor(String subViewId) {
        ObservableList<Node> children = stackPaneContainer.getChildren();
        Node nextNode = null;

        for(Node node : children) {
            if(node.getId().equals(subViewId + "Node"))
                nextNode = node;

        }

        return nextNode;
    }
}
