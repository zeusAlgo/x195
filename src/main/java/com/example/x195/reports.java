package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class reports {
    public TextArea textarea;

    public void initialize() {
        textarea.setText("dlkfsjldjflsfl\nlksdjflajdlfkjsjdf\n");
    }

    public void report1(ActionEvent actionEvent) {
        HashMap<String, Integer> janhm = new HashMap<>(), febhm = new HashMap<>(), marhm = new HashMap<>(), aprhm = new HashMap<>(), mayhm = new HashMap<>(), junehm = new HashMap<>(), julhm = new HashMap<>(), aughm = new HashMap<>(), septhm = new HashMap<>(), octhm = new HashMap<>(), novhm = new HashMap<>(), dechm = new HashMap<>();

        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery(
                    "Select * from appointments");
            while (rs.next()) {
                String monthstring = rs.getString("Start");
                int monthint = Integer.parseInt(monthstring.substring(5, 7));
                switch

            }

        } catch (SQLException e) {System.out.println(e.getMessage());}

    }

    public void report2(ActionEvent actionEvent) {
    }

    public void report3(ActionEvent actionEvent) {
    }
}
