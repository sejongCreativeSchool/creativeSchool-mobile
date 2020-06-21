package com.booreum.model;

public class Errand {

    boolean done;
    String _id;
    String category;
    String from;
    String to;
    int price;
    String dueto;
    User user;
    String desc;

    public Errand() {
    }

    public Errand(String category, String from, String to, int price, String dueto, User user, String desc) {
        this.category = category;
        this.from = from;
        this.to = to;
        this.price = price;
        this.dueto = dueto;
        this.user = user;
        this.desc = desc;
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
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
