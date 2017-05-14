package com.miguel.mywatchlist.fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 10/04/2017.
 */

public class SignupFragment extends Fragment implements View.OnClickListener{

    TextInputLayout layoutEmail,layoutPass,layoutConfirm,layoutIndicio;
    TextInputEditText editEmail,editPass,editConfirm,editIndicio;

    public static SignupFragment nuevaInstancia(){
        return new SignupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.signup_fragment,container,false);
        Button buttonRegistrarse = (Button) vista.findViewById(R.id.button_registrarse);
        layoutEmail = (TextInputLayout) vista.findViewById(R.id.layout_email);
        layoutPass = (TextInputLayout) vista.findViewById(R.id.layout_password);
        layoutConfirm = (TextInputLayout) vista.findViewById(R.id.layout_confirm);
        layoutIndicio = (TextInputLayout) vista.findViewById(R.id.layout_indicio);
        editEmail = (TextInputEditText) vista.findViewById(R.id.edit_email);
        editPass = (TextInputEditText) vista.findViewById(R.id.edit_password);
        editConfirm = (TextInputEditText) vista.findViewById(R.id.edit_confirm);
        editIndicio = (TextInputEditText) vista.findViewById(R.id.edit_indicio);

        buttonRegistrarse.setOnClickListener(this);

        return vista;
    }

    private void validaciones(String email,String pass,String confirmacion,String indicio){
        boolean emailVacio,passVacio,confirmVacio,indicioVacio;
        if(TextUtils.isEmpty(email)){
            layoutEmail.setError(getString(R.string.label_requerido));
            emailVacio = true;
        }else {
            emailVacio = false;
            layoutEmail.setError(null);
        }
        if(TextUtils.isEmpty(pass)){
            passVacio=true;
            layoutPass.setError(getString(R.string.label_requerido));
        }else {
            passVacio=false;
            layoutPass.setError(null);
        }
        if(TextUtils.isEmpty(confirmacion)){
            confirmVacio=true;
            layoutConfirm.setError(getString(R.string.label_requerido));
        }else {
            confirmVacio=false;
            layoutConfirm.setError(null);
        }
        if(TextUtils.isEmpty(indicio)){
            indicioVacio=true;
            layoutIndicio.setError(getString(R.string.label_requerido));
        }else {
            indicioVacio=false;
            layoutIndicio.setError(null);
        }

        if(!emailVacio&&!passVacio&&!confirmVacio&&!indicioVacio){
            if(pass.equalsIgnoreCase(confirmacion)){
                layoutConfirm.setError(null);
                registrarNuevoUsuario(email,pass,indicio);
            }else{
                layoutConfirm.setError(getString(R.string.label_nocoincide));
            }
        }
    }

    private void registrarNuevoUsuario(String email,String password,String indicio){
        AdaptadorBD adaptadorBD = new AdaptadorBD(getContext());
        ArrayList<ModeloUsuario> usuarios = adaptadorBD.obtenerUsuarios();
        boolean existeUsuario=false;
        for(ModeloUsuario usuario : usuarios) {
            if (email.equalsIgnoreCase(usuario.getEmail())) {
                existeUsuario=true;
                layoutEmail.setError(getString(R.string.label_error_email));
                break;
            } else {
                layoutEmail.setError(null);
            }
        }

        if(!existeUsuario){
            ModeloUsuario usuario = new ModeloUsuario();
            usuario.setEmail(email);
            usuario.setPass(password);
            usuario.setIndicio(indicio);
            usuario.setSesion(0);
            adaptadorBD.guardarUsuario(usuario);
            editConfirm.setText("");
            editEmail.setText("");
            editPass.setText("");
            editIndicio.setText("");
            Toast.makeText(getContext(),getString(R.string.label_exito),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        String email,pass,confirmacion,indicio;
        email = editEmail.getText().toString().trim();
        pass = editPass.getText().toString().trim();
        confirmacion = editConfirm.getText().toString().trim();
        indicio = editIndicio.getText().toString().trim();
        validaciones(email,pass,confirmacion,indicio);
    }
}
