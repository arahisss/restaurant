package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateUserController {

    @FXML
    private Button backButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("appForUser.fxml");
        });

        updateButton.setOnAction(event -> {
            updateButton.getScene().getWindow().hide();
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();

            if (!login.equals("") && !password.equals("")) {
                DatabaseHandler db = new DatabaseHandler();

                DatabaseHandler.currentUser.setLogin(loginField.getText().trim());
                DatabaseHandler.currentUser.setPassword(CryptWithMD5.cryptWithMD5(passwordField.getText().trim()));
                db.updateUser();

                openNewScene("appForUser.fxml");

            }
            else {
                System.out.println("Заполните все поля!");
            }


        });
    }


    private void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
