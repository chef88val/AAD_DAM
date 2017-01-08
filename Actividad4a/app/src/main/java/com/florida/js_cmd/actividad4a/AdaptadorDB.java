package com.florida.js_cmd.actividad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by js_cmd on 6/1/17.
 */

public class AdaptadorDB {


    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbx.db";
    private static final String DATABASE_TABLE = "discos";
    private static final int DATABASE_VERSION = 1;

    private static final String TITLE = "title";
    private static final String YEAR = "year";

    private static final String DATABASE_CREATE_ESTUDIANTE =
            "CREATE TABLE ESTUDIANTE (_id integer primary key autoincrement, nombre text, edad integer, ciclo integer,curso integer,nota integer);";
    private static final String DATABASE_CREATE_PROFESORES =
            "CREATE TABLE PROFESOR (_id integer primary key autoincrement, nombre text, edad integer, ciclo integer,curso integer,despacho integer);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";

    //private static final String[] listaTablas={"ESTUDIANTE", "PROFESOR"};

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public AdaptadorDB (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarDisco(String t, int y){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(TITLE,t);
        newValues.put(YEAR,y);
        db.insert(DATABASE_TABLE,null,newValues);
    }

    public void insertarRegistroJSON(JSONObject datos){
        ContentValues newValues = new ContentValues();

        System.out.println(datos.length());
        for(int i=0;i<datos.names().length()-1;i++){
            String variable= "";
            try {
                variable=datos.names().getString(i);

                newValues.put(variable,datos.get(variable).toString());



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        try {
            db.insert(datos.getString("db").toString(),null,newValues);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*try {

        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }
    public void insertarRegistro(String DATABASE_TABLE, ArrayList<String> datos){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        System.out.println(datos);
        //System.out.println(db.execSQL(".schema ESTUDIANTES"));
        //Asignamos los valores de cada campo
        //for (int i=0;i<datos.size();i++)
        //nombre text, edad integer, ciclo integer,curso integer,nota_media real
        if(DATABASE_TABLE=="PROFESOR"){
            newValues.put("nombre",datos.get(0));newValues.put("edad",datos.get(1));newValues.put("ciclo",datos.get(2));
            newValues.put("curso",datos.get(3));newValues.put("despacho",datos.get(4));
        }
        else if(DATABASE_TABLE=="ESTUDIANTE"){
            newValues.put("nombre",datos.get(0));newValues.put("edad",datos.get(1));newValues.put("ciclo",datos.get(2));
            newValues.put("curso",datos.get(3));newValues.put("nota",datos.get(4));
        }

        db.insert(DATABASE_TABLE,null,newValues);
    }

    public void borrarRegistro(String DATABASE_TABLE, int id){
        db.delete(DATABASE_TABLE,"_id="+id,null);
    }


   /* public void modificarRegistro(String DATABASE_TABLE,int id, int y){
        //Creamos un nuevo ContentValues
        ContentValues newValues = new ContentValues();
        //Asignamos el valor del campo a modificar
        newValues.put(YEAR,y);
        db.update(DATABASE_TABLE,newValues,"_id=" + id,null);
    }*/

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_ESTUDIANTE);
            db.execSQL(DATABASE_CREATE_PROFESORES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }

}
