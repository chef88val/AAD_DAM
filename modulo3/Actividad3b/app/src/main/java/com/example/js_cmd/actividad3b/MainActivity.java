package com.example.js_cmd.actividad3b;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //Declaro los objetos
    private TextView txtNombre,txtDNI,txtFecha;
    private Spinner txtSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Me creo/inicializo los objetos dentro de mi clase que están conectados con el xml/resources
        final Button btnAction= (Button) findViewById(R.id.btn_Accion);
        txtNombre = (TextView) findViewById(R.id.txt_Nombre);
        txtDNI = (TextView) findViewById(R.id.txt_DNI);
        txtFecha = (TextView) findViewById(R.id.txt_FNac);
        txtSex = (Spinner) findViewById(R.id.spinner_Sexo);
        btnAction.setOnClickListener(new View.OnClickListener() {//Evento listener del boton
            @Override
            public void onClick(View v) {

                //ME creo un objeto para guardar las preferencias
                SharedPreferences myPreferencias= getSharedPreferences(String.valueOf(R.string.Preferencias), Activity.MODE_PRIVATE);
                SharedPreferences.Editor editorPrefe = myPreferencias.edit();

                //Creo los diferentes campos para mis preferencias
                editorPrefe.putString("Nombre",txtNombre.getText().toString());
                editorPrefe.putString("DNI",txtDNI.getText().toString());
                editorPrefe.putString("FechaNac",txtFecha.getText().toString());
                editorPrefe.putInt("Sexo",txtSex.getSelectedItemPosition());

                //Guardo las preferencias en el objeto
                editorPrefe.commit();


                //Creo un objeto para hacer la accion de pasar de un activity a otra
                Intent preferencias_activity = new Intent(MainActivity.this,PreferencesActivity.class);

                //Comienzo la activity
                startActivity(preferencias_activity);
            }
        });

    }
}
