package com.miguel.mywatchlist.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.GridRecyclerAdapter;
import com.miguel.mywatchlist.adaptadores.RecyclerAdapter;
import com.miguel.mywatchlist.auxiliares.ServiceGenerator;
import com.miguel.mywatchlist.auxiliares.TheMovieDBClient;
import com.miguel.mywatchlist.modelos.ModeloMovie;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 13/04/2017.
 */

public class TopPeliculasFragment extends Fragment{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    GridRecyclerAdapter gridRecyclerAdapter;
    Call<ModeloMovie> peliculas;
    TheMovieDBClient theMovieDBClient;

    public static TopPeliculasFragment nuevaInstancia(){
        return new TopPeliculasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.top_peliculas_fragment,container,false);
        recyclerView = (RecyclerView) vista.findViewById(R.id.recycler_view);
        theMovieDBClient = ServiceGenerator.createService(TheMovieDBClient.class);
        layoutManager = new GridLayoutManager(getContext(),calculateNoOfColumns(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        peliculas = theMovieDBClient.getTopMovies((getString(R.string.api_key)),Locale.getDefault().getLanguage());

        peliculas.enqueue(new Callback<ModeloMovie>() {
            @Override
            public void onResponse(Call<ModeloMovie> call, Response<ModeloMovie> response) {
                gridRecyclerAdapter = new GridRecyclerAdapter(getContext(),response.body().getResults());
                recyclerView.setAdapter(gridRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<ModeloMovie> call, Throwable t) {

            }
        });
        return vista;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth /118);
    }
}
