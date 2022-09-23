package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class customers {
    public TextField modifyTxtFld, deleteTxtFld;
    public Button delBtn;
    private CustomerModel customerModel = new CustomerModel();
    @FXML private TableView<Customer> tblview;
    @FXML private TableColumn<Customer, String> customerIdCol, customerNameCol, customerAddressCol, customerPostalCodeCol, customerPhoneCol, customerDivIdCol;
    @FXML private final ObservableList<Customer> customerinfo = FXCollections.observableArrayList();

    

    @FXML private TableColumn<Customer, String> tblcola, tblcolb;
    @FXML private TableView<customer> tblview;
    public static ObservableList<Customer> ar;
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
    @FXML public void delete() {home.del("customers", "Customer_ID", Integer.parseInt(deleteTxtFld.getText()));}

    public void initialize() {

        tblcola.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcolb.setCellValueFactory(new PropertyValueFactory<>("name"));
        ar = FXCollections.observableArrayList();
    }
}
