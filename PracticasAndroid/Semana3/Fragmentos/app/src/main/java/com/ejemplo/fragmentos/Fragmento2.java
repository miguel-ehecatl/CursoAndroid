package com.ejemplo.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by miguelangel on 4/1/17.
 */

public class Fragmento2 extends Fragment {

    TextView textViewTitulo;

    public static Fragmento2 nuevaInstancia(){
        return new Fragmento2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragmento_2,container,false);

        textViewTitulo = (TextView) vista.findViewById(R.id.textViewTitulo);


        return vista;
    }

    public void setText(String mensaje){
        textViewTitulo.setText(mensaje);
    }
}
