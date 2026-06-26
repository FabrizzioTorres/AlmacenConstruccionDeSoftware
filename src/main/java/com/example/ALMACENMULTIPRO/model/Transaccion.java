package com.example.ALMACENMULTIPRO.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Transaccion {

    private String idTransaccion;

    private String tipo;

    private String proveedor;

    private String destino;

    private String responsable;

    private String asignado;

    private LocalDate fecha;

    private LocalTime hora;

    private List<DetalleTransaccion> detalles;

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(
            String asignado) {

        this.asignado = asignado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<DetalleTransaccion> getDetalles() {
        return detalles;
    }

    public void setDetalles(
            List<DetalleTransaccion> detalles) {

        this.detalles = detalles;
    }
    public String getDestino() {
        return destino;
    }

    public void setDestino(
            String destino) {

        this.destino = destino;
    }
}