package com.example.vasiliy.encyclopedia_of_the_sky.Activitys.Encyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBaseImpl;

import java.util.List;

public class EncyclopediaActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG_CLICK = "myClick";

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_1);

        dataBase = new SkyDataBaseImpl(this);

        List<SkyObject> listSkyObjects = dataBase.getListSkyObjects();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for (SkyObject skyOb : listSkyObjects) {

            View item = ltInflater.inflate(R.layout.list_item_text_with_img, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(skyOb.getName());

            int imageId = EncyclopediaActivity.this.getResources().getIdentifier(skyOb.getImg(), "drawable", getPackageName());

            //Log.d(TAG_CLICK, "By rID: " + String.valueOf(imageId));

            ImageView imgView = (ImageView) item.findViewById(R.id.imageView);
            imgView.setImageDrawable(getResources().getDrawable(imageId));

            item.setId(skyOb.getIntId());
            item.setOnClickListener(this);
            //Log.d(TAG_CLICK, String.valueOf(item.getId()));

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG_CLICK, "Click " + v.getId());
        Intent intent = null;
        String theme = ((TextView) v.findViewById(R.id.tvName)).getText().toString();
        switch (theme){
            case "Созвездия":;
            case "Планеты":
                intent = new Intent(EncyclopediaActivity.this, ObjectsListActivity.class);
                break;
            default:
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                break;
        }
        intent.putExtra("ObjectType", theme);
        /*
        switch (v.getId()) {
            case 101:
                Log.d(TAG_CLICK, "Созвездия");
                intent = new Intent(EncyclopediaActivity.this, ObjectsListActivity.class);
                intent.putExtra("ObjectType", "Созвездия");
                break;
            case 102:
                Log.d(TAG_CLICK, "Планеты");
                intent = new Intent(EncyclopediaActivity.this, ObjectsListActivity.class);
                intent.putExtra("ObjectType", "Планеты");
                break;
            case 103:
                Log.d(TAG_CLICK, "Звезды");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Звезды");
                break;
            case 104:
                Log.d(TAG_CLICK, "Звездные скопления");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Звездные скопления");
                break;
            case 105:
                Log.d(TAG_CLICK, "Туманности");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Туманности");
                break;
            case 106:
                Log.d(TAG_CLICK, "Черные дыры");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Черные дыры");
                break;
            case 107:
                Log.d(TAG_CLICK, "Галактики");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Галактики");
                break;
            case 108:
                Log.d(TAG_CLICK, "Астероиды и кометы");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Астероиды и кометы");
                break;
            case 109:
                Log.d(TAG_CLICK, "Темная материя");
                intent = new Intent(EncyclopediaActivity.this, ViewActivity.class);
                intent.putExtra("ObjectType", "Темная материя");
                break;
        }
        */
        startActivity(intent);
    }
}