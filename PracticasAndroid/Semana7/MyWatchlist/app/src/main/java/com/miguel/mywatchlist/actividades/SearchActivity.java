package com.miguel.mywatchlist.actividades;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.auxiliares.ServiceGenerator;
import com.miguel.mywatchlist.auxiliares.TheMovieDBClient;
import com.miguel.mywatchlist.modelos.MovieResult;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ListView listView;
    LinearLayout sinResultados;
    ActionBar actionBar;
    String busquedaActual;
    Call<MovieResult> peliculas;
    TheMovieDBClient theMovieDBClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        listView = (ListView) findViewById(R.id.list_view);
        sinResultados = (LinearLayout) findViewById(R.id.sin_resultados);
        actionBar = getSupportActionBar();
        theMovieDBClient = ServiceGenerator.createService(TheMovieDBClient.class);
        if(savedInstanceState!=null){
            busquedaActual = savedInstanceState.getString("busqueda_actual");
        }
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.label_buscar);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.elementos_barra_search,menu);
        MenuItem accionBusqueda = menu.findItem(R.id.accion_busqueda);
        final SearchView searchView = (SearchView) accionBusqueda.getActionView();
        searchView.setIconified(false);
        if(busquedaActual!=null){
            searchView.setQuery(busquedaActual,false);
            searchView.clearFocus();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                busquedaActual = newText;
                searchMovies(busquedaActual);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("busqueda_actual",busquedaActual);
    }

    private void searchMovies(String busqueda){
        if(!TextUtils.isEmpty(busqueda.trim())){
            peliculas = theMovieDBClient.getSearchMovie(getString(R.string.api_key),
                    busqueda, Locale.getDefault().getLanguage());

            peliculas.enqueue(new Callback<MovieResult>() {
                @Override
                public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                    if(response.code()==200){
                        if(response.body()!=null&&response.body().getResults().size()>0){

                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieResult> call, Throwable t) {

                }
            });
        }
    }
}
