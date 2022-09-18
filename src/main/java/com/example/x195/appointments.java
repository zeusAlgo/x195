package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class appointments {
    public TextField modifytxtfld, deltxtfld;
    static HashMap<String, Integer> contactshm = new HashMap<>();
    static HashMap<String, Integer> usershm = new HashMap<>();

    public static int toalterid = 0;

    public void launchA(ActionEvent actionEvent) {home.launchActivity("aptadd");}
    public void launchB(ActionEvent actionEvent) {
        toalterid = Integer.parseInt(modifytxtfld.getText());
        home.launchActivity("aptmmodify");}


    public void deleteapt(ActionEvent actionEvent) { home.del("appointments", "Appointment_ID", Integer.parseInt(deltxtfld.getText()));
    }
}
