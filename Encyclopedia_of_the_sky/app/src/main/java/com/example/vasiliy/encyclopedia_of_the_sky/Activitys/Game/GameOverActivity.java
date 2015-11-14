package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.QuestionObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.Score;

import java.util.List;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBAck;

    List<QuestionObject> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        btnBAck = (Button) findViewById(R.id.btnBack);
        btnBAck.setOnClickListener(this);

        questions = getIntent().getParcelableArrayListExtra("Questions");

        int numOfRightAns = 0;
        int numOfQuestions = questions.size();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for(int i = 0; i < questions.size(); ++i) {

            View item = ltInflater.inflate(R.layout.list_item_result, linLayout, false);

            TextView tvQuesNum = (TextView) item.findViewById(R.id.tvQuesNum);
            tvQuesNum.setText(String.valueOf(i + 1));

            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(questions.get(i).getName());

            int imageId;

            if(questions.get(i).isRightAnswer()) {
                imageId = GameOverActivity.this.getResources().getIdentifier("positive64", "drawable", getPackageName());
                ++numOfRightAns;
            } else {
                imageId = GameOverActivity.this.getResources().getIdentifier("negative64", "drawable", getPackageName());
            }

            ImageView imgView = (ImageView) item.findViewById(R.id.imageView2);
            imgView.setImageDrawable(getResources().getDrawable(imageId));

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }

        Score score = new Score(this);
        score.add(numOfRightAns, numOfQuestions);
        score.save();

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
