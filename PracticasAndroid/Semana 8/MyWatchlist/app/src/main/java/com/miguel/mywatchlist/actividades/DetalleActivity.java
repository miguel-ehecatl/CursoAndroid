package com.miguel.mywatchlist.actividades;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.modelos.Result;

public class DetalleActivity extends AppCompatActivity {

    Result pelicula;
    ImageView backdrop,poster;
    CollapsingToolbarLayout barraColapsable;
    TextView tituloOriginal,year,tagline,generos,resumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        barraColapsable = (CollapsingToolbarLayout) findViewById(R.id.barra_colapsable);
        tituloOriginal = (TextView) findViewById(R.id.titulo_original);
        year = (TextView) findViewById(R.id.year);
        tagline = (TextView) findViewById(R.id.tagline);
        generos = (TextView) findViewById(R.id.generos);
        resumen = (TextView) findViewById(R.id.resumen);
        poster = (ImageView) findViewById(R.id.poster);
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            pelicula = extras.getParcelable("pelicula_seleccionada");
            if(pelicula!=null){
                barraColapsable.setTitle(pelicula.getTitle());

                asignarDetalles();
                if(pelicula.getBackdropPath()!=null){
                    Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w780"+pelicula.getBackdropPath())
                            .into(backdrop);
                }
            }
        }
    }

    private void asignarDetalles(){
        if(pelicula.getOriginalTitle()!=null){
            tituloOriginal.setText(pelicula.getOriginalTitle());
        }
        if(pelicula.getReleaseDate()!=null){
            String [] arregloFecha = pelicula.getReleaseDate().split("-");
            if(!TextUtils.isEmpty(arregloFecha[0])){
                year.setText("("+arregloFecha[0]+")");
            }else{
                year.setVisibility(View.GONE);
            }
        }

    }
}
