package com.miguel.mywatchlist.actividades;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.PageAdapter;

public class HomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    FloatingActionButton fab;
    ActionBar actionBar;
    TabLayout.Tab favsTab,topPeliculasTab,topSeriesTab;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.label_favoritos));
        agregarTabs();
        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        actionBar.setTitle(getString(R.string.label_favoritos));
                        break;
                    case 1:
                        actionBar.setTitle(getString(R.string.label_top_movies));
                        break;
                    case 2:
                        actionBar.setTitle(getString(R.string.label_top_series));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void agregarTabs(){
        favsTab = tabLayout.newTab();
        favsTab.setIcon(ContextCompat.getDrawable(this,R.drawable.favorito));
        tabLayout.addTab(favsTab);
        topPeliculasTab = tabLayout.newTab();
        topPeliculasTab.setIcon(ContextCompat.getDrawable(this,R.drawable.peliculas));
        tabLayout.addTab(topPeliculasTab);
        topSeriesTab = tabLayout.newTab();
        topSeriesTab.setIcon(ContextCompat.getDrawable(this,R.drawable.series));
        tabLayout.addTab(topSeriesTab);
    }
}
