package com.example.ALMACENMULTIPRO.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long catId;

    @Column(
            name = "cat_nombre",
            nullable = false,
            unique = true
    )
    private String catNombre;

    @Column(
            name = "cat_estado",
            nullable = false
    )
    private String catEstado;

    @Column(
            name = "cat_descripcion",
            length = 500
    )
    private String catDescripcion;

    @OneToMany(
            mappedBy = "categoria",
            fetch = FetchType.LAZY
    )
    private List<ProductoEntity> productos;


    public CategoriaEntity(){

    }
    public CategoriaEntity(Long catId,
                           String catNombre,
                           String catEstado,
                           String catDescripcion,
                           List<ProductoEntity> productos) {
        this.catId = catId;
        this.catNombre = catNombre;
        this.catEstado = catEstado;
        this.catDescripcion = catDescripcion;
        this.productos = productos;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatNombre() {
        return catNombre;
    }

    public void setCatNombre(String catNombre) {
        this.catNombre = catNombre;
    }

    public String getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(String catEstado) {
        this.catEstado = catEstado;
    }

    public String getCatDescripcion() {
        return catDescripcion;
    }

    public void setCatDescripcion(String catDescripcion) {
        this.catDescripcion = catDescripcion;
    }

    public List<ProductoEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoEntity> productos) {
        this.productos = productos;
    }
}