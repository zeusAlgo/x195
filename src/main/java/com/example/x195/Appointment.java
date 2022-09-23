package com.example.x195;

/**
 * Appointment class
 */
public class Appointment {
    public int appointmentId, customerId, userId, contactId;
    public String title, description, location, type, start, end;

    /**
     * Constructs an appointment
     * @param appointmentId id of appointment
     * @param title title of appointment
     * @param description description of appointment
     * @param location location of appointment
     * @param type type of appointment
     * @param start beginning time of appointment
     * @param end end time of appointment
     * @param customerId id of customer
     * @param userId id of user
     * @param contactId id of contact
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId; this.title=title; this.description=description;
        this.location=location;this.type=type;this.start=start;this.end=end;this.customerId=customerId; this.userId=userId;this.contactId=contactId;
    }


    /**
     * Sets appointment id
     * @param appointmentId id of appointment
     */
    public void setAppointmentId(int appointmentId) {this.appointmentId=appointmentId;}

    /**
     * Sets appointment title
     * @param title title of appointment
     */
    public void setTitle(String title) {this.title=title;}

    /**
     * Sets description of appointment
     * @param description description of appointment
     */
    public void setDescription(String description) {this.description=description;}

    /**
     * Sets location of appointment
     * @param location location of appointment
     */
    public void setLocation(String location) {this.location=location;}

    /**
     * Sets type of appointment
     * @param type type of appointment
     */
    public void setType(String type) {this.type=type;}

    /**
     * Sets beginning time of appointment
     * @param start start time of appointment
     */
    public void setStart(String start) {this.start=start;}

    /**
     * Sets end time of appointment
     * @param end end time of appointment
     */
    public void setEnd(String end) {this.end=end;}

    /**
     * Sets customer id
     * @param customerId id of customer
     */
    public void setCustomerId(int customerId) {this.customerId=customerId;}

    /**
     * Sets user id
     * @param userId id of user
     */
    public void setUserId(int userId) {this.userId=userId;}

    /**
     * Sets contact id
     * @param contactId id of contact
     */
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
