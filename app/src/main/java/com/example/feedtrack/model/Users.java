package com.example.feedtrack.model;

public class Users {
    String user_name,password,email,profile_img,user_id;

    public Users(String user_name, String password, String email, String profile_img, String user_id) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.profile_img = profile_img;
        this.user_id = user_id;
    }

    public Users() {
    }

    public Users(String user_name, String password, String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
