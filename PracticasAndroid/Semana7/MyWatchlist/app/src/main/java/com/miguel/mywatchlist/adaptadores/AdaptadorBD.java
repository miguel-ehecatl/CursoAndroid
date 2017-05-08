package com.miguel.mywatchlist.adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.miguel.mywatchlist.auxiliares.AuxiliarBD;
import com.miguel.mywatchlist.modelos.ModeloUsuario;

import java.util.ArrayList;

/**
 * Created by miguelangel on 4/22/17.
 */

public class AdaptadorBD {

    private SQLiteDatabase database;

    public AdaptadorBD(Context context){
        database = AuxiliarBD.obtenerInstancia(context).getWritableDatabase();
    }

    public long guardarUsuario(ModeloUsuario usuario){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuxiliarBD.COLUMNA_CORREO,usuario.getEmail());
        contentValues.put(AuxiliarBD.COLUMNA_PASS,usuario.getPass());
        contentValues.put(AuxiliarBD.COLUMNA_INDICIO,usuario.getIndicio());
        contentValues.put(AuxiliarBD.COLUMNA_SESION,usuario.getSesion());
        return database.insert(AuxiliarBD.TABLA_USUARIOS,null,contentValues);
    }

    public ArrayList<ModeloUsuario> obtenerUsuarios(){
        ArrayList<ModeloUsuario> usuarios = new ArrayList<>();
        String consulta = "select * from "+AuxiliarBD.TABLA_USUARIOS;
        Cursor cursor = database.rawQuery(consulta,null);
        while (cursor.moveToNext()){
            ModeloUsuario usuario = new ModeloUsuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_ID)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_CORREO)));
            usuario.setSesion(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_SESION)));
            usuario.setPass(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PASS)));
            usuario.setIndicio(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_INDICIO)));
            usuarios.add(usuario);
        }
        cursor.close();
        return usuarios;
    }

    public boolean actualizarUsuario(ModeloUsuario usuarioModificado){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuxiliarBD.COLUMNA_SESION,usuarioModificado.getSesion());
        int filasAlteradas = database.update(AuxiliarBD.TABLA_USUARIOS,
                contentValues,
                AuxiliarBD.COLUMNA_ID+" =?",
                new String[]{String.valueOf(usuarioModificado.getId())});
        return filasAlteradas>0;
    }
}
