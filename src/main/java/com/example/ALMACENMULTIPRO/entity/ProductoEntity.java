package com.example.ALMACENMULTIPRO.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;

    @Column(nullable = false)
    private String prodNombre;

    private int stock;

    @Column(nullable = false)
    private String prodEstado;

    @Column(length = 500)
    private String prodDescripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    public ProductoEntity(){

    }
    public ProductoEntity(Long prodId,
                          String prodNombre,
                          int stock,
                          String prodEstado,
                          String prodDescripcion,
                          CategoriaEntity categoria) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.stock = stock;
        this.prodEstado = prodEstado;
        this.prodDescripcion = prodDescripcion;
        this.categoria = categoria;
    }

    public Long getProdId() {
        return prodId;
    }


    public String getProdNombre() {
        return prodNombre;
    }

    public int getStock() {
        return stock;
    }

    public String getProdEstado() {
        return prodEstado;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setProdEstado(String prodEstado) {
        this.prodEstado = prodEstado;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}