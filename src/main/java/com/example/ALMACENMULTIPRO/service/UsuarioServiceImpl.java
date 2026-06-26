package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl
        implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository =
                usuarioRepository;
    }

    @Override
    public void guardarUsuario(
            Usuario usuario) {
        if(!usuario.getIdUsuario()
                .matches("\\d{8}")){

            throw new RuntimeException(
                    "El DNI debe tener 8 dígitos."
            );
        }
        if(usuario.getUsuNombre() == null
                ||
                usuario.getUsuNombre().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        if(usuario.getUsuCorreo() == null ||
                !usuario.getUsuCorreo()
                        .contains("@")){

            throw new RuntimeException(
                    "Correo inválido."
            );
        }
        if(usuario.getUsuRol() == null
                ||
                usuario.getUsuRol().isBlank()){

            throw new RuntimeException(
                    "Debe seleccionar un rol."
            );
        }
        if(usuario.getUsuContraseña() == null ||
                usuario.getUsuContraseña()
                        .length() < 4){

            throw new RuntimeException(
                    "La contraseña debe tener al menos 4 caracteres."
            );
        }
        if(usuario.getUsuEstado() == null ||
                usuario.getUsuEstado().isBlank()){

            usuario.setUsuEstado(
                    "Inactivo"
            );
        }
        Usuario existente = buscarUsuario(usuario.getIdUsuario());

        if (existente != null) {
            throw new RuntimeException(
                    "Ya existe un usuario con ese DNI"
            );
        }

        if(usuario.getUsuRol()
                .equalsIgnoreCase("Dueño")){

            long cantidadDuenos =

                    listarUsuarios()
                            .stream()
                            .filter(
                                    u -> u.getUsuRol()
                                            .equalsIgnoreCase(
                                                    "Dueño"
                                            )
                            )
                            .count();

            if(cantidadDuenos >= 2){

                throw new RuntimeException(
                        "Solo se permiten 2 usuarios Dueño."
                );
            }
        }
        boolean correoExiste =
                listarUsuarios()
                        .stream()
                        .anyMatch(
                                u ->
                                        u.getUsuCorreo()
                                                .equalsIgnoreCase(
                                                        usuario.getUsuCorreo()
                                                )
                        );

        if(correoExiste){

            throw new RuntimeException(
                    "Ya existe un usuario con ese correo."
            );
        }
        usuarioRepository.guardarUsuario(
                usuario
        );
    }

    @Override
    public List<Usuario> listarUsuarios() {

        return usuarioRepository.listarUsuarios();
    }

    @Override
    public void cambiarEstadoUsuario(String id) {
        Usuario usuario =
                buscarUsuario(id);

        if(usuario == null){

            throw new RuntimeException(
                    "Usuario no encontrado."
            );
        }

        if(usuario.getUsuEstado()
                .equalsIgnoreCase("Activo")

                &&

                usuario.getUsuRol()
                        .equalsIgnoreCase("Dueño")){

            long dueñosActivos =

                    listarUsuarios()
                            .stream()
                            .filter(
                                    u ->

                                            u.getUsuRol()
                                                    .equalsIgnoreCase("Dueño")

                                                    &&

                                                    u.getUsuEstado()
                                                            .equalsIgnoreCase("Activo")
                            )
                            .count();

            if(dueñosActivos <= 1){

                throw new RuntimeException(
                        "Debe existir al menos un Dueño activo."
                );
            }
        }
        usuarioRepository.cambiarEstadoUsuario(id);
    }

    @Override
    public Usuario buscarUsuario(String id) {

        return usuarioRepository.buscarUsuario(id);
    }

    @Override
    public void actualizarUsuario(
            Usuario usuario) {
        Usuario actual =
                buscarUsuario(
                        usuario.getIdUsuario()
                );

        if(actual == null){

            throw new RuntimeException(
                    "Usuario no encontrado."
            );
        }
        if(usuario.getUsuNombre() == null
                ||
                usuario.getUsuNombre().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        if(usuario.getUsuRol() == null
                ||
                usuario.getUsuRol().isBlank()){

            throw new RuntimeException(
                    "Debe seleccionar un rol."
            );
        }
        if(usuario.getUsuCorreo() == null ||
                !usuario.getUsuCorreo().contains("@")){

            throw new RuntimeException(
                    "Correo inválido."
            );
        }
        if(usuario.getUsuContraseña() == null ||
                usuario.getUsuContraseña()
                        .length() < 4){

            throw new RuntimeException(
                    "La contraseña debe tener al menos 4 caracteres."
            );
        }
        boolean correoExiste =
                listarUsuarios()
                        .stream()
                        .anyMatch(
                                u ->

                                        !u.getIdUsuario()
                                                .equals(
                                                        usuario.getIdUsuario()
                                                )

                                                &&

                                                u.getUsuCorreo()
                                                        .equalsIgnoreCase(
                                                                usuario.getUsuCorreo()
                                                        )
                        );

        if(correoExiste){

            throw new RuntimeException(
                    "Ya existe un usuario con ese correo."
            );
        }

        if(usuario.getUsuRol()
                .equalsIgnoreCase("Dueño")) {

            long cantidadDuenos =

                    listarUsuarios()
                            .stream()
                            .filter(
                                    u -> u.getUsuRol()
                                            .equalsIgnoreCase(
                                                    "Dueño"
                                            )
                            )
                            .count();


            boolean yaEraDueño =
                            actual.getUsuRol()
                                    .equalsIgnoreCase(
                                            "Dueño"
                                    );

            if(!yaEraDueño &&
                    cantidadDuenos >= 2) {

                throw new RuntimeException(
                        "Solo se permiten 2 usuarios Dueño."
                );
            }
        }

        usuarioRepository.actualizarUsuario(
                usuario
        );
    }
}