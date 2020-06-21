package com.booreum.model;

public class Errand_ {

    String user;
    String category;
    String from;
    String to;
    int price;
    String dueto;
    String desc;

    public Errand_(String user, String category, String from, String to, int price, String dueto, String desc) {
        this.user = user;
        this.category = category;
        this.from = from;
        this.to = to;
        this.price = price;
        this.dueto = dueto;
        this.desc = desc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDueto() {
        return dueto;
    }

    public void setDueto(String dueto) {
        this.dueto = dueto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
