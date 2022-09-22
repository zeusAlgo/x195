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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class home {
    public Button customersBtn, aptBtn, reportsBtn;
    static HashMap<String, Integer> countryhm = new HashMap<>(), usdivhm = new HashMap<>(), ukdivhm = new HashMap<>(), canadadivhm = new HashMap<>();static HashMap<Integer, String> alldivshm = new HashMap<>();
    static ObservableList<String> countryar = FXCollections.observableArrayList("U.S.", "UK", "Canada"), usdivar = FXCollections.observableArrayList(), ukdivar = FXCollections.observableArrayList(), canadadivar = FXCollections.observableArrayList();

    static public void launchActivity(String activityname){
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
            ResultSet rs = stmnt.executeQuery("Select * from first_level_divisions where Country_ID=" + countryid);
            while (rs.next()) {
                String divname = rs.getString("Division");
                hashbrownmap.put(divname, rs.getInt("Division_ID"));
                switch (countryid) {
                    case 1: usdivar.add(divname); break;
                    case 2: ukdivar.add(divname); break;
                    case 3: canadadivar.add(divname); break;
                }
            }
        } catch (SQLException e) {e.getMessage();}
        return hashbrownmap;
    }

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

    static public void del(String tbl, String col, int id) {
        try {HelloController.connection.createStatement().execute(
                    "Delete from " + tbl + " Where " +  col + "=" + id);
        } catch (SQLException error) {System.out.println("SQL Error: " + error.getMessage());}
   }

    static public int incrementcolval(String tbl, String col) {
        int colv = 0;
        try {
            Statement statement = HelloController.connection.createStatement();
            ResultSet results = statement.executeQuery("Select * from " + tbl);
            while (results.next()) colv = results.getInt(col);
        } catch (SQLException e) { System.out.println(e.getMessage());}
        return colv+1;
    }

    public void impendingapt() {
        ZonedDateTime curzdt = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime utczdt = curzdt.withZoneSameInstant(ZoneId.of("UTC"));
        //brute force check if apt in 15 min by checking every minute
        System.out.println(curzdt);
        System.out.println(utczdt);

        HashSet<ZonedDateTime> zdths = new HashSet<>();
        for (int i=0; i <= 15; i ++) zdths.add(utczdt.plusMinutes(i));

        HashSet<String> stringhs = new HashSet<>();
        for (ZonedDateTime zdt : zdths) {

        }
    }

    public void initialize() {
        countryhm.put("U.S", 1); countryhm.put("UK", 2); countryhm.put("Canada", 3);usdivhm = getdivs(1);ukdivhm = getdivs(2);canadadivhm = getdivs(3);alldivshm = getalldivs();
        impendingapt();
    }
}
