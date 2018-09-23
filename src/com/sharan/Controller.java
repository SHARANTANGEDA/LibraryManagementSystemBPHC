package com.sharan;

import com.sharan.admin.AdminCredentials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


import static com.sharan.Main.fileName;

public class Controller {


    @FXML
    protected BorderPane rootPane;

    @FXML
    protected BorderPane adminSignIn;

    @FXML
    protected BorderPane librarianSignIn;

    @FXML
    protected BorderPane adminSection;

    @FXML
    protected BorderPane librarianSection;

    @FXML
    private TextField adminUserName;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Label inValidCredentials;


    @FXML
    public void initialize() {

    }


    @FXML
    public void handleButtonClickAdmin(ActionEvent event) throws Exception {
        fileName = "admin/adminLoginPage.fxml";
        adminSignIn = FXMLLoader.load(getClass().getResource(fileName));
        rootPane.getChildren().add(adminSignIn);
        rootPane.getChildren().setAll(adminSignIn);
    }

    @FXML
    public void handleButtonClickLibrarian(ActionEvent event) throws Exception {
        fileName = "student/studentLoginPage.fxml";
        librarianSignIn = FXMLLoader.load(getClass().getResource(fileName));
        rootPane.getChildren().add(librarianSignIn);
        rootPane.getChildren().setAll(librarianSignIn);
    }

    @FXML
    private void handleButtonClickAdminSignIn(ActionEvent event) throws Exception {
        String typedUserName=adminUserName.getText().trim();
        String typedPassword=adminPassword.getText().trim();
        if((typedUserName.equalsIgnoreCase(AdminCredentials.getUsername())) && (typedPassword.equals(AdminCredentials.getPassword()))) {
            fileName = "admin/adminSection.fxml";
            adminUserName.setText("");
            adminPassword.setText("");
            adminSection = FXMLLoader.load(getClass().getResource(fileName));
            adminSignIn.getChildren().setAll(adminSection);
        }else  {
            adminUserName.setText("");
            adminPassword.setText("");
            inValidCredentials.setText("Please enter valid username and password!");
        }




    }

    @FXML
    public void handleButtonClickLibrarianSignIn(ActionEvent event) throws Exception {
        fileName = "student/studentSection.fxml";
        librarianSection= FXMLLoader.load(getClass().getResource(fileName));
        librarianSignIn.getChildren().setAll(librarianSection);
    }

    @FXML
    public void handleButtonClickReturntoMainMenu(ActionEvent event) throws Exception {
        fileName = "mainWindow.fxml";
        BorderPane pane = FXMLLoader.load(getClass().getResource(fileName));
        adminSignIn.getChildren().setAll(pane);
    }




}



