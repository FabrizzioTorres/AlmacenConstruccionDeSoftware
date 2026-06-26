package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Proveedor;

import java.util.List;

public interface ProveedorRepository {

    void guardarProveedor(
            Proveedor proveedor);

    List<Proveedor> listarProveedores();

    Proveedor buscarProveedor(
            String id);

    void actualizarProveedor(
            Proveedor proveedor);

    void cambiarEstadoProveedor(
            String id);
}