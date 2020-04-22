package com.example.practicesharedpreferences;

public class Memo {
    String title;
    String text;
    String time;

    final static String TITLE_INTENT_KEY = "newTitle";
    final static String CONTENTS_INTENT_KEY = "newContents";
    final static String TIME_INTENT_KEY = "newTime";

    public Memo(String title, String text, String time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
