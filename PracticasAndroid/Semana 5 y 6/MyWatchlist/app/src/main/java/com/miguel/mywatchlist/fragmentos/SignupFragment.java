package com.miguel.mywatchlist.fragmentos;

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
 * Created by miguelangel on 4/22/17.
 */

public class SignupFragment extends Fragment implements View.OnClickListener{

    TextInputLayout layoutEmail,layoutPass,layoutConfirm,layoutIndicio;
    TextInputEditText editEmail,editPass,editConfirm,editIndicio;
    Button buttonRegistrarse;
    AdaptadorBD adaptadorBD;

    public static SignupFragment nuevaInstancia(){
        return new SignupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.signup_fragment,container,false);

        layoutEmail = (TextInputLayout) vista.findViewById(R.id.layout_email);
        editEmail = (TextInputEditText) vista.findViewById(R.id.edit_email);
        layoutPass = (TextInputLayout) vista.findViewById(R.id.layout_password);
        editPass = (TextInputEditText) vista.findViewById(R.id.edit_password);
        layoutConfirm = (TextInputLayout) vista.findViewById(R.id.layout_confirm);
        editConfirm = (TextInputEditText) vista.findViewById(R.id.edit_confirm);
        layoutIndicio = (TextInputLayout) vista.findViewById(R.id.layout_indicio);
        editIndicio = (TextInputEditText) vista.findViewById(R.id.edit_indicio);
        buttonRegistrarse = (Button) vista.findViewById(R.id.button_registrarse);
        buttonRegistrarse.setOnClickListener(this);
        adaptadorBD = new AdaptadorBD(getContext());
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_registrarse:
                String email,pass,confirm,indicio;
                email = editEmail.getText().toString().trim();
                pass = editPass.getText().toString().trim();
                confirm = editConfirm.getText().toString().trim();
                indicio = editIndicio.getText().toString().trim();
                validaciones(email,pass,confirm,indicio);
                break;
        }
    }

    private void validaciones(String email,String pass,String confirm,String indicio){

        boolean emailVacio,passVacio,confirmVacio,indicioVacio;

        if(TextUtils.isEmpty(email)){
            emailVacio = true;
            layoutEmail.setError(getString(R.string.label_requerido));
        }else{
            emailVacio = false;
            layoutEmail.setError(null);
        }
        if(TextUtils.isEmpty(pass)){
            passVacio = true;
            layoutPass.setError(getString(R.string.label_requerido));
        }else{
            passVacio = false;
            layoutPass.setError(null);
        }
        if(TextUtils.isEmpty(confirm)){
            confirmVacio = true;
            layoutConfirm.setError(getString(R.string.label_requerido));
        }else{
            confirmVacio=false;
            layoutConfirm.setError(null);
        }
        if(TextUtils.isEmpty(indicio)){
            indicioVacio = true;
            layoutIndicio.setError(getString(R.string.label_requerido));
        }else{
            indicioVacio = false;
            layoutIndicio.setError(null);
        }

        if(!emailVacio&&!passVacio&&!confirmVacio&&!indicioVacio){
            if(pass.equals(confirm)){
                layoutConfirm.setError(null);
                ModeloUsuario usuario = new ModeloUsuario();
                usuario.setEmail(email);
                usuario.setPass(pass);
                usuario.setIndicio(indicio);
                usuario.setSesion(0);
                registrarNuevoUsuario(usuario);
            }else{
                layoutConfirm.setError(getString(R.string.label_nocoincide));
            }
        }
    }

    private void registrarNuevoUsuario(ModeloUsuario usuarioNuevo){
        ArrayList<ModeloUsuario> usuariosGuardados = adaptadorBD.obtenerUsuarios();
        boolean existeUsuario = false;
        for(ModeloUsuario usuarioGuardado : usuariosGuardados){
            if(usuarioGuardado.getEmail().equalsIgnoreCase(usuarioNuevo.getEmail())){
                existeUsuario=true;
                layoutEmail.setError(getString(R.string.label_error_email));
                break;
            }else {
                layoutEmail.setError(null);
            }
        }

        if(!existeUsuario){
            long codigo = adaptadorBD.guardarUsuario(usuarioNuevo);
            if(codigo>-1){
                Toast.makeText(getContext(),getString(R.string.label_exito),Toast.LENGTH_SHORT).show();
                editConfirm.setText("");
                editEmail.setText("");
                editIndicio.setText("");
                editPass.setText("");
            }
        }
    }
}
