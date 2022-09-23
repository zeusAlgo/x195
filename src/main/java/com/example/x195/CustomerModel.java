package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public ObservableList<Customer> customerInfo;
    public CustomerModel() {customerInfo = FXCollections.observableArrayList(); getCustomerInfo();}

    public void getCustomerInfo() {
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from customers");
            while (rs.next()) {
                Customer curCustomer = new Customer (
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getInt("Division_ID"));
                customerInfo.add(curCustomer);
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
    }
    public ObservableList<Customer> getCustomers() {return customerInfo;}
}
