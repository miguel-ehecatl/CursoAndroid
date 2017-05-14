package com.miguel.mywatchlist.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.actividades.SearchActivity;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.adaptadores.RecyclerAdapter;
import com.miguel.mywatchlist.modelos.ModeloResult;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 13/04/2017.
 */

public class FavoritosFragment extends Fragment {

    LinearLayout layoutVacio;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter recyclerAdapter;
    AdaptadorBD adaptadorBD;
    ModeloUsuario usuario;

    public static FavoritosFragment nuevaInstancia(){
        return new FavoritosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.favoritos_fragment,container,false);
        adaptadorBD = new AdaptadorBD(getContext());
        usuario = adaptadorBD.obtenerUsuarioActivo();
        layoutVacio = (LinearLayout) vista.findViewById(R.id.layout_vacio);
        ArrayList<ModeloResult> peliculas = adaptadorBD.obtenerPeliculasFavoritas(usuario.getId());
        recyclerView = (RecyclerView) vista.findViewById(R.id.recycler_view);
        if(peliculas.size()>0){
            layoutVacio.setVisibility(View.INVISIBLE);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerAdapter = new RecyclerAdapter(getContext(),peliculas);
            recyclerView.setAdapter(recyclerAdapter);
        }else{
            layoutVacio.setVisibility(View.VISIBLE);
        }
        return vista;
    }


    public void refreshFavs(){
        ArrayList<ModeloResult> peliculas = adaptadorBD.obtenerPeliculasFavoritas(usuario.getId());
        recyclerAdapter = null;
        recyclerAdapter = new RecyclerAdapter(getContext(),peliculas);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }
}
