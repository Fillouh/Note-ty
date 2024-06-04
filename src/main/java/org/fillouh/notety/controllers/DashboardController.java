package org.fillouh.notety.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.fillouh.notety.Notety;
import org.fillouh.notety.general_classes.LoggedStudent;
import javafx.fxml.FXML;

import java.io.IOException;
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


    public void logout() throws IOException {
        logstudString.setText("");
        logstudString.setVisible(false);
        logoutButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Notety.class.getResource("views/login-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logstudString.setText(actualUser.username);
        logstudString.setVisible(true);
    }
}
