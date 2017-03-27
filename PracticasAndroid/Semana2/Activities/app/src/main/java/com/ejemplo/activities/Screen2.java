package com.ejemplo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by miguelangel on 3/25/17.
 */

public class Screen2 extends AppCompatActivity {

    Toast toast;
    TextView textWelcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        textWelcome = (TextView) findViewById(R.id.textWelcome);
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            String nombreDelUsuario = extras.getString("nombre_usuario","");
            int edad = extras.getInt("edad");
            double dinero = extras.getDouble("dinero");
            showToast(String.valueOf(edad)+" "+String.valueOf(dinero));
            textWelcome.setText(String.format(getString(R.string.textWelcome),nombreDelUsuario));
        }
    }

    private void showToast(String mensaje){
        if(toast!=null&&toast.getView().isShown()){
            toast.cancel();
        }
        toast = Toast.makeText(Screen2.this,mensaje, Toast.LENGTH_SHORT);
        toast.show();
    }
}
