package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
        boolean bool = false;

        int month = monthcombobox.getSelectionModel().getSelectedIndex()+1;
        int day = (int) daycombobox.getSelectionModel().getSelectedItem();
        String hrS = (String) apttimecombobox.getSelectionModel().getSelectedItem();
        int hr = Integer.parseInt(hrS.substring(0, 1));

        LocalDateTime candapt = LocalDateTime.of(2022, month, day, hr, 0);
        ZonedDateTime candaptzdt = LocalDateTime.of(2022, month, day, hr, 0).atZone(ZoneId.systemDefault());
        System.out.println(candapt);
        System.out.println(candaptzdt);


        return bool;
    }

    //todo: apt time
    // from db utc to est and to usr time
    // chk for outside biz hrs with est and usr
    // chk apt not in db


    //todo: convert to est to chk outside biz hours
    // display all times in local time for user
    public void addapt(ActionEvent actionEvent) {
        try {
           int colv = home.incrementcolval("appointments");
           //todo: fix apt time
           String apttime = (String) apttimecombobox.getSelectionModel().getSelectedItem();
           int custid = appointments.customershm.get(customercombobox.getSelectionModel().getSelectedItem());
           int userid = appointments.usershm.get(usercombobox.getSelectionModel().getSelectedItem());
           int contactid = appointments.contactshm.get(contactcombobox.getSelectionModel().getSelectedItem());
           
           HelloController.connection.createStatement().execute(
                   "Insert into appointments values(" + colv + ", '" + titletxtfld.getText() +
                           "', '" + desctxtfld.getText() + "', '" + loctxtfld.getText() + "', '" + 
                           typetxtfld.getText() + "', '" + apttime + "', '" + apttime + 
                           "', '" + "2022-08-30 17:02:46" + "', '" + "script" + "', '" +
                           "2022-08-30 17:02:46" + "', '" + "script" + "', " + custid + ", " +
                           userid + ", " + contactid + ");");
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
    }
}
