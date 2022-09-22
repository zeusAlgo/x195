package com.example.x195;
import com.mysql.cj.jdbc.exceptions.SQLError;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class aptadd {
    public ComboBox usercombobox, apttimecombobox, customercombobox, contactcombobox, monthcombobox, daycombobox;
    public TextField titletxtfld, desctxtfld, loctxtfld, typetxtfld;

    public void initialize() {
        usercombobox.setItems(appointments.usersar);contactcombobox.setItems(appointments.contactsar);
        customercombobox.setItems(appointments.customersar);apttimecombobox.setItems(appointments.timesar);
        monthcombobox.setItems(appointments.monthsar);daycombobox.setItems(appointments.daysar);
    }

    public boolean isbizopen() {
        boolean bool = true;
        int month = monthcombobox.getSelectionModel().getSelectedIndex()+1;
        int day = (int) daycombobox.getSelectionModel().getSelectedItem();
        String hrS = (String) apttimecombobox.getSelectionModel().getSelectedItem();
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

    //todo: make apt pull apt creds and insert work on reports

    public void addapt(ActionEvent actionEvent) {
        try {
            if (!isbizopen()) return;
            int colv = home.incrementcolval("appointments", "Appointment_ID");
            String hrstring = (String) apttimecombobox.getSelectionModel().getSelectedItem();
            int month = monthcombobox.getSelectionModel().getSelectedIndex()+1,
                    day = (int) daycombobox.getSelectionModel().getSelectedItem(),
                    hr = appointments.timeshm.get(hrstring);

            ZonedDateTime aptcurzdt = LocalDateTime.of(2022, month, day, hr, 0).atZone(ZoneId.systemDefault());
            ZonedDateTime aptutc = aptcurzdt.withZoneSameInstant(ZoneId.of("UTC")), aptutcend = aptutc.plusHours(1);
            LocalDateTime aptstart1 = aptutc.toLocalDateTime(), aptend1 = aptutcend.toLocalDateTime();

            int custid = appointments.customershm.get(customercombobox.getSelectionModel().getSelectedItem()),
                    userid = appointments.usershm.get(usercombobox.getSelectionModel().getSelectedItem()),
                    contactid = appointments.contactshm.get(contactcombobox.getSelectionModel().getSelectedItem());

            HelloController.connection.createStatement().execute(
                   "Insert into appointments values(" + colv + ", '" + titletxtfld.getText() +
                           "', '" + desctxtfld.getText() + "', '" + loctxtfld.getText() + "', '" + 
                           typetxtfld.getText() + "', '" + aptstart1 + "', '" + aptend1 +
                           "', '" + "2022-08-30 17:02:46" + "', '" + "script" + "', '" +
                           "2022-08-30 17:02:46" + "', '" + "script" + "', " + custid + ", " +
                           userid + ", " + contactid + ");");
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
    }
}
