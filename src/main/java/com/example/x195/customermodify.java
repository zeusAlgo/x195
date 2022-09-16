package com.example.x195;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customermodify {
    public HashMap<String, String> getcustomerinfo(int customerid) {
        HashMap<String, String> customerhm = new HashMap<>();
        try {
            Statement statement = statement = HelloController.connection.createStatement();

        } catch (SQLException e) {

        }
        return customerhm;
    }
}
