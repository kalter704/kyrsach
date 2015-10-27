package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ConstellationSimplyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

import java.util.List;

public class ConstellationListActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG_CLICK = "myClick";

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_1);

        dataBase = new SkyDataBase(this);

        List<ConstellationSimplyObject> listConstellation = dataBase.getListConstellationSimply();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for(ConstellationSimplyObject constellationObj: listConstellation) {

            View item = ltInflater.inflate(R.layout.list_item_text, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(constellationObj.getName());

            //int imageId = ConstellationListActivity.this.getResources().getIdentifier(skyOb.getImg(), "drawable", getPackageName());

            //Log.d(TAG_CLICK, "By rID: " + String.valueOf(imageId));

            //ImageView imgView = (ImageView) item.findViewById(R.id.imageView);
            //imgView.setImageDrawable(getResources().getDrawable(imageId));

            item.setId(constellationObj.getInt_id());
            item.setOnClickListener(this);
            //Log.d(TAG_CLICK, String.valueOf(item.getId()));

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG_CLICK, "Click " + v.getId());
    }
}
