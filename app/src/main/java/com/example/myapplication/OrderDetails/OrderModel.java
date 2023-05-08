package com.example.myapplication.OrderDetails;

import java.util.ArrayList;

public class OrderModel {
    private String phoneNumber;
    private ArrayList<Item> itemList ;





    public OrderModel(String phoneNumber, ArrayList<Item> itemList) {
        this.phoneNumber = phoneNumber;
        this.itemList = itemList;
    }

    public OrderModel() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }





}