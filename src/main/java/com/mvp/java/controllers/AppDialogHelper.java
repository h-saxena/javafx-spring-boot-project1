package com.mvp.java.controllers;

import javafx.scene.control.Alert;

import java.util.List;

public class AppDialogHelper {

    public static void showErrorDialog(List<String>  errorMessages) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errors");
        //alert.setHeaderText("Look, an Error Dialog");

        String content = "";

        for(String msg : errorMessages) {
            content+= "\n" + msg;
        }

        alert.setContentText(content);

        alert.showAndWait();

    }

    public static void showDialog(String title, List<String>  messages) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Error Dialog");

        String content = "";

        for(String msg : messages) {
            content+= "\n" + msg;
        }

        alert.setContentText(content);

        alert.showAndWait();

    }

}
