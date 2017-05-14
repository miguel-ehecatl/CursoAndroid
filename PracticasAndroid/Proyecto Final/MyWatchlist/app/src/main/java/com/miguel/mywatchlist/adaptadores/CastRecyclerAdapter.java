package com.miguel.mywatchlist.adaptadores;

import android.content.Context;
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
import com.miguel.mywatchlist.modelos.Cast;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 05/05/2017.
 */

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.RowHolder>{

    private ArrayList<Cast> cast;
    private Context context;

    public CastRecyclerAdapter(ArrayList<Cast> cast) {
        this.cast=cast;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item,parent,false);
        this.context=parent.getContext();
        return new RowHolder(vista);
    }

    @Override
    public void onBindViewHolder(final RowHolder holder, int position) {
        String nombre,personaje,profilePath;
        try {
            nombre = cast.get(position).getName();
        }catch (NullPointerException npe){
            nombre="";
        }
        try {
            personaje = cast.get(position).getCharacter();
        }catch (NullPointerException npe){
            personaje="";
        }
        try {
            profilePath = cast.get(position).getProfilePath();
        }catch (NullPointerException npe){
            profilePath="";
        }
        if(nombre!=null&&!TextUtils.isEmpty(nombre.trim())){
            holder.nombre.setText(nombre);
        }
        if(personaje!=null&&!TextUtils.isEmpty(personaje.trim())){
            holder.personaje.setText(personaje);
        }

        if(profilePath!=null&&!TextUtils.isEmpty(profilePath.trim())){
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185"+profilePath)
                    .into(holder.foto);
        }
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView personaje;
        private ImageView foto;
        public RowHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            personaje = (TextView) itemView.findViewById(R.id.personaje);
            foto = (ImageView) itemView.findViewById(R.id.foto);
        }
    }
}
