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

public class UpdateController {

    public static TeacherQuote currentQuote;

    @FXML
    private Button updateButton;

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

        teacherField.setText(currentQuote.getTeacher());
        subjectField.setText(currentQuote.getSubject());
        quoteField.setText(currentQuote.getQuote());

        updateButton.setOnAction(event -> {
            String quote = quoteField.getText().trim();
            String teacher = teacherField.getText().trim();
            String subject = subjectField.getText().trim();

            if (!quote.equals("") && !teacher.equals("") && !subject.equals("")) {
                updateQuote(teacher, subject, quote);

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

    public static void updateQuote(String teacher, String subject, String quote) {
        DatabaseHandler db = new DatabaseHandler();
        currentQuote.setTeacher(teacher);
        currentQuote.setSubject(subject);
        currentQuote.setQuote(quote);
        db.updateTeacherQuote(currentQuote);
    }

    private void openNewScene(String window) {
        updateButton.getScene().getWindow().hide();
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
