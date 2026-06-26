package com.example.ALMACENMULTIPRO.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "destinos")
public class DestinoEntity {

    @Id
    @Column(name = "id_destino")
    private String idDestino;

    @Column(nullable = false)
    private String desNombre;

    private String desDireccion;

    private String desContacto;

    @Column(nullable = false)
    private String desEstado;

    @Column(length = 500)
    private String desDescripcion;

    public DestinoEntity(){

    }

    public DestinoEntity(String idDestino,
                         String desNombre,
                         String desDireccion,
                         String desContacto,
                         String desEstado,
                         String desDescripcion) {
        this.idDestino = idDestino;
        this.desNombre = desNombre;
        this.desDireccion = desDireccion;
        this.desContacto = desContacto;
        this.desEstado = desEstado;
        this.desDescripcion = desDescripcion;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public String getDesNombre() {
        return desNombre;
    }

    public String getDesDireccion() {
        return desDireccion;
    }

    public String getDesContacto() {
        return desContacto;
    }

    public String getDesEstado() {
        return desEstado;
    }

    public String getDesDescripcion() {
        return desDescripcion;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public void setDesNombre(String desNombre) {
        this.desNombre = desNombre;
    }

    public void setDesDireccion(String desDireccion) {
        this.desDireccion = desDireccion;
    }

    public void setDesContacto(String desContacto) {
        this.desContacto = desContacto;
    }

    public void setDesEstado(String desEstado) {
        this.desEstado = desEstado;
    }

    public void setDesDescripcion(String desDescripcion) {
        this.desDescripcion = desDescripcion;
    }
}
