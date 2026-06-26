package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.DetalleTransaccionEntity;
import com.example.ALMACENMULTIPRO.entity.TransaccionEntity;
import com.example.ALMACENMULTIPRO.model.Transaccion;

import java.util.List;

public class TransaccionMapper {
    public static TransaccionEntity toEntity(
            Transaccion transaccion){

        TransaccionEntity entity =
                new TransaccionEntity();

        entity.setTipo(
                transaccion.getTipo()
        );

        entity.setFecha(
                transaccion.getFecha()
        );

        entity.setHora(
                transaccion.getHora()
        );

        return entity;
    }
    public static Transaccion toModel(
            TransaccionEntity entity){

        Transaccion transaccion =
                new Transaccion();

        transaccion.setIdTransaccion(
                "TR" + String.format(
                        "%04d",
                        entity.getId()
                )
        );

        transaccion.setTipo(
                entity.getTipo()
        );

        transaccion.setFecha(
                entity.getFecha()
        );

        transaccion.setHora(
                entity.getHora()
        );

        if(entity.getProveedor() != null){

            transaccion.setProveedor(
                    entity.getProveedor()
                            .getIdProveedor()
            );
        }

        if(entity.getDestino() != null){

            transaccion.setDestino(
                    entity.getDestino()
                            .getIdDestino()
            );
        }

        if(entity.getResponsable() != null){

            transaccion.setResponsable(
                    entity.getResponsable()
                            .getIdUsuario()
            );
        }

        if(entity.getAsignado() != null){

            transaccion.setAsignado(
                    entity.getAsignado()
                            .getIdUsuario()
            );
        }

        if(entity.getDetalles() != null){

            transaccion.setDetalles(

                    entity.getDetalles()
                            .stream()
                            .map(
                                    DetalleTransaccionMapper
                                            ::toModel
                            )
                            .toList()
            );
        }

        return transaccion;
    }
}