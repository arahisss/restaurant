package com.example.courseproject;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.courseproject.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {

        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                loginUser(loginText, passwordText);
            }
            else {
                System.out.println("Поле логин или пароль пусты!");
            }

        });

        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            openNewScene("signUp.fxml");
        });
    }

    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler db = new DatabaseHandler();
        User user = new User(loginText, passwordText);
        ResultSet result = db.getUser(user);


        int counter = 0;

        while(true) {

            try {
                if (!result.next()) break;
                user.setId(result.getLong("id"));
//                System.out.println(result.getInt("id"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;


        }

        if (counter >= 1) {
            DatabaseHandler.setCurrentUser(user);

//            DatabaseHandler.currentUser.setId(user.getId());

            signInButton.getScene().getWindow().hide();
            openNewScene("quotes.fxml");
            System.out.println("Пользователь успешно найден");
        }
        else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPassAnim = new Shake(passwordField);

            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }

    }

    public void openNewScene(String window) {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource(window));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Цитаты преподавателей");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
