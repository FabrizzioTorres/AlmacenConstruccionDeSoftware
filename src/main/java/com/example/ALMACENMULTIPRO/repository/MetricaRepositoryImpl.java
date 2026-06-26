package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.model.Usuario;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetricaRepositoryImpl implements MetricaRepository {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProveedorRepository proveedorRepository;
    private final TransaccionRepository transaccionRepository;

    public MetricaRepositoryImpl(
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository,
            ProveedorRepository proveedorRepository,
            TransaccionRepository transaccionRepository) {

        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.proveedorRepository = proveedorRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {

        return productoRepository.listarProductos();
    }

    @Override
    public List<Usuario> obtenerUsuarios() {

        return usuarioRepository.listarUsuarios();
    }

    @Override
    public List<Proveedor> obtenerProveedores() {

        return proveedorRepository.listarProveedores();
    }

    @Override
    public List<Transaccion> obtenerTransacciones() {

        return transaccionRepository.listarTransacciones();
    }
}