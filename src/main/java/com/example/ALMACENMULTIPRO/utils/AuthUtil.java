package com.example.ALMACENMULTIPRO.utils;

import com.example.ALMACENMULTIPRO.model.Usuario;

import com.example.ALMACENMULTIPRO.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

public class AuthUtil {

    public static Usuario obtenerUsuarioLogueado(
            HttpSession session) {

        return (Usuario)
                session.getAttribute(
                        "usuarioLogueado"
                );
    }

    public static boolean noLogueado(
            HttpSession session) {

        return obtenerUsuarioLogueado(
                session
        ) == null;
    }

    public static boolean esDueño(
            Usuario usuario) {

        return usuario != null &&
                usuario.getUsuRol()
                        .equalsIgnoreCase("Dueño");
    }

    public static boolean esAdministrador(
            Usuario usuario) {

        return usuario != null &&
                usuario.getUsuRol()
                        .equalsIgnoreCase("Administrador");
    }

    public static boolean esAsistente(
            Usuario usuario) {

        return usuario != null &&
                usuario.getUsuRol()
                        .equalsIgnoreCase("Asistente");
    }

    public static boolean esAdminODueño(
            Usuario usuario) {

        return esDueño(usuario) ||
                esAdministrador(usuario);
    }
    public static boolean usuarioInactivo(
            HttpSession session,
            UsuarioService usuarioService){

        Usuario usuario =
                obtenerUsuarioLogueado(session);

        if(usuario == null){
            return false;
        }

        Usuario actualizado =
                usuarioService.buscarUsuario(
                        usuario.getIdUsuario()
                );

        return actualizado != null
                &&
                actualizado.getUsuEstado()
                        .equalsIgnoreCase("Inactivo");
    }
}