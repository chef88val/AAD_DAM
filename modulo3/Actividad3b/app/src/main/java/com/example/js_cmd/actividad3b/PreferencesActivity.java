package com.example.js_cmd.actividad3b;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PreferencesActivity extends AppCompatActivity {
    //Declaro los objetos
    private TextView txtNombre,txtDNI,txtFecha;
    private Spinner txtSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //Me creo/inicializo los objetos dentro de mi clase que están conectados con el xml/resources
        final Button btnAccion = (Button) findViewById(R.id.btn_Accion);
        txtNombre = (TextView) findViewById(R.id.txt_Nombre);
        txtDNI = (TextView) findViewById(R.id.txt_DNI);
        txtFecha = (TextView) findViewById(R.id.txt_FNac);
        txtSex = (Spinner) findViewById(R.id.spinner_Sexo);
        //Habilito los elementos de la vista para que no se puedan editar los valores recuperados
        txtSex.setEnabled(false);
        txtFecha.setEnabled(false);
        txtDNI.setEnabled(false);
        txtNombre.setEnabled(false);

        btnAccion.setOnClickListener(new View.OnClickListener() {//Evento listener del boton
            @Override
            public void onClick(View v) {
                //ME creo un objeto para guardar las preferencias
                SharedPreferences myPreferencias= getSharedPreferences(String.valueOf(R.string.Preferencias), Activity.MODE_PRIVATE);

                //Recupero en cada elemento de la vista la preferencia deseada
                txtNombre.setText(myPreferencias.getString("Nombre",""));
                txtDNI.setText(myPreferencias.getString("DNI",""));
                txtFecha.setText(myPreferencias.getString("FechaNac",""));
                txtSex.setSelection(myPreferencias.getInt("Sexo",1));



            }
        });
    }
}
