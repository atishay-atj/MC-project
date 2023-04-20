package com.example.myapplication.models;

public class CartModel {
    int image;
    String name;
    String product_name;
    String price;


    public CartModel(int image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;


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
