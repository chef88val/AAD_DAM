package com.florida.js_cmd.actividad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdaptadorDB conexDB;

     public TextView txt_nombre ,txt_edad,txt_ciclo,txt_curso,txt_nota,txt_id_delete;

    public Button btn_estudiante,btn_profesor;

    public ArrayList xxxx() {
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(0,txt_nombre.getText().toString());
        valores.add(1,txt_edad.getText().toString());
        valores.add(2,txt_ciclo.getText().toString());
        valores.add(3,txt_curso.getText().toString());
        valores.add(4,txt_nota.getText().toString());
        yyyy();
        return valores;
    }
    public JSONObject yyyy() {
        JSONObject json = new JSONObject();
       // JSONObject jsonFinal= new JSONObject();
       // JSONArray jsonArray = new JSONArray();
        try {
            json.put("nombre",txt_nombre.getText().toString());
            json.put("edad",txt_edad.getText().toString());
            json.put("ciclo",txt_ciclo.getText().toString());
            json.put("curso",txt_curso.getText().toString());
            json.put("nota",txt_nota.getText().toString());
            json.put("db","ESTUDIANTE");
            //jsonArray.put(json);
            //jsonFinal.put("datos",jsonArray);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.print(json);
        return json;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        conexDB = new AdaptadorDB(this);
        conexDB.open();

        txt_ciclo = (TextView) findViewById(R.id.TXT_CICLO);
        txt_nombre = (TextView) findViewById(R.id.TXT_NOMBRE);
        txt_edad = (TextView) findViewById(R.id.TXT_EDAD);
        txt_curso = (TextView) findViewById(R.id.TXT_CURSO);
        txt_nota = (TextView) findViewById(R.id.TXT_NOTA);


        btn_estudiante= (Button)findViewById(R.id.btn_EST);
         btn_profesor= (Button)findViewById(R.id.btn_PROF);

         txt_id_delete = (TextView) findViewById(R.id.txt_id_delete);

        Button btn_estudiante_delete= (Button)findViewById(R.id.btn_EST_DELETE);
        Button btn_profesor_delete= (Button)findViewById(R.id.btn_PROF_DELETE);


        btn_estudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                 //conexDB.insertarRegistro("ESTUDIANTE",yyyy());
    //System.out.println("x");
               conexDB.insertarRegistroJSON(yyyy());
            }
        });

        btn_profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conexDB.insertarRegistro("PROFESOR",xxxx());
            }
        });

        btn_estudiante_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conexDB.borrarRegistro("ESTUDIANTE",Integer.parseInt(txt_id_delete.getText().toString()));
            }
        });

        btn_profesor_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conexDB.borrarRegistro("PROFESOR",Integer.parseInt(txt_id_delete.getText().toString()));
            }
        });





    }
}
