package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.DestinoEntity;
import com.example.ALMACENMULTIPRO.model.Destino;

public class DestinoMapper {
    public static DestinoEntity toEntity(
            Destino destino){

        DestinoEntity entity =
                new DestinoEntity();

        entity.setIdDestino(
                destino.getIdDestino()
        );

        entity.setDesNombre(
                destino.getDesNombre()
        );

        entity.setDesDireccion(
                destino.getDesDireccion()
        );

        entity.setDesContacto(
                destino.getDesContacto()
        );

        entity.setDesEstado(
                destino.getDesEstado()
        );

        entity.setDesDescripcion(
                destino.getDesDescripcion()
        );

        return entity;
    }
    public static Destino toModel(
            DestinoEntity entity){

        Destino destino =
                new Destino();

        destino.setIdDestino(
                entity.getIdDestino()
        );

        destino.setDesNombre(
                entity.getDesNombre()
        );

        destino.setDesDireccion(
                entity.getDesDireccion()
        );

        destino.setDesContacto(
                entity.getDesContacto()
        );

        destino.setDesEstado(
                entity.getDesEstado()
        );

        destino.setDesDescripcion(
                entity.getDesDescripcion()
        );

        return destino;
    }
}
