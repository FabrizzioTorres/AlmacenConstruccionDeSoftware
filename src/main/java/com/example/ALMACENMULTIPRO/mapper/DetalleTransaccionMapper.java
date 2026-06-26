package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.DetalleTransaccionEntity;
import com.example.ALMACENMULTIPRO.model.DetalleTransaccion;

public class DetalleTransaccionMapper {
    public static DetalleTransaccionEntity toEntity(
            DetalleTransaccion detalle){

        DetalleTransaccionEntity entity =
                new DetalleTransaccionEntity();

        entity.setCantidad(
                detalle.getCantidad()
        );

        entity.setStockAnterior(
                detalle.getStockAnterior()
        );

        entity.setStockResultante(
                detalle.getStockResultante()
        );

        return entity;
    }
    public static DetalleTransaccion toModel(
            DetalleTransaccionEntity entity){

        DetalleTransaccion detalle =
                new DetalleTransaccion();

        detalle.setCantidad(
                entity.getCantidad()
        );

        detalle.setStockAnterior(
                entity.getStockAnterior()
        );

        detalle.setStockResultante(
                entity.getStockResultante()
        );

        if(entity.getProducto() != null){

            detalle.setIdProducto(
                    "P" + String.format(
                            "%04d",
                            entity.getProducto()
                                    .getProdId()
                    )
            );

            detalle.setNombreProducto(
                    entity.getProducto()
                            .getProdNombre()
            );
        }

        return detalle;
    }
}