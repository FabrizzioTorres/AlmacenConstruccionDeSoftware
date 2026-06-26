package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String inicio() {

        return "Login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String dni,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {

        Usuario usuario =
                usuarioService.buscarUsuario(dni);

        // NO EXISTE
        if (usuario == null) {

            model.addAttribute(
                    "error",
                    "Usuario no encontrado"
            );

            return "Login";
        }
        // Usuario INCORRECTO
        if (!usuario.getIdUsuario()
                .equals(dni)){

            model.addAttribute(
                    "error",
                    "Usuario incorrecto"
            );

            return "Login";
        }
        // CONTRASEÑA INCORRECTA
        if (!usuario.getUsuContraseña()
                .equals(password)) {

            model.addAttribute(
                    "error",
                    "Contraseña incorrecta"
            );

            return "Login";
        }
        if(usuario.getUsuEstado()
                .equalsIgnoreCase("Inactivo")){

            model.addAttribute(
                    "error",
                    "La cuenta se encuentra inactiva."
            );

            return "Login";
        }
        // LOGIN CORRECTO
        session.setAttribute(
                "usuarioLogueado",
                usuario
        );

        return "redirect:/Main";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        session.invalidate();

        return "redirect:/";
    }
    @GetMapping("/AccesoRegistro")
    public String accesoRegistro() {

        return "AccesoRegistro";
    }
    @PostMapping("/validarRegistro")
    public String validarRegistro(@RequestParam String dniDueño,

                                  @RequestParam
                                  String passwordDueño,

                                  Model model
    ) {

        boolean accesoValido =

                usuarioService
                        .listarUsuarios()
                        .stream()
                        .anyMatch(
                                u ->

                                        u.getUsuRol()
                                                .equalsIgnoreCase(
                                                        "Dueño"
                                                )

                                                &&

                                                u.getIdUsuario()
                                                        .equals(
                                                                dniDueño
                                                        )

                                                &&

                                                u.getUsuContraseña()
                                                        .equals(
                                                                passwordDueño
                                                        )
                        );

        if (!accesoValido) {

            model.addAttribute(
                    "error",
                    "DNI o contraseña incorrectos."
            );

            return "AccesoRegistro";
        }

        return "redirect:/NuevoUsulog";
    }
    @GetMapping("/Recuperacion")
    public String recuperacion() {

        return "Recuperacion";
    }
}