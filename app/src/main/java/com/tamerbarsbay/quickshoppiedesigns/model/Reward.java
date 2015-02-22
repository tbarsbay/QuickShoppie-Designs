package com.tamerbarsbay.quickshoppiedesigns.model;

/**
 * Created by Tamer on 2/21/2015.
 */
public class Reward {

    private String storeName;
    private String userPoints;
    private boolean dealAvailable;

    public Reward(String _storeName, String _userPoints, boolean _dealAvailable) {
        storeName = _storeName;
        userPoints = _userPoints;
        dealAvailable = _dealAvailable;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getUserPoints() {
        return userPoints;
    }

    public boolean isDealAvailable() {
        return dealAvailable;
    }

}
