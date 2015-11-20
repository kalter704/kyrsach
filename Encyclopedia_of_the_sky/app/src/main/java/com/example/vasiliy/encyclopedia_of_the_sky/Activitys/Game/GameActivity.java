package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Game;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.QuestionObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    TextView tvScore;
    ImageView imgView;

    SkyDataBase dataBase;

    final int numberOfQues = 10;
    int questionNum;

    List<QuestionObject> questions;

    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        theme = getIntent().getStringExtra("theme");

        //dataBase = new SkyDataBaseImpl(this);

        btnOne = (Button) findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this);

        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this);

        btnThree = (Button) findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this);

        btnFour = (Button) findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this);

        tvScore = (TextView) findViewById(R.id.tvScore);

        imgView = (ImageView) findViewById(R.id.imgViewGame);

        //dataBase.getQuestionsOfConstellation(numberOfQues);

        dataBase = new SkyDataBaseImpl(this);

        if("Созвездия".equals(theme)) {
            questions = dataBase.getQuestionsOfConstellation(numberOfQues);
        }
        if("Планеты".equals(theme)) {
            questions = dataBase.getQuestionOfPlanet(numberOfQues);
        }

        questionNum = 0;

        setNextQuestion();
    }

    private void setNextQuestion() {
        tvScore.setText(String.valueOf(questionNum + 1) + '/' + String.valueOf(numberOfQues));

        int imageId = GameActivity.this.getResources().getIdentifier(questions.get(questionNum).getImg(), "drawable", getPackageName());
        imgView.setImageDrawable(getResources().getDrawable(imageId));

        String ans1 = questions.get(questionNum).getAnswer(0);
        String ans2 = questions.get(questionNum).getAnswer(1);
        String ans3 = questions.get(questionNum).getAnswer(2);
        String ans4 = questions.get(questionNum).getAnswer(3);

        ans1 = ans1.replaceAll(" ", "\n");
        ans2 = ans2.replaceAll(" ", "\n");
        ans3 = ans3.replaceAll(" ", "\n");
        ans4 = ans4.replaceAll(" ", "\n");

        Log.d("TEXT", "ans1 = " + ans1);
        Log.d("TEXT", "ans2 = " + ans2);
        Log.d("TEXT", "ans3 = " + ans3);
        Log.d("TEXT", "ans4 = " + ans4);

        btnOne.setText(ans1);
        btnTwo.setText(ans2);
        btnThree.setText(ans3);
        btnFour.setText(ans4);
    }

    @Override
    public void onClick(View v) {
        boolean right = false;
        /*
        switch(v.getId()) {
            case R.id.btnOne:
                if(questions.get(questionNum).getRightAnswer().equals(btnOne.getText())){
                    right = true;
                }
                break;
            case R.id.btnTwo:
                if(questions.get(questionNum).getRightAnswer().equals(btnTwo.getText())){
                    right = true;
                }
                break;
            case R.id.btnThree:
                if(questions.get(questionNum).getRightAnswer().equals(btnThree.getText())){
                    right = true;
                }
                break;
            case R.id.btnFour:
                if(questions.get(questionNum).getRightAnswer().equals(btnFour.getText())){
                    right = true;
                }
                break;
        }
        */
        String ans = ((TextView) findViewById(v.getId())).getText().toString();
        ans = ans.replaceAll("\n", " ");
        if(questions.get(questionNum).getRightAnswer().equals(ans)) {
            right = true;
        }
        questions.get(questionNum).setRightAnswer(right);
        ++questionNum;
        if(questionNum != numberOfQues) {
            setNextQuestion();
        } else {
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            intent.putParcelableArrayListExtra("Questions", (ArrayList<? extends Parcelable>) questions);
            startActivity(intent);
            //--questionNum;
        }
    }
}