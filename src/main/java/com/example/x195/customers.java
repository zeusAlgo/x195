package com.example.x195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class customers {
    @FXML private TableColumn<customer, String> tblcola;
    @FXML private TableColumn<customer, String> tblcolb;
    @FXML private TableView<customer> tblview;
    ObservableList ar = FXCollections.observableArrayList();


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
    public void laundh2(ActionEvent actionEvent) { launchActivity("customermodify");}
    public void launch3(ActionEvent actionEvent) {}



    public void initialize() {
        tblcola.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcolb.setCellValueFactory(new PropertyValueFactory<>("name"));
        customer custa = new customer(1, "dklaj", "slkfj", "lksdj", "lsdkjf",23);
//        ar.add(String.valueOf(custa.getid()));
        ar.add(custa);

//        ar.add(
//                String.valueOf(new customer(1, "dklaj", "slkfj", "lksdj", "lsdkjf",23)));
//        ar.add("yes");
        tblview.setItems(ar);
    }
}
