package com.example.courseproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class HomeController {
    @FXML
    private Button authButton;

    @FXML
    private Button signUpButton;

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

        fillTable();

        authButton.setOnAction(event -> {
            authButton.getScene().getWindow().hide();
            openNewScene("auth.fxml");
        });


        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            openNewScene("signUp.fxml");
        });
    }

    private void fillTable() {
        DatabaseHandler db = new DatabaseHandler();
        ObservableList<TeacherQuote> quotesData = FXCollections.observableArrayList(db.getTeacherQuotes());

        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(quotesData);
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

    private static void con() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1971_my_app",
                    "std_1971_my_app", "root1234");

            connection.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        con();
    }
}
