package com.example.ALMACENMULTIPRO.model;

public class Proveedor {

    private String idProveedor; // RUC

    private String provNombre;

    private String provDireccion;

    private String provEstado;

    private String provDescripcion;

    private String proRubro;

    public Proveedor() {
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(
            String idProveedor) {

        this.idProveedor = idProveedor;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(
            String provNombre) {

        this.provNombre = provNombre;
    }

    public String getProvDireccion() {
        return provDireccion;
    }

    public void setProvDireccion(
            String provDireccion) {

        this.provDireccion = provDireccion;
    }

    public String getProvDescripcion() {
        return provDescripcion;
    }

    public void setProvDescripcion(
            String provDescripcion) {

        this.provDescripcion =
                provDescripcion;
    }

    public String getProvEstado() {
        return provEstado;
    }

    public void setProvEstado(String provEstado) {
        this.provEstado = provEstado;
    }
    public String getProRubro() {
        return proRubro;
    }

    public void setProRubro(String proRubro) {
        this.proRubro = proRubro;
    }
}