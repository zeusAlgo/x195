module com.example.x195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.x195 to javafx.fxml;
    exports com.example.x195;
}