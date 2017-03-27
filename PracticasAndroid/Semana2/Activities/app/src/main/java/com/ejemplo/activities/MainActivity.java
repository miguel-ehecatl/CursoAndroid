package com.ejemplo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Button btnLogin;
    EditText editName;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Invocado onCreate");
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editName = (EditText) findViewById(R.id.editName);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreDelUsuario = editName.getText().toString().trim();
                if(TextUtils.isEmpty(nombreDelUsuario)){
                    showToast(getString(R.string.emptyName));
                }else{
                    Intent lanzaScreen2 = new Intent(MainActivity.this,Screen2.class);
                    Bundle extras = new Bundle();
                    int edad = 29;
                    extras.putString("nombre_usuario",nombreDelUsuario);
                    extras.putInt("edad",edad);
                    double dinero = 1500.40;
                    extras.putDouble("dinero",dinero);
                    lanzaScreen2.putExtras(extras);
                    startActivity(lanzaScreen2);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Invocado onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Invocado onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Invocado onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Invocado onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Invocado onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Invocado onDestroy");
    }

    private void showToast(String mensaje){
        if(toast!=null&&toast.getView().isShown()){
              toast.cancel();
        }
        toast = Toast.makeText(MainActivity.this,mensaje, Toast.LENGTH_SHORT);
        toast.show();
    }
}
