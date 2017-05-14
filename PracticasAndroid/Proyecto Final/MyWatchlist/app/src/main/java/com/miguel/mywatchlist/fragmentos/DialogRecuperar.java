package com.miguel.mywatchlist.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miguel.mywatchlist.R;
import com.miguel.mywatchlist.adaptadores.AdaptadorBD;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

/**
 * Created by miguelangel on 5/13/17.
 */

public class DialogRecuperar extends DialogFragment{

    TextInputLayout layoutEmail,layoutIndicio;
    TextInputEditText editEmail,editIndicio;
    AppCompatButton recuperar;

    public static DialogRecuperar nuevaInstancia() {
        return new DialogRecuperar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.recuperar_dialog,container,false);
        layoutEmail = (TextInputLayout) vista.findViewById(R.id.layout_email);
        layoutIndicio = (TextInputLayout) vista.findViewById(R.id.layout_indicio);
        editEmail = (TextInputEditText) vista.findViewById(R.id.edit_email);
        editIndicio = (TextInputEditText) vista.findViewById(R.id.edit_indicio);
        recuperar = (AppCompatButton) vista.findViewById(R.id.button_recuperar);
        final AdaptadorBD adaptadorBD = new AdaptadorBD(getContext());

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,indicio;
                email = editEmail.getText().toString().trim();
                indicio = editIndicio.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                   layoutEmail.setError(getString(R.string.label_requerido));
                }else{
                    layoutEmail.setError(null);
                }

                if(TextUtils.isEmpty(indicio)){
                    layoutIndicio.setError(getString(R.string.label_requerido));
                }else {
                    layoutIndicio.setError(null);
                }

                if(!TextUtils.isEmpty(indicio)&&!TextUtils.isEmpty(email)){
                    ModeloUsuario usuario = adaptadorBD.recuperarUsuario(email,indicio);

                    if(usuario!=null){
                        Toast.makeText(getContext(),usuario.getPass(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),getString(R.string.label_error_recuperar),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return vista;
    }
}
