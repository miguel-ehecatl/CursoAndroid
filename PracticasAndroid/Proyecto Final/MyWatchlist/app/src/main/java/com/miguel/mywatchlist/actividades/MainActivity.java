package com.miguel.mywatchlist.actividades;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.fragmentos.SigninFragment;
import com.miguel.mywatchlist.fragmentos.SignupFragment;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SigninFragment.ClicBoton{

    private static final String SIGNIN_TAG = "signin_fragment";
    private static final String SIGNUP_TAG = "signup_fragment";
    ActionBar actionBar;
    LinearLayout contenedorPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        contenedorPrincipal = (LinearLayout) findViewById(R.id.contenedor_principal);

        if(actionBar!=null){
            actionBar.hide();
        }

        AdaptadorBD adaptadorBD = new AdaptadorBD(this);

        SigninFragment signinFragment = (SigninFragment) getSupportFragmentManager().findFragmentByTag(SIGNIN_TAG);
        SignupFragment signupFragment = (SignupFragment) getSupportFragmentManager().findFragmentByTag(SIGNUP_TAG);

        ModeloUsuario usuario = adaptadorBD.obtenerUsuarioActivo();

        if(usuario!=null&&usuario.getSesion()==1){
            configurarHome();
        }else{
            if(signinFragment==null&&signupFragment==null){
                agregarFragmento(SigninFragment.nuevaInstancia(),SIGNIN_TAG);
            }
        }
    }

    private void agregarFragmento(Fragment fragmento,String TAG){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(TAG.equalsIgnoreCase(SIGNUP_TAG)){
            fragmentTransaction.setCustomAnimations(R.anim.entrar_deslizando_izquierda,R.anim.salir_deslizando_izquierda,
                    R.anim.entrar_deslizando_derecha, R.anim.salir_deslizando_derecha);
            fragmentTransaction.addToBackStack(TAG);
        }
        fragmentTransaction.replace(R.id.contenedor_principal,fragmento,TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void signup() {
        agregarFragmento(SignupFragment.nuevaInstancia(),SIGNUP_TAG);
    }

    @Override
    public void sesionIniciada(String email) {
        configurarHome();
        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
    }

    private void configurarHome(){
        Intent lanzarHome = new Intent(this,HomeActivity.class);
        startActivity(lanzarHome);
        finish();
    }
}
