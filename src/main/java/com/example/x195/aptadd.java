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
           int colv = customersadd.incrementcolval("appointments");
        } catch (SQLException e) {System.out.println("SQL Error: " + e.getMessage());}
    }
}
