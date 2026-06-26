package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @GetMapping("/Usuario")
    public String listarUsuarios(
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }

        // DUEÑO VE TODO
        if (AuthUtil.esDueño(
                usuarioLogueado)) {

            model.addAttribute(
                    "usuarios",
                    usuarioService.listarUsuarios()
            );
        }

        // ADMIN NO VE DUEÑOS
        else if (AuthUtil.esAdministrador(
                usuarioLogueado)) {

            model.addAttribute(
                    "usuarios",

                    usuarioService
                            .listarUsuarios()
                            .stream()
                            .filter(
                                    u ->

                                            u.getUsuRol()
                                                    .equals("Asistente")

                                                    ||

                                                    u.getIdUsuario()
                                                            .equals(
                                                                    usuarioLogueado
                                                                            .getIdUsuario()
                                                            )
                            )
                            .toList()
            );
        }

        return "Usuario";
    }

    @GetMapping("/NuevoUsu")
    public String nuevoUsuario(Model model,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }

        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }
        model.addAttribute(
                "usuario",
                new Usuario()
        );
        model.addAttribute(
                "usuarioLogueado",
                usuarioLogueado
        );
        return "NuevoUsu";
    }
    @GetMapping("/NuevoUsulog")
    public String nuevoUsuariologin(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario()
        );
        return "NuevoUsulog";
    }
    @PostMapping("/guardarUsuariolog")
    public String guardarUsuariologin(
            @ModelAttribute Usuario usuario,
            Model model) {

        try {

            usuarioService.guardarUsuario(
                    usuario
            );

            return "redirect:/";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "usuario",
                    usuario
            );

            return "NuevoUsulog";
        }
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(
            @ModelAttribute Usuario usuario,
            HttpSession session,
            RedirectAttributes redirectAttributes, Model model) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }
        try {

            if (AuthUtil.esAdministrador(
                    usuarioLogueado)

                    &&

                    !usuario.getUsuRol()
                            .equalsIgnoreCase(
                                    "Asistente"
                            )) {

                throw new RuntimeException(
                        "Un administrador solo puede crear asistentes"
                );
            }

            usuarioService.guardarUsuario(
                    usuario
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Usuario registrado correctamente."
            );

            return "redirect:/Usuario";
        }
        catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "usuario",
                    usuario
            );

            model.addAttribute(
                    "usuarioLogueado",
                    usuarioLogueado
            );

            return "NuevoUsu";
        }
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(
            @PathVariable String id,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );
        Usuario usuarioEditar =
                usuarioService.buscarUsuario(id);

        if (usuarioEditar == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Usuario no encontrado."
            );

            return "redirect:/Usuario";
        }

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }
        if (AuthUtil.esAdministrador(
                usuarioLogueado)) {

            boolean esSuPropiaCuenta =
                    usuarioEditar.getIdUsuario()
                            .equals(
                                    usuarioLogueado.getIdUsuario()
                            );

            boolean esAsistente =
                    usuarioEditar.getUsuRol()
                            .equalsIgnoreCase(
                                    "Asistente"
                            );

            if (!esSuPropiaCuenta &&
                    !esAsistente) {

                redirectAttributes.addFlashAttribute(
                        "errorPermiso",
                        "No tienes permisos para editar ese usuario."
                );

                return "redirect:/Usuario";
            }
        }
        model.addAttribute(
                "usuario",
                usuarioEditar
        );
        model.addAttribute(
                "usuarioLogueado",
                usuarioLogueado
        );
        return "EditarUsuario";
    }

    @PostMapping("/actualizarUsuario")
    public String actualizarUsuario(
            @ModelAttribute Usuario usuario,
            HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }
        Usuario usuarioOriginal =
                usuarioService.buscarUsuario(
                        usuario.getIdUsuario()
                );

        if (usuarioOriginal == null) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Usuario no encontrado."
            );
            return "redirect:/Usuario";
        }
        if(usuarioLogueado.getIdUsuario()
                .equals(usuario.getIdUsuario())

                &&

                !usuarioOriginal.getUsuRol()
                        .equalsIgnoreCase(
                                usuario.getUsuRol()
                        )){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No puedes cambiar tu propio rol."
            );
        }

        // ADMINISTRADOR
        if (AuthUtil.esAdministrador(
                usuarioLogueado)) {

            boolean esSuPropiaCuenta =
                    usuarioOriginal.getIdUsuario()
                            .equals(
                                    usuarioLogueado.getIdUsuario()
                            );

            boolean esAsistente =
                    usuarioOriginal.getUsuRol()
                            .equalsIgnoreCase(
                                    "Asistente"
                            );

            if (!esSuPropiaCuenta &&
                    !esAsistente) {

                redirectAttributes.addFlashAttribute(
                        "errorPermiso",
                        "No tienes permisos para editar ese usuario."
                );

                return "redirect:/Usuario";
            }

            // NO PUEDE CAMBIAR ROL
            usuario.setUsuRol(
                    usuarioOriginal.getUsuRol()
            );

            // NO PUEDE CAMBIAR DNI
            usuario.setIdUsuario(
                    usuarioOriginal.getIdUsuario()
            );
        }

        try {

            usuarioService.actualizarUsuario(
                    usuario
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Usuario actualizado correctamente."
            );

            return "redirect:/Usuario";

        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );
            model.addAttribute(
                    "usuario",
                    usuario
            );

            model.addAttribute(
                    "usuarioLogueado",
                    usuarioLogueado
            );

            return "EditarUsuario";
        }
    }

    @GetMapping("/cambiarEstadoUsuario/{id}")
    public String cambiarEstadoUsuario(
            @PathVariable String id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Esta cuenta está actualmente inactiva."
            );

            return "redirect:/";
        }
        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }

        Usuario usuario =
                usuarioService.buscarUsuario(id);

        if(usuario == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Usuario no encontrado."
            );

            return "redirect:/Usuario";
        }
        if(usuarioLogueado.getIdUsuario()
                .equals(id)){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No puedes desactivar tu propia cuenta."
            );

            return "redirect:/Usuario";
        }
        if(AuthUtil.esAdministrador(
                usuarioLogueado)){

            if(!usuario.getUsuRol()
                    .equalsIgnoreCase("Asistente")){

                redirectAttributes.addFlashAttribute(
                        "error",
                        "Solo puedes administrar asistentes."
                );

                return "redirect:/Usuario";
            }
        }
        try{

            usuarioService.cambiarEstadoUsuario(id);

            Usuario actualizado =
                    usuarioService.buscarUsuario(id);

            String mensaje =
                    actualizado.getUsuEstado()
                            .equalsIgnoreCase("Activo")

                            ?

                            "Usuario activado correctamente."

                            :

                            "Usuario desactivado correctamente.";

            redirectAttributes.addFlashAttribute(
                    "success",
                    mensaje
            );
        }
        catch(RuntimeException e){

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/Usuario";
    }

}