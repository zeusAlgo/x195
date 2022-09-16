package com.example.x195;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customermodify {
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
                customerhm.put("")
            }
        } catch (SQLException e) { System.out.println("SQL Error: " + e.getMessage()); }
        return customerhm;
    }
}
