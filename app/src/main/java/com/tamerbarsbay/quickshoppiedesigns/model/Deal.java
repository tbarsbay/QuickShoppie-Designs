package com.tamerbarsbay.quickshoppiedesigns.model;

/**
 * Created by Tamer on 2/21/2015.
 */
public class Deal {

    private String imageUrl;
    private String title;
    private String host;
    private int labelBgColor;

    public Deal(String _title, String _host, int _labelBgColor, String _imageUrl) {
        title = _title;
        host = _host;
        labelBgColor = _labelBgColor;
        imageUrl = _imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getHost() {
        return host;
    }

    public int getLabelBgColor() {
        return labelBgColor;
    }
}
