package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddQuoteController {

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

                add.getScene().getWindow().hide();
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
