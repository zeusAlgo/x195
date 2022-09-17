package com.example.x195;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
            Statement statement = HelloController.connection.createStatement();
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

    @FXML
    setdivs() {

    }

    public void initialize() {
        int curcustomerid = customers.tomodifyid;
        HashMap<String, String> custhm = getcustomerinfo(curcustomerid);
        idtxtfld.setText(custhm.get("id"));
        nametxtfld.setText(custhm.get("name"));addresstxtfld.setText(custhm.get("address"));
        postcodetxtfld.setText(custhm.get("postalcode"));phonetxtfld.setText(custhm.get("phone"));

        int divid = Integer.parseInt(custhm.get("divid"));
        String divname = home.alldivshm.get(divid);
//        System.out.println(divid);
//        System.out.println(divname);
//        System.out.println(home.usdivar);
//        System.out.println(home.alldivshm);
//        System.out.println(home.usdivhm);

        //todo: investigate why the ars and hs are empty
        if (home.usdivar.contains(divname)) divisioncombobox.setItems(home.usdivar);
        else if (home.ukdivar.contains(divname)) divisioncombobox.setItems(home.ukdivar);
        else if (home.canadadivar.contains(divname)) divisioncombobox.setItems(home.canadadivar);

        countrycombobox.setItems(home.countryar);
    }
}
