package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ConstellationObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

import java.util.List;

public class ConstellationViewActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    final String TABS_TAG_1 = "Tab 1";
    final String TABS_TAG_2 = "Tab 2";

    TabHost tabHost;
    TextView tvTitle;
    TextView tvTextOnTab1;
    TextView tvTitle2;
    TextView tvTextOnTab2;
    ImageView imgView;

    ConstellationObject constellationObject;

    private List<Integer> idList;

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

        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(this);
        dataBase = new SkyDataBase(this);

        Intent intent = getIntent();

        int idConstellation = Integer.valueOf(intent.getStringExtra("id_constellation"));

        constellationObject = dataBase.getConstellationById(idConstellation);

        idList = dataBase.getConstellationIdList();

    }

    private void setContentOnTab1() {
        if(tvTitle == null || tvTextOnTab1 == null || imgView == null) {
            tvTitle = (TextView) findViewById(R.id.tvTitleInTab1);
            tvTextOnTab1 = (TextView) findViewById(R.id.tvTextWhereInTab1);
            imgView = (ImageView) findViewById(R.id.imgInTab1);

            tvTitle.setText(constellationObject.getName());
            tvTextOnTab1.setText(constellationObject.getTextWhereFrom());

            int imageId = ConstellationViewActivity.this.getResources().getIdentifier(constellationObject.getImg(), "drawable", getPackageName());
            imgView.setImageDrawable(getResources().getDrawable(imageId));
        }
    }

    private void setContentOnTab2() {
        if(tvTitle2 == null || tvTextOnTab2 == null) {
            tvTitle2 = (TextView) findViewById(R.id.tvTitleInTab2);
            tvTextOnTab2 = (TextView) findViewById(R.id.tvTextInTab2);

            tvTitle2.setText(constellationObject.getName());
            tvTextOnTab2.setText(constellationObject.getTextInf());
        }
    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            View view = null;
            if (tag.equals(TABS_TAG_1)) {
                view = getLayoutInflater().inflate(R.layout.tab_1_inf_1, null);
            }
            if (tag.equals(TABS_TAG_2)) {
                view = getLayoutInflater().inflate(R.layout.tab_2_story_1, null);
            }
            return view;
        }
    };

    @Override
    public void onTabChanged(String tabId) {
        if(TABS_TAG_1.equals(tabId)) {
            setContentOnTab1();
        }
        if(TABS_TAG_2.equals(tabId)) {
            setContentOnTab2();
        }
    }
}
