package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener{

    final int DIALOG_DEL = 1;

    TextView tvNumOfGame;
    TextView tvRightAns;
    TextView tvNumOfQues;

    SkyDataBase dataBase;

    Button btnBack;
    Button btnDelStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnDelStat = (Button) findViewById(R.id.btnDelStat);
        btnDelStat.setOnClickListener(this);

        tvNumOfGame = (TextView) findViewById(R.id.tvNumOfGames);
        tvRightAns = (TextView) findViewById(R.id.tvRightAns);
        tvNumOfQues = (TextView) findViewById(R.id.tvNumOfQues);

        dataBase = new SkyDataBase(this);

        int[] score = dataBase.getScore();

        tvNumOfGame.setText(String.valueOf(score[0]));
        tvRightAns.setText(String.valueOf(score[1]));
        tvNumOfQues.setText(String.valueOf(score[2]));


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnDelStat:
                showDialog(DIALOG_DEL);
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DEL) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("Поддтверждение");
            adb.setMessage("Вы точно хотите удалить статистику?");

            adb.setIcon(android.R.drawable.ic_dialog_info);
            adb.setPositiveButton("Да", myClickListener);
            adb.setNegativeButton("Нет", myClickListener);

            return adb.create();
        }
    return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    dataBase.deleteStatistic();
                    tvNumOfGame.setText(String.valueOf(0));
                    tvRightAns.setText(String.valueOf(0));
                    tvNumOfQues.setText(String.valueOf(0));
                    break;
            }
        }
    };
}
