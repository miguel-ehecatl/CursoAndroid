package com.ejemplo.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by miguelangel on 4/1/17.
 */

public class Fragmento1 extends Fragment{

    TextView textViewTitulo;
    String cadenaTitulo;
    Clic clic;

    public static Fragmento1 nuevaInstancia(String cadenaTitulo){
        Fragmento1 fragmento1 = new Fragmento1();
        Bundle extras = new Bundle();
        extras.putString("titulo",cadenaTitulo);
        fragmento1.setArguments(extras);
        return fragmento1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clic = (Fragmento1.Clic) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cadenaTitulo = getArguments().getString("titulo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragmento_1,container,false);

        textViewTitulo = (TextView) vista.findViewById(R.id.textViewTitulo);
        Button botonEnviarAFragmento2 = (Button) vista.findViewById(R.id.button);

        if(cadenaTitulo!=null){
            textViewTitulo.setText(cadenaTitulo);
        }

        botonEnviarAFragmento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clic.buttonClick(cadenaTitulo);
            }
        });
        return vista;
    }

    public interface Clic{
        void buttonClick(String mensaje);
    }
}
