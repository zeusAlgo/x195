package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customeradd {
    public ComboBox countryComboBox, divisionComboBox;
    public TextField addressTxtFld, phoneTxtFld, nameTxtFld, postalCodeTxtFld;

    HashMap<String, Integer> countryhm1 = new HashMap<>(), divisionhm1 = new HashMap<>();
    HashMap<Integer, String> countryhm2 = new HashMap<>(), divisionhm2 = new HashMap<>();
    ObservableList<String> countryar = FXCollections.observableArrayList(), divisionsar = FXCollections.observableArrayList();

    public void set_divisions() {
        int countryidx = countryComboBox.getSelectionModel().getSelectedIndex()+1;
        get_divisions(countryidx);
        divisionComboBox.setItems(divisionsar);
    }

    public int incrementcolval() {
        int colv = 0;
        try {
            Statement statement = HelloController.connection.createStatement();
            String query = "Select * from customers";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                colv = results.getInt("Customer_ID");
            }

        } catch (SQLException e) { System.out.println(e.getMessage());}
        return colv+1;
    }

    public void get_divisions(int countryIndex) {
        try {
            divisionhm1.clear();
            divisionhm2.clear();
            divisionsar.clear();
            Statement statement = HelloController.connection.createStatement();
            String query = "SELECT * FROM first_level_divisions where Country_ID="  + countryIndex;
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String divname = results.getString("Division");
                int divid =  results.getInt("Division_ID");

                divisionhm1.put(divname, divid);
                divisionhm2.put(divid, divname);
                divisionsar.add(divname);
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

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

    public void addCustomer(ActionEvent actionEvent) {
        try {
            int colv = incrementcolval();
            String divisionname = divisionComboBox.getSelectionModel().getSelectedItem().toString();
            int divisionidx = divisionhm1.get(divisionname);

            Statement statement = HelloController.connection.createStatement();
            String insertion = "Insert into customers values(" + colv + ", '" + nameTxtFld.getText() + "', '" +
                    addressTxtFld.getText() + "', '" + postalCodeTxtFld.getText() + "', '"
                    + phoneTxtFld.getText() + "', '" + "2022-09-14 20:00:00" + "', '" + "script" +
                    "', '" + "2022-09-14 20:00:00" + "', '" + "script" + "', "+ divisionidx + ")";
            statement.execute(insertion);
        } catch (SQLException e) {
            System.out.println("SQL Error " + e.getMessage());
        }
    }
}
