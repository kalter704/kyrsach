package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class SkyObject extends MyObject{
    private String img;

    public SkyObject(String name, int intId) {
        super(name, intId);
    }

    public SkyObject(String name, int intId, String img) {
        super(name, intId);
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
