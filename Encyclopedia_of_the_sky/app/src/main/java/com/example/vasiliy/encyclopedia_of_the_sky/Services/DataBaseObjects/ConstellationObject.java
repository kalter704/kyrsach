package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class ConstellationObject extends SkyObject {
    private String textWhereFrom;
    private String textInf;

    public ConstellationObject(String name, int int_id) {
        super(name, int_id);
    }

    public ConstellationObject(String name, int int_id, String img) {
        super(name, int_id, img);
    }

    public ConstellationObject(String name, int int_id, String img, String textWhereFrom, String textInf) {
        super(name, int_id, img);
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
