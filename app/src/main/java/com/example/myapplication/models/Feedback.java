package com.example.myapplication.models;

public class Feedback {

    private String customerId;
    private String itemName;
    private float rating;
    private String feedback;

    public Feedback(String customerId, String itemName, float rating, String feedback) {
        this.customerId = customerId;
        this.itemName = itemName;
        this.rating = rating;
        this.feedback = feedback;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getItemName() {
        return itemName;
    }

    public float getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

}