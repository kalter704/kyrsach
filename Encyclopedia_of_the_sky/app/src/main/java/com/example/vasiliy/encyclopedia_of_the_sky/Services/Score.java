package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import android.content.Context;
import android.content.SharedPreferences;

public class Score {
    private String nameFile = "score";

    private String numOfGamesString = "numOfGames";
    private int numOfGames;

    private String rightAnswersString = "rightAnswers";
    private int rightAnswers;

    private String numOfQuestionString = "numOfQuestion";
    private int numOfQuestion;

    private SharedPreferences pref;

    private final Context context;

    public Score(Context context) {
        this.context = context;
        load();
    }

    public void load() {
        pref = context.getSharedPreferences(nameFile, context.MODE_PRIVATE);
        numOfGames = pref.getInt(numOfGamesString, 0);
        rightAnswers = pref.getInt(rightAnswersString, 0);
        numOfQuestion = pref.getInt(numOfQuestionString, 0);
    }

    public void save() {
        pref = context.getSharedPreferences(nameFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(numOfGamesString, numOfGames);
        editor.putInt(rightAnswersString, rightAnswers);
        editor.putInt(numOfQuestionString, numOfQuestion);
        editor.commit();
    }

    public void add(int rightAnswers, int numOfQuestion) {
        this.numOfGames++;
        this.rightAnswers += rightAnswers;
        this.numOfQuestion += numOfQuestion;
    }

    public void delete() {
        numOfGames = 0;
        rightAnswers = 0;
        numOfQuestion = 0;
    }


    public int getNumOfGames() {
        return numOfGames;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }
}
