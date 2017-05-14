package com.miguel.mywatchlist.auxiliares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creado por Miguel Ángel Hernández Muñoz
 * para Servicios de Software Ehecatl con fecha 12/04/2017.
 */

public class AuxiliarBD extends SQLiteOpenHelper{

    private static final String NOMBRE_BD="myWatchlist";
    private static final int VERSION_DB=2;
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_CORREO = "correo";
    public static final String COLUMNA_PASS = "password";
    public static final String COLUMNA_INDICIO = "indicio";
    public static final String COLUMNA_SESION = "sesion";
    public static final String TABLA_FAVORITOS = "favoritos";
    public static final String COLUMNA_ID_PELICULA = "id_pelicula";
    public static final String COLUMNA_IDS_USUARIOS = "ids_usuarios";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_CALIFICACION = "calificacion";
    public static final String COLUMNA_PATH_BACK = "path_back";
    public static final String COLUMNA_PATH_POSTER = "path_poster";
    private final String crearTablaFavoritos = "CREATE TABLE " + TABLA_FAVORITOS
            +" ("+COLUMNA_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMNA_ID_PELICULA + " INTEGER NOT NULL,"
            + COLUMNA_IDS_USUARIOS + " INTEGER NOT NULL,"
            + COLUMNA_NOMBRE + " TEXT NOT NULL,"
            + COLUMNA_CALIFICACION + " NUMBER NOT NULL,"
            + COLUMNA_PATH_BACK + " NUMBER NOT NULL,"
            + COLUMNA_PATH_POSTER + " TEXT NOT NULL);";

    private static AuxiliarBD instanciaBD;

    private AuxiliarBD(Context context) {
        super(context,NOMBRE_BD,null,VERSION_DB);
    }

    public static AuxiliarBD obtenerInstancia(Context context){
        if(instanciaBD==null){
            instanciaBD = new AuxiliarBD(context);
        }
        return instanciaBD;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String crearTablaUsuarios = "CREATE TABLE " + TABLA_USUARIOS
                +" ("+COLUMNA_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMNA_CORREO + " TEXT NOT NULL,"
                + COLUMNA_PASS + " TEXT NOT NULL,"
                + COLUMNA_INDICIO + " TEXT NOT NULL,"
                + COLUMNA_SESION + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(crearTablaFavoritos);
        sqLiteDatabase.execSQL(crearTablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion==1&&newVersion==2){
            sqLiteDatabase.execSQL(crearTablaFavoritos);
        }
    }
}
