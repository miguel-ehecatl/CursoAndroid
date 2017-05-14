package com.miguel.mywatchlist.adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguel.mywatchlist.auxiliares.AuxiliarBD;
import com.miguel.mywatchlist.modelos.ModeloMovie;
import com.miguel.mywatchlist.modelos.ModeloResult;
import com.miguel.mywatchlist.modelos.ModeloUsuario;
import com.miguel.mywatchlist.modelos.Result;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 12/04/2017.
 */

public class AdaptadorBD {

    private SQLiteDatabase database;

    public AdaptadorBD(Context context) {
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

    public ModeloUsuario obtenerUsuarioActivo(){
        String consulta = "select * from "+AuxiliarBD.TABLA_USUARIOS + " where "+AuxiliarBD.COLUMNA_SESION+"='1'";
        Cursor cursor = database.rawQuery(consulta,null);
        ModeloUsuario modeloUsuario = null;
        while (cursor.moveToNext()){
            modeloUsuario = new ModeloUsuario();
            modeloUsuario.setId(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_ID)));
            modeloUsuario.setEmail(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_CORREO)));
            modeloUsuario.setPass(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PASS)));
            modeloUsuario.setIndicio(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_INDICIO)));
            modeloUsuario.setSesion(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_SESION)));
        }
        cursor.close();
        return modeloUsuario;
    }

    public ModeloUsuario recuperarUsuario(String email,String indicio){
        String consulta = "select * from "
                + AuxiliarBD.TABLA_USUARIOS
                + " where "+AuxiliarBD.COLUMNA_CORREO+"='"+email
                + "' and "+AuxiliarBD.COLUMNA_INDICIO + "='"+indicio+"'";
        Cursor cursor = database.rawQuery(consulta,null);
        ModeloUsuario modeloUsuario = null;
        while (cursor.moveToNext()){
            modeloUsuario = new ModeloUsuario();
            modeloUsuario.setId(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_ID)));
            modeloUsuario.setEmail(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_CORREO)));
            modeloUsuario.setPass(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PASS)));
            modeloUsuario.setIndicio(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_INDICIO)));
            modeloUsuario.setSesion(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_SESION)));
        }
        cursor.close();
        return modeloUsuario;
    }

    public boolean cerrarSesion(int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuxiliarBD.COLUMNA_SESION,0);
        int filas = database.update(AuxiliarBD.TABLA_USUARIOS,
                contentValues,
                AuxiliarBD.COLUMNA_ID+" =?",new String[]{String.valueOf(id)});
        return filas>0;
    }



    public boolean eliminarUsuario(ModeloUsuario usuario){
        int filas = database.delete(AuxiliarBD.TABLA_USUARIOS,AuxiliarBD.COLUMNA_ID+" =?",new String[]{String.valueOf(usuario.getId())});
        return filas!=0;
    }

    public boolean actualizarUsuario(ModeloUsuario usuario){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuxiliarBD.COLUMNA_CORREO,usuario.getEmail());
        contentValues.put(AuxiliarBD.COLUMNA_PASS,usuario.getPass());
        contentValues.put(AuxiliarBD.COLUMNA_INDICIO,usuario.getIndicio());
        contentValues.put(AuxiliarBD.COLUMNA_SESION,usuario.getSesion());
        int filas = database.update(AuxiliarBD.TABLA_USUARIOS,contentValues,AuxiliarBD.COLUMNA_ID+" =?",new String[]{String.valueOf(usuario.getId())});
        return filas!=0;
    }

    public ArrayList<ModeloUsuario> obtenerUsuarios(){
        ArrayList<ModeloUsuario> usuarios = new ArrayList<>();
        String consulta = "select * from "+AuxiliarBD.TABLA_USUARIOS;
        Cursor cursor = database.rawQuery(consulta,null);
        while(cursor.moveToNext()){
            ModeloUsuario usuario = new ModeloUsuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_ID)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_CORREO)));
            usuario.setPass(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PASS)));
            usuario.setIndicio(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_INDICIO)));
            usuario.setSesion(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_SESION)));
            usuarios.add(usuario);
        }
        cursor.close();
        return usuarios;
    }

    public long guardarPeliculaFavorita(ModeloResult pelicula,int idUsuario){

        ArrayList<Integer> idsUsuarios = null;
        Type arrayListType = new TypeToken<ArrayList<Integer>>() {}.getType();
        Gson gson = new Gson();

        String consulta = "select * from "+AuxiliarBD.TABLA_FAVORITOS + " where "+AuxiliarBD.COLUMNA_ID_PELICULA+"="+String.valueOf(pelicula.getId());

        Cursor cursor = database.rawQuery(consulta,null);
        if(cursor.moveToNext()){
            String idsJson = cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_IDS_USUARIOS));
            idsUsuarios = gson.fromJson(idsJson,arrayListType);
        }

        cursor.close();
        ContentValues contentValues = new ContentValues();

        if(idsUsuarios==null) {
            idsUsuarios = new ArrayList<>();
            idsUsuarios.add(idUsuario);
            contentValues.put(AuxiliarBD.COLUMNA_ID_PELICULA,pelicula.getId());
            contentValues.put(AuxiliarBD.COLUMNA_IDS_USUARIOS,gson.toJson(idsUsuarios,arrayListType));
            contentValues.put(AuxiliarBD.COLUMNA_NOMBRE,pelicula.getTitle());
            contentValues.put(AuxiliarBD.COLUMNA_CALIFICACION,pelicula.getVoteAverage());
            contentValues.put(AuxiliarBD.COLUMNA_PATH_POSTER,pelicula.getPosterPath());
            contentValues.put(AuxiliarBD.COLUMNA_PATH_BACK,pelicula.getBackdropPath());
            return database.insert(AuxiliarBD.TABLA_FAVORITOS,null,contentValues);
        }else{
            idsUsuarios.add(idUsuario);
            contentValues.put(AuxiliarBD.COLUMNA_IDS_USUARIOS,gson.toJson(idsUsuarios,arrayListType));
            return database.update(AuxiliarBD.TABLA_FAVORITOS,contentValues,AuxiliarBD.COLUMNA_ID_PELICULA+" =?",new String[]{String.valueOf(pelicula.getId())});
        }
    }

    public boolean estaPeliculaEnFavoritos(int idPelicula,int idUsuario){
        boolean estaEnFavs = false;

        String consulta = "select * from "+AuxiliarBD.TABLA_FAVORITOS + " where "+AuxiliarBD.COLUMNA_ID_PELICULA+"="+String.valueOf(idPelicula);

        Cursor cursor = database.rawQuery(consulta,null);
        if(cursor.moveToNext()){
            String idsJson = cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_IDS_USUARIOS));
            estaEnFavs = tieneUsuarioIDPeliculaEnFavs(idsJson,idUsuario);
        }
        cursor.close();
        return estaEnFavs;
    }

    public void quitarPeliculaFavorita(int movieID,int idUsuario){
        boolean executeDelete = false;
        String consulta = "select * from "+AuxiliarBD.TABLA_FAVORITOS + " where "+AuxiliarBD.COLUMNA_ID_PELICULA+"="+String.valueOf(movieID);
        Cursor cursor = database.rawQuery(consulta,null);
        if(cursor.moveToNext()){
            String idsJson = cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_IDS_USUARIOS));
            executeDelete = quitarIdUsuarioPeliculaFavorita(idsJson,idUsuario,movieID);
        }
        if(executeDelete){
            database.delete(AuxiliarBD.TABLA_FAVORITOS,AuxiliarBD.COLUMNA_ID_PELICULA+" =?",new String[]{String.valueOf(movieID)});
        }
        cursor.close();
    }

    private boolean quitarIdUsuarioPeliculaFavorita(String idsJson,int idUsuario,int movieID){
        boolean eliminarPelicula=false;
        ArrayList<Integer> idsUsuarios;
        Type arrayListType = new TypeToken<ArrayList<Integer>>() {}.getType();
        idsUsuarios = new Gson().fromJson(idsJson,arrayListType);
        if(idsUsuarios!=null){
            Iterator<Integer> iteradorIdsUsuarios = idsUsuarios.iterator();
            while (iteradorIdsUsuarios.hasNext()){
                int idUsuarioPelicula = iteradorIdsUsuarios.next();
                if(idUsuarioPelicula==idUsuario){
                    iteradorIdsUsuarios.remove();
                }
            }
        }
        if(idsUsuarios!=null&&idsUsuarios.size()>0){
            ContentValues contentValues = new ContentValues();
            contentValues.put(AuxiliarBD.COLUMNA_IDS_USUARIOS,new Gson().toJson(idsUsuarios,arrayListType));
            database.update(AuxiliarBD.TABLA_FAVORITOS,
                    contentValues,
                    AuxiliarBD.COLUMNA_ID_PELICULA+" =?",new String[]{String.valueOf(movieID)});
        }else{
            eliminarPelicula=true;
        }
        return eliminarPelicula;
    }

    private boolean tieneUsuarioIDPeliculaEnFavs(String idsJson,int idUsuario){
        boolean tieneEnFavs = false;
        ArrayList<Integer> idsUsuarios;
        Type arrayListType = new TypeToken<ArrayList<Integer>>() {}.getType();
        idsUsuarios = new Gson().fromJson(idsJson,arrayListType);
        if(idsUsuarios!=null){
            for(Integer idUsuarioPelicula : idsUsuarios){
                if(idUsuarioPelicula==idUsuario){
                    tieneEnFavs=true;
                }
            }
        }
        return tieneEnFavs;
    }

    public ArrayList<ModeloResult> obtenerPeliculasFavoritas(int idUsuario){
        ArrayList<ModeloResult> peliculas = new ArrayList<>();
        String consulta = "select * from "+AuxiliarBD.TABLA_FAVORITOS + " order by "+AuxiliarBD.COLUMNA_NOMBRE;
        Cursor cursor = database.rawQuery(consulta,null);
        while(cursor.moveToNext()){

            ModeloResult  pelicula = new ModeloResult();
            String idsJson = cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_IDS_USUARIOS));
            pelicula.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(AuxiliarBD.COLUMNA_ID_PELICULA))));
            pelicula.setTitle(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_NOMBRE)));
            pelicula.setVoteAverage(cursor.getFloat(cursor.getColumnIndex(AuxiliarBD.COLUMNA_CALIFICACION)));
            pelicula.setPosterPath(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PATH_POSTER)));
            pelicula.setBackdropPath(cursor.getString(cursor.getColumnIndex(AuxiliarBD.COLUMNA_PATH_BACK)));
            if(tieneUsuarioIDPeliculaEnFavs(idsJson,idUsuario)){
                peliculas.add(pelicula);
            }
        }
        cursor.close();
        return peliculas;
    }
}
