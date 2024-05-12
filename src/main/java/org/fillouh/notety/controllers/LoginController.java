package org.fillouh.notety.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fillouh.notety.Notety;
import org.fillouh.notety.database.SimpleDB;
import org.fillouh.notety.general_classes.LoggedStudent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private Button aboutButton;
    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameField;

    private String username;
    private String password;
    private String sql;
    private static String link="https://github.com/";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void loginAdmin(){
        username=usernameField.getText();
        password=passwordField.getText();
        LoggedStudent.username=username;
        Alert alert;
        if(username.isEmpty()||password.isEmpty()){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else{
            connection= SimpleDB.connectDB("notety_db","postgres","qwerty");
            try{
                sql=String.format("select * from students where username = ? and password = ?;");
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,usernameField.getText());
                preparedStatement.setString(2,passwordField.getText());

                resultSet=preparedStatement.executeQuery();

                if(resultSet.next()){
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    //CAMBIARE SCENA SU JAVAFX
                    loginButton.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(Notety.class.getResource("views/main-dashboard-view.fxml"));
                    Stage stage=new Stage();
                    Scene scene=new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();

                }
                else{
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password!");
                    alert.showAndWait();
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void signUp() throws IOException {
        loginButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Notety.class.getResource("views/register-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    public void about(){
        Button btn=new Button("Copy Link");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(link);
                System.out.println("link copied");
                clipboard.setContent(content);
            }
        });
        DialogPane dialogPane = new DialogPane();
        dialogPane.setHeaderText("Note-ty by Filippo Leonelli");
        Text t= new Text("Note-ty is an open-source project to allow students to track their activities and grades! \n" +
        "For more information visit : "+link);
        VBox vBox=new VBox(20);
        vBox.getChildren().addAll(t,btn);
        dialogPane.setContent(vBox);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("About it");
        dialog.setDialogPane(dialogPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        dialog.showAndWait();
    }




}