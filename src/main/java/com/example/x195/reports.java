package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        /**
          Justification for lambda - The lambda form is more readable.
         */
        String s = "";
        s += "Jan \n ======= \n";
        var aa = new Object() {String s2;};
        janhm.forEach((k, v) -> {aa.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (aa.s2 != null) s += aa.s2;

        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Feb \n ======= \n";
        var ab = new Object() {String s2;};
        febhm.forEach((k, v) -> {ab.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ab.s2 != null) s += ab.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Mar \n ======= \n";
        var ac = new Object() {String s2;};
        marhm.forEach((k, v) -> {ac.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ac.s2 != null) s += ac.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Apr \n ======= \n";
        var ad = new Object() {String s2;};
        aprhm.forEach((k, v) -> {ad.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ad.s2 != null) s += ad.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "May \n ======= \n";
        var ae = new Object() {String s2;};
        mayhm.forEach((k, v) -> {ae.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ae.s2 != null)s += ae.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Jun \n ======= \n";
        var af = new Object() {String s2;};
        junehm.forEach((k, v) -> {af.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (af.s2 != null) s += af.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Jul \n ======= \n";
        var ag = new Object() {String s2;};
        julhm.forEach((k, v) -> {ag.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ag.s2 != null) s += ag.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Aug \n ======= \n";
        var ah = new Object() {String s2;};
        aughm.forEach((k, v) -> {ah.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ah.s2 != null) s += ah.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Sept \n ====== \n";
        var ai = new Object() {String s2;};
        septhm.forEach((k, v) -> {ai.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ai.s2 != null) s += ai.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Oct \n ======= \n";
        var aj = new Object() {String s2;};
        octhm.forEach((k, v) -> {aj.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (aj.s2 != null) s += aj.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Nov \n ======= \n";
        var ak = new Object() {String s2;};
        novhm.forEach((k, v) -> {ak.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (ak.s2 != null) s += ak.s2;
        /**
          Justification for lambda - The lambda form is more readable.
         */
        s += "Dec \n ======= \n";
        var al = new Object() {String s2;};
        dechm.forEach((k, v) -> {al.s2 += k + ":" + String.valueOf(v) + "\n";});
        if (al.s2 != null) s += al.s2;
        s = s.replaceAll("null", "");
        textarea.setText(s);
    }

    public void report2(ActionEvent actionEvent) {
        String anikas = "Anika Costa\n========", daniels = "\nDaniel Garcia\n========", lis = "\nLi Lee\n=======";
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                int contactid = rs.getInt("Contact_ID");
                switch (contactid) {
                    case 1 -> anikas +=
                            "\n"+ String.valueOf(rs.getInt("Appointment_ID")) + " "+ rs.getString("Title") +" "+ rs.getString("Description") +" "+ rs.getString("Start") +" "+ rs.getString("End") +" "+ String.valueOf(rs.getInt("Customer_ID"));
                    case 2 -> daniels +=
                            "\n"+ String.valueOf(rs.getInt("Appointment_ID")) +" "+ rs.getString("Title") +" "+ rs.getString("Description") +" "+ rs.getString("Start") +" "+ rs.getString("End") +" "+ String.valueOf(rs.getInt("Customer_ID"));
                    case 3 -> lis +=
                            "\n"+ String.valueOf(rs.getInt("Appointment_ID")) +" "+ rs.getString("Title") +" "+ rs.getString("Description") +" "+ rs.getString("Start") +" "+ rs.getString("End") +" "+ String.valueOf(rs.getInt("Customer_ID"));
                }
            }
        } catch (SQLException e) { System.out.println(e.getMessage());}
        textarea.setText(anikas + daniels + lis);
    }

    public void report3(ActionEvent actionEvent) {
        HashMap<String, Integer> ctr = new HashMap<>();
        ctr.put("Anika", 0); ctr.put("Daniel", 0); ctr.put("Li", 0);
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                int contactid = rs.getInt("Contact_ID");
                switch (contactid) {
                    case 1 -> ctr.put("Anika", ctr.get("Anika") + 1);
                    case 2 -> ctr.put("Daniel", ctr.get("Daniel") + 1);
                    case 3 -> ctr.put("Li", ctr.get("Li") + 1);
                }
            }
        } catch (SQLException e) { System.out.println(e.getMessage());}
        String s = "Total Appointments by Contact\nAnika Costa : " + String.valueOf(ctr.get("Anika")) + "\nDaniel Garcia : " + String.valueOf(ctr.get("Daniel")) + "\nLi Lee : " + String.valueOf(ctr.get("Li"));
        textarea.setText(s);
    }
}
