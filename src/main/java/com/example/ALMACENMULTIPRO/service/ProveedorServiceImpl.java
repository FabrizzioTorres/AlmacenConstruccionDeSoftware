package com.example.ALMACENMULTIPRO.service;


import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.repository.ProveedorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl
        implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(
            ProveedorRepository proveedorRepository) {

        this.proveedorRepository =
                proveedorRepository;
    }

    @Override
    public List<Proveedor> listarProveedores() {

        return proveedorRepository.listarProveedores();
    }

    @Override
    public void guardarProveedor(
            Proveedor proveedor) {
        if(proveedor == null){

            throw new RuntimeException(
                    "Proveedor inválido."
            );
        }
        if (!proveedor.getIdProveedor()
                .matches("\\d{11}")) {

            throw new RuntimeException(
                    "El RUC debe tener 11 dígitos"
            );
        }
        if(proveedor.getProvNombre() == null
                || proveedor.getProvNombre().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        if (proveedor.getProvDireccion() == null ||
                proveedor.getProvDireccion().isBlank()) {

            throw new RuntimeException(
                    "Debe ingresar una dirección."
            );
        }
        Proveedor existente =
                buscarProveedor(
                        proveedor.getIdProveedor()
                );

        if (existente != null) {

            throw new RuntimeException(
                    "Ya existe un proveedor con ese RUC"
            );
        }
        boolean existeNombre =
                listarProveedores()
                        .stream()
                        .anyMatch(
                                p -> p.getProvNombre()
                                        .equalsIgnoreCase(
                                                proveedor.getProvNombre()
                                        )
                        );

        if(existeNombre){

            throw new RuntimeException(
                    "Ya existe un proveedor con ese nombre."
            );
        }

        if(proveedor.getProRubro() == null
                || proveedor.getProRubro().isBlank()){

            throw new RuntimeException(
                    "Debe seleccionar un rubro."
            );
        }
        if (proveedor.getProvEstado() == null ||
                proveedor.getProvEstado().isBlank()) {
            proveedor.setProvEstado("Activo");
        }

        proveedorRepository.guardarProveedor(
                proveedor
        );
    }

    @Override
    public Proveedor buscarProveedor(
            String id) {

        return proveedorRepository.buscarProveedor(id);
    }

    @Override
    public void actualizarProveedor(
            Proveedor proveedor) {
        if(proveedor == null){
            throw new RuntimeException(
                    "Proveedor inválido."
            );
        }
        Proveedor existente =
                buscarProveedor(
                        proveedor.getIdProveedor()
                );

        if(existente == null){

            throw new RuntimeException(
                    "Proveedor no encontrado."
            );
        }
        boolean existeNombre =
                listarProveedores()
                        .stream()
                        .anyMatch(
                                p ->
                                        !p.getIdProveedor()
                                                .equals(
                                                        proveedor.getIdProveedor()
                                                )
                                                &&
                                                p.getProvNombre()
                                                        .equalsIgnoreCase(
                                                                proveedor.getProvNombre()
                                                        )
                        );

        if(existeNombre){

            throw new RuntimeException(
                    "Ya existe un proveedor con ese nombre."
            );
        }
        if(proveedor.getProRubro() == null
                || proveedor.getProRubro().isBlank()){

            throw new RuntimeException(
                    "Debe seleccionar un rubro."
            );
        }
        proveedorRepository.actualizarProveedor(
                proveedor
        );
    }

    @Override
    public void cambiarEstadoProveedor(
            String id) {
        Proveedor proveedor =
                buscarProveedor(id);
        if(proveedor == null){

            throw new RuntimeException(
                    "Proveedor no encontrado."
            );
        }
        proveedorRepository.cambiarEstadoProveedor(id);
    }
}