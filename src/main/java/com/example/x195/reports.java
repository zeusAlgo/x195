package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                String monthstring = rs.getString("Start"), type = rs.getString("Type");
                int monthint = Integer.parseInt(monthstring.substring(5, 7));
                switch (monthint) {
                    case 1 -> incrementhm(janhm, type); case 2 -> incrementhm(febhm, type);
                    case 3 -> incrementhm(marhm, type); case 4 -> incrementhm(aprhm, type);
                    case 5 -> incrementhm(mayhm, type); case 6 -> incrementhm(junehm, type);
                    case 7 -> incrementhm(julhm, type); case 8 -> incrementhm(aughm, type);
                    case 9 -> incrementhm(septhm, type); case 10 -> incrementhm(octhm, type);
                    case 11 -> incrementhm(novhm, type); case 12 -> incrementhm(dechm, type);}
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}

        String s = "";
        s += "Jan \n";
        var aa = new Object() {String s2;};
        janhm.forEach((k, v) -> {aa.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += aa.s2;

        s += "Feb \n";
        var ab = new Object() {String s2;};
        janhm.forEach((k, v) -> {ab.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += ab.s2;

        s += "Mar \n";
        var ac = new Object() {String s2;};
        janhm.forEach((k, v) -> {ac.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += ac.s2;

        s += "Apr \n";
        var ad = new Object() {String s2;};
        janhm.forEach((k, v) -> {ad.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += ad.s2;

        s += "May \n";
        var fb = new Object() {String s2;};
        janhm.forEach((k, v) -> {fb.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += "Jun \n";
        var fb = new Object() {String s2;};
        janhm.forEach((k, v) -> {fb.s2 += k + ":" + String.valueOf(v) + "\n";});
        s += fb.s2;
        textarea.setText(s);
    }



    public void report2(ActionEvent actionEvent) {
    }

    public void report3(ActionEvent actionEvent) {
    }
}
