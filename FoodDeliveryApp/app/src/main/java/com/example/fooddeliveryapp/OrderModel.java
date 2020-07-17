package com.example.fooddeliveryapp;

import java.io.Serializable;

public class OrderModel implements Serializable {

    String currentStatus,description,foodname,price,qty,totalprice,orderno;

    public OrderModel(String currentStatus, String description, String foodname, String price, String qty, String totalprice, String orderno) {
        this.currentStatus = currentStatus;
        this.description = description;
        this.foodname = foodname;
        this.price = price;
        this.qty = qty;
        this.totalprice = totalprice;
        this.orderno = orderno;

    }

    public OrderModel() {

    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}
