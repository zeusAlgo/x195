package com.example.x195;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.time.ZoneId;
import java.util.Locale;

public class HelloController {
    public TextField usrTxtFld;
    public PasswordField passTxtFld;
    @FXML Label geoLbl;
    @FXML Button signinBtn;
    public String lang = "";
    static String usr = "admin";
    static String pass = "admin";
    static String dbsrc = "jdbc:mysql://localhost/client_schedule";

    public static Connection dbconnection() {
        try {
            return DriverManager.getConnection(dbsrc, usr, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML protected void signin() {
        String usrcreds = usrTxtFld.getText();
        usr = usrcreds;
        String passcreds = passTxtFld.getText();
        pass = passcreds;
        String db = "jdbc:mysql://localhost/client_schedule";
        try {
            Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
            try {
                FileWriter logger = new FileWriter("login_activity.txt");
                logger.write("dklaj");
                logger.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            gohome();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
            String alerts = "";
            if (lang.equals("English")) alerts = "Please enter valid username and password";
            if (lang.equals("French")) alerts = "Veuillez entrer un nom d'utilisateur et un mot de passe valides";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alerts);
            alert.show();
        }
    }

    public void gohome(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {System.out.println(e.getMessage());}
    }

    static Connection connection = dbconnection();
    public void initialize() {
        ZoneId zone = ZoneId.systemDefault();
        geoLbl.setText(String.valueOf(zone));

        lang = Locale.getDefault().getDisplayLanguage();
        System.out.println(lang);

        if (lang.equals("French")) signinBtn.setText("S'identifier");

    }
}