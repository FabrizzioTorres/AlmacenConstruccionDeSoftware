package com.example.ALMACENMULTIPRO.model;

public class Destino {

    private String idDestino;

    private String desNombre;

    private String desDireccion;

    private String desContacto;

    private String desEstado;

    private String desDescripcion;

    public Destino() {
    }

    public String getIdDestino() {

        return idDestino;
    }

    public void setIdDestino(
            String idDestino) {

        this.idDestino = idDestino;
    }

    public String getDesNombre() {

        return desNombre;
    }

    public void setDesNombre(
            String desNombre) {

        this.desNombre = desNombre;
    }

    public String getDesDescripcion() {

        return desDescripcion;
    }

    public void setDesDescripcion(
            String desDescripcion) {

        this.desDescripcion =
                desDescripcion;
    }

    public String getDesDireccion() {
        return desDireccion;
    }

    public void setDesDireccion(String desDireccion) {
        this.desDireccion = desDireccion;
    }

    public String getDesEstado() {
        return desEstado;
    }

    public void setDesEstado(String desEstado) {
        this.desEstado = desEstado;
    }

    public String getDesContacto() {
        return desContacto;
    }

    public void setDesContacto(String desContacto) {
        this.desContacto = desContacto;
    }
}