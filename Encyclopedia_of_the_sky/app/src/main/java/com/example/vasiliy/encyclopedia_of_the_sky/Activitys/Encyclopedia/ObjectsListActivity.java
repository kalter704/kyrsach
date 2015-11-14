package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

import java.util.List;

public class ObjectsListActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG_CLICK = "myClick";

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_1);

        dataBase = new SkyDataBaseImpl(this);

        Intent intent = getIntent();
        String objectType = intent.getStringExtra("ObjectType");

        List<SkyObject> listSkyObjects = null;

        if("Созвездия".equals(objectType)) {
            listSkyObjects = dataBase.getListConstellationSimply();
        }
        if("Планеты".equals(objectType)){
            listSkyObjects = dataBase.getListPlanetsSimply();
        }


        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for(SkyObject skyObject: listSkyObjects) {

            View item = ltInflater.inflate(R.layout.list_item_text, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(skyObject.getName());

            item.setId(skyObject.getIntId());
            item.setOnClickListener(this);

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG_CLICK, "Click " + v.getId());
        int viewId = v.getId();
        if(viewId > 150 && viewId < 201) {
            Intent intent = new Intent(ObjectsListActivity.this, ConstellationViewActivity.class);
            intent.putExtra("id_constellation", String.valueOf(v.getId()));
            startActivity(intent);
        }
        if(viewId > 200 && viewId < 251) {
            Intent intent = new Intent(ObjectsListActivity.this, PlanetViewActivity.class);
            intent.putExtra("id_planet", String.valueOf(v.getId()));
            startActivity(intent);
        }

    }
}
