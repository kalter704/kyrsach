package com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionObject extends SkyObject implements Parcelable {
    private List<String> answers;
    private int countAns;
    private boolean rightAnswer;

    private final String TAG_OBJ = "QuestionObject";

    public QuestionObject(String name, int intId) {
        super(name, intId);
        countAns = 0;
        this.answers = new ArrayList<>();
        rightAnswer = false;
    }

    public QuestionObject(String name, int intId, String img) {
        super(name, intId, img);
        countAns = 0;
        this.answers = new ArrayList<>();
        rightAnswer = false;
    }

    public QuestionObject(String name, int intId, String img, List<String> answers) {
        super(name, intId, img);
        this.answers = new ArrayList<>();
        this.answers = answers;
        countAns = answers.size();
        rightAnswer = false;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void addAnswer(String ans) {
        if(answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(ans);
        ++countAns;
    }

    public void mixAnswers() {
        String tempStr = answers.get(0);
        Random random = new Random();
        int tempInt = random.nextInt(countAns);
        answers.set(0, answers.get(tempInt));
        answers.set(tempInt, tempStr);
    }

    public String getAnswer(int i) {
        if(i < countAns){
            return answers.get(i);
        } else {
            return null;
        }
    }

    public String getRightAnswer() {
        return super.getName();
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
        countAns = answers.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(super.getName());
        parcel.writeInt(super.getIntId());
        parcel.writeString(super.getImg());
        parcel.writeBooleanArray(new boolean[]{rightAnswer});
        parcel.writeInt(countAns);
        parcel.writeStringList(answers);
    }

    public static final Parcelable.Creator<QuestionObject> CREATOR = new Parcelable.Creator<QuestionObject>() {
        // распаковываем объект из Parcel
        public QuestionObject createFromParcel(Parcel in) {
            return new QuestionObject(in);
        }

        public QuestionObject[] newArray(int size) {
            return new QuestionObject[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private QuestionObject(Parcel parcel) {

        super(
                parcel.readString(),
                parcel.readInt(),
                parcel.readString()
        );
        boolean[] arrB = new boolean[1];
        parcel.readBooleanArray(arrB);
        rightAnswer = arrB[0];
        countAns = parcel.readInt();
        answers = new ArrayList<>(countAns);
        parcel.readStringList(answers);
    }

    public void writeToLog() {
        Log.d(TAG_OBJ, "QuestionObject:");
        Log.d(TAG_OBJ, "..." + super.getName());
        Log.d(TAG_OBJ, "..." + String.valueOf(super.getIntId()));
        Log.d(TAG_OBJ, "..." + super.getImg());
        Log.d(TAG_OBJ, "..." + String.valueOf(rightAnswer));
        for(String str: answers) {
            Log.d(TAG_OBJ, "..." + str);
        }
    }

}
