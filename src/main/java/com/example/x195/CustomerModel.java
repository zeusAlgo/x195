package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    private ObservableList<Customer> customerinfo;
    public CustomerModel() {customerinfo = FXCollections.observableArrayList(); getcustomerinfo();}

    private void getcustomerinfo() {
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from customers");
            while (rs.next()) {
                Customer curcustomer = new Customer (
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getInt("Division_ID")
                );
                customerinfo.add(curcustomer);
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
    }
    public ObservableList<Customer> getcustomers() {return customerinfo;}
}
