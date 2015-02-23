package com.tamerbarsbay.quickshoppiedesigns.model;

/**
 * Created by Tamer on 2/22/2015.
 */
public class Category {

    public String id;
    public String title;
    public int iconResource;

    public Category(String id, String content, int iconResourceId) {
        this.id = id;
        this.title = content;
        this.iconResource = iconResourceId;
    }

    @Override
    public String toString() {
            return title;
        }
}
