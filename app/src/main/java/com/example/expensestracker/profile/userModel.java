package com.example.expensestracker.profile;

public class userModel {
    private String phone;
    private String username;
    private String userId;

    public userModel() {
    }

    public userModel(String phone, String username, String userId) {
        this.phone = phone;
        this.username = username;

        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
