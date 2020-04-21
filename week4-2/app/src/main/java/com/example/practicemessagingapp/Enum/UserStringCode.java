package com.example.practicemessagingapp.Enum;

public enum UserStringCode {
    PROFILE("Profile"),
    NICKNAME("NickName"),
    ID("Id");

    final private String value;

    UserStringCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
