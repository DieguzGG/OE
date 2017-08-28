package com.example.diegoapp.servicioweb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by diego on 28/08/2017.
 */

public class Evento  extends AppCompatActivity implements View.OnClickListener{

    TextView fechaEvento, nombreEvento,asistente;

    private static final String IP = "http://orientacioneducativa.000webhostapp.com/listar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fechaEvento = (TextView) findViewById(R.id.fechaEvento);
        nombreEvento = (TextView) findViewById(R.id.nombreEvento);
        asistente=(TextView) findViewById(R.id.Asistente);
    }

    @Override
    public void onClick(View v) {

    }

    public class ObtenerWebService extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //.setText(s);
            String uno=s;
            // if(uno.length() > 80){
            //    startActivity(i);
            //}
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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
                    //String resultJSON = respuestaJSON.getString(/*"estado"*/"frutas");
                    JSONArray arr=respuestaJSON.getJSONArray("frutas");

                    if(arr.length()>0) {

                        devuelve=arr.getJSONObject(0).getString("nombre");
                        //devuelve = respuestaJSON.toString()+"hey222"+arr;
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


