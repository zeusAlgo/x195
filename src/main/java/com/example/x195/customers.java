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
    @FXML public TableColumn<Customer, String> customerIdCol;
    @FXML public TableColumn<Customer, String> customerNameCol;
    @FXML public TableColumn<Customer, String> customerAddressCol;
    @FXML public TableColumn<Customer, String> customerPostalCodeCol;
    @FXML public TableColumn<Customer, String> customerPhoneCol;
    @FXML public TableColumn<Customer, String> customerDivIdCol;
    @FXML public final ObservableList<Customer> customerInfo = FXCollections.observableArrayList();

    public static int tomodifyid = 0;

    public void launchActivity(String activityname){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource(activityname+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
            Stage stage = new Stage();
            stage.setTitle(activityname);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {System.out.println(e.getMessage());}
    }
    public void launch1(ActionEvent actionEvent) { launchActivity("customeradd");}
    public void laundh2(ActionEvent actionEvent) {
        tomodifyid = Integer.parseInt(modifyTxtFld.getText());
        launchActivity("customermodify");}

    //todo: can clean this up on btn.pressed -> fn
    @FXML public void delete() {
        Integer customerid = Integer.valueOf(deleteTxtFld.getText());
        try {HelloController.connection.createStatement().execute("Delete from appointments where Customer_ID=" + customerid);
        } catch (SQLException e) {System.out.println(e.getMessage());}
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerid + " deleted.");
        alert.showAndWait();
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
