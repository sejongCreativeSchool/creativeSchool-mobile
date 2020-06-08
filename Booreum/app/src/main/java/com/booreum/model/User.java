package com.booreum.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    public final static String CURRNET_USER_INTENT_CODE = "currentUser";

    @NonNull
    private String name;

    @NonNull
    private String accessToken;

    @NonNull
    private Boolean helper;

    @NonNull
    private String phone;

    private String department;

    public User(@NonNull String name, @NonNull String accessToken, @NonNull Boolean helper, @NonNull String phone) {
        this.name = name;
        this.accessToken = accessToken;
        this.helper = helper;
        this.phone = phone;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    public Boolean getHelper() {
        return helper;
    }

    public void setHelper(@NonNull Boolean helper) {
        this.helper = helper;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
