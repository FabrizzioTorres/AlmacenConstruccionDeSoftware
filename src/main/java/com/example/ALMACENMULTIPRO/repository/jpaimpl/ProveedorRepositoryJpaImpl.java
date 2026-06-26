package com.example.ALMACENMULTIPRO.repository.jpaimpl;

import com.example.ALMACENMULTIPRO.entity.ProveedorEntity;
import com.example.ALMACENMULTIPRO.mapper.ProveedorMapper;
import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.repository.ProveedorRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.ProveedorJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorRepositoryJpaImpl
        implements ProveedorRepository {

    private final ProveedorJpaRepository
            proveedorJpaRepository;

    public ProveedorRepositoryJpaImpl(
            ProveedorJpaRepository proveedorJpaRepository) {

        this.proveedorJpaRepository =
                proveedorJpaRepository;
    }

    @Override
    public void guardarProveedor(
            Proveedor proveedor) {

        ProveedorEntity entity =
                ProveedorMapper.toEntity(
                        proveedor
                );

        proveedorJpaRepository.save(
                entity
        );
    }

    @Override
    public List<Proveedor> listarProveedores() {

        return proveedorJpaRepository
                .findAll()
                .stream()
                .map(
                        ProveedorMapper::toModel
                )
                .toList();
    }

    @Override
    public Proveedor buscarProveedor(
            String id) {

        return proveedorJpaRepository
                .findById(id)
                .map(
                        ProveedorMapper::toModel
                )
                .orElse(null);
    }

    @Override
    public void actualizarProveedor(
            Proveedor proveedor) {

        ProveedorEntity entity =
                proveedorJpaRepository
                        .findById(
                                proveedor.getIdProveedor()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Proveedor no encontrado."
                                )
                        );

        entity.setProvNombre(
                proveedor.getProvNombre()
        );


        entity.setProvDireccion(
                proveedor.getProvDireccion()
        );

        entity.setProvDescripcion(
                proveedor.getProvDescripcion()
        );

        entity.setProRubro(
                proveedor.getProRubro()
        );

        proveedorJpaRepository.save(
                entity
        );
    }

    @Override
    public void cambiarEstadoProveedor(
            String id) {

        ProveedorEntity entity =
                proveedorJpaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Proveedor no encontrado."
                                )
                        );

        if(entity.getProvEstado()
                .equalsIgnoreCase(
                        "Activo"
                )){

            entity.setProvEstado(
                    "Inactivo"
            );

        }else{

            entity.setProvEstado(
                    "Activo"
            );
        }

        proveedorJpaRepository.save(
                entity
        );
    }
}