package com.example.ALMACENMULTIPRO.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "proveedores")
public class ProveedorEntity {

    @Id
    @Column(name = "id_proveedor")
    private String idProveedor;

    @Column(nullable = false)
    private String provNombre;

    private String provDireccion;

    @Column(nullable = false)
    private String provEstado;

    @Column(length = 500)
    private String provDescripcion;

    @Column(length = 100)
    private String proRubro;

    public ProveedorEntity() {
    }

    public ProveedorEntity(String idProveedor,
                           String provNombre,
                           String provDireccion,
                           String provEstado,
                           String provDescripcion,
                           String proRubro) {

        this.idProveedor = idProveedor;
        this.provNombre = provNombre;
        this.provDireccion = provDireccion;
        this.provEstado = provEstado;
        this.provDescripcion = provDescripcion;
        this.proRubro = proRubro;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public String getProvDireccion() {
        return provDireccion;
    }

    public String getProvEstado() {
        return provEstado;
    }

    public String getProvDescripcion() {
        return provDescripcion;
    }

    public String getProRubro() {
        return proRubro;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public void setProvDireccion(String provDireccion) {
        this.provDireccion = provDireccion;
    }

    public void setProvEstado(String provEstado) {
        this.provEstado = provEstado;
    }

    public void setProvDescripcion(String provDescripcion) {
        this.provDescripcion = provDescripcion;
    }

    public void setProRubro(String proRubro) {
        this.proRubro = proRubro;
    }
}



