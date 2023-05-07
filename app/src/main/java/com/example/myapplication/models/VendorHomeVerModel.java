package com.example.myapplication.models;

public class VendorHomeVerModel {
    int image;
    String name;
    Boolean availibility;

    public VendorHomeVerModel(int image, String name, boolean availibility) {
        this.image = image;
        this.name = name;
        this.availibility= availibility ;
    }
//    public HomeVerModel(int image, String name) {
//        this.image = image;
//        this.name = name;
////    this.price = price;
//    }

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

    public boolean getavailibility() {
        return availibility;
    }

    public void setAvailibility(boolean availibility) {
        this.availibility = availibility ;
    }
}
