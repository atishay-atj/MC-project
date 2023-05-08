package com.example.myapplication.OrderDetails;

public class Item extends OrderModel {
    private String foodItem;
    private int quantity;

    public Item(String foodItem, int quantity) {
        super();

        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public String getName() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }
}