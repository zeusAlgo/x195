package com.example.x195;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    public TextField usrTxtFld;
    public PasswordField passTxtFld;

    @FXML Label geoLbl;
    @FXML Button signinBtn;
    @FXML protected void printHi() {
        String usrcreds = usrTxtFld.getText();
        String passcreds = passTxtFld.getText();

        System.out.println("Hi");
    }
    
}