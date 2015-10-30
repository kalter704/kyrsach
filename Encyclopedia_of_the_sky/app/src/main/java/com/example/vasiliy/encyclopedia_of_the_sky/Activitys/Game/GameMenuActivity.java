package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vasiliy.encyclopedia_of_the_sky.R;

public class GameMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button newGame;
    Button statistika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        newGame = (Button) findViewById(R.id.new_game_btn);
        newGame.setOnClickListener(this);

        statistika = (Button) findViewById(R.id.statistica_btn);
        statistika.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.new_game_btn:
                intent = new Intent(GameMenuActivity.this, GameActivity.class);
                break;
            case R.id.statistica_btn:
                intent = new Intent(GameMenuActivity.this, StatisticsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
