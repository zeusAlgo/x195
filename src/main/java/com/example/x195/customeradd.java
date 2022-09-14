package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customeradd {
    public ComboBox countryComboBox;
    public ComboBox divisionComboBox;

    HashMap<String, Integer> countryhm1 = new HashMap<>();
    HashMap<Integer, String> countryhm2 = new HashMap<>();
    HashMap<String, Integer> divisionhm1 = new HashMap<>();
    HashMap<Integer, String> divisionhm2 = new HashMap<>();
    ObservableList<String> countryar = FXCollections.observableArrayList();
    ObservableList<String> divisionsar = FXCollections.observableArrayList();

    public void set_divisions() {
        int countryidx = countryComboBox.getSelectionModel().getSelectedIndex()+1;
        get_divisions(countryidx);
        divisionComboBox.setItems(divisionsar);

    }

    public void get_divisions(int countryIndex) {
        try {
                divisionhm1.clear();
                divisionhm2.clear();
                divisionsar.clear();
                Connection connection = HelloController.connection;
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM first_level_divisions where Country_ID="  + countryIndex;
                ResultSet results = statement.executeQuery(query);
                while (results.next()) {
                    String divname = results.getString("Division");
                    int divid =  results.getInt("Division_ID");

                    divisionhm1.put(divname, divid);
                    divisionhm2.put(divid, divname);
                    divisionsar.add(divname);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }


    }

//    public void dbconnect() {
//
//    }

    public void initialize() {
        countryhm1.put("U.S", 1);
        countryhm1.put("UK", 2);
        countryhm1.put("Canada", 3);
        countryhm2.put(1, "U.S");
        countryhm2.put(2, "UK");
        countryhm2.put(3, "Canada");
        countryar.add("U.S"); countryar.add("UK"); countryar.add("Canada");
        countryComboBox.setItems(countryar);

    }

}
