package org.fillouh.notety.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fillouh.notety.Notety;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
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

    @FXML
    private AnchorPane layer;

    public void back() throws IOException {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Notety.class.getResource("views/login-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void clear(){
        for (javafx.scene.Node node : layer.getChildren()) {
            if (node instanceof TextField tx) {
                tx.clear();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderBox.setItems(FXCollections.observableArrayList("Male","Female","Other"));
    }
}
