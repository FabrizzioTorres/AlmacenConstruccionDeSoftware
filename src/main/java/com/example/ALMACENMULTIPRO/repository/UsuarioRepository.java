package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Usuario;

import java.util.List;

public interface UsuarioRepository {

    void guardarUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuario(String id);

    void cambiarEstadoUsuario(String id);

    void actualizarUsuario(Usuario usuario);
}