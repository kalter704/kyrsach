package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

public class StatisticsActivity extends AppCompatActivity {

    TextView tvNumOfGame;
    TextView tvRightAns;
    TextView tvNumOfQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvNumOfGame = (TextView) findViewById(R.id.tvNumOfGames);
        tvRightAns = (TextView) findViewById(R.id.tvRightAns);
        tvNumOfQues = (TextView) findViewById(R.id.tvNumOfQues);

        SkyDataBase dataBase = new SkyDataBase(this);

        int[] score = dataBase.getScore();

        tvNumOfGame.setText(String.valueOf(score[0]));
        tvRightAns.setText(String.valueOf(score[1]));
        tvNumOfQues.setText(String.valueOf(score[2]));

    }

}
