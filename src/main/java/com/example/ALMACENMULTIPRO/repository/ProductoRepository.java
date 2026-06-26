package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Producto;

import java.util.List;

public interface ProductoRepository {

    void guardarProducto(Producto producto);

    List<Producto> listarProductos();

    Producto buscarProducto(String id);

    void cambiarEstadoProducto(String id);

    void actualizarProducto(Producto producto);

}