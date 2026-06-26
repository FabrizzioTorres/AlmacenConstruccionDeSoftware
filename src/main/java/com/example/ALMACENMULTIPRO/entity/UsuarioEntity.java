package com.example.ALMACENMULTIPRO.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(nullable = false)
    private String usuNombre;

    @Column(
            nullable = false,
            unique = true
    )
    private String usuCorreo;

    @Column(name = "usu_contrasena", nullable = false)
    private String usuContrasena;

    @Column(nullable = false)
    private String usuRol;

    @Column(nullable = false)
    private String usuEstado;


    public UsuarioEntity() {
    }
    public UsuarioEntity(
            String idUsuario,
            String usuNombre,
            String usuCorreo,
            String usuContrasena,
            String usuRol,
            String usuEstado) {

        this.idUsuario = idUsuario;
        this.usuNombre = usuNombre;
        this.usuCorreo = usuCorreo;
        this.usuContrasena = usuContrasena;
        this.usuRol = usuRol;
        this.usuEstado = usuEstado;
    }
    public String getIdUsuario() {

        return idUsuario;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public String getUsuContraseña() {
        return usuContrasena;
    }

    public String getUsuRol() {
        return usuRol;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public void setUsuContraseña(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public void setUsuRol(String usuRol) {
        this.usuRol = usuRol;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

}
