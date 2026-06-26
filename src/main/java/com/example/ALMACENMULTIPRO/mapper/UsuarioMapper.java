package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.UsuarioEntity;
import com.example.ALMACENMULTIPRO.model.Usuario;

public class UsuarioMapper {
    public static UsuarioEntity toEntity(
            Usuario usuario){

        UsuarioEntity entity =
                new UsuarioEntity();

        entity.setIdUsuario(
                usuario.getIdUsuario()
        );

        entity.setUsuNombre(
                usuario.getUsuNombre()
        );

        entity.setUsuCorreo(
                usuario.getUsuCorreo()
        );

        entity.setUsuContraseña(
                usuario.getUsuContraseña()
        );

        entity.setUsuRol(
                usuario.getUsuRol()
        );

        entity.setUsuEstado(
                usuario.getUsuEstado()
        );
        return entity;
    }
    public static Usuario toModel(
            UsuarioEntity entity){

        Usuario usuario =
                new Usuario();

        usuario.setIdUsuario(
                entity.getIdUsuario()
        );

        usuario.setUsuNombre(
                entity.getUsuNombre()
        );

        usuario.setUsuCorreo(
                entity.getUsuCorreo()
        );

        usuario.setUsuContraseña(
                entity.getUsuContraseña()
        );

        usuario.setUsuRol(
                entity.getUsuRol()
        );

        usuario.setUsuEstado(
                entity.getUsuEstado()
        );


        return usuario;
    }
}