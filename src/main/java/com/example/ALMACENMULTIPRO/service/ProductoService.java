package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Producto;

import java.util.List;

public interface ProductoService {

    void guardarProducto(Producto producto);

    List<Producto> listarProductos();

    void cambiarEstadoProducto(String id);

    Producto buscarProducto(String id);

    void actualizarProducto(Producto producto);
}