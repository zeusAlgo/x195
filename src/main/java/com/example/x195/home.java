package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Home activity
 */
public class home {
    public Button customersBtn;
    public Button aptBtn, reportsBtn;
    static HashMap<String, Integer> countryhm = new HashMap<>(), usdivhm = new HashMap<>(), ukdivhm = new HashMap<>(), canadadivhm = new HashMap<>();static HashMap<Integer, String> alldivshm = new HashMap<>();
    static ObservableList<String> countryar = FXCollections.observableArrayList("U.S.", "UK", "Canada"), usdivar = FXCollections.observableArrayList(), ukdivar = FXCollections.observableArrayList(), canadadivar = FXCollections.observableArrayList();
    public Button emptyBtn;

    /**
     * Launches activity
     * @param activityname activity to be launched
     */
    static public void launchActivity(String activityname){
        try {
            Stage stage0 = (Stage) customersBtn.getScene().getWindow();
            stage0.close();
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource(activityname+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
            Stage stage = new Stage();
            stage.setTitle(activityname);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {System.out.println(e.getMessage());}
    }

    /**
     * Launches customers activity
     * @param actionEvent Mouse or keyboard press
     */
    public void launchA(ActionEvent actionEvent) { launchActivity("customers");}

    /**
     * Launches appointments activity
     * @param actionEvent Mouse or keyboard press
     */
    public void launchB(ActionEvent actionEvent) { launchActivity("appointments");}

    /**
     * Launches appointments activity
     * @param actionEvent Mouse or keyboard press
     */
    public void launchC(ActionEvent actionEvent) { launchActivity("reports");}

    /**
     * Gets divisions
     * @param countryid id of country
     * @return Hashmap of divisions
     */
    public HashMap<String, Integer> getdivs(int countryid) {
        HashMap<String, Integer> hashbrownmap = new HashMap<>();
        try {
            Statement stmnt = HelloController.connection.createStatement();
            ResultSet rs = stmnt.executeQuery("Select * from first_level_divisions where Country_ID=" + countryid);
            while (rs.next()) {
                String divname = rs.getString("Division");
                hashbrownmap.put(divname, rs.getInt("Division_ID"));
                switch (countryid) {
                    case 1 -> usdivar.add(divname);
                    case 2 -> ukdivar.add(divname);
                    case 3 -> canadadivar.add(divname);
                }
            }
        } catch (SQLException e) {e.getMessage();}
        return hashbrownmap;
    }

    /**
     * Gets all divisons
     * @return Hashmap of all divisions
     */
    public HashMap<Integer, String> getalldivs() {
        HashMap<Integer, String> hashmap = new HashMap<>();
        try {
            Statement stmnt1 = HelloController.connection.createStatement();
            ResultSet rs1 = stmnt1.executeQuery("Select * from first_level_divisions");
            while (rs1.next()) {
                String divname1 = rs1.getString("Division");
                hashmap.put(rs1.getInt("Division_ID"), divname1);
            }
        } catch (SQLException error) {error.getMessage();}
        return hashmap;
    }

    /**
     * Deletes from database
     * @param tbl table to delete from
     * @param col col to delete from
     * @param id id of item to be deleted
     */
    static public void del(String tbl, String col, int id) {
        try {HelloController.connection.createStatement().execute(
                    "Delete from " + tbl + " Where " +  col + "=" + id);
        } catch (SQLException error) {System.out.println("SQL Error: " + error.getMessage());}
   }

    /**
     * Increments the column
     * @param tbl table to find column
     * @param col column to increment
     * @return Incremented column value
     */
    static public int incrementcolval(String tbl, String col) {
        int colv = 0;
        try {
            Statement statement = HelloController.connection.createStatement();
            ResultSet results = statement.executeQuery("Select * from " + tbl);
            while (results.next()) colv = results.getInt(col);
        } catch (SQLException e) { System.out.println(e.getMessage());}
        return colv+1;
    }

    /**
     * If there is an appointment within 15 mins of login
     * @return Boolean if there is an appointment within 15 mins of login
     */
    static public boolean impendingapt() {
        ZonedDateTime curzdt = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime utczdt = curzdt.withZoneSameInstant(ZoneId.of("UTC"));
        HashSet<ZonedDateTime> zdths = new HashSet<>();
        for (int i=0; i <= 15; i ++) zdths.add(utczdt.plusMinutes(i));

        HashSet<String> stringhs = new HashSet<>();
        for (ZonedDateTime zdt : zdths) {
            String string = zdt.toString();
            string = string.substring(0, 17);
            string = string.replace("T", " ");
            string += "00";
            stringhs.add(string);
        }
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                System.out.println(rs.getString("Start"));
                if (stringhs.contains(rs.getString("Start"))) {

                    // apt modify needs to display all orignal info in correct timezone
                    HelloController.apthm.put("aptid", String.valueOf(rs.getInt("Appointment_ID")));
                    HelloController.apthm.put("datetime", String.valueOf(rs.getString("Start")));
                    return true;
                }
            }
        } catch (SQLException e) { System.out.println(e.getMessage());}
        return false;
    }

    /**
     * Set ui and hashmaps
     */
    public void initialize() {
        countryhm.put("U.S", 1); countryhm.put("UK", 2); countryhm.put("Canada", 3);usdivhm = getdivs(1);ukdivhm = getdivs(2);canadadivhm = getdivs(3);alldivshm = getalldivs();
    }
}
