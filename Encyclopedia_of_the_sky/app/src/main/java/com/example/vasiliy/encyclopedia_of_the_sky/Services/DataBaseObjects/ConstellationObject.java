package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class ConstellationObject extends SkyObject {
    private String textWhereFrom;
    private String textInf;

    public ConstellationObject(String name, int intId) {
        super(name, intId);
    }

    public ConstellationObject(String name, int intId, String img) {
        super(name, intId, img);
    }

    public ConstellationObject(String name, int intId, String img, String textWhereFrom, String textInf) {
        super(name, intId, img);
        this.textWhereFrom = textWhereFrom;
        this.textInf = textInf;
    }

    public String getTextWhereFrom() {
        return textWhereFrom;
    }

    public void setTextWhereFrom(String textWhereFrom) {
        this.textWhereFrom = textWhereFrom;
    }

    public String getTextInf() {
        return textInf;
    }

    public void setTextInf(String textInf) {
        this.textInf = textInf;
    }
}
