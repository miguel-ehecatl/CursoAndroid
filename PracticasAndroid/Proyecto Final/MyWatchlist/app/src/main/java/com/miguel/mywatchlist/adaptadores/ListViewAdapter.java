package com.miguel.mywatchlist.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.fragmentos.DialogRemove;
import com.miguel.mywatchlist.modelos.ModeloResult;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 17/04/2017.
 */

public class ListViewAdapter extends BaseAdapter{

    private ArrayList<ModeloResult> peliculas;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListViewAdapter(ArrayList<ModeloResult> peliculas,Context context) {
        this.peliculas=peliculas;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final FilaHolder filaHolder;
        if(view==null){
            filaHolder = new FilaHolder();
            view = layoutInflater.inflate(R.layout.fila_lista,viewGroup,false);
            filaHolder.titulo = (TextView) view.findViewById(R.id.titulo);
            filaHolder.rating = (TextView) view.findViewById(R.id.rating);
            filaHolder.poster = (ImageView) view.findViewById(R.id.poster);
            filaHolder.placeholder = (RelativeLayout) view.findViewById(R.id.placeholder);
            view.setTag(filaHolder);
        }else{
            filaHolder = (FilaHolder) view.getTag();
        }

        String titulo = peliculas.get(i).getTitle();
        Float rating = peliculas.get(i).getVoteAverage();
        if(titulo!=null&&!TextUtils.isEmpty(titulo)){
            filaHolder.titulo.setText(titulo);
        }

        if(rating>0)
            filaHolder.rating.setText(String.valueOf(rating));

        String posterPath = peliculas.get(i).getPosterPath();

        if(posterPath!=null&&!TextUtils.isEmpty(posterPath)){
            Glide .with(context)
                    .load("https://image.tmdb.org/t/p/w154"+peliculas.get(i).getPosterPath().trim())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            filaHolder.placeholder.setVisibility(View.INVISIBLE);
                            return false;
                        }
                    })
                    .into(filaHolder.poster);
        }
        return view;
    }

    private static class FilaHolder{
        TextView titulo;
        TextView rating;
        ImageView poster;
        RelativeLayout placeholder;
    }

    public void clearData(){
        peliculas.clear();
        notifyDataSetChanged();
    }
}
