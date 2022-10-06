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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    private Button updateButton;

    @FXML
    private Text countText;

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

        fillTable(db);

        addButton.setOnAction(event -> {
            addButton.getScene().getWindow().hide();
            openNewScene("addQuote.fxml");
        });

        updateButton.setOnAction(event -> {
            TeacherQuote selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                System.out.println(selectedItem.getId());

                UpdateQuoteController.currentQuote = selectedItem;
                updateButton.getScene().getWindow().hide();
                openNewScene("updateQuote.fxml");
            }
        });

        deleteButton.setOnAction(e -> {
            TeacherQuote selectedItem = table.getSelectionModel().getSelectedItem();
            db.deleteTeacherQuote(selectedItem);
            table.getItems().remove(selectedItem);
            setCountText(db);
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

        setCountText(db);

    }

    private void fillTable(DatabaseHandler db) {
        ObservableList<TeacherQuote> quotesData = FXCollections.observableArrayList(db.getMyNotes());

        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(quotesData);
    }

    private void setCountText(DatabaseHandler db) {
        try {
            ResultSet rs = db.countQuotes();
            rs.next();
            countText.setText(String.valueOf(rs.getInt("rowcount")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
