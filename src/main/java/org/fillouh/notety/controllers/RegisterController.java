package org.fillouh.notety.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fillouh.notety.Notety;

import java.io.IOException;

public class RegisterController {
    @FXML
    private PasswordField addressField;

    @FXML
    private PasswordField ageField;

    @FXML
    private Button backButton;

    @FXML
    private TextField cityField;

    @FXML
    private Button clearButton;

    @FXML
    private PasswordField cpasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField facultyField;

    @FXML
    private TextField firstnameField;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private TextField lastnameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField uniField;

    @FXML
    private TextField usernameField;



    public void back() throws IOException {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Notety.class.getResource("views/login-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
