package com.example.x195;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customermodify {
    public ComboBox countrycombobox;
    public ComboBox divisioncombobox;
    public TextField nametxtfld;
    public TextField addresstxtfld;
    public TextField postcodetxtfld;
    public TextField phone;

    public HashMap<String, String> getcustomerinfo(int customerid) {
        HashMap<String, String> customerhm = new HashMap<>();
        try {
            Statement statement = statement = HelloController.connection.createStatement();
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
        System.out.println(curcustomerid);
        HashMap<String, String> custhm = getcustomerinfo(curcustomerid);
        System.out.println(custhm.get("phone"));


    }

}
