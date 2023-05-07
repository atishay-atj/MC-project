package com.example.myapplication.models;

public class CartModel {
    int image;
    String name;
    String quantity;
    String price;


    public CartModel(int image, String name, String quantity, String price) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}