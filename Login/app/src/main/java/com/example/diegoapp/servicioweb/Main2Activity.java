package com.example.diegoapp.servicioweb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.Result;

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
import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,ZXingScannerView.ResultHandler{

    private ZXingScannerView scaner;
    protected ArrayAdapter<CharSequence> adapter;
    public String qr="";
    Spinner spinner;
    TextView fechaEvento, nombreEvento, asistente;
    Button escanear,registrar;

    private ArrayList<String> values;
    ArrayAdapter<String> dataAdapter;

    ObtenerWebService hiloconexion;

    private static final String IP = "http://orientacioneducativa.000webhostapp.com/listar.php";

    public static final String INSERTARASISTENCIA = IP + "insertarAsistencia.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner = (Spinner) findViewById(R.id.spinner);
        fechaEvento = (TextView) findViewById(R.id.fechaEvento);
        nombreEvento = (TextView) findViewById(R.id.nombreEvento);
        asistente=(TextView) findViewById(R.id.Asistente);
        escanear = (Button) findViewById(R.id.Escanear);
        registrar = (Button) findViewById(R.id.Registrar);
        //spinner.setOnItemSelectedListener(this);
        hiloconexion = new ObtenerWebService();
        String cadena= IP;
        hiloconexion.execute(cadena);
        //arreglo();
    }

    public void arreglo(){
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>
                                               arg0, View arg1, int arg2, long arg3) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                values = new ArrayList<String>();
            }
        });
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);


}

    public void EscanerQR(View view){
        scaner= new ZXingScannerView(this);
        setContentView(scaner);
        scaner.setResultHandler(this);
        scaner.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scaner.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        qr=result.getText().intern();
        asistente.setText(qr);
        scaner.resumeCameraPreview(this);
        scaner.stopCamera();
        setContentView(R.layout.activity_main2);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public class ObtenerWebService extends AsyncTask<String,Void,String>{

        protected void onPreExecute(String s) {
            fechaEvento.setText(s);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            values.add(s);
            spinner.setAdapter(dataAdapter);
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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}