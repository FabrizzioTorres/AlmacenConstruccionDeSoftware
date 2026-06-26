package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.ProveedorService;

import com.example.ALMACENMULTIPRO.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final UsuarioService usuarioService;

    public ProveedorController(
            ProveedorService proveedorService, UsuarioService usuarioService) {

        this.proveedorService = proveedorService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Proveedor")
    public String listarProveedores(Model model,
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
                "proveedores",
                proveedorService.listarProveedores()
        );

        return "Proveedor";
    }

    @GetMapping("/NuevoProveedor")
    public String nuevoProveedor(Model model,
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
                "proveedor",
                new Proveedor()
        );

        return "NuevoProveedor";
    }

    @PostMapping("/guardarProveedor")
    public String guardarProveedor(
            @ModelAttribute Proveedor proveedor,
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

            proveedorService.guardarProveedor(
                    proveedor
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Proveedor registrado correctamente."
            );

            return "redirect:/Proveedor";
        }

        catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "proveedor",
                    proveedor
            );

            return "NuevoProveedor";
        }
    }

    @GetMapping("/editarProveedor/{id}")
    public String editarProveedor(
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
        Proveedor proveedor =
                proveedorService.buscarProveedor(id);

        if(proveedor == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Proveedor no encontrado."
            );

            return "redirect:/Proveedor";
        }
        model.addAttribute(
                "proveedor",
                proveedor
        );

        return "EditarProveedor";
    }

    @PostMapping("/actualizarProveedor")
    public String actualizarProveedor(
            @ModelAttribute Proveedor proveedor,
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

            proveedorService.actualizarProveedor(
                    proveedor
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Proveedor actualizado correctamente."
            );

            return "redirect:/Proveedor";
        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "proveedor",
                    proveedor
            );

            return "EditarProveedor";
        }
    }

    @GetMapping("/cambiarEstadoProveedor/{id}")
    public String cambiarEstadoProveedor(
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
        Proveedor proveedor =
                proveedorService.buscarProveedor(id);

        if(proveedor == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Proveedor no encontrado."
            );

            return "redirect:/Proveedor";
        }
        try{

            proveedorService.cambiarEstadoProveedor(id);

            Proveedor actualizado =
                    proveedorService.buscarProveedor(id);

            String mensaje =
                    actualizado.getProvEstado()
                            .equalsIgnoreCase("Activo")

                            ?

                            "Proveedor activado correctamente."

                            :

                            "Proveedor desactivado correctamente.";

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
        return "redirect:/Proveedor";
    }
    @GetMapping("/VerMasProveedor/{id}")
    public String verMasProveedor(
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

        Proveedor proveedor =
                proveedorService.buscarProveedor(
                        id
                );
        if (proveedor == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Proveedor no encontrado."
            );
            return "redirect:/Proveedor";
        }
        model.addAttribute(
                "proveedor",
                proveedor
        );

        return "VerMasProveedor";
    }
}