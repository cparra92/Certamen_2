package com.example.cristian.certamen2;

import com.example.cristian.certamen2.conection.HttpServerConnection;
import com.example.cristian.certamen2.listaadapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private Button btnAceptar;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos una referencia a los controles de la interfaz
        btnAceptar = (Button)findViewById(R.id.button);
        edit = (EditText)findViewById(R.id.editText);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, NoUsuario.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                //b.putString("NOMBRE", txtNombre.getText().toString());

                //Añadimos la información al intent
                //intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.RecView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57eee3822600009324111202", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);

                    // specify an adapter (see also next example)
                    mAdapter = new listaadapter(getLista(result));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }

    private List<lista> getLista(String result){
        List<lista> listaLibros = new ArrayList<lista>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();
            for(int i = 0; i < size; i++){
                lista libro = new lista();
                JSONObject objeto = lista.getJSONObject(i);

                //libro.setId(objeto.getInt("id"));
                libro.setTitulo(objeto.getString("name"));
                //libro.setEditorial(objeto.getString("editorial"));
                libro.setDescripcion(objeto.getString("description"));
                //libro.setAutor(objeto.getInt("autor"));

                listaLibros.add(libro);
            }
            return listaLibros;
        } catch (JSONException e) {
            e.printStackTrace();
            return listaLibros;
        }
    }
}
