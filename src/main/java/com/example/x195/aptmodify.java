package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class aptmodify {
    public TextField titletxtfld, descriptiontxtfld, locationtxtfld, typetxtfld;
    public ComboBox userComboBox, contactcombobox, monthcombox, daycombobox, timescombobox, customercombobox;

    public HashMap<String, String> getaptinfo(int aptid) {
        HashMap<String, String> apthm = new HashMap<>();
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery(
                "Select * from appointments where Appointment_ID=" + aptid);
            while (rs.next()) {
                apthm.put("id", String.valueOf(rs.getInt("Appointment_ID")));
                apthm.put("title", rs.getString("Title"));
                apthm.put("description", rs.getString("Description"));
                apthm.put("location", rs.getString("Location"));
                apthm.put("type", rs.getString("Type"));
                apthm.put("start", rs.getString("Start"));
                apthm.put("end", rs.getString("End"));
                apthm.put("customerid", String.valueOf(rs.getString("Customer_ID")));
                apthm.put("userid", String.valueOf(rs.getString("User_ID")));
                apthm.put("contactid", String.valueOf(rs.getString("Contact_ID")));
            }
        } catch(SQLException e) {System.out.println(e.getMessage());}
        return apthm;
    }

    public void updateapt(ActionEvent actionEvent) {
    }

    public void initialize() {
        HashMap<String, String> apthm = getaptinfo(appointments.toalterid);
        titletxtfld.setText(apthm.get("title"));
        descriptiontxtfld.setText(apthm.get("description"));
        locationtxtfld.setText(apthm.get("location"));
        typetxtfld.setText(apthm.get("type"));
        
    }
}
