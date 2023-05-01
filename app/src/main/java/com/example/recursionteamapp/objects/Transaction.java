package com.example.recursionteamapp.objects;

import android.os.Parcel;

public class Transaction{
    private int id;

    private Match matchInfo;
    private int userId;

    private String date;
    private String code;
    private String type;
    private int quantity;
    private double totalPrice;

    public Transaction(int id, Match matchInfo, int userId, String date, String code, String type, int quantity, double totalPrice) {
        this.id = id;
        this.matchInfo = matchInfo;
        this.userId = userId;
        this.date = date;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Match getMatchInfo() {
        return matchInfo;
    }

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
