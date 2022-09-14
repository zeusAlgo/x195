package com.example.x195;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class home {
    public Button customersBtn;
    public Button aptBtn;
    public Button reportsBtn;

    public void launchActivity(String activityname){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(home.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 1100);
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {System.out.println(e.getMessage());}
    }
}
