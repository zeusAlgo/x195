package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class customers {
    public TextField modifyTxtFld, deleteTxtFld;
    public Button delBtn;
    private CustomerModel customerModel = new CustomerModel();
    @FXML public TableView<Customer> tblView;
    @FXML public TableColumn<Customer, String> customerIdCol, customerNameCol, customerAddressCol, customerPostalCodeCol, customerPhoneCol, customerDivIdCol;
    @FXML public final ObservableList<Customer> customerInfo = FXCollections.observableArrayList();

    public static int tomodifyid = 0;

    public void launch1(ActionEvent actionEvent) {
        Stage stage = (Stage) delBtn.getScene().getWindow();
        stage.close();
        home.launchActivity("customeradd");}

    public void launch2(ActionEvent actionEvent) {
        tomodifyid = Integer.parseInt(modifyTxtFld.getText());
        Stage stage = (Stage) delBtn.getScene().getWindow();
        stage.close();
        home.launchActivity("customermodify");}

    @FXML public void delete() {
        Integer customerid = Integer.valueOf(deleteTxtFld.getText());
        try {HelloController.connection.createStatement().execute("Delete from appointments where Customer_ID=" + customerid);
        } catch (SQLException e) {System.out.println(e.getMessage());}
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerid + " deleted.");
        alert.showAndWait();
        Stage stage = (Stage) delBtn.getScene().getWindow();
        stage.close();
        home.del("customers", "Customer_ID", Integer.parseInt(deleteTxtFld.getText()));
        home.launchActivity("customers");
    }

    public void initialize() {
        customerIdCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerPostalCodeCol.setCellValueFactory(new  PropertyValueFactory<Customer, String>("postalcode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerDivIdCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("divid"));
        tblView.setItems(customerModel.getCustomers());
    }

    public void setcustomerid(MouseEvent mouseEvent) {
        tomodifyid = tblView.getSelectionModel().getSelectedItem().getId();
        modifyTxtFld.setText(String.valueOf(tomodifyid));
        deleteTxtFld.setText(String.valueOf(tomodifyid));
    }
}
