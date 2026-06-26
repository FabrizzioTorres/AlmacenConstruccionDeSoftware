package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.ProductoEntity;
import com.example.ALMACENMULTIPRO.model.Producto;

public class ProductoMapper {
    public static ProductoEntity toEntity(
            Producto producto) {

        ProductoEntity entity =
                new ProductoEntity();

        entity.setProdNombre(
                producto.getProdNombre()
        );

        entity.setStock(
                producto.getStock()
        );

        entity.setProdEstado(
                producto.getProdEstado()
        );

        entity.setProdDescripcion(
                producto.getProdDescripcion()
        );

        return entity;
    }

    public static Producto toModel(
            ProductoEntity entity) {

        Producto producto =
                new Producto();

        producto.setIdProducto(
                "P" + String.format(
                        "%04d",
                        entity.getProdId()
                )
        );

        producto.setProdNombre(
                entity.getProdNombre()
        );

        producto.setStock(
                entity.getStock()
        );

        producto.setProdEstado(
                entity.getProdEstado()
        );

        producto.setProdDescripcion(
                entity.getProdDescripcion()
        );

        if (entity.getCategoria() != null) {

            producto.setProdCategoria(
                    "C" + String.format(
                            "%04d",
                            entity.getCategoria()
                                    .getCatId()
                    )
            );

            producto.setNombCategoria(
                    entity.getCategoria()
                            .getCatNombre()
            );
        }

        return producto;
    }
}
