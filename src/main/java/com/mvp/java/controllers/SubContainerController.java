package com.mvp.java.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SubContainerController {

    @Autowired
    TaskSchedulerController taskSchedulerController;

    @Autowired
    SalesHierarchyController salesHierarchyController;

    @Autowired
    CompensationPlanController compensationPlanController;

    @Autowired
    ApproveRejectCommReportController approveRejectCommReportController;

    @FXML
    ScrollPane contentScrollPaneContainer;

    @FXML
    StackPane stackPaneContainer;

    String currSelectedSubViewId = null;

    public void initialize() {
        hideAllSubViews();
        makeViewVisible("Demo");
    }


    public void menuItemClicked(ActionEvent event) throws IOException {
        Hyperlink link = (Hyperlink)event.getSource();
        String subViewId =  link.getId();

        makeViewVisible(subViewId);
        //FXMLLoader loader = (FXMLLoader)nextNode.getScene().getUserData();


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
