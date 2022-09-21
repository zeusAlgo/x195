package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class appointments {
    public TextField modifytxtfld, deltxtfld;
    static HashMap<String, Integer> contactshm = new HashMap<>(), usershm = new HashMap<>(), customershm = new HashMap<>(), timeshm = new HashMap<>();
    static ObservableList<String> contactsar = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee"), usersar = FXCollections.observableArrayList("test", "admin"), customersar = FXCollections.observableArrayList(), timesar = FXCollections.observableArrayList("4 am", "5 am", "6 am", "7 am", "8 am", "9 am", "10 am", "11 am", "12 am", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"), monthsar = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");static ObservableList<Integer> daysar = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);

    public static int toalterid = 0;

    public void launchA(ActionEvent actionEvent) {home.launchActivity("aptadd");}
    public void launchB(ActionEvent actionEvent) {
        toalterid = Integer.parseInt(modifytxtfld.getText());
        home.launchActivity("aptmmodify");}

    public HashMap<String, Integer> getallcustomers() {
        HashMap<String, Integer> customershm = new HashMap<>();
        try {
            ResultSet res = HelloController.connection.createStatement().executeQuery("Select * from customers");
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

    public void convloctime2biztime() {
        ZonedDateTime curzdt = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime utczdt = LocalDateTime.now().atZone(ZoneId.of("UTC"));
        System.out.println(utczdt);

        ZonedDateTime estzdt = curzdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(estzdt);

        ZonedDateTime utczonedt = curzdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(utczonedt);
    }



    //todo: add month and days to layout apt add
    // get combo box vals
    public void initialize() {
       contactshm.put("Anika Costa", 1);contactshm.put("Daniel Garcia", 2);contactshm.put("Li Lee", 3);
       usershm.put("test", 1);usershm.put("admin", 2);
       timeshm.put("4 am", 1); timeshm.put("5 am", 2);timeshm.put("6 am", 3);timeshm.put("7 am", "8 am", "9 am", "10 am", "11 am", "12 am", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm");
//       isbizopen("5 am");
       customershm = getallcustomers();
//       convloctime2biztime();
    }
}
