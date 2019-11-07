package com.example.houseme.Models;

import java.util.List;

public class TestModelClass {

    String featured_image;
    List<String> other_images;
    String price;
    String region;
    String location;
    String bedrooms;
    String bathrooms;
    String description;
    Boolean forSale;

    public TestModelClass() {
    }

    public TestModelClass(String featured_image, List<String> other_images, String price, String region,
                          String location, String bedrooms, String bathrooms, String description, Boolean forSale) {
        this.featured_image = featured_image;
        this.other_images = other_images;
        this.price = price;
        this.region = region;
        this.location = location;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.description = description;
        this.forSale = forSale;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public List<String> getOther_images() {
        return other_images;
    }

    public void setOther_images(List<String> other_images) {
        this.other_images = other_images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }
}
