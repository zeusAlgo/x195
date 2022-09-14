package com.example.x195;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.time.ZoneId;

public class HelloController {
    public TextField usrTxtFld;
    public PasswordField passTxtFld;
    @FXML Label geoLbl;
    @FXML Button signinBtn;

    @FXML protected void signin() throws SQLException {
        String usrcreds = usrTxtFld.getText();
        String passcreds = passTxtFld.getText();
        String db = "jdbc:mysql://localhost/client_schedule";
        try {
            Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
            System.out.println("Connected to db");

        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Please enter valid username and password");
            alert.show();
        }
    }

    public void initialize() {
        ZoneId zone = ZoneId.systemDefault();
        geoLbl.setText(String.valueOf(zone));
    }
}