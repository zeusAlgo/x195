package com.example.x195;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

import static com.example.x195.home.impendingapt;

/**
 * Entry point into the application
 */
public class HelloController {
    public TextField usrTxtFld;public PasswordField passTxtFld;
    @FXML Label geoLbl;@FXML Button signinBtn;
    public String lang = "";
    static String usr = "admin", pass = "admin", dbsrc = "jdbc:mysql://localhost/client_schedule";
    Stage stage;Parent scene;

    /**
     * Gets connection to database
     * @return Database connection
     */
    public static Connection dbconnection() {
        try {
            return DriverManager.getConnection(dbsrc, usr, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Signs user into app, logs sign in and potentially changes display language
     */
    @FXML protected void signin() {
        String usrcreds = usrTxtFld.getText();
        usr = usrcreds;
        String passcreds = passTxtFld.getText();
        pass = passcreds;
        String db = "jdbc:mysql://localhost/client_schedule";
        try {
            Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
            try {
                FileWriter logger = new FileWriter("login_activity.txt", true);
                logger.write("\n\n");
                logger.write(usrcreds +" ");
                logger.write(String.valueOf(ZonedDateTime.now()));
                logger.write("\nSuccessful Login");
                logger.close();
            } catch (IOException e) {System.out.println(e.getMessage());}

            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Appointment in 15 mins!"),
                    alertb = new Alert(Alert.AlertType.INFORMATION, "No upcoming appointments.");
            if (impendingapt()) alerta.showAndWait();
            else {alertb.showAndWait();}
            gohome();
        } catch (SQLException e) {
            try {
                FileWriter logger = new FileWriter("login_activity.txt" ,true);
                logger.write("\n\n");
                logger.write(usrcreds + " ");
                logger.write(String.valueOf(ZonedDateTime.now()));
                logger.write("\nFailed Login");
                logger.close();
            } catch (IOException e2) {System.out.println(e2.getMessage());}

            System.out.println("Error" + e.getMessage());
            String alerts = "";
            if (lang.equals("English")) alerts = "Please enter valid username and password";
            if (lang.equals("French")) alerts = "Veuillez entrer un nom d'utilisateur et un mot de passe valides";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alerts);
            alert.show();
        }
    }

    /**
     * Launches the home activity
     */
    public void gohome(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
//            Stage stage = new Stage();
            stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {System.out.println(e.getMessage());}
    }

    static Connection connection = dbconnection();

    /**
     * Sets ui and language
     */
    public void initialize() {
        ZoneId zone = ZoneId.systemDefault();
        geoLbl.setText(String.valueOf(zone));

        lang = Locale.getDefault().getDisplayLanguage();
        System.out.println(lang);

        if (lang.equals("French")) signinBtn.setText("S'identifier");

    }

    public void signin2(ActionEvent actionEvent) {
               String usrcreds = usrTxtFld.getText();
        usr = usrcreds;
        String passcreds = passTxtFld.getText();
        pass = passcreds;
        String db = "jdbc:mysql://localhost/client_schedule";
        try {
            Connection connection = DriverManager.getConnection(db, usrcreds, passcreds);
            try {
                FileWriter logger = new FileWriter("login_activity.txt", true);
                logger.write("\n\n");
                logger.write(usrcreds +" ");
                logger.write(String.valueOf(ZonedDateTime.now()));
                logger.write("\nSuccessful Login");
                logger.close();
            } catch (IOException e) {System.out.println(e.getMessage());}
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            loader.load();
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.show();

        } catch (SQLException | IOException e) {
            try {
                FileWriter logger = new FileWriter("login_activity.txt" ,true);
                logger.write("\n\n");
                logger.write(usrcreds + " ");
                logger.write(String.valueOf(ZonedDateTime.now()));
                logger.write("\nFailed Login");
                logger.close();
            } catch (IOException e2) {System.out.println(e2.getMessage());}

            System.out.println("Error" + e.getMessage());
            String alerts = "";
            if (lang.equals("English")) alerts = "Please enter valid username and password";
            if (lang.equals("French")) alerts = "Veuillez entrer un nom d'utilisateur et un mot de passe valides";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alerts);
            alert.show();
        }
    }
}