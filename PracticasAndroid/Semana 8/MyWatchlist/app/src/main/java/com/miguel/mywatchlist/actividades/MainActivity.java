package com.miguel.mywatchlist.actividades;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.fragmentos.SigninFragment;
import com.miguel.mywatchlist.fragmentos.SignupFragment;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SigninFragment.ClicBoton{

    private static final String SIGNIN_TAG = "signin_fragment";
    private static final String SIGNUP_TAG = "signup_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        boolean haySesion = false;
        AdaptadorBD adaptadorBD = new AdaptadorBD(this);
        ArrayList<ModeloUsuario> usuariosGuardados = adaptadorBD.obtenerUsuarios();

        if(usuariosGuardados.size()>0){
            for(ModeloUsuario usuarioGuardado : usuariosGuardados){
                if(usuarioGuardado.getSesion()==1){
                    haySesion=true;
                    break;
                }
            }
        }

        SigninFragment signinFragment = (SigninFragment) getSupportFragmentManager().findFragmentByTag(SIGNIN_TAG);
        SignupFragment signupFragment = (SignupFragment) getSupportFragmentManager().findFragmentByTag(SIGNUP_TAG);

        if(!haySesion){
            if(signinFragment==null&&signupFragment==null){
                agregarFragmento(SigninFragment.nuevaInstancia(),SIGNIN_TAG);
            }
        }else{
            configurarHome();
        }

    }

    private void agregarFragmento(Fragment fragmento,String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(tag.equalsIgnoreCase(SIGNUP_TAG)){
            fragmentTransaction.setCustomAnimations(R.anim.entrar_deslizando_izquierda,R.anim.salir_deslizando_izquierda,
                    R.anim.entrar_deslizando_derecha,R.anim.salir_deslizando_derecha);
            fragmentTransaction.addToBackStack(SIGNUP_TAG);
        }
        fragmentTransaction.replace(R.id.contenedor_principal,fragmento,tag);
        fragmentTransaction.commit();
    }

    @Override
    public void signup() {
        agregarFragmento(SignupFragment.nuevaInstancia(),SIGNUP_TAG);
    }

    @Override
    public void signin() {
        configurarHome();
    }

    private void configurarHome(){
        Intent lanzarHome = new Intent(this,HomeActivity.class);
        startActivity(lanzarHome);
        finish();
    }
}
