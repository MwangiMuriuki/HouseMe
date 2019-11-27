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
    Boolean hasParking;
    Boolean hasCctv;
    Boolean hasWifi;
    Boolean hasAccessibility;
    Boolean hasLaundry;
    Boolean hasPlayground;
    Boolean hasSolar;
    Boolean hasSwimmingpool;
    Boolean forSale;
    Boolean isResidential;

    public TestModelClass() {
    }

    public TestModelClass(String featured_image, List<String> other_images, String price, String region,
                          String location, String bedrooms, String bathrooms, String description,
                          Boolean hasParking, Boolean hasCctv, Boolean hasWifi, Boolean hasAccessibility,
                          Boolean hasLaundry, Boolean hasPlayground, Boolean hasSolar, Boolean hasSwimmingpool,
                          Boolean forSale, Boolean isResidential) {

        this.featured_image = featured_image;
        this.other_images = other_images;
        this.price = price;
        this.region = region;
        this.location = location;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.description = description;
        this.hasParking = hasParking;
        this.hasCctv = hasCctv;
        this.hasWifi = hasWifi;
        this.hasAccessibility = hasAccessibility;
        this.hasLaundry = hasLaundry;
        this.hasPlayground = hasPlayground;
        this.hasSolar = hasSolar;
        this.hasSwimmingpool = hasSwimmingpool;
        this.forSale = forSale;
        this.isResidential = isResidential;
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

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean getHasCctv() {
        return hasCctv;
    }

    public void setHasCctv(Boolean hasCctv) {
        this.hasCctv = hasCctv;
    }

    public Boolean getHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public Boolean getHasAccessibility() {
        return hasAccessibility;
    }

    public void setHasAccessibility(Boolean hasAccessibility) {
        this.hasAccessibility = hasAccessibility;
    }

    public Boolean getHasLaundry() {
        return hasLaundry;
    }

    public void setHasLaundry(Boolean hasLaundry) {
        this.hasLaundry = hasLaundry;
    }

    public Boolean getHasPlayground() {
        return hasPlayground;
    }

    public void setHasPlayground(Boolean hasPlayground) {
        this.hasPlayground = hasPlayground;
    }

    public Boolean getHasSolar() {
        return hasSolar;
    }

    public void setHasSolar(Boolean hasSolar) {
        this.hasSolar = hasSolar;
    }

    public Boolean getHasSwimmingpool() {
        return hasSwimmingpool;
    }

    public void setHasSwimmingpool(Boolean hasSwimmingpool) {
        this.hasSwimmingpool = hasSwimmingpool;
    }

    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    public Boolean getResidential() {
        return isResidential;
    }

    public void setResidential(Boolean residential) {
        isResidential = residential;
    }
}
