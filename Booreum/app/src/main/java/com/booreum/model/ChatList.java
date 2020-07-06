package com.booreum.model;

import java.util.ArrayList;
import java.util.List;

public class ChatList
{
    String needer;
    String helper;
    String neederName;
    String helperName;

    List<ChatData> chat = new ArrayList<>();

    public void addChatData(ChatData data){
        chat.add(data);
    }

    public List<ChatData> getChat() {
        return chat;
    }

    public void setChat(List<ChatData> chat) {
        this.chat = chat;
    }

    public String getLastMsg(){
        return chat.get(chat.size()-1).getMsg();
    }

    public String getNeeder() {
        return needer;
    }

    public void setNeeder(String needer) {
        this.needer = needer;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public String getNeederName() {
        return neederName;
    }

    public void setNeederName(String neederName) {
        this.neederName = neederName;
    }

    public String getHelperName() {
        return helperName;
    }

    public void setHelperName(String helperName) {
        this.helperName = helperName;
    }
}
