package com.miguel.mywatchlist.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguel.mywatchlist.R;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 13/04/2017.
 */

public class TopSeriesFragment extends Fragment{

    public static TopSeriesFragment nuevaInstancia(){
        return new TopSeriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.top_series_fragment,container,false);

        return vista;
    }
}
