package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class MyObject {
    private String name;
    private int intId;

    public MyObject(String name, int intId) {
        this.name = name;
        this.intId = intId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int int_id) {
        this.intId = int_id;
    }
}
