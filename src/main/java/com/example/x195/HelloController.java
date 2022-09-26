package com.example.x195;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
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
    static HashMap<String, String> apthm = new HashMap<>();

    /**
     * Gets connection to database
     * @return Database connection
     */
    public static Connection dbconnection() {
        try {return DriverManager.getConnection(dbsrc, usr, pass);}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    /**
     * Signs user into app, logs sign in, checks for impending appointments and potentially changes display language
     */
    @FXML protected void signin() {
        String usrcreds = usrTxtFld.getText(), passcreds = passTxtFld.getText(), db = "jdbc:mysql://localhost/client_schedule";
        usr = usrcreds; pass = passcreds;
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

            if (impendingapt()) {
                LocalDateTime ldt = aptmodify.conv2usrtime(apthm.get("datetime"));
                String ldts = ldt.toString();
                String date = ldts.substring(0, 10);
                int h = ldt.getHour();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Appointment in 15 minutes!\n"
                        + "Appointment " + apthm.get("aptid") + " on " + date + " at " + h + ":00");
                alerta.showAndWait();
            } else {
                Alert alertb = new Alert(Alert.AlertType.INFORMATION, "No upcoming appointments.");
                alertb.showAndWait();}
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
            Stage stage0 = (Stage) usrTxtFld.getScene().getWindow();
            stage0.close();
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
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
}