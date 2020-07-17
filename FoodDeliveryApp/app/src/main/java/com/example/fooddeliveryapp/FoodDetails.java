package com.example.fooddeliveryapp;

public class FoodDetails {
    String foodname;
    String description;
    String price;

    public FoodDetails(String foodname, String description, String price) {
        this.foodname = foodname;
        this.description = description;
        this.price = price;
    }

    public FoodDetails() {
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
