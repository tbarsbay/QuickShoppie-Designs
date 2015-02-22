package com.tamerbarsbay.quickshoppiedesigns.model;

/**
 * Created by Tamer on 2/20/2015.
 */
public class Event {

    private String title;
    private String description;
    private String dateAndTime;
    private String website;
    private String address;

    public Event(String _title, String _description, String _dateAndTime, String _website, String _address) {
        title = _title;
        description = _description;
        dateAndTime = _dateAndTime;
        website = _website;
        address = _address;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

}
