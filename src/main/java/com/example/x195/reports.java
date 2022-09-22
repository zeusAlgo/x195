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

    public void incrementhm(HashMap<String, Integer> hm, String type) {
       if (hm.containsKey(type)) hm.put(type, hm.get(type) + 1);
       else {hm.put(type, 1);}
    }

    public void report1(ActionEvent actionEvent) {
        HashMap<String, Integer> janhm = new HashMap<>(), febhm = new HashMap<>(), marhm = new HashMap<>(), aprhm = new HashMap<>(), mayhm = new HashMap<>(), junehm = new HashMap<>(), julhm = new HashMap<>(), aughm = new HashMap<>(), septhm = new HashMap<>(), octhm = new HashMap<>(), novhm = new HashMap<>(), dechm = new HashMap<>();

        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery(
                    "Select * from appointments");
            while (rs.next()) {
                String monthstring = rs.getString("Start"), type = rs.getString("Type");
                int monthint = Integer.parseInt(monthstring.substring(5, 7));
                switch (monthint) {
                    case 1 -> incrementhm(janhm, type);
                    case 2 -> incrementhm(febhm, type);
                    case 3 -> incrementhm(marhm, type);
                    case 4 -> incrementhm(mayhm, type);
                    case 5 -> incrementhm(junehm, type);
                    case 6 -> incrementhm(julhm, type);
                    case 7 -> incrementhm(aughm, type);
                    case 8 -> incrementhm(septhm, type);
                    case 9 -> incrementhm(octhm, type);
                    case 10 -> incrementhm();

                }
            }

        } catch (SQLException e) {System.out.println(e.getMessage());}

    }



    public void report2(ActionEvent actionEvent) {
    }

    public void report3(ActionEvent actionEvent) {
    }
}
