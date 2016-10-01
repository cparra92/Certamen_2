package com.example.cristian.certamen2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class lista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }


    private String titulo, descripcion, actualizacion;

    public lista(){}
    public lista(String titulo, String descripcion, String actualizacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.actualizacion = actualizacion;
    }

    public String getTitulo() {
        return this.titulo;
    }
    public void setTitulo(String nombre) {
        this.titulo = nombre;
    }

    public  String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String nombre) {
        this.descripcion = nombre;
    }

}
