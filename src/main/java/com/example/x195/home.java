package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class home {
    public Button customersBtn, aptBtn, reportsBtn;
    HashMap<String, Integer> countryhm = new HashMap<>();
    HashMap<String, Integer> usdivhm = new HashMap<>();
    HashMap<String, Integer> ukdivhm = new HashMap<>();
    HashMap<String, Integer> canadadivhm = new HashMap<>();
    static ObservableList<String> countryar = FXCollections.observableArrayList();
    static ObservableList<String> usdivar = FXCollections.observableArrayList();
    static ObservableList<String> ukdivar = FXCollections.observableArrayList();
    static ObservableList<String> canadadivar = FXCollections.observableArrayList();
    public void launchActivity(String activityname){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource(activityname+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
            Stage stage = new Stage();
            stage.setTitle(activityname);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {System.out.println(e.getMessage());}
    }

    public void launchA(ActionEvent actionEvent) { launchActivity("customers");}
    public void launchB(ActionEvent actionEvent) { launchActivity("appointments");}
    public void launchC(ActionEvent actionEvent) { launchActivity("reports");}

    public HashMap<String, Integer> getdivs(int countryid) {
        HashMap<String, Integer> hashbrownmap = new HashMap<>();
        try {
            Statement stmnt = HelloController.connection.createStatement();
            ResultSet rs = stmnt.executeQuery("Select * from first_level_divisions where=" + countryid);
            while (rs.next()) {
                String divname = rs.getString("Division");
                hashbrownmap.put(divname, rs.getInt("Division_ID"));
                switch (countryid) {
                    case 1: usdivar.add(divname);
                    case 2: ukdivar.add(divname);
                    case 3: canadadivar.add(divname);
                }
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return hashbrownmap;
    }

    public void initialize() {
        countryhm.put("U.S", 1); countryhm.put("UK", 2); countryhm.put("Canada", 3);
        countryar.add("U.S."); countryar.add("UK"); countryar.add("Canada");
        usdivhm = getdivs(1);ukdivhm = getdivs(2);
        canadadivhm = getdivs(3);

    }
}
