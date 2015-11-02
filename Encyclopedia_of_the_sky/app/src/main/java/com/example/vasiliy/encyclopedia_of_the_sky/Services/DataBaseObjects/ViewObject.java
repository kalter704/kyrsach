package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;


public class ViewObject extends MyObject {
    private String text;

    public ViewObject(String name, String text) {
        super(name, 0);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
