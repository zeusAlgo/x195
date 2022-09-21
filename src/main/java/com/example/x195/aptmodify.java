package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class aptmodify {
    public TextField titletxtfld, descriptiontxtfld, locationtxtfld, typetxtfld;
    public ComboBox userComboBox, contactcombobox, monthcombobox, daycombobox, timescombobox, customercombobox;

    public HashMap<String, String> getaptinfo(int aptid) {
        HashMap<String, String> apthm = new HashMap<>();
        try {ResultSet rs = HelloController.connection.createStatement().executeQuery(
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
                apthm.put("contactid", String.valueOf(rs.getString("Contact_ID")));}
        } catch(SQLException e) {System.out.println(e.getMessage());}
        return apthm;
    }

    public boolean isbizopen() {
        boolean bool = true;
        int month = monthcombobox.getSelectionModel().getSelectedIndex()+1;
        int day = (int) daycombobox.getSelectionModel().getSelectedItem();
        String hrS = (String) timescombobox.getSelectionModel().getSelectedItem();
        int hr = appointments.timeshm.get(hrS);

        ZonedDateTime aptcurzdt = LocalDateTime.of(2022, month, day, hr, 0).atZone(ZoneId.systemDefault());
        ZonedDateTime aptestzdt = aptcurzdt.withZoneSameInstant(ZoneId.of("America/New_York")),
                aptutc = aptcurzdt.withZoneSameInstant(ZoneId.of("UTC"));
        int utchr = aptutc.getHour(), hour = aptestzdt.getHour();
        String dayofweek = aptestzdt.getDayOfWeek().toString();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Business not open"),
                alert4 = new Alert(Alert.AlertType.INFORMATION, "Closed that day"),
                alert2 = new Alert(Alert.AlertType.INFORMATION, "Appointment unavailable");
        if (hour < 8 | hour > 22) {alert.show();return false;}
        if (dayofweek.equals("SATURDAY") | dayofweek.equals("SUNDAY")) {alert4.show();return false;}
//        int contactid = appointments.contactshm.get(contactcombobox.getSelectionModel().getSelectedItem());
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                String time = rs.getString("Start");
                String time1 = time.substring(11, 12), time2 = time.substring(11, 13);
                String hr3 = String.valueOf(utchr);
                if (time1.equals(hr3) | time2.equals(hr3)) {alert2.show();return false;}//perhaps check for contact ids
            }
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
        return bool;
    }

    public void updateapt(ActionEvent actionEvent) {
        try {
            if (!isbizopen()) return;
            int colv = home.incrementcolval("appointments", "Appointment_ID");
            String hrS = (String) timescombobox.getSelectionModel().getSelectedItem();

            int month = monthcombobox.getSelectionModel().getSelectedIndex()+1,
                    day = (int) daycombobox.getSelectionModel().getSelectedItem(),
                    hr = Integer.parseInt(hrS.substring(0, 1));
            ZonedDateTime aptcurzdt = LocalDateTime.of(2022, month, day, hr, 0).atZone(ZoneId.systemDefault());
            ZonedDateTime aptutc = aptcurzdt.withZoneSameInstant(ZoneId.of("UTC")), aptutcend = aptutc.plusHours(1);
            LocalDateTime aptstart1 = aptutc.toLocalDateTime(), aptend1 = aptutcend.toLocalDateTime();

            int custid = appointments.customershm.get(customercombobox.getSelectionModel().getSelectedItem()),
                    userid = appointments.usershm.get(userComboBox.getSelectionModel().getSelectedItem()),
                    contactid = appointments.contactshm.get(contactcombobox.getSelectionModel().getSelectedItem());
//            HelloController.connection.createStatement().executeUpdate("Update appointments set " +
//                    "where Appointment_ID=")
            //todo: add apt id
            HelloController.connection.createStatement().execute(
                   "Insert into appointments values(" + colv + ", '" + titletxtfld.getText() +
                           "', '" + descriptiontxtfld.getText() + "', '" + locationtxtfld.getText() + "', '" +
                           typetxtfld.getText() + "', '" + aptstart1 + "', '" + aptend1 +
                           "', '" + "2022-08-30 17:02:46" + "', '" + "script" + "', '" +
                           "2022-08-30 17:02:46" + "', '" + "script" + "', " + custid + ", " +
                           userid + ", " + contactid + ");");
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
    }
    public void initialize() {
        HashMap<String, String> apthm = getaptinfo(appointments.toalterid);
        titletxtfld.setText(apthm.get("title"));descriptiontxtfld.setText(apthm.get("description"));
        locationtxtfld.setText(apthm.get("location"));typetxtfld.setText(apthm.get("type"));
        userComboBox.setItems(appointments.usersar); contactcombobox.setItems(appointments.contactsar);
        monthcombobox.setItems(appointments.monthsar); daycombobox.setItems(appointments.daysar);
        timescombobox.setItems(appointments.timesar); customercombobox.setItems(appointments.customersar);

    }
}
