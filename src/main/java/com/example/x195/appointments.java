package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class appointments {
    public TextField modifytxtfld, deltxtfld;
    static HashMap<String, Integer> contactshm = new HashMap<>();
    static HashMap<String, Integer> usershm = new HashMap<>();
    static HashMap<String, Integer> customershm = new HashMap<>();
    static ObservableList<String> contactsar = FXCollections.observableArrayList();
    static ObservableList<String> usersar = FXCollections.observableArrayList();
    static ObservableList<String> customersar = FXCollections.observableArrayList();

    public static int toalterid = 0;

    public void launchA(ActionEvent actionEvent) {home.launchActivity("aptadd");}
    public void launchB(ActionEvent actionEvent) {
        toalterid = Integer.parseInt(modifytxtfld.getText());
        home.launchActivity("aptmmodify");}

    public HashMap<String, Integer> getallcustomers() {
        HashMap<String, Integer> customershm = new HashMap<>();
        try {
            ResultSet res = HelloController.connection.createStatement().executeQuery(
                    "Select * from customers");
            while (res.next()) {
                String custname = res.getString("Customer_Name");
                customershm.put(custname, res.getInt("Customer_ID"));
                customersar.add(custname);
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return customershm;
    }

    public void deleteapt(ActionEvent actionEvent) { home.del("appointments", "Appointment_ID", Integer.parseInt(deltxtfld.getText()));
    }

    public void addapt() {

    }

    public void initialize() {
       contactshm.put("Anika Costa", 1);contactshm.put("Daniel Garcia", 2);contactshm.put("Li Lee", 3);
       contactsar.add("Anika Costa"); contactsar.add("Daniel Garcia"); contactsar.add("Li Lee");
       usershm.put("test", 1);usershm.put("admin", 2); usersar.add("test"); usersar.add("admin");
       customershm = getallcustomers();
    }
}
