package com.example.practicemessagingapp;

public class User {
    private String profile;
    private String nickname;
    private String lastContext;

    public User() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastContext() {
        return lastContext;
    }

    public void setLastContext(String lastContext) {
        this.lastContext = lastContext;
    }
}
