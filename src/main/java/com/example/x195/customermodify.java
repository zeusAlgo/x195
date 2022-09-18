package com.example.x195;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class customermodify {
    public ComboBox countrycombobox, divisioncombobox;
    public TextField nametxtfld, addresstxtfld, postcodetxtfld, idtxtfld, phonetxtfld;

    public HashMap<String, String> getcustomerinfo(int customerid) {
        HashMap<String, String> customerhm = new HashMap<>();
        try {
            ResultSet res = HelloController.connection.createStatement().executeQuery(
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

    public void setdivs() {
        String countryname = (String) countrycombobox.getSelectionModel().getSelectedItem();
        switch (countryname) {
            case "U.S.": divisioncombobox.setItems(home.usdivar); break;
            case "UK": divisioncombobox.setItems(home.ukdivar); break;
            case "Canada": divisioncombobox.setItems(home.canadadivar); break;
        }
    }

    public void updatecustomer() {
        try {
            Statement stmnt = HelloController.connection.createStatement();
        String update = "UPDATE customers set Customer_Name='" + nametxtfld.getText() + "', Address='" + addresstxtfld.getText() + "', Postal_Code='" +   "WHERE Customer_ID=" + customers.tomodifyid;
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    public void initialize() {
        countrycombobox.setItems(home.countryar);
        int curcustomerid = customers.tomodifyid;
        HashMap<String, String> custhm = getcustomerinfo(curcustomerid);
        idtxtfld.setText(custhm.get("id"));
        nametxtfld.setText(custhm.get("name"));addresstxtfld.setText(custhm.get("address"));
        postcodetxtfld.setText(custhm.get("postalcode"));phonetxtfld.setText(custhm.get("phone"));

        int divid = Integer.parseInt(custhm.get("divid"));
        String divname = home.alldivshm.get(divid), nation = "";

        if (home.usdivar.contains(divname)) {divisioncombobox.setItems(home.usdivar); nation = "U.S.";}
        else if (home.ukdivar.contains(divname)) {divisioncombobox.setItems(home.ukdivar); nation = "UK";}
        else if (home.canadadivar.contains(divname)) {divisioncombobox.setItems(home.canadadivar); nation = "Canada";}

        countrycombobox.getSelectionModel().select(nation);
        divisioncombobox.getSelectionModel().select(divname);
    }
}
