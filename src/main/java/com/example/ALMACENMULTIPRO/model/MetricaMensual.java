package com.example.ALMACENMULTIPRO.model;

public class MetricaMensual {

    private String mes;
    private int ingresos;
    private int salidas;
    private int movimientos;
    private int productosDistintos;
    private int proveedoresUsados;

    public MetricaMensual() {
    }

    public MetricaMensual(
            String mes,
            int ingresos,
            int salidas,
            int movimientos,
            int productosDistintos,
            int proveedoresUsados) {

        this.mes = mes;
        this.ingresos = ingresos;
        this.salidas = salidas;
        this.movimientos = movimientos;
        this.productosDistintos = productosDistintos;
        this.proveedoresUsados = proveedoresUsados;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public int getProductosDistintos() {
        return productosDistintos;
    }

    public void setProductosDistintos(int productosDistintos) {
        this.productosDistintos = productosDistintos;
    }

    public int getProveedoresUsados() {
        return proveedoresUsados;
    }

    public void setProveedoresUsados(int proveedoresUsados) {
        this.proveedoresUsados = proveedoresUsados;
    }
}