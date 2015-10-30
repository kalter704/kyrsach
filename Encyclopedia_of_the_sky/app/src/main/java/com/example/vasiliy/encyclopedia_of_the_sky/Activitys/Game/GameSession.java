package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;


import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;

import java.util.List;

public class GameSession {
    int numberQuestion;
    int amount;
    List<SkyObject> skyObjectList;

    public GameSession(List<SkyObject> skyObjectList) {
        this.skyObjectList = skyObjectList;
        numberQuestion = 0;
        amount = skyObjectList.size();
    }



}
