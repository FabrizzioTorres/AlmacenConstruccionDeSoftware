package com.example.ALMACENMULTIPRO.model;

public class Producto {

    private String idProducto;
    private String prodNombre;
    private String prodCategoria;
    private int stock;
    private String prodEstado;
    private String nombCategoria;
    private String prodDescripcion;

    public Producto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdCategoria() {
        return prodCategoria;
    }

    public void setProdCategoria(String prodCategoria) {
        this.prodCategoria = prodCategoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProdEstado() {
        return prodEstado;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public void setProdEstado(String prodEstado) {
        this.prodEstado = prodEstado;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public String getNombCategoria() {
        return nombCategoria;
    }

    public void setNombCategoria(String nombCategoria) {
        this.nombCategoria = nombCategoria;
    }
}