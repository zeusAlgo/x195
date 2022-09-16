package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashMap;

public class home {
    public Button customersBtn, aptBtn, reportsBtn;
    HashMap<String, Integer> countryhm = new HashMap<>();
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

    public void launchA(ActionEvent actionEvent) { launchActivity("customers");}
    public void launchB(ActionEvent actionEvent) { launchActivity("appointments");}
    public void launchC(ActionEvent actionEvent) { launchActivity("reports");}

    public void initialize() {
        countryhm.put("U.S", 1); countryhm.put("UK", 2); countryhm.put("Canada", 3);
        ObservableList<String> countryar = FXCollections.observableArrayList();
        countryar.add("U.S."); countryar.add("UK"); countryar.add("Canada");
    }
}
