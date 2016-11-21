package com.example.js_cmd.actividad3a;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



/**
 * Created by isgarsi on 19/2/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Nombres de archivos que se crearan en dos botones de la vista y tendran el contenido del eleento texto
    private static final String INTERNAL_FILENAME = "pruebaI.txt";
    private static final String EXTERNAL_FILENAME = "pruebaE.txt";


    //Declaro los elementos de mi activity que luego iran linkados al layout
    private EditText text;
    private EditText filename;
    private Button btnArchivoDefault;
    private Button btnGuardarMI;
    private Button btnLeerMI;
    private Button btnGuardarSD;
    private Button btnLeerSD;
    private Button btnCrearTxt;
    private Button btnLeerTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Les asigno el id del layout para luego manipularlo
        text = (EditText) findViewById(R.id.texto);
        filename = (EditText) findViewById(R.id.txt_nombrefichero);
        btnArchivoDefault = (Button) findViewById(R.id.btn_archivo_default);
        btnGuardarMI = (Button) findViewById(R.id.btn_guardar_MI);
        btnLeerMI = (Button) findViewById(R.id.btn_leer_MI);
        btnGuardarSD = (Button) findViewById(R.id.btn_guardar_SD);
        btnLeerSD = (Button) findViewById(R.id.btn_leer_SD);
        btnCrearTxt = (Button) findViewById(R.id.btn_crear_txt);
        btnLeerTXT = (Button) findViewById(R.id.btn_leer_txt);

        // cada boton tiene una accion
        btnArchivoDefault.setOnClickListener(this);
        btnGuardarMI.setOnClickListener(this);
        btnLeerMI.setOnClickListener(this);
        btnGuardarSD.setOnClickListener(this);
        btnLeerSD.setOnClickListener(this);
        btnCrearTxt.setOnClickListener(this);
        btnLeerTXT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_archivo_default://Lee el archivo que está almacenado en la carpeta raw
                InputStream is = getResources().openRawResource(R.raw.holamundo);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                try {
                    String textRead = buffer.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error leyendo fichero", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_leer_MI://Lee el archivo de memoria interna
                File rFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try {
                    BufferedReader bReader = new BufferedReader(new FileReader(rFile));
                    String textRead = bReader.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error leyendo fichero desde la memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_guardar_MI://Guarda el archivo de memoria interna
                File wFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try {
                    FileWriter out = new FileWriter(wFile);
                    out.write(text.getText().toString());
                    out.close();
                } catch (IOException e) {
                    Toast.makeText(this, "Error escribiendo fichero en la memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_leer_SD://Lee el archivo de memoria externa
                if (esSDRead()) {
                    File rFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        BufferedReader bReader = new BufferedReader(new FileReader(rFileE));
                        String textRead = bReader.readLine();
                        Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error leyendo fichero desde la memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_guardar_SD://Guarda el archivo de memoria externa
                if (esSDWrite()) {
                    File wFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        FileWriter out = new FileWriter(wFileE);
                        out.write(text.getText().toString());
                        out.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error escribiendo fichero en la memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_crear_txt://Crea un archivo con el texto y el nombre del fichero que viene dado en la vista en la memoria interna
                String valor="";
                File cFile = new File(getFilesDir(), filename.getText() + ".txt");
                if (!cFile.exists()){
                    valor=text.getText().toString();
                }else{Toast.makeText(this, "El fichero ya existia, pero se ha creado correctamente", Toast.LENGTH_SHORT).show();
                    valor=text.getText().toString().concat(filename.getText().toString());}

                try {
                    FileWriter out = new FileWriter(cFile);
                    out.write(valor);
                    out.close();
                } catch (IOException e) {
                    Toast.makeText(this, "Error escribiendo fichero en la memoria interna", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "Fichero creado correctamente", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_leer_txt://Lee un archivo con el texto y el nombre del fichero que viene dado en la vista en la memoria interna
                File readtxtFile = new File(getFilesDir(), filename.getText() + ".txt");
                if(!readtxtFile.exists()){ Toast.makeText(this, "No existe el fichero", Toast.LENGTH_SHORT).show();}
                try {
                    BufferedReader bReader = new BufferedReader(new FileReader(readtxtFile));
                    String textRead = bReader.readLine();
                    text.setText(textRead);
                } catch (IOException e) {
                    Toast.makeText(this, "Error leyendo fichero desde la memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //Comprueba si la memoria externa tiene permisos de escritura
    public boolean esSDWrite() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //Comprueba si la memoria externa tiene permisos de lectura
    public boolean esSDRead() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}