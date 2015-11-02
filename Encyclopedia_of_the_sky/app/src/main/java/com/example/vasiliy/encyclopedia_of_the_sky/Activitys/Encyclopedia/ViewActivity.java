package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vasiliy.encyclopedia_of_the_sky.R;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String theme = getIntent().getStringExtra("ObjectType");

        /*
        switch(theme) {
            case "Звезды":

                break;
            case "Звездные скопления":

                break;
            case "Туманности":

                break;
            case "Черные дыры":

                break;
            case "Галактики":

                break;
            case "Астероиды и кометы":

                break;
            case "Темная материя":

                break;


        }
        */

    }
}
