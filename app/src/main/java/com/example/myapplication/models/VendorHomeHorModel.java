package com.example.myapplication.models;

public class VendorHomeHorModel {
    int image;
    String name;
    boolean avail;

    public VendorHomeHorModel(int image, String name) {
        this.image = image;
        this.name = name;
        //this.avail = avail;
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

    /*public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public boolean isAvail() {
        return avail;
    }*/
}