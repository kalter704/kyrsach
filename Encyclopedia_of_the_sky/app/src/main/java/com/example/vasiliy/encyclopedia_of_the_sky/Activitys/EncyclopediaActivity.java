package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.app.ActionBar;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DBHelper;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG_CLICK = "myClick";

    private SkyDataBase dataBase;

    private ListView lvSkyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        dataBase = new SkyDataBase(this);

        dataBase.open();
        List<SkyObject> listSkyObjects = dataBase.getListSkyObjects();
        dataBase.close();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for(SkyObject skyOb: listSkyObjects) {

            View item = ltInflater.inflate(R.layout.list_item_sky_object, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(skyOb.getName());

            int imageId = EncyclopediaActivity.this.getResources().getIdentifier(skyOb.getImg(), "drawable", getPackageName());

            //Log.d(TAG_CLICK, "By rID: " + String.valueOf(imageId));

            ImageView imgView = (ImageView) item.findViewById(R.id.imageView);
            imgView.setImageDrawable(getResources().getDrawable(imageId));

            item.setId(skyOb.getInt_id());

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