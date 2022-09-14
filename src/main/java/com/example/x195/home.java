package com.example.x195;
import javafx.event.ActionEvent;
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
}
