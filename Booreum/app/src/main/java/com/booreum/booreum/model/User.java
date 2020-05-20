package com.booreum.booreum.model;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;
    private String phone;

    public User() {

    }

    public User(String id, String name,  String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
