module com.example.x195 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.x195 to javafx.fxml;
    exports com.example.x195;
}