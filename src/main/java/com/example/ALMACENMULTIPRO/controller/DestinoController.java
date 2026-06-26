package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Destino;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.DestinoService;

import com.example.ALMACENMULTIPRO.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DestinoController {

    private final DestinoService destinoService;
    private final UsuarioService usuarioService;

    public DestinoController(
            DestinoService destinoService, UsuarioService usuarioService) {

        this.destinoService = destinoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Destino")
    public String listarDestinos(Model model,
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
                "destinos",
                destinoService.listarDestinos()
        );

        return "Destino";
    }

    @GetMapping("/NuevoDestino")
    public String nuevoDestino(Model model,
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
                "destino",
                new Destino()
        );

        return "NuevoDestino";
    }

    @PostMapping("/guardarDestino")
    public String guardarDestino(
            @ModelAttribute Destino destino,
            HttpSession session, Model model, RedirectAttributes redirectAttributes) {

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

            destinoService.guardarDestino(
                    destino
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Destino registrado correctamente."
            );

            return "redirect:/Destino";
        }

        catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "destino",
                    destino
            );

            return "NuevoDestino";
        }
    }

    @GetMapping("/editarDestino/{id}")
    public String editarDestino(
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

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {
            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }
        Destino destino =
                destinoService.buscarDestino(id);

        if(destino == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Destino no encontrado."
            );

            return "redirect:/Destino";
        }

        model.addAttribute(
                "destino",
                destino
        );

        return "EditarDestino";
    }

    @PostMapping("/actualizarDestino")
    public String actualizarDestino(
            @ModelAttribute Destino destino,
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

        try{

            destinoService.actualizarDestino(
                    destino
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Destino actualizado correctamente."
            );

            return "redirect:/Destino";
        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "destino",
                    destino
            );

            return "EditarDestino";
        }
    }

    @GetMapping("/cambiarEstadoDestino/{id}")
    public String cambiarEstadoDestino(
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
        Destino destino =
                destinoService.buscarDestino(id);
        if(destino == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Destino no encontrado."
            );

            return "redirect:/Destino";
        }
        try{
            destinoService.cambiarEstadoDestino(id);
            Destino actualizado =
                    destinoService.buscarDestino(id);
            String mensaje =
                    actualizado.getDesEstado()
                            .equalsIgnoreCase("Activo")

                            ?

                            "Destino activado correctamente."

                            :

                            "Destino desactivado correctamente.";

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

        return "redirect:/Destino";
    }
    @GetMapping("/VerMasDestino/{id}")
    public String verMasDestino(
            @PathVariable String id,
            Model model,
            HttpSession session,RedirectAttributes redirectAttributes) {

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

        Destino destino =
                destinoService.buscarDestino(
                        id
                );
        if (destino == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Destino no encontrado."
            );

            return "redirect:/Destino";
        }
        model.addAttribute(
                "destino",
                destino
        );

        return "VerMasDestino";
    }
}