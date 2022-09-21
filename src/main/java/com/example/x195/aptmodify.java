package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class aptmodify {
    public ComboBox userComboBox;public TextField titlecombobox;
    public TextField locationcombobox, typecombobox, descriptioncombobox;
    public ComboBox contactcombobox, monthcombox, daycombobox, timescombobox, customercombobox;

    public HashMap<String, String> getaptinfo(int aptid) {
        HashMap<String, String> apthm = new HashMap<>();
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery(

            )
        } catch(SQLException e) {System.out.println(e.getMessage());}
    }

    public void updateapt(ActionEvent actionEvent) {
    }
}
