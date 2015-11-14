package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnUpdataDB;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnUpdataDB = (Button) findViewById(R.id.updata_db);
        btnUpdataDB.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.updata_db:
                SkyDataBase skyDataBase = new SkyDataBaseImpl(this);
                skyDataBase.onUpdataDB();
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
