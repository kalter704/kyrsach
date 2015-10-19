package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vasiliy.encyclopedia_of_the_sky.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button encyc_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encyc_btn = (Button) findViewById(R.id.encyc_btn_main);
        encyc_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.encyc_btn_main:
                intent = new Intent(this, EncyclopediaActivity.class);
                break;
        }
        startActivity(intent);
    }
}
