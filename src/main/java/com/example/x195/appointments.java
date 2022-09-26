package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class appointments {
    public TextField modifytxtfld, deltxtfld;
    static HashMap<String, Integer> contactshm = new HashMap<>(), usershm = new HashMap<>(), customershm = new HashMap<>(), timeshm = new HashMap<>();
    static ObservableList<String> contactsar = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee"), usersar = FXCollections.observableArrayList("test", "admin"), customersar = FXCollections.observableArrayList(), timesar = FXCollections.observableArrayList("4 am", "5 am", "6 am", "7 am", "8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"), monthsar = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");static ObservableList<Integer> daysar = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
    static ObservableList<Integer> weeksar = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52);
    public static int toalterid = 0;
    private AppointmentModel appointmentModelM = new AppointmentModel();
    private AppointmentModel appointmentModelW = new AppointmentModel();
    @FXML public TableView<Appointment> tblViewM, tblViewW;
    @FXML public ComboBox monthcombobox, weekcombobox;
    @FXML public TableColumn appointmentContactColM, appointmentIdColM, appointmentTitleColM, appointmentDescriptionColM, appointmentLocationColM, appointmentTypeColM, appointmentStartColM, appointmentEndColM, appointmentCustomerIdColM, appointmentUserIdColM, appointmentIdColW, appointmentTitleColW, appointmentDescriptionColW, appointmentLocationColW, appointmentContactColW, appointmentTypeColW, appointmentStartColW, appointmentEndColW, appointmentCustomerIdColW, appointmentUserIdColW;

    public void launchA(ActionEvent actionEvent) {
        Stage stage = (Stage) modifytxtfld.getScene().getWindow();
        stage.close();
        home.launchActivity("aptadd");}

    public void launchB(ActionEvent actionEvent) {
        toalterid = Integer.parseInt(modifytxtfld.getText());
        Stage stage = (Stage) modifytxtfld.getScene().getWindow();
        stage.close();
        home.launchActivity("aptmodify");}

    public HashMap<String, Integer> getallcustomers() {
        HashMap<String, Integer> customershm = new HashMap<>();
        try {
            ResultSet res = HelloController.connection.createStatement().executeQuery("Select * from customers");
            while (res.next()) {
                String custname = res.getString("Customer_Name");
                customershm.put(custname, res.getInt("Customer_ID"));
                customersar.add(custname);
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return customershm;
    }

    public HashMap<Integer, String> getallcustomers2() {
        HashMap<Integer, String> customershm = new HashMap<>();
        try {
            ResultSet res = HelloController.connection.createStatement().executeQuery("Select * from customers");
            while (res.next()) {
                String custname = res.getString("Customer_Name");
                customershm.put(res.getInt("Customer_ID"), custname);
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return customershm;
    }

    public void deleteapt(ActionEvent actionEvent) {
        int aptid= Integer.parseInt(deltxtfld.getText());
        String type =  aptmodify.getaptinfo(aptid).get("type");
        home.del("appointments", "Appointment_ID", aptid);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment " + aptid + " : "+ type + " canceled.");
        alert.showAndWait();
        Stage stage = (Stage) modifytxtfld.getScene().getWindow();
        stage.close();
        home.launchActivity("appointments");
    }

    public void setMonthTblView() {tblViewM.setItems(appointmentModelM.getAppointmentInfoM( monthcombobox.getSelectionModel().getSelectedIndex()+1));}

    public void setWeekTblView() {tblViewW.setItems(appointmentModelW.getAppointmentInfoW(weekcombobox.getSelectionModel().getSelectedIndex()+1));}

    public void initialize() {
       contactshm.put("Anika Costa", 1);contactshm.put("Daniel Garcia", 2);contactshm.put("Li Lee", 3);
       usershm.put("test", 1);usershm.put("admin", 2);
       timeshm.put("4 am", 4); timeshm.put("5 am", 5);timeshm.put("6 am", 6);timeshm.put("7 am", 7);timeshm.put("8 am", 8);timeshm.put("9 am", 9);timeshm.put("10 am", 10);timeshm.put("11 am",11);timeshm.put("12 pm", 12);timeshm.put("1 pm", 13);timeshm.put("2 pm", 14);timeshm.put("3 pm", 15);timeshm.put("4 pm", 16);timeshm.put("5 pm",17);timeshm.put("6 pm",18);timeshm.put("7 pm", 19);timeshm.put("8 pm", 20);timeshm.put("9 pm",21);timeshm.put("10 pm",22);timeshm.put("11 pm", 23);
       customershm = getallcustomers();
       monthcombobox.setItems(monthsar);
       weekcombobox.setItems(weeksar);

       appointmentIdColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentId"));
       appointmentTitleColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
       appointmentDescriptionColM.setCellValueFactory(new PropertyValueFactory<Appointment,String>("description"));
       appointmentLocationColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
       appointmentContactColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contactId"));
       appointmentTypeColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
       appointmentStartColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
       appointmentEndColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
       appointmentCustomerIdColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerId"));
       appointmentUserIdColM.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
       tblViewM.setItems(appointmentModelM.getAppointments());

       appointmentIdColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentId"));
       appointmentTitleColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
       appointmentDescriptionColW.setCellValueFactory(new PropertyValueFactory<Appointment,String>("description"));
       appointmentLocationColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
       appointmentContactColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contactId"));
       appointmentTypeColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
       appointmentStartColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
       appointmentEndColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
       appointmentCustomerIdColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerId"));
       appointmentUserIdColW.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
        tblViewW.setItems(appointmentModelW.getAppointments());
    }


    public void setappointmentidW(javafx.scene.input.MouseEvent mouseEvent) {
        toalterid = tblViewW.getSelectionModel().getSelectedItem().getAppointmentId();
        modifytxtfld.setText(String.valueOf(toalterid));
        deltxtfld.setText(String.valueOf(toalterid));
    }

    public void setappointmentidM(javafx.scene.input.MouseEvent mouseEvent) {
        toalterid = tblViewM.getSelectionModel().getSelectedItem().getAppointmentId();
        modifytxtfld.setText(String.valueOf(toalterid));
        deltxtfld.setText(String.valueOf(toalterid));
    }
}
