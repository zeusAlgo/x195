package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class aptadd {
    public ComboBox usercombobox, apttimecombobox, customercombobox, contactcombobox;
    public TextField titletxtfld, desctxtfld, loctxtfld, typetxtfld;

    public void initialize() {
        usercombobox.setItems(appointments.usersar);
        contactcombobox.setItems(appointments.contactsar);
        customercombobox.setItems(appointments.customersar);
    }

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
                           userid + ", " + contactid + ");"
                   
           );
           String insert = "Insert into appointments values(" + colv +
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
    }
}
