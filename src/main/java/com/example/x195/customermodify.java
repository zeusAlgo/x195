package com.example.x195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customermodify {
    public ComboBox countrycombobox, divisioncombobox;
    public TextField nametxtfld, addresstxtfld, postcodetxtfld, idtxtfld, phonetxtfld;
    HashMap<String, Integer> countryhm = new HashMap<>();

    public HashMap<String, String> getcustomerinfo(int customerid) {
        HashMap<String, String> customerhm = new HashMap<>();
        try {
            Statement statement = HelloController.connection.createStatement();
            ResultSet res = statement.executeQuery(
                    "SELECT * FROM customers where Customer_ID="+ customerid);
            while (res.next()) {
                customerhm.put("id", String.valueOf(res.getInt("Customer_ID")));
                customerhm.put("name", res.getString("Customer_Name"));
                customerhm.put("address", res.getString("Address"));
                customerhm.put("postalcode", res.getString("Postal_Code"));
                customerhm.put("phone", res.getString("Phone"));
                customerhm.put("divid", String.valueOf(res.getInt("Division_ID")));
            }
        } catch (SQLException e) { System.out.println("SQL Error: " + e.getMessage()); }
        return customerhm;
    }

    public void initialize() {
        int curcustomerid = customers.tomodifyid;
        HashMap<String, String> custhm = getcustomerinfo(curcustomerid);
        idtxtfld.setText(custhm.get("id"));
        nametxtfld.setText(custhm.get("name"));addresstxtfld.setText(custhm.get("address"));
        postcodetxtfld.setText(custhm.get("postalcode"));phonetxtfld.setText(custhm.get("phone"));

        countryhm.put("U.S", 1); countryhm.put("UK", 2); countryhm.put("Canada", 3);

        ObservableList<String> countryar = FXCollections.observableArrayList();
        countryar.add("U.S."); countryar.add("UK"); countryar.add("Canada");
        countrycombobox.setItems(countryar);

    }
}
