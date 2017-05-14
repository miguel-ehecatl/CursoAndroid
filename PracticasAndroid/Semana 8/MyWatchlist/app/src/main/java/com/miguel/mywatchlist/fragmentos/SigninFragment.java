package com.miguel.mywatchlist.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import java.util.ArrayList;

/**
 * Created by miguelangel on 4/8/17.
 */

public class SigninFragment extends Fragment implements View.OnClickListener{

    Button signup,signin;
    ClicBoton clicBoton;
    TextInputLayout layoutEmail,layoutPass;
    TextInputEditText editEmail,editPass;

    public static SigninFragment nuevaInstancia(){
        return new SigninFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clicBoton = (ClicBoton) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.signin_fragment,container,false);
        signup = (Button) vista.findViewById(R.id.button_signup);
        signin = (Button) vista.findViewById(R.id.button_login);
        layoutEmail = (TextInputLayout) vista.findViewById(R.id.email);
        layoutPass = (TextInputLayout) vista.findViewById(R.id.password);
        editEmail = (TextInputEditText) vista.findViewById(R.id.edit_email);
        editPass = (TextInputEditText) vista.findViewById(R.id.edit_password);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:
                clicBoton.signup();
                break;
            case R.id.button_login:
                String email,pass;
                email = editEmail.getText().toString().trim();
                pass = editPass.getText().toString().trim();
                validaciones(email,pass);
                break;
        }
    }

    public interface ClicBoton{
        void signup();
        void signin();
    }

    private void validaciones(String email,String pass){
        boolean emailVacio,passVacio;
        if(TextUtils.isEmpty(email)){
            emailVacio=true;
            layoutEmail.setError(getString(R.string.label_requerido));
        }else{
            emailVacio=false;
            layoutEmail.setError(null);
        }
        if(TextUtils.isEmpty(pass)){
            passVacio=true;
            layoutPass.setError(getString(R.string.label_requerido));
        }else{
            passVacio=false;
            layoutPass.setError(null);
        }
        if(!emailVacio&&!passVacio){
            if(existeUsuario(email,pass)){
                clicBoton.signin();
            }else{
                Toast.makeText(getContext(),getString(R.string.label_error_sesion),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean existeUsuario(String email,String pass){
        AdaptadorBD adaptadorBD = new AdaptadorBD(getContext());
        ArrayList<ModeloUsuario> usuarios = adaptadorBD.obtenerUsuarios();
        boolean iniciarSesion = false;
        for(ModeloUsuario usuario : usuarios){
            if(email.equals(usuario.getEmail())&&pass.equals(usuario.getPass())){
                iniciarSesion=true;
                usuario.setSesion(1);
                adaptadorBD.actualizarUsuario(usuario);
                break;
            }
        }
        return iniciarSesion;
    }
}
