package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class SkyObject {
    private String name;
    private String name_id;
    private int int_id;
    private String img;

    public SkyObject(String name, String name_id, int int_id, String img) {
        this.name = name;
        this.name_id = name_id;
        this.int_id = int_id;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
