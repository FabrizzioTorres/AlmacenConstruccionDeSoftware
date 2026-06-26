package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.ProveedorEntity;
import com.example.ALMACENMULTIPRO.model.Proveedor;

public class ProveedorMapper {
    public static ProveedorEntity toEntity(
            Proveedor proveedor){

        ProveedorEntity entity =
                new ProveedorEntity();

        entity.setIdProveedor(
                proveedor.getIdProveedor()
        );

        entity.setProvNombre(
                proveedor.getProvNombre()
        );

        entity.setProvDireccion(
                proveedor.getProvDireccion()
        );

        entity.setProvEstado(
                proveedor.getProvEstado()
        );

        entity.setProvDescripcion(
                proveedor.getProvDescripcion()
        );

        entity.setProRubro(
                proveedor.getProRubro()
        );

        return entity;
    }
    public static Proveedor toModel(
            ProveedorEntity entity){

        Proveedor proveedor =
                new Proveedor();

        proveedor.setIdProveedor(
                entity.getIdProveedor()
        );

        proveedor.setProvNombre(
                entity.getProvNombre()
        );

        proveedor.setProvDireccion(
                entity.getProvDireccion()
        );

        proveedor.setProvEstado(
                entity.getProvEstado()
        );

        proveedor.setProvDescripcion(
                entity.getProvDescripcion()
        );

        proveedor.setProRubro(
                entity.getProRubro()
        );

        return proveedor;
    }
}
