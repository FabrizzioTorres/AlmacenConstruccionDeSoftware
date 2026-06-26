package com.example.ALMACENMULTIPRO.repository.jpaimpl;
import com.example.ALMACENMULTIPRO.entity.UsuarioEntity;
import com.example.ALMACENMULTIPRO.mapper.UsuarioMapper;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.repository.UsuarioRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.UsuarioJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryJpaImpl
        implements UsuarioRepository {

    private final UsuarioJpaRepository
            usuarioJpaRepository;

    public UsuarioRepositoryJpaImpl(
            UsuarioJpaRepository usuarioJpaRepository){

        this.usuarioJpaRepository =
                usuarioJpaRepository;
    }

    @Override
    public void guardarUsuario(
            Usuario usuario){

        UsuarioEntity entity =
                UsuarioMapper.toEntity(
                        usuario
                );

        usuarioJpaRepository.save(
                entity
        );
    }

    @Override
    public List<Usuario> listarUsuarios(){

        return usuarioJpaRepository
                .findAll()
                .stream()
                .map(
                        UsuarioMapper::toModel
                )
                .toList();

    }

    @Override
    public Usuario buscarUsuario(
            String id){

        return usuarioJpaRepository
                .findById(id)
                .map(
                        UsuarioMapper::toModel
                )
                .orElse(null);
    }

    @Override
    public void cambiarEstadoUsuario(
            String id){

        UsuarioEntity entity =
                usuarioJpaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usuario no encontrado"
                                )
                        );

        if(entity.getUsuEstado()
                .equalsIgnoreCase(
                        "Activo")){

            entity.setUsuEstado(
                    "Inactivo"
            );

        }else{

            entity.setUsuEstado(
                    "Activo"
            );
        }

        usuarioJpaRepository.save(
                entity
        );
    }

    @Override
    public void actualizarUsuario(
            Usuario usuario){

        UsuarioEntity entity =
                usuarioJpaRepository
                        .findById(
                                usuario.getIdUsuario()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usuario no encontrado"
                                )
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


        usuarioJpaRepository.save(
                entity
        );
    }
}