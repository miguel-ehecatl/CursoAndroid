package com.miguel.mywatchlist.actividades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.adaptadores.HomeAdapter;
import com.miguel.mywatchlist.auxiliares.ServiceGenerator;
import com.miguel.mywatchlist.auxiliares.TheMovieDBClient;
import com.miguel.mywatchlist.fragmentos.DialogRemove;
import com.miguel.mywatchlist.fragmentos.FavoritosFragment;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 13/04/2017.
 */

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    TabLayout.Tab favsTab,topPeliculasTab,topSeriesTab;
    ViewPager viewPager;
    HomeAdapter homeAdapter;
    FloatingActionButton floatingActionButton;
    TheMovieDBClient theMovieDBClient;
    AdaptadorBD adaptadorBD;
    ModeloUsuario usuario;

    BroadcastReceiver changedFavReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(homeAdapter!=null){
                FavoritosFragment favoritosFragment = (FavoritosFragment) homeAdapter.getRegisteredFragment(0);
                if(favoritosFragment!=null){
                    favoritosFragment.refreshFavs();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(HomeActivity.this).registerReceiver(changedFavReceiver, new IntentFilter("FAVORITE_CHANGED"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        theMovieDBClient = ServiceGenerator.createService(TheMovieDBClient.class);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.label_favoritos));
        agregarTabs();
        homeAdapter = new HomeAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(homeAdapter);
        adaptadorBD = new AdaptadorBD(this);
        usuario = adaptadorBD.obtenerUsuarioActivo();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanzarBusqueda = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(lanzarBusqueda);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(changedFavReceiver!=null) {
            LocalBroadcastManager.getInstance(HomeActivity.this).unregisterReceiver(changedFavReceiver);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.elementos_barra_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.perfil:
                Toast.makeText(this,usuario.getEmail(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cerrar_sesion:
                if(usuario!=null&&adaptadorBD!=null){
                    if(adaptadorBD.cerrarSesion(usuario.getId())){
                        Intent lanzarLogin = new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(lanzarLogin);
                        finish();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void agregarTabs(){
        favsTab=tabLayout.newTab();
        favsTab.setIcon(ContextCompat.getDrawable(this,R.drawable.favorito_contorno));
        tabLayout.addTab(favsTab);

        topPeliculasTab = tabLayout.newTab();
        topPeliculasTab.setIcon(ContextCompat.getDrawable(this,R.drawable.peliculas));
        tabLayout.addTab(topPeliculasTab);

        topSeriesTab = tabLayout.newTab();
        topSeriesTab.setIcon(ContextCompat.getDrawable(this,R.drawable.series));
        tabLayout.addTab(topSeriesTab);
    }
}
