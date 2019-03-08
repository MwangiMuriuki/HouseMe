package com.example.houseme;

public class ModalClass {
    private String location;
    private String price;
    private String size;
    private String bath;
    private String picture;
    private String description;

    public ModalClass() {
    }

    public ModalClass(String location, String price, String bath, String size, String picture, String description) {
        this.location = location;
        this.price = price;
        this.size = size;
        this.bath = bath;
        this.picture = picture;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String bedrooms) {
        this.size = size;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bathrooms) {
        this.bath = bath;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String image) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

