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
    public void setLocation(String location) {this.location=location;}
    public void setType(String type) {this.type=type;}
    public void setStart(String start) {this.start=start;}
    public void setEnd(String end) {this.end=end;}
    public void setCustomerId(int customerId) {this.customerId=customerId;}
    public void setUserId(int userId) {this.userId=userId;}
    public void setContactId(int contactId) {this.contactId=contactId;}

    public int getAppointmentId() {return appointmentId;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getType() {return type;}
    public String getStart() {return start;}
    public String getEnd() {return end;}
    public int getCustomerId() {return customerId;}
    public int getUserId() {return userId;}
    public int getContactId() {return contactId;}
}
