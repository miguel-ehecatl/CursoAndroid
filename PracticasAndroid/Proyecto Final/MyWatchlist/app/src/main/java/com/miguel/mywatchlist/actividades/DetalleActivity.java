package com.miguel.mywatchlist.actividades;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.adaptadores.CastRecyclerAdapter;
import com.miguel.mywatchlist.adaptadores.RecyclerAdapter;
import com.miguel.mywatchlist.auxiliares.AuxiliarBD;
import com.miguel.mywatchlist.auxiliares.ServiceGenerator;
import com.miguel.mywatchlist.auxiliares.TheMovieDBClient;
import com.miguel.mywatchlist.fragmentos.DialogRemove;
import com.miguel.mywatchlist.modelos.Crew;
import com.miguel.mywatchlist.modelos.Genre;
import com.miguel.mywatchlist.modelos.ModeloResult;
import com.miguel.mywatchlist.modelos.ModeloUsuario;
import com.miguel.mywatchlist.modelos.MovieCredits;
import com.miguel.mywatchlist.modelos.MovieDetail;
import com.miguel.mywatchlist.modelos.MovieVideo;
import com.miguel.mywatchlist.modelos.Result;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 17/04/2017.
 */

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener,DialogRemove.FavoriteDialog{

    ModeloResult peliculaSeleccionada;
    ImageView backdrop,poster,play;
    TextView tituloOriginal,year,generos,resumen,rating,votos,duracion,tagline,director;
    CollapsingToolbarLayout barraColapsable;
    TheMovieDBClient theMovieDBClient;
    Call<MovieDetail> detallePelicula;
    Call<MovieVideo> videosPelicula;
    Call<MovieCredits> creditosPelicula;
    String youtubeID;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CastRecyclerAdapter castRecyclerAdapter;
    FloatingActionButton agregar;
    AdaptadorBD adaptadorBD;
    boolean estaEnFav=false;
    private final String TAG = "DetalleActivity";
    ModeloUsuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        barraColapsable = (CollapsingToolbarLayout) findViewById(R.id.barra_colapsable);
        poster = (ImageView) findViewById(R.id.poster);
        play = (ImageView) findViewById(R.id.play);
        tituloOriginal = (TextView) findViewById(R.id.titulo_original);
        year = (TextView) findViewById(R.id.year);
        generos = (TextView) findViewById(R.id.generos);
        resumen = (TextView) findViewById(R.id.resumen);
        rating = (TextView) findViewById(R.id.rating);
        votos = (TextView) findViewById(R.id.votos);
        duracion = (TextView) findViewById(R.id.duracion);
        tagline = (TextView) findViewById(R.id.tagline);
        director = (TextView) findViewById(R.id.director);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Bundle extras = getIntent().getExtras();
        theMovieDBClient = ServiceGenerator.createService(TheMovieDBClient.class);
        agregar = (FloatingActionButton) findViewById(R.id.agregar);
        adaptadorBD= new AdaptadorBD(this);
        usuario = adaptadorBD.obtenerUsuarioActivo();
        if(extras!=null){
            peliculaSeleccionada = extras.getParcelable("pelicula_seleccionada");

            if(peliculaSeleccionada!=null){
                if(peliculaSeleccionada.getBackdropPath()!=null){
                    Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w780"+peliculaSeleccionada.getBackdropPath().trim())
                            .into(backdrop);
                }
                if(peliculaSeleccionada.getPosterPath()!=null){
                    Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w342"+peliculaSeleccionada.getPosterPath().trim())
                            .into(poster);
                }
                if(peliculaSeleccionada.getOriginalTitle()!=null){
                    tituloOriginal.setText(peliculaSeleccionada.getOriginalTitle());
                }

                barraColapsable.setTitle(peliculaSeleccionada.getTitle());

                if(peliculaSeleccionada.getId()!=null){
                    agregar.setOnClickListener(this);
                    estaEnFav = adaptadorBD.estaPeliculaEnFavoritos(Integer.parseInt(peliculaSeleccionada.getId()),usuario.getId());
                    if(estaEnFav){
                        agregar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.done));
                    }else{
                        agregar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.agregar_blanco));
                    }
                    consumirDetalle(peliculaSeleccionada.getId());
                    consumirVideo(peliculaSeleccionada.getId());
                    consumirCast(peliculaSeleccionada.getId());
                }
            }
        }
    }

    private void consumirDetalle(String id){
        detallePelicula = theMovieDBClient.getMovieDetail(id,getString(R.string.api_key),Locale.getDefault().getLanguage());

        detallePelicula.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.code()==200){
                    if(response.body()!=null) {
                        asignarDetalles(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(TAG,"Error al consumir detalle",t);
            }
        });
    }

    private void asignarDetalles(MovieDetail movieDetail){
        if(movieDetail.getReleaseDate()!=null){
            String[] arregloFecha = movieDetail.getReleaseDate().split("-");
            if(!TextUtils.isEmpty(arregloFecha[0].trim())){
                year.setText("("+arregloFecha[0]+")");
            }else{
                year.setVisibility(View.GONE);
            }
            if(movieDetail.getGenres()!=null){
                ArrayList<Genre> arregloGeneros = movieDetail.getGenres();
                String[] arregloNombreGeneros = new String[arregloGeneros.size()];
                for(int i=0;i<arregloGeneros.size();i++){
                    arregloNombreGeneros[i] = arregloGeneros.get(i).getName();
                }
                if(arregloNombreGeneros.length>0){
                    String cadenaGeneros = TextUtils.join(" | ",arregloNombreGeneros);
                    generos.setText(cadenaGeneros);
                }else{
                    generos.setVisibility(View.GONE);
                }
            }
            if(movieDetail.getOverview()!=null&&!TextUtils.isEmpty(movieDetail.getOverview().trim())){
                resumen.setText(movieDetail.getOverview());
            }else{
                resumen.setVisibility(View.GONE);
            }
            if(movieDetail.getVoteAverage()!=null){
                rating.setText(String.valueOf(movieDetail.getVoteAverage()+"/10"));
            }
            if(movieDetail.getVoteCount()!=null){
                votos.setText(String.valueOf(movieDetail.getVoteCount()));
            }
            if(movieDetail.getRuntime()!=null){
                int minutos = movieDetail.getRuntime();
                if(minutos>0){
                    int horas = minutos/60;
                    int minutosRestantes = minutos % 60;
                    duracion.setText(String.format(Locale.getDefault(),"%d hrs %d mins",horas,minutosRestantes));
                }else{
                    duracion.setVisibility(View.GONE);
                }
            }
            if(movieDetail.getTagline()!=null&&!TextUtils.isEmpty(movieDetail.getTagline().trim())){
                tagline.setText(movieDetail.getTagline());
            }else{
                tagline.setVisibility(View.GONE);
            }
        }
    }

    private void consumirVideo(String id){
        videosPelicula = theMovieDBClient.getMovieVideo(id,getString(R.string.api_key),Locale.getDefault().getLanguage());

        videosPelicula.enqueue(new Callback<MovieVideo>() {
            @Override
            public void onResponse(Call<MovieVideo> call, Response<MovieVideo> response) {
                if(response.code()==200){
                    if(response.body()!=null){
                        asignarVideo(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieVideo> call, Throwable t) {
                Log.e(TAG,"Error al consumir video",t);
            }
        });
    }

    private void consumirCast(String id){
        creditosPelicula = theMovieDBClient.getMovieCredits(id,getString(R.string.api_key));

        creditosPelicula.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(Call<MovieCredits> call, Response<MovieCredits> response) {
                if(response.code()==200){
                    if(response.body()!=null){
                        asignarCreditos(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {
                Log.e(TAG,"Error al consumir creditos",t);
            }
        });
    }

    private void asignarVideo(MovieVideo videos){
        if(videos.getResults()!=null&&videos.getResults().size()>0){
            for(Result resultado : videos.getResults()){
                if(resultado.getSite().equalsIgnoreCase("youtube")){
                    play.setVisibility(View.VISIBLE);
                    play.setOnClickListener(this);
                    youtubeID = resultado.getKey();
                    break;
                }
            }
        }
    }

    private void asignarCreditos(MovieCredits movieCredits){
        if(movieCredits.getCast()!=null){
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            castRecyclerAdapter = new CastRecyclerAdapter(movieCredits.getCast());
            recyclerView.setAdapter(castRecyclerAdapter);
        }
        if(movieCredits.getCrew()!=null){
            for(Crew crew : movieCredits.getCrew()){
                if(crew.getJob().equalsIgnoreCase("director")){
                    director.setVisibility(View.VISIBLE);
                    director.setText(String.format(Locale.getDefault(),getString(R.string.director),crew.getName()));
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                if(youtubeID!=null&&!TextUtils.isEmpty(youtubeID)){
                    Intent applicationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID));
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/watch?v="+youtubeID));
                    try {
                        startActivity(applicationIntent);
                    } catch (ActivityNotFoundException ex) {
                        startActivity(browserIntent);
                    }
                }
                break;
            case R.id.agregar:
                estaEnFav = adaptadorBD.estaPeliculaEnFavoritos(Integer.parseInt(peliculaSeleccionada.getId()),usuario.getId());
                if(estaEnFav){
                    DialogRemove dialogRemove = DialogRemove.nuevaInstancia(Integer.parseInt(peliculaSeleccionada.getId()));
                    dialogRemove.show(getSupportFragmentManager(),"dialog_remove");
                }else{
                    agregar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.done));
                    Toast.makeText(this,getString(R.string.label_fav_added), Toast.LENGTH_SHORT).show();
                    adaptadorBD.guardarPeliculaFavorita(peliculaSeleccionada,usuario.getId());
                    Intent intent = new Intent("FAVORITE_CHANGED");
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
                break;
        }
    }

    @Override
    public void favoriteRemoved(int movieID) {
        adaptadorBD.quitarPeliculaFavorita(movieID,usuario.getId());
        agregar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.agregar_blanco));
        Toast.makeText(this,getString(R.string.label_remove_fav), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("FAVORITE_CHANGED");
        LocalBroadcastManager.getInstance(DetalleActivity.this).sendBroadcast(intent);
    }
}
