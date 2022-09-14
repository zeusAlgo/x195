package com.example.x195;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Locale;

public class HelloController {
    public TextField usrTxtFld;
    public PasswordField passTxtFld;
    @FXML Label geoLbl;
    @FXML Button signinBtn;
    public String lang = "";

    @FXML protected void signin() throws SQLException {
        String usrcreds = usrTxtFld.getText();
        String passcreds = passTxtFld.getText();
        String db = "jdbc:mysql://localhost/client_schedule";
        try {
            Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
            System.out.println("Connected to db");

        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
            String alerts = "";
            if (lang.equals("English")) alerts = "Please enter valid username and password";
            if (lang.equals("French")) alerts = "Veuillez entrer un nom d'utilisateur et un mot de passe valides";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alerts);
            alert.show();
        }
    }

    public void initialize() {
        ZoneId zone = ZoneId.systemDefault();
        geoLbl.setText(String.valueOf(zone));

        lang = Locale.getDefault().getDisplayLanguage();
        System.out.println(lang);

        if (lang.equals("French")) signinBtn.setText("S'identifier");

    }
}