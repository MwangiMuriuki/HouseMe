package com.example.houseme.Models;

public class ModelClassHouseInfo {
    private String picture;
    private String price;
    private String location;
    private String size;
    private String bath;
    private String description;

    public ModelClassHouseInfo() {
    }

    public ModelClassHouseInfo(String picture, String price, String location, String size, String bath, String description) {
        this.picture = picture;
        this.price = price;
        this.location = location;
        this.size = size;
        this.bath = bath;
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

