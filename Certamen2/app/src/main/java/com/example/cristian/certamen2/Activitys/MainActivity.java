package com.example.cristian.certamen2.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cristian.certamen2.Adapters.ListaAdapter;
import com.example.cristian.certamen2.Models.Lista;
import com.example.cristian.certamen2.R;
import com.example.cristian.certamen2.Views.ItemClickListener;
import com.example.cristian.certamen2.Views.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView,
        ItemClickListener {


    TextView texto;
    String url="";
    String url1="";
    private static final String TAG ="Error" ;
    private ArrayList<Lista> myDataset;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instanciamos el RecyclerView del activity_main layout y lo conectamos con la MainActivity
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Instanciamos unlinear layout manager para setearlo en el RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Cargamos los datos en el dataset
        //loadDummyData();

        url1=getIntent().getExtras().getString("dato");
        System.out.println("url1="+url1);
        //Cargamos los datos en el dataset
        loadPhotosFromWeb();
    }

  /*  private void loadDummyData() {
        //Armamos un listado de tiulos y URL de foto para probar que ya tenemos el UI
        // y el Codigo Conectados
        myDataset = new ArrayList<Lista>();
        int i = 0;
        while ( i < 20) {
            Lista aPhoto = new Lista();
            aPhoto.setTitulo("Titulo " + String.valueOf(i));
            aPhoto.setDescripcion("asd");
            aPhoto.setActualizacion("123");
            myDataset.add(aPhoto);
            i++;
        }

        //Refrescamos el DATASET en el Adapter
        refreshDataset();
    }*/

    @Override
    public void refreshDataset() {

        if (mRecyclerView == null)
            return;

        if (mAdapter == null) {
            ListaAdapter mAdapter = new ListaAdapter(this, myDataset);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadPhotosFromWeb() {

        url = "https://api.github.com/users/"+url1+"/repos";
        //System.out.println(url);


        //Hacemos uso de Volley para consumir el End-point
        myDataset = new ArrayList<Lista>();

        //Definimos un String con la URL del End-point
        //String url = "http://www.mocky.io/v2/57eee3822600009324111202";

        //Instanciamos un objeto RequestQueue el cual se encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(this);

        //Armamos una peticion de tipo JSONArray por que es un JsonArray lo que obtendremos como resultado
        JsonArrayRequest aRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse");
                        //Obtenemos un JSONArray como respuesta
                        if (response != null && response.length() > 0) {
                            //Iteramos son el JSONArray
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject p = (JSONObject) response.get(i);
                                    if (p != null) {
                                        //Armamos un objeto Photo con el Title y la URL de cada JSONObject
                                        //Lista lista = new Lista();

                                        String name = p.getString("name");
                                        String description = p.getString("description");
                                        String updated_at = p.getString("updated_at");
                                        String html_url = p.getString("html_url");
                                        Lista lista = new Lista(name, description,updated_at,html_url);

                                        /*if (p.has("name"))
                                            lista.setTitulo(p.getString("name"));
                                        if (p.has("description"))
                                            lista.setDescripcion(p.getString("description"));
                                        if (p.has("updated_at"))
                                            lista.setDescripcion(p.getString("updated_at"));

                                        //Agreagamos el objeto Photo al Dataset
                                        myDataset.add(lista);*/
                                        myDataset.add(lista);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDataset.size() > 0)
                                        refreshDataset();
                                }
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        });

        //Agregamos la petición de tipo JSON a la cola
        queue.add(aRequest);
    }

    @Override
    public void onClick(View view, int position) {
        // The onClick implementation of the RecyclerView item click
        final Lista repo = myDataset.get(position);
        //Intent i = new Intent(this, CityviewActivity.class);
        //i.putExtra("url", repo.getUrl());
        //startActivity(i);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getWeb()));
        startActivity(browserIntent);
    }
}
