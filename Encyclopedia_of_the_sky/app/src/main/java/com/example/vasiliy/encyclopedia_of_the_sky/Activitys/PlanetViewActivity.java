package com.example.vasiliy.encyclopedia_of_the_sky.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.vasiliy.encyclopedia_of_the_sky.R;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.PlanetObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.SkyDataBase;

import java.util.List;

public class PlanetViewActivity extends AppCompatActivity implements View.OnClickListener, TabHost.OnTabChangeListener {

    final String TABS_TAG_1 = "Tab 1";
    final String TABS_TAG_2 = "Tab 2";

    TabHost tabHost;
    TextView tvTitle;
    TextView tvTextOnTab1;
    TextView tvTitle2;
    TextView tvTextOnTab2;
    ImageView imgView;
    Button btnPrev;
    Button btnNext;
    ScrollView svTab1;
    ScrollView svTab2;

    private PlanetObject planetObject;
    private boolean isChangeTab1;
    private boolean isChangeTab2;

    private LinearLayout lineInBat1;
    private LinearLayout lineInBat2;

    private List<Integer> idList;

    private SkyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_view);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(TabFactory);
        View tab_header1 = getLayoutInflater().inflate(R.layout.tab_1_header_1, null);
        TextView tvTextInHeadTab1 = (TextView) tab_header1.findViewById(R.id.tvTab1Header);
        tvTextInHeadTab1.setText("Информация");
        tabSpec.setIndicator(tab_header1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(TabFactory);
        View tab_header2 = getLayoutInflater().inflate(R.layout.tab_2_header_1, null);
        TextView tvTextInHeadTab2 = (TextView) tab_header2.findViewById(R.id.tvTab2Header);
        tvTextInHeadTab2.setText("История");
        tabSpec.setIndicator(tab_header2);
        tabHost.addTab(tabSpec);

        lineInBat1 = (LinearLayout) findViewById(R.id.lineInTab1);
        lineInBat2 = (LinearLayout) findViewById(R.id.lineInTab2);

        tabHost.setCurrentTab(0);

        lineInBat1.setBackgroundColor(getResources().getColor(R.color.line_selected_color));
        lineInBat2.setBackgroundColor(getResources().getColor(R.color.line_no_selected_color));

        tabHost.setOnTabChangedListener(this);
        dataBase = new SkyDataBase(this);

        Intent intent = getIntent();
        int idPlanet = Integer.valueOf(intent.getStringExtra("id_planet"));

        planetObject = dataBase.getPlanetById(idPlanet);

        idList = dataBase.getPlanetIdList();
        isChangeTab1 = true;
        isChangeTab2 = true;

        this.setContentIntoTab1();

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(this);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

    }

    private void setContentIntoTab1() {
        if(tvTitle == null || tvTextOnTab1 == null || imgView == null || svTab1 == null) {
            tvTitle = (TextView) findViewById(R.id.tvTitleInTab1);
            tvTextOnTab1 = (TextView) findViewById(R.id.tvTextWhereInTab1);
            imgView = (ImageView) findViewById(R.id.imgInTab1);
            svTab1 = (ScrollView) findViewById(R.id.svTab1Inf1);
        }
        if(isChangeTab1) {
            svTab1.scrollTo(0, 0);
            tvTitle.setText(planetObject.getName());
            //tvTextOnTab1.setText(planetObject.getTextWhereFrom());
            svTab2 = (ScrollView) findViewById(R.id.svTab2Stiry1);
            int imageId = PlanetViewActivity.this.getResources().getIdentifier(planetObject.getImg(), "drawable", getPackageName());
            imgView.setImageDrawable(getResources().getDrawable(imageId));
            isChangeTab1 = false;
        }
        lineInBat1.setBackgroundColor(getResources().getColor(R.color.line_selected_color));
        lineInBat2.setBackgroundColor(getResources().getColor(R.color.line_no_selected_color));
    }

    private void setContentIntoTab2() {
        if(tvTitle2 == null || tvTextOnTab2 == null) {
            tvTitle2 = (TextView) findViewById(R.id.tvTitleInTab2);
            tvTextOnTab2 = (TextView) findViewById(R.id.tvTextInTab2);
            svTab2 = (ScrollView) findViewById(R.id.svTab2Stiry1);
        }
        if(isChangeTab2) {
            svTab2.scrollTo(0, 0);
            tvTitle2.setText(planetObject.getName());
            //tvTextOnTab2.setText(planetObject.getTextInf());
            isChangeTab2 = false;
        }
        lineInBat2.setBackgroundColor(getResources().getColor(R.color.line_selected_color));
        lineInBat1.setBackgroundColor(getResources().getColor(R.color.line_no_selected_color));
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
            setContentIntoTab1();
        }
        if(TABS_TAG_2.equals(tabId)) {
            setContentIntoTab2();
        }
    }

    @Override
    public void onClick(View v) {
        int i = idList.indexOf(planetObject.getInt_id());
        switch(v.getId()) {
            case R.id.btnPrev:
                --i;
                if(i < 0) {
                    i = idList.size() - 1;
                }
                break;
            case R.id.btnNext:
                ++i;
                if(i == idList.size()) {
                    i = 0;
                }
                break;
        }
        planetObject = dataBase.getPlanetById(idList.get(i));
        isChangeTab1 = true;
        isChangeTab2 = true;
        if(tabHost.getCurrentTab() == 0) {
            setContentIntoTab1();
        } else {
            setContentIntoTab2();
        }
    }



}
