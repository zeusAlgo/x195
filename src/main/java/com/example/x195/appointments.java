package com.example.x195;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class appointments {
    public TextField modifytxtfld, deltxtfld;

    public void launchA(ActionEvent actionEvent) {home.launchActivity("aptadd");}
    public void launchB(ActionEvent actionEvent) {home.launchActivity("aptmmodify");}


}
