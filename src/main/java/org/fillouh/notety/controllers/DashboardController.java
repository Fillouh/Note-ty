package org.fillouh.notety.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.fillouh.notety.general_classes.LoggedStudent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button accountButton;

    @FXML
    private Button activitiesButton;

    @FXML
    private Label currentuserLabel;

    @FXML
    private Button gradesButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label logstudString;

    @FXML
    private Button notificationsButton;


    //DA ANDARE A RESETTARE QUANDO FACCIO IL LOGOUT COME IL TESTO NEL CAMPO STRINGA
    public static LoggedStudent actualUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logstudString.setText(actualUser.username);
        logstudString.setVisible(true);
    }
}
