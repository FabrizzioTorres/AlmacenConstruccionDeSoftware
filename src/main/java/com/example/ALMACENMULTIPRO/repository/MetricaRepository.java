package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.model.Usuario;

import java.util.List;

public interface MetricaRepository {

    List<Producto> obtenerProductos();

    List<Usuario> obtenerUsuarios();

    List<Proveedor> obtenerProveedores();

    List<Transaccion> obtenerTransacciones();
}
