package com.example.houseme.Models;

public class PropertyInfoModelClass {

    String featured_image;
    String extra_image_one;
    String extra_image_two;
    String extra_image_three;
    String extra_image_four;
    String extra_image_five;
    String price;
    String location;
    String bedrooms;
    String bathrooms;
    String description;
    Boolean forSale;

    public PropertyInfoModelClass() {
    }

    public PropertyInfoModelClass(String featured_image, String extra_image_one, String extra_image_two,
                                  String extra_image_three, String extra_image_four, String extra_image_five,
                                  String price, String location, String bedrooms, String bathrooms, String description, Boolean forSale) {

        this.featured_image = featured_image;
        this.extra_image_one = extra_image_one;
        this.extra_image_two = extra_image_two;
        this.extra_image_three = extra_image_three;
        this.extra_image_four = extra_image_four;
        this.extra_image_five = extra_image_five;
        this.price = price;
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

    public String getExtra_image_one() {
        return extra_image_one;
    }

    public void setExtra_image_one(String extra_image_one) {
        this.extra_image_one = extra_image_one;
    }

    public String getExtra_image_two() {
        return extra_image_two;
    }

    public void setExtra_image_two(String extra_image_two) {
        this.extra_image_two = extra_image_two;
    }

    public String getExtra_image_three() {
        return extra_image_three;
    }

    public void setExtra_image_three(String extra_image_three) {
        this.extra_image_three = extra_image_three;
    }

    public String getExtra_image_four() {
        return extra_image_four;
    }

    public void setExtra_image_four(String extra_image_four) {
        this.extra_image_four = extra_image_four;
    }

    public String getExtra_image_five() {
        return extra_image_five;
    }

    public void setExtra_image_five(String extra_image_five) {
        this.extra_image_five = extra_image_five;
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
