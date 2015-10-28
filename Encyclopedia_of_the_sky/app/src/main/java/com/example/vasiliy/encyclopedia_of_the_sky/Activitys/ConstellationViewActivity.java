package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ConstellationObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

public class ConstellationViewActivity extends AppCompatActivity {

    final String TABS_TAG_1 = "Tab 1";
    final String TABS_TAG_2 = "Tab 2";

    TabHost tabHost;
    TextView tvTitle;
    TextView tvTextOnBat1;
    ImageView imgView;

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation_view);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator("Вкладка 1");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator("Вкладка 2");
        tabHost.addTab(tabSpec);

        tvTitle = (TextView) findViewById(R.id.tvTitleInTab);
        tvTextOnBat1 = (TextView) findViewById(R.id.tvTextWhereInTab);
        imgView = (ImageView) findViewById(R.id.imgInTab);

        dataBase = new SkyDataBase(this);

        Intent intent = getIntent();

        int idConstellation = Integer.valueOf(intent.getStringExtra("id_constellation"));

        ConstellationObject constellationObject = dataBase.getConstellationById(idConstellation);

        tvTitle.setText(constellationObject.getName());
        tvTextOnBat1.setText(constellationObject.getTextWhereFrom());

        int imageId = ConstellationViewActivity.this.getResources().getIdentifier(constellationObject.getImg(), "drawable", getPackageName());
        imgView.setImageDrawable(getResources().getDrawable(imageId));

    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (tag.equals(TABS_TAG_1)) {
                return getLayoutInflater().inflate(R.layout.tab_main_inf_1, null);
            } else if (tag.equals(TABS_TAG_2)) {
                return getLayoutInflater().inflate(R.layout.tab_main_inf_1, null);
            }
            return null;
        }
    };
}
