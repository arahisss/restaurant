module com.example.courseproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.courseproject to javafx.fxml;
    exports com.example.courseproject;
}