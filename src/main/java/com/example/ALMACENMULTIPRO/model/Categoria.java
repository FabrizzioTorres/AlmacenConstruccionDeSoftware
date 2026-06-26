package com.example.ALMACENMULTIPRO.model;

public class Categoria {

    private String idCategoria;
    private String catNombre;
    private String catEstado;
    private String catDescripcion;

    public Categoria() {
    }

    public Categoria(String idCategoria, String catNombre, String catEstado, String catDescripcion) {

        this.idCategoria = idCategoria;
        this.catNombre = catNombre;
        this.catEstado = catEstado;
        this.catDescripcion = catDescripcion;
    }

    public String getIdCategoria() {

        return idCategoria;
    }

    public void setIdCategoria(String id_Categoria) {

        idCategoria = id_Categoria;
    }

    public String getCatNombre() {

        return catNombre;
    }

    public void setCatNombre(String catNombre) {

        this.catNombre = catNombre;
    }

    public String getCatEstado() {
        return catEstado;
    }

    public String getCatDescripcion() {
        return catDescripcion;
    }

    public void setCatEstado(String catEstado) {
        this.catEstado = catEstado;
    }

    public void setCatDescripcion(String catDescripcion) {
        this.catDescripcion = catDescripcion;
    }
}