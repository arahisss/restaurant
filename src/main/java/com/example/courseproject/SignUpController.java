package com.example.courseproject;

import com.example.courseproject.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class SignUpController {

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
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                signUpNewUser(loginText, passwordText);
            }
            else {
                Shake userLoginAnim = new Shake(loginField);
                Shake userPassAnim = new Shake(passwordField);

                userLoginAnim.playAnim();
                userPassAnim.playAnim();

                System.out.println("Поле логин или пароль пусты!");
            }
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

    private void signUpNewUser(String loginText, String passwordText) {
        DatabaseHandler db = new DatabaseHandler();
        User user = new User(loginText, CryptWithMD5.cryptWithMD5(passwordText));
        db.signUpUser(user);

        signUpButton.getScene().getWindow().hide();
        openNewScene("appForUser.fxml");
    }

}

