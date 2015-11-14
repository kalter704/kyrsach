package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.QuestionObject;

public class GameOverShowQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;

    TextView btnOne;
    TextView btnTwo;
    TextView btnThree;
    TextView btnFour;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        setContentView(R.layout.activity_game_over_show_question);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        QuestionObject question = (QuestionObject) getIntent().getParcelableExtra("Question");

        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);
        btnThree = (TextView) findViewById(R.id.btnThree);
        btnFour = (TextView) findViewById(R.id.btnFour);

        imgView = (ImageView) findViewById(R.id.imgViewGame);

        int imageId = GameOverShowQuestionActivity.this.getResources().getIdentifier(question.getImg(), "drawable", getPackageName());
        imgView.setImageDrawable(getResources().getDrawable(imageId));

        String ans1 = question.getAnswer(0);
        String ans2 = question.getAnswer(1);
        String ans3 = question.getAnswer(2);
        String ans4 = question.getAnswer(3);

        ans1 = ans1.replaceAll(" ", "\n");
        ans2 = ans2.replaceAll(" ", "\n");
        ans3 = ans3.replaceAll(" ", "\n");
        ans4 = ans4.replaceAll(" ", "\n");

        btnOne.setText(ans1);
        btnTwo.setText(ans2);
        btnThree.setText(ans3);
        btnFour.setText(ans4);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
