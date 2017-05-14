package com.miguel.mywatchlist.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.actividades.DetalleActivity;
import com.miguel.mywatchlist.actividades.SearchActivity;
import com.miguel.mywatchlist.modelos.ModeloResult;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 14/04/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TextHolder>{

    private ArrayList<ModeloResult> peliculas;
    private Context context;

    public RecyclerAdapter(Context context,ArrayList<ModeloResult> peliculas) {
        this.peliculas=peliculas;
        this.context=context;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_favorito,parent,false);
        return new TextHolder(vista);
    }

    @Override
    public void onBindViewHolder(final TextHolder holder, final int position) {

        String titulo = peliculas.get(position).getTitle();
        Float rating = peliculas.get(position).getVoteAverage();
        if(titulo!=null&&!TextUtils.isEmpty(titulo)){
            holder.titulo.setText(titulo);
        }

        if(rating>0)
            holder.rating.setText(String.valueOf(rating));

        String posterPath = peliculas.get(position).getPosterPath();

        if(posterPath!=null&&!TextUtils.isEmpty(posterPath)){
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w154"+peliculas.get(position).getPosterPath().trim())
                    .into(holder.poster);
        }

        holder.tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verDetalle = new Intent(context,DetalleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("pelicula_seleccionada",peliculas.get(holder.getAdapterPosition()));
                verDetalle.putExtras(bundle);
                context.startActivity(verDetalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class TextHolder extends RecyclerView.ViewHolder{

        private CardView tarjeta;
        private TextView titulo;
        private TextView rating;
        private ImageView poster;

        private TextHolder(View itemView) {
            super(itemView);
            tarjeta = (CardView) itemView.findViewById(R.id.tarjeta);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            rating = (TextView) itemView.findViewById(R.id.rating);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
