package com.example.practicemessagingapp;

public class User {

    private String profile;
    private String nickname;
    private boolean newmark;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNewmark() {
        return newmark;
    }

    public void setNewmark(boolean newmark) {
        this.newmark = newmark;
    }
}
