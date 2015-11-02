package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ViewObject;

public class ViewActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvTitle = (TextView) findViewById(R.id.tvTitleView);
        tvText = (TextView) findViewById(R.id.tvTextView);

        ViewObject viewObject = null;

        String theme = getIntent().getStringExtra("ObjectType");

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

        tvTitle.setText(viewObject.getName());
        tvText.setText(viewObject.getText());

    }
}
