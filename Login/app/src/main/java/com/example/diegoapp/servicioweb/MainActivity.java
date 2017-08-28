package com.example.diegoapp.servicioweb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button ingresar;

    EditText nombre;
    EditText contraseña;
    TextView resultado;

    private static final String IP = "http://orientacioneducativa.000webhostapp.com/buscarAdmin.php?";
    private static final String IP2 = "http://orientacioneducativa.000webhostapp.com/listar.php";
    /**
     * URLs del Web Service
     */
    public static final String LOGIN = IP  + "/obtenerAdmin.php";
    public static final String ASISTENTE = IP  + "/obtenerAsistente.php";
    public static final String ASISTENCIA = IP  + "insertarAsistencia.php";
    public static final String EVENTOS = IP  + "obtenerEventos.php";

    ObtenerWebService hiloconexion;
    ObtenerWebService hiloconexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.Nombre);
        contraseña = (EditText) findViewById(R.id.Contraseña);
        resultado =(TextView) findViewById(R.id.resultado);
        ingresar = (Button) findViewById(R.id.Ingresar);
        ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Ingresar:
                hiloconexion = new ObtenerWebService();

                String cadena= IP+"user="+nombre.getText().toString()+"&pass="+contraseña.getText().toString();

                hiloconexion.execute(cadena);

                break;
            default:
                break;
        }

    }

    public class ObtenerWebService extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
           //resultado.setText("Hey");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
           //resultado.setText(s);
            String uno=s;
            if(uno.length() > 80){
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(i);
            }
        }


        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }


        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            String devuelve="";

            URL url = null;
            try {
                url= new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){

                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine())!= null){
                        result.append(line);
                    }

                    JSONObject respuestaJSON = new JSONObject(result.toString());
                    String resultJSON = respuestaJSON.getString("estado");
                   // JSONArray arr=respuestaJSON.getJSONArray(params[1]);

                    if(resultJSON.length()==1) {
                        devuelve = respuestaJSON.toString();
                    }
                    else devuelve="No2";
                }else devuelve="No1";

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return devuelve;
        }
    }

}
