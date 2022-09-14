package com.example.x195;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;

public class HelloController {
    public TextField usrTxtFld;
    public PasswordField passTxtFld;

    @FXML Label geoLbl;
    @FXML Button signinBtn;
    @FXML protected void signin() throws SQLException, ClassNotFoundException {
        String usrcreds = usrTxtFld.getText();
        String passcreds = passTxtFld.getText();

        Class.forName("com.mysql.jdbc.Driver");

        String db = "jbdc:mysql://localhost:3306/client_schedule";
        Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
    }
}