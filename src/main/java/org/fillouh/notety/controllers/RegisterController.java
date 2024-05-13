package org.fillouh.notety.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fillouh.notety.Notety;
import org.fillouh.notety.database.SimpleDB;
import org.fillouh.notety.general_classes.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Student student;

    private Alert alert;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;




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
            } else if (node instanceof ComboBox<?> cx) {
                cx.setItems(null);
            }
        }
    }


    public void signup() throws IOException {
        try{
            student.age=Integer.parseInt(ageField.getText());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please insert a valid age value!");
            alert.showAndWait();
        }

        student.firstName=firstnameField.getText();
        student.lastName=lastnameField.getText();
        student.email=emailField.getText();
        student.username=usernameField.getText();
        student.password=passwordField.getText();
        String cpass=cpasswordField.getText();
        student.phone=phoneField.getText();
        student.university=uniField.getText();
        student.faculty=facultyField.getText();
        student.city=cityField.getText();
        student.address=addressField.getText();
        student.gender=genderBox.getValue();

        //verificare che i parametri obbligatori ci siano
        if(student.firstName==null||student.lastName==null||student.email==null||student.username==null||student.password==null||cpass==null||student.phone==null||student.university==null||student.faculty==null||student.gender==null){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the blank mandatory fields!");
            alert.showAndWait();
        }

        //verificare che le 2 password inserite coincidano
        if(!student.password.equals(cpass)){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Passwords don't match!");
            alert.showAndWait();
        }

        //verificare che l'username che si è inserito sia univoco

        try{
            connection= SimpleDB.connectDB("notety_db","postgres","qwerty");
            sql= "select * from students where username=?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,student.username);
            resultSet=preparedStatement.executeQuery();

            if(!resultSet.next()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("This username is already taken! Choose another one");
                alert.showAndWait();
                usernameField.clear();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Errore di connessione al db");
        }

        //eseguire la query di inserimento dello studente nel database

        try{
            connection= SimpleDB.connectDB("notety_db","postgres","qwerty");
            sql="insert into students(firstName,lastName,username,password,email,phone,university,faculty,city,address,age,gender"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, student.firstName);
            preparedStatement.setString(2, student.lastName);
            preparedStatement.setString(3, student.username);
            preparedStatement.setString(4, student.password);
            preparedStatement.setString(5, student.email);
            preparedStatement.setString(6, student.phone);
            preparedStatement.setString(7, student.university);
            preparedStatement.setString(8, student.faculty);
            preparedStatement.setString(9, student.city);
            preparedStatement.setString(10, student.address);
            preparedStatement.setString(11, String.valueOf(student.age));
            preparedStatement.setString(12, student.gender);

            int rIns=preparedStatement.executeUpdate();
            if(rIns==0){
                System.out.println("Signup problem");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to Notety you've registered successfully!\n You will be redirected at the login page");
        alert.showAndWait();

        back();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderBox.setItems(FXCollections.observableArrayList("Male","Female","Other"));
    }
}
