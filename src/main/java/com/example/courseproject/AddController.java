package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController {

    @FXML
    private Button add;

    @FXML
    private TextArea quoteField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField teacherField;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {

        add.setOnAction(event -> {
            String quote = quoteField.getText().trim();
            String teacher = teacherField.getText().trim();
            String subject = subjectField.getText().trim();

            if (!quote.equals("") && !teacher.equals("") && !subject.equals("")) {
                addQuote(quote, teacher, subject);

                if (DatabaseHandler.currentUser.getId_role() == 2) {
                    openNewScene("appForSuper.fxml");
                }
                else {
                    openNewScene("appForUser.fxml");
                }
            }
            else {
                System.out.println("Заполните все поля!");
            }
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            if (DatabaseHandler.currentUser.getId_role() == 2) {
                openNewScene("appForSuper.fxml");
            }
            else {
                openNewScene("appForUser.fxml");
            }
        });
    }

    private void addQuote(String quote, String teacher, String subject) {
        DatabaseHandler db = new DatabaseHandler();
        TeacherQuote teacherQuote = new TeacherQuote(teacher, subject, quote);
        db.addTeacherQuote(teacherQuote);

    }

    private void openNewScene(String window) {
        add.getScene().getWindow().hide();
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
