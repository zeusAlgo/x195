package com.example.x195;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class reports {
    public TextArea textarea;

    public void initialize() {
        textarea.setText("dlkfsjldjflsfl\nlksdjflajdlfkjsjdf\n");
    }

    public void report1(ActionEvent actionEvent) {
        HashMap<String, Integer> janhm = new HashMap<>();
        HashMap<String, Integer> febhm = new HashMap<>();
        HashMap<String, Integer> marhm = new HashMap<>();
        HashMap<String, Integer> aprhm = new HashMap<>();
        HashMap<String, Integer> mayhm = new HashMap<>();
        HashMap<String, Integer> junehm = new HashMap<>();
        HashMap<String, Integer> julhm = new HashMap<>();
        HashMap<String, Integer> aughm = new HashMap<>();
        HashMap<String, Integer> septhm = new HashMap<>();
        HashMap<String, Integer> octhm = new HashMap<>();
        HashMap<String, Integer> novhm = new HashMap<>();
        HashMap<String, Integer> dechm = new HashMap<>();

        try {

            ResultSet rs = HelloController.connection.createStatement().executeQuery(
                    "Select * from appointments");
        } catch (SQLException e) {System.out.println(e.getMessage());}
    }

    public void report2(ActionEvent actionEvent) {
    }

    public void report3(ActionEvent actionEvent) {
    }
}
