package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Button backImageButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            signUpNewUser();
            signUpButton.getScene().getWindow().hide();

            openNewScene("appForUser.fxml");
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("home.fxml");
        });

    }

    public void openNewScene(String window) {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource(window));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Цитаты преподавателей");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void signUpNewUser() {
        DatabaseHandler db = new DatabaseHandler();
        User user = new User(loginField.getText().trim(), CryptWithMD5.cryptWithMD5(passwordField.getText().trim()));
        db.signUpUser(user);
    }

}
