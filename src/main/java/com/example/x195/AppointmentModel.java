package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentModel {
    public ObservableList<Appointment> appointmentInfo;
    public ObservableList<Appointment> appointmentInfoM;
    public ObservableList<Appointment> appointmentInfoW;
    public AppointmentModel() {
        appointmentInfo = FXCollections.observableArrayList(); getAppointmentInfo();
        appointmentInfoM = FXCollections.observableArrayList();
        appointmentInfoW = FXCollections.observableArrayList();
    }


    //todo: week month
    public void getAppointmentInfo() {
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments");
            while (rs.next()) {
                Appointment curApt = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));
                appointmentInfo.add(curApt);
            }
        } catch (SQLException e) { System.out.println(e.getMessage());}
    }

       public ObservableList<Appointment> getAppointmentInfoM(Integer month) {
            ObservableList<Appointment> appointmentInfoM = FXCollections.observableArrayList();
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments where month(Start)="+ month);
            while (rs.next()) {
                Appointment curApt = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));
                appointmentInfoM.add(curApt);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();}
        return appointmentInfoM;
    }

    public ObservableList<Appointment> getAppointmentInfoW(Integer week) {
        ObservableList<Appointment> appointmentInfoW = FXCollections.observableArrayList();
        try {
            ResultSet rs = HelloController.connection.createStatement().executeQuery("Select * from appointments where week(Start)=" + week);
            while (rs.next()) {
                Appointment curApt = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));
                appointmentInfoW.add(curApt);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        return appointmentInfoW;
    }

    public ObservableList<Appointment> getAppointments() {return appointmentInfo;}
    public ObservableList<Appointment> getAppointmentsM(Integer month) {return getAppointmentInfoM(month);}
    public ObservableList<Appointment> getAppointmentsW(Integer week) {return getAppointmentInfoW(week);}
}
