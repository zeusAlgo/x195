package com.example.x195;

public class Appointment {
    public int appointmentId, customerId, userId, contactId;
    public String title, description, location, type, start, end;

    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId; this.title=title; this.description=description;
        this.location=location;this.type=type;this.start=start;this.end=end;this.customerId=customerId; this.userId=userId;this.contactId=contactId;
    }
    public void setAppointmentId(int appointmentId) {this.appointmentId=appointmentId;}
    public void setTitle(String title) {this.title=title;}
    public void setDescription(String description) {this.description=description;}
    
}
