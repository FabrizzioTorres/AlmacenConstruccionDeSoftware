package com.example.ALMACENMULTIPRO.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_transaccion")
public class DetalleTransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private int stockAnterior;

    private int stockResultante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaccion_id")
    private TransaccionEntity transaccion;

    public DetalleTransaccionEntity(){

    }
    public DetalleTransaccionEntity(Long id,
                                    int cantidad,
                                    int stockAnterior,
                                    int stockResultante,
                                    ProductoEntity producto,
                                    TransaccionEntity transaccion) {
        this.id = id;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockResultante = stockResultante;
        this.producto = producto;
        this.transaccion = transaccion;
    }

    public Long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public int getStockResultante() {
        return stockResultante;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setStockAnterior(int stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public void setStockResultante(int stockResultante) {
        this.stockResultante = stockResultante;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }
}