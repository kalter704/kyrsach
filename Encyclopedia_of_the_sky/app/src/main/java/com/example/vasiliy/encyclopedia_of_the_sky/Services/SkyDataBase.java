package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ConstellationObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.MyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.PlanetObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.QuestionObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ViewObject;

import java.util.List;

public interface SkyDataBase {

    void onUpdataDB();

    List<SkyObject> getListSkyObjects();

    List<SkyObject> getListConstellationSimply();

    ConstellationObject getConstellationById(int id);

    List<Integer> getConstellationIdList();

    List<SkyObject> getListPlanetsSimply();

    PlanetObject getPlanetById(int id);

    List<Integer> getPlanetIdList();

    List<MyObject> getThemes();

    List<QuestionObject> getQuestionsOfConstellation(int amount);

    List<QuestionObject> getQuestionOfPlanet(int amount);

    ViewObject getViewObjectByName(String name);

}
