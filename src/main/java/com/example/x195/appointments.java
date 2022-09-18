package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class appointments {
    public TextField modifytxtfld, deltxtfld;
    static HashMap<String, Integer> contactshm = new HashMap<>();
    static HashMap<String, Integer> usershm = new HashMap<>();
    static ObservableList<String> contactsar = FXCollections.observableArrayList();
    static ObservableList<String> usersar = FXCollections.observableArrayList();


    public static int toalterid = 0;

    public void launchA(ActionEvent actionEvent) {home.launchActivity("aptadd");}
    public void launchB(ActionEvent actionEvent) {
        toalterid = Integer.parseInt(modifytxtfld.getText());
        home.launchActivity("aptmmodify");}


    public void deleteapt(ActionEvent actionEvent) { home.del("appointments", "Appointment_ID", Integer.parseInt(deltxtfld.getText()));
    }

    public void initialize() {
       contactshm.put("Anika Costa", 1);
       contactshm.put("Daniel Garcia", 2);
       contactshm.put("Li Lee", 3);
       usershm.put("test", 1);
       usershm.put("admin", 2);

    }
}
