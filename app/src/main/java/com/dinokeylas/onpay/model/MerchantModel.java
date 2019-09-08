package com.dinokeylas.onpay.model;

public class MerchantModel {

    private String name, location, promotionDetail;
    private double balance;

    public MerchantModel() {

    }

    public MerchantModel(String name, String location, String promotionDetail, double balance) {
        this.name = name;
        this.location = location;
        this.promotionDetail = promotionDetail;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPromotionDetail() {
        return promotionDetail;
    }

    public void setPromotionDetail(String promotionDetail) {
        this.promotionDetail = promotionDetail;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
