package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyNotesController {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logOutButton;

    @FXML
    private TableColumn<?, ?> quoteColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> teacherColumn;

}
