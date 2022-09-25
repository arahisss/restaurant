package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MyNotesController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<TeacherQuote> table;

    @FXML
    private TableColumn<TeacherQuote, String> dateColumn;

    @FXML
    private TableColumn<TeacherQuote, String> quoteColumn;

    @FXML
    private TableColumn<TeacherQuote, String> subjectColumn;

    @FXML
    private TableColumn<TeacherQuote, String> teacherColumn;

    @FXML
    void initialize() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<TeacherQuote> quotesData = FXCollections.observableArrayList(db.getMyNotes());

        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(quotesData);


        addButton.setOnAction(event -> {
            addButton.getScene().getWindow().hide();
            openNewScene("add.fxml");
        });

        deleteButton.setOnAction(e -> {
            TeacherQuote selectedItem = table.getSelectionModel().getSelectedItem();
            db.deleteTeacherQuote(selectedItem);
            table.getItems().remove(selectedItem);
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("appForUser.fxml");
        });

        logOutButton.setOnAction(event -> {
            logOutButton.getScene().getWindow().hide();
            DatabaseHandler.setCurrentUser(null);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
