package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

public class PlanetObject extends SkyObject{
    private String mass;
    private String radius;
    private String day;
    private String year;
    private String radiusSun;
    private String info;

    public PlanetObject(String name, int intId) {
        super(name, intId);
    }

    public PlanetObject(String name, int intId, String img) {
        super(name, intId, img);
    }

    public PlanetObject(
            String name,
            int intId,
            String img,
            String mass,
            String radius,
            String day,
            String year,
            String radiusSun,
            String info
            ) {
        super(name, intId, img);
        this.mass = mass;
        this.radius = radius;
        this.day = day;
        this.year = year;
        this.radiusSun = radiusSun;
        this.info = info;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRadiusSun() {
        return radiusSun;
    }

    public void setRadiusSun(String radiusSun) {
        this.radiusSun = radiusSun;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
