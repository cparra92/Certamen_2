package com.example.cristian.certamen2.Models;

/**
 * Created by cristian on 16-10-2016.
 */

public class Lista {
    private String titulo;
    private String descripcion;
    private String actualizacion;
    private String web;

    public Lista() {
    }

    public Lista(String actualizacion, String descripcion, String titulo) {
        this.actualizacion = actualizacion;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }

    public String getWeb() {

        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Lista(String titulo, String descripcion, String actualizacion, String web) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.actualizacion = actualizacion;
        this.web = web;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }
}
