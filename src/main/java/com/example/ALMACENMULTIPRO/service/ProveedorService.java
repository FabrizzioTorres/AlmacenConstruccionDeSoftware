package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> listarProveedores();

    void guardarProveedor(
            Proveedor proveedor);

    Proveedor buscarProveedor(
            String id);

    void actualizarProveedor(
            Proveedor proveedor);

    void cambiarEstadoProveedor(
            String id);
}