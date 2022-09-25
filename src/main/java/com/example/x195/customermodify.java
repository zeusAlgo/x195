package com.example.x195;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Modifies customer
 */
public class customermodify {
    public ComboBox countrycombobox, divisioncombobox;
    public TextField nametxtfld, addresstxtfld, postcodetxtfld, idtxtfld, phonetxtfld;
    String divname, nation;

    /**
     * Gets customer information
     * @param customerid id of customer
     * @return Hashmap of customer information
     */
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

    /**
     * Sets divisions
     */
    public void setdivs() {
        String countryname = (String) countrycombobox.getSelectionModel().getSelectedItem();
        switch (countryname) {
            case "U.S." -> {divisioncombobox.setItems(home.usdivar);nation = "U.S.";}
            case "UK" -> {divisioncombobox.setItems(home.ukdivar);nation = "UK";}
            case "Canada" -> {divisioncombobox.setItems(home.canadadivar);nation = "Canada";}
        }
    }

    /**
     * Updates customer
     */
    public void updatecustomer() {
        try {
            int divid = 0;
            String divisionname = divisioncombobox.getSelectionModel().getSelectedItem().toString();
            switch (nation) {
                case "U.S." -> divid = home.usdivhm.get(divisionname);
                case "UK" -> divid = home.ukdivhm.get(divisionname);
                case "Canada" -> divid = home.canadadivhm.get(divisionname);
            }
            String update = "UPDATE customers set Customer_Name='" + nametxtfld.getText() + "', Address='" + addresstxtfld.getText() + "', Postal_Code='" + postcodetxtfld.getText()+ "', Phone='" + phonetxtfld.getText() + "', Division_ID=" + divid  + " WHERE Customer_ID=" + customers.tomodifyid;
            HelloController.connection.createStatement().executeUpdate(update);
            Stage stage = (Stage) countrycombobox.getScene().getWindow();
            stage.close();
            home.launchActivity("customers");
        } catch (SQLException e) {System.out.println(e.getMessage());}
//        customers.tblView.refresh();
    }

    /**
     * Sets ui, hashmap, and array values
     */
    public void initialize() {
        countrycombobox.setItems(home.countryar);
        int curcustomerid = customers.tomodifyid;
        HashMap<String, String> custhm = getcustomerinfo(curcustomerid);
        idtxtfld.setText(custhm.get("id"));
        nametxtfld.setText(custhm.get("name"));addresstxtfld.setText(custhm.get("address"));
        postcodetxtfld.setText(custhm.get("postalcode"));phonetxtfld.setText(custhm.get("phone"));

        int divid = Integer.parseInt(custhm.get("divid"));
        divname = home.alldivshm.get(divid); nation = "";

        if (home.usdivar.contains(divname)) {divisioncombobox.setItems(home.usdivar); nation = "U.S.";}
        else if (home.ukdivar.contains(divname)) {divisioncombobox.setItems(home.ukdivar); nation = "UK";}
        else if (home.canadadivar.contains(divname)) {divisioncombobox.setItems(home.canadadivar); nation = "Canada";}

        countrycombobox.getSelectionModel().select(nation);
        divisioncombobox.getSelectionModel().select(divname);
    }
}
