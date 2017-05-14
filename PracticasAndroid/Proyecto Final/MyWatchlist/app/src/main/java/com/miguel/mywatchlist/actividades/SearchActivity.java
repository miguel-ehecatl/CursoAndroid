package com.miguel.mywatchlist.actividades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.adaptadores.ListViewAdapter;
import com.miguel.mywatchlist.auxiliares.ServiceGenerator;
import com.miguel.mywatchlist.auxiliares.TheMovieDBClient;
import com.miguel.mywatchlist.fragmentos.DialogRemove;
import com.miguel.mywatchlist.fragmentos.DialogoProgreso;
import com.miguel.mywatchlist.modelos.ModeloMovie;
import com.miguel.mywatchlist.modelos.ModeloResult;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements DialogRemove.FavoriteDialog{

    ActionBar actionBar;
    RadioGroup radioGroup;
    Call<ModeloMovie> peliculas;
    TheMovieDBClient theMovieDBClient;
    ListView listView;
    ListViewAdapter listViewAdapter;
    LinearLayout sinResultados;
    boolean buscandoPeliculas=true;
    String busquedaActual;
    ArrayList<ModeloResult> resultadoPeliculas;
    AdaptadorBD adaptadorBD;
    ModeloUsuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        actionBar = getSupportActionBar();
        listView = (ListView) findViewById(R.id.list_view);
        sinResultados = (LinearLayout) findViewById(R.id.sin_resultados);
        theMovieDBClient = ServiceGenerator.createService(TheMovieDBClient.class);
        adaptadorBD = new AdaptadorBD(this);
        usuario = adaptadorBD.obtenerUsuarioActivo();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.label_buscar));
        }

        if(savedInstanceState!=null){
            busquedaActual = savedInstanceState.getString("busqueda_actual");
            buscandoPeliculas = savedInstanceState.getBoolean("buscando_peliculas");
            resultadoPeliculas = savedInstanceState.getParcelableArrayList("resultado_peliculas");
            setAdapter(resultadoPeliculas);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_movies:
                        if(!buscandoPeliculas){
                            if(listViewAdapter!=null)
                                listViewAdapter.clearData();
                        }
                        buscandoPeliculas=true;
                        break;
                    case R.id.radio_series:
                        if(buscandoPeliculas){
                            if(listViewAdapter!=null)
                                listViewAdapter.clearData();
                        }
                        buscandoPeliculas=false;
                        break;
                }
            }
        });
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
    protected void onPause() {
        super.onPause();
        if(peliculas!=null)
            peliculas.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.elementos_barra_search, menu);
        MenuItem accionBusqueda= menu.findItem( R.id.accion_busqueda);
        final SearchView searchView = (SearchView) accionBusqueda.getActionView();
        searchView.setIconified(false);
        if(busquedaActual!=null) {
            searchView.setQuery(busquedaActual, false);
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
                if(buscandoPeliculas)
                    searchMovies(busquedaActual);
                else
                    searchSeries(busquedaActual);
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("buscando_peliculas",buscandoPeliculas);
        outState.putString("busqueda_actual",busquedaActual);
        outState.putParcelableArrayList("resultado_peliculas",resultadoPeliculas);
    }

    private void searchMovies(String busqueda){
        if(!TextUtils.isEmpty(busqueda.trim())){
            peliculas = theMovieDBClient.getMovie((getString(R.string.api_key)),busqueda,Locale.getDefault().getLanguage());
            peliculas.enqueue(new Callback<ModeloMovie>() {
                @Override
                public void onResponse(Call<ModeloMovie> call, Response<ModeloMovie> response) {
                    if(response.code()==200){
                        if(response.body()!=null&&response.body().getResults().size()>0){

                            if(sinResultados.isShown()){
                                sinResultados.setVisibility(View.INVISIBLE);
                            }

                            setAdapter(response.body().getResults());
                        }else{
                            if(listViewAdapter!=null){
                                listViewAdapter.clearData();
                            }
                            sinResultados.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),getString(R.string.label_error_servicio),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModeloMovie> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),getString(R.string.label_error_servicio),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void searchSeries(String busqueda){

    }

    private void setAdapter(ArrayList<ModeloResult> respuesta){
        resultadoPeliculas = new ArrayList<>(respuesta);
        listViewAdapter = new ListViewAdapter(resultadoPeliculas,getApplicationContext());
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent verDetalle = new Intent(SearchActivity.this,DetalleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("pelicula_seleccionada",resultadoPeliculas.get(i));
                verDetalle.putExtras(bundle);
                startActivity(verDetalle);
            }
        });
    }

    @Override
    public void favoriteRemoved(int movieID) {
        adaptadorBD.quitarPeliculaFavorita(movieID,usuario.getId());
        Toast.makeText(this,getString(R.string.label_remove_fav), Toast.LENGTH_SHORT).show();
        if(listViewAdapter!=null){
            listViewAdapter.notifyDataSetChanged();
            Intent intent = new Intent("FAVORITE_CHANGED");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }
}
