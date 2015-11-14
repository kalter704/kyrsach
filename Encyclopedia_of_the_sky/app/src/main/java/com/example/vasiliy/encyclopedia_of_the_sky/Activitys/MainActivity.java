package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia.EncyclopediaActivity;
import com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game.GameMenuActivity;
import com.example.vasiliy.encyclopedia_of_the_sky.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnToEncyc;
    Button btnToGameMenu;
    Button btnSettings;
    Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToEncyc = (Button) findViewById(R.id.encyc_btn_main);
        btnToEncyc.setOnClickListener(this);

        btnToGameMenu = (Button) findViewById(R.id.game_btn_main);
        btnToGameMenu.setOnClickListener(this);

        btnSettings = (Button) findViewById(R.id.settings_btn_main);
        btnSettings.setOnClickListener(this);

        btnHelp = (Button) findViewById(R.id.help_btn_main);
        btnHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.encyc_btn_main:
                intent = new Intent(MainActivity.this, EncyclopediaActivity.class);
                break;
            case R.id.game_btn_main:
                intent = new Intent(MainActivity.this, GameMenuActivity.class);
                break;
            case R.id.settings_btn_main:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                break;
            case R.id.help_btn_main:
                intent = new Intent(MainActivity.this, HelpActivity.class);
                break;
        }
        startActivity(intent);
    }


}
