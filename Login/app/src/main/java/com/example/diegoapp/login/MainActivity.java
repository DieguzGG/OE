package com.example.diegoapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario,txtPass;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Creación del fragmento principal
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new MainFragment(),"MainFragment")
                        .commit();
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtPass=(EditText) findViewById(R.id.txtPass);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        final String res = enviarPost(txtUsuario.toString(),txtPass.toString());
                       // Toast.makeText(getApplicationContext(),"res",Toast.LENGTH_SHORT);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int result = objJSON(res);
                                if(result>0) {
                                    Intent i = new Intent(getApplicationContext(), Principal.class);
                                    startActivity(i);
                                }else
                                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrectos",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                tr.start();
                Toast.makeText(getApplicationContext(),"Botton",Toast.LENGTH_SHORT);

            }
        });
    }

    public String enviarPost(String use, String pass){

        String parametros = "user="+use+"pass="+pass;
        HttpURLConnection connection=null;
        String respuesta="";

        try {
            URL url = new URL("http://orientacioneducativa.000webhostapp.com/buscarAdmin.php");
            connection=(HttpURLConnection)url.openConnection();


            InputStream in = connection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Lenght",""+Integer.toString(parametros.getBytes().length));

            connection.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            Scanner inStream = new Scanner(connection.getInputStream());
            while(inStream.hasNextLine()){
                respuesta+=(inStream.nextLine());}

            Toast.makeText(getApplicationContext(),"Usuario"+txtUsuario.toString()+"Pass"+txtPass.toString(),Toast.LENGTH_SHORT);

        }catch (Exception e){}

            return respuesta.toString();
    }

    public int objJSON(String resp){
        int res = 0;
        try{
            JSONArray json= new JSONArray(resp);
            if(json.length()>0)
                res=1;
        }catch (Exception e){}
        return res;
    }



}
