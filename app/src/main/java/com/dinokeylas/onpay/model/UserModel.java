package com.dinokeylas.onpay.model;

public class UserModel {

    private String userName, fullName, emailAddress, address, profileImageUrl, phoneNumber, password;
    private int pin;
    private double balance;

    public UserModel(){

    }

    public UserModel(String userName, String fullName, String emailAddress, String address, String profileImageUrl, String phoneNumber, String password, int pin) {
        this.userName = userName;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.profileImageUrl = profileImageUrl;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.pin = pin;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateBalance(double nominal){
        balance = balance + nominal;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }
}
