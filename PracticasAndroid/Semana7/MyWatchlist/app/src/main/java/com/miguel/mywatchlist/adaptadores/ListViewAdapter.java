package com.miguel.mywatchlist.adaptadores;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.modelos.Result;

import java.util.ArrayList;

/**
 * Created by miguelangel on 5/6/17.
 */

public class ListViewAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Result> peliculas;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(ArrayList<Result> peliculas, Context context) {
        this.peliculas=peliculas;
        this.context=context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FilaHolder filaHolder;
        if(convertView==null){
            filaHolder = new FilaHolder();
            convertView = layoutInflater.inflate(R.layout.fila_pelicula,parent,false);
            filaHolder.titulo = (TextView) convertView.findViewById(R.id.titulo);
            filaHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            filaHolder.agregar = (ImageView) convertView.findViewById(R.id.agregar);
            filaHolder.poster = (ImageView) convertView.findViewById(R.id.poster);
            convertView.setTag(filaHolder);
        }else{
            filaHolder = (FilaHolder) convertView.getTag();
        }

        String titulo = peliculas.get(position).getTitle();
        Float rating = peliculas.get(position).getVoteAverage();

        if(titulo!=null&&!TextUtils.isEmpty(titulo)){
            filaHolder.titulo.setText(titulo);
        }

        if(rating>0){
            filaHolder.rating.setText(String.valueOf(rating));
        }

        return convertView;
    }

    private static class FilaHolder{
        TextView titulo;
        TextView rating;
        ImageView poster;
        ImageView agregar;
    }
}
