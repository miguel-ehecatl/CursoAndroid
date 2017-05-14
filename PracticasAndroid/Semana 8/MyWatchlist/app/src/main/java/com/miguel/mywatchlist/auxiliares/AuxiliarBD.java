package com.miguel.mywatchlist.auxiliares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miguelangel on 4/22/17.
 */

public class AuxiliarBD extends SQLiteOpenHelper{

    private static final String NOMBRE_BD = "myWatchlist";
    private static final int VERSION_BD = 1;
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_CORREO = "correo";
    public static final String COLUMNA_PASS = "password";
    public static final String COLUMNA_INDICIO = "indicio";
    public static final String COLUMNA_SESION = "sesion";
    private static AuxiliarBD instanciaBD;

    private AuxiliarBD(Context context) {
        super(context,NOMBRE_BD,null,VERSION_BD);
    }

    public static AuxiliarBD obtenerInstancia(Context context){
        if(instanciaBD==null){
            instanciaBD = new AuxiliarBD(context);
        }
        return instanciaBD;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String crearTablaUsuarios = "CREATE TABLE " + TABLA_USUARIOS
            + " ("+COLUMNA_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + COLUMNA_CORREO + " TEXT NOT NULL,"
            + COLUMNA_PASS + " TEXT NOT NULL,"
            + COLUMNA_INDICIO + " TEXT NOT NULL,"
            + COLUMNA_SESION + " INTEGER NOT NULL);";

        db.execSQL(crearTablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
