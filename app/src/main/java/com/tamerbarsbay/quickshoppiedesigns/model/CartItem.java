package com.tamerbarsbay.quickshoppiedesigns.model;

/**
 * Created by Tamer on 2/19/2015.
 */
public class CartItem {

    private String mTitle;
    private int mQuantity;
    private double mUnitCost;

    public CartItem(String title, int quantity, double unitCost) {
        mTitle = title;
        mQuantity = quantity;
        mUnitCost = unitCost;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public double getUnitCost() {
        return mUnitCost;
    }

    public double getTotalCost() {
        return mQuantity*mUnitCost;
    }

}
