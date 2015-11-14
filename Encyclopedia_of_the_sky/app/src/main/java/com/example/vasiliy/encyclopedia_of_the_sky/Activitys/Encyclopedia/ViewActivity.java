package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ViewObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

public class ViewActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvText;
    SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvTitle = (TextView) findViewById(R.id.tvTitleView);
        tvText = (TextView) findViewById(R.id.tvTextView);

        dataBase = new SkyDataBaseImpl(this);

        ViewObject viewObject = null;

        String theme = getIntent().getStringExtra("ObjectType");

        switch(theme) {
            case "Звезды":
                viewObject = dataBase.getViewObjectByName("Звезды");
                break;
            case "Звездные скопления":
                viewObject = dataBase.getViewObjectByName("Звездные скопления");
                break;
            case "Туманности":
                viewObject = dataBase.getViewObjectByName("Туманности");
                break;
            case "Черные дыры":
                viewObject = dataBase.getViewObjectByName("Черные дыры");
                break;
            case "Галактики":
                viewObject = dataBase.getViewObjectByName("Галактики");
                break;
            case "Астероиды и кометы":
                viewObject = dataBase.getViewObjectByName("Астероиды и кометы");
                break;
            case "Темная материя":
                viewObject = dataBase.getViewObjectByName("Темная материя");
                break;
        }

        tvTitle.setText(viewObject.getName());
        tvText.setText(viewObject.getText());

    }
}
