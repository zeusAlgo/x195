package com.example.x195;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class customers {


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

    public void laundh2(ActionEvent actionEvent) { launchActivity("customermodify");
    }

    public void launch3(ActionEvent actionEvent) {

    }
}
