package com.booreum.model;

public class ChatData {
    private String msg;
    private String name;
    private String time;

    public ChatData() {
    }

    public ChatData(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
