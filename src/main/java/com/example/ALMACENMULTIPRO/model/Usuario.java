package com.example.ALMACENMULTIPRO.model;

public class Usuario {

    private String idUsuario;
    private String usuNombre;
    private String usuCorreo;
    private String usuContraseña;
    private String usuRol;
    private String usuEstado;


    public Usuario() {
    }

    public Usuario(
            String idUsuario,
            String usuNombre,
            String usuCorreo,
            String usuContraseña,
            String usuRol) {

        this.idUsuario = idUsuario;
        this.usuNombre = usuNombre;
        this.usuCorreo = usuCorreo;
        this.usuContraseña = usuContraseña;
        this.usuRol = usuRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String id_Usuario) {
        this.idUsuario = id_Usuario;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuContraseña() {
        return usuContraseña;
    }

    public void setUsuContraseña(String usuContraseña) {
        this.usuContraseña = usuContraseña;
    }

    public String getUsuRol() {
        return usuRol;
    }

    public void setUsuRol(String usuRol) {
        this.usuRol = usuRol;
    }

    public String getUsuEstado() {
        return usuEstado;
    }


    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }
}