package com.booreum.booreum.model;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String department;
    Boolean helper;
    String phone;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getHelper() {
        return helper;
    }

    public void setHelper(Boolean helper) {
        this.helper = helper;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
