package com.ejemplo.fragmentos;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Fragmento1.Clic{

    EditText editTextMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMensaje = (EditText) findViewById(R.id.editTextMensaje);
        Button buttonSend = (Button) findViewById(R.id.button_send);

        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_send:
                String cadenaMensaje = editTextMensaje.getText().toString().trim();
                if(!TextUtils.isEmpty(cadenaMensaje)){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.contenedor_fragmento1,Fragmento1.nuevaInstancia(cadenaMensaje),"fragmento_1").commit();
                    fragmentManager.beginTransaction().add(R.id.contenedor_fragmento2,Fragmento2.nuevaInstancia(),"fragmento_2").commit();
                }
                break;
        }
    }

    @Override
    public void buttonClick(String mensaje) {
        Fragmento2 fragmento2 = (Fragmento2) getSupportFragmentManager().findFragmentByTag("fragmento_2");
        if(fragmento2!=null){
            fragmento2.setText(mensaje);
        }
    }
}
