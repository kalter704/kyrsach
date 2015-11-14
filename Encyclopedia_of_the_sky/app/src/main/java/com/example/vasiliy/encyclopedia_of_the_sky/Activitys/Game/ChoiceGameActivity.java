package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.MyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

import java.util.List;

public class ChoiceGameActivity extends AppCompatActivity implements View.OnClickListener {

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_game);

        dataBase = new SkyDataBaseImpl(this);

        List<MyObject> listThemes = dataBase.getThemes();


        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linearLayoutInChoiceAct);
        LayoutInflater ltInflater = getLayoutInflater();


        for(MyObject theme: listThemes) {

            View item = ltInflater.inflate(R.layout.button_item, linLayout, false);
            Button btnTheme = (Button) item.findViewById(R.id.btnTheme);
            btnTheme.setText(theme.getName());

            item.setId(theme.getIntId());
            item.setOnClickListener(this);

            linLayout.addView(item);
        }

        View item = ltInflater.inflate(R.layout.button_back_item, linLayout, false);
        Button btnBack = (Button) item.findViewById(R.id.btnBack);
        btnBack.setText("Назад");

        item.setId(Integer.valueOf(10));
        item.setOnClickListener(this);

        linLayout.addView(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != Integer.valueOf(10)) {
            Intent intent = new Intent(ChoiceGameActivity.this, GameActivity.class);
            intent.putExtra("theme", ((Button) v).getText());
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else {
            finish();
        }
    }
}
