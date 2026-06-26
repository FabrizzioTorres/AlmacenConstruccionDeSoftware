package com.example.ALMACENMULTIPRO;

import com.example.ALMACENMULTIPRO.service.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import com.example.ALMACENMULTIPRO.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;

    public HomeController(
            UsuarioService usuarioService) {

        this.usuarioService =
                usuarioService;

    }

    @GetMapping("/Main")
    public String Main(
            HttpSession session,Model model, RedirectAttributes redirectAttributes) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }
        if(AuthUtil.usuarioInactivo(
                session,
                usuarioService)){

            session.invalidate();

            redirectAttributes.addFlashAttribute("error", "Esta cuenta está actualmente inactiva.");

            return "redirect:/";
        }

        String mensaje =
                (String) session.getAttribute(
                        "errorPermisos"
                );

        if (mensaje != null) {

            model.addAttribute(
                    "errorPermisos",
                    mensaje
            );

            session.removeAttribute(
                    "errorPermisos"
            );
        }

        return "Main";
    }

    @GetMapping("/Contacto")
    public String contacto(
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
        return "Contacto";
    }

    @GetMapping("/Publicidad")
    public String publicidad(
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
        return "Publicidad";
    }
}