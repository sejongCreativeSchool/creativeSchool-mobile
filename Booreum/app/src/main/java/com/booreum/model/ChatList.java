package com.booreum.model;

import androidx.annotation.NonNull;

public class ChatList
{
    @NonNull
    private String chatId;

    private String photoUrl;
    private String name;
    private String lastChat;

    public ChatList(String chatId) {
        this.chatId = chatId;
    }

    public ChatList(String chatId, String photoUrl, String name, String lastChat) {
        this.chatId = chatId;
        this.photoUrl = photoUrl;
        this.name = name;
        this.lastChat = lastChat;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }
}
