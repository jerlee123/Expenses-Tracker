package com.example.expensestracker.profile;

public class userModel {
    private String phone;
    private String username;
    private String userId;
    private String profilePicUrl;


    public userModel() {
    }

    public userModel(String phone, String username, String userId, String profilePicUrl) {
        this.phone = phone;
        this.username = username;
        this.profilePicUrl = profilePicUrl;
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

    // Getter and Setter for profilePicUrl
    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
