package com.example.ALMACENMULTIPRO.controller;
import com.example.ALMACENMULTIPRO.service.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.ALMACENMULTIPRO.model.Categoria;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.CategoriaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;

@Controller
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public CategoriaController(
            CategoriaService categoriaService, UsuarioService usuarioService) {

        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Categoria")
    public String listarCategorias(
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

        model.addAttribute(
                "categorias",
                categoriaService.listarCategorias()
        );

        return "Categoria";
    }

    @GetMapping("/NuevaCat")
    public String nuevaCategoria(
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
        model.addAttribute(
                "categoria",
                new Categoria()
        );

        return "NuevaCat";
    }

    @PostMapping("/guardarCategoria")
    public String guardarCategoria(
            @ModelAttribute Categoria categoria,Model model,
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
        try {

            categoriaService.guardarCategoria(
                    categoria
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Categoría registrada correctamente."
            );

            return "redirect:/Categoria";

        }

        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "categoria",
                    categoria
            );

            return "NuevaCat";
        }
    }

    @GetMapping("/editarCategoria/{id}")
    public String editarCategoria(
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
        Categoria categoria =
                categoriaService.buscarCategoria(id);

        if(categoria == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Categoría no encontrada."
            );

            return "redirect:/Categoria";
        }

        model.addAttribute(
                "categoria",
                categoria
        );

        return "EditarCategoria";
    }

    @PostMapping("/actualizarCategoria")
    public String actualizarCategoria(
            @ModelAttribute Categoria categoria, Model model,
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
        try{

            categoriaService.actualizarCategoria(
                    categoria
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Categoría actualizada correctamente."
            );

            return "redirect:/Categoria";

        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "categoria",
                    categoria
            );

            return "EditarCategoria";
        }
    }

    @GetMapping("/cambiarEstadoCategoria/{id}")
    public String cambiarEstadoCategoria(
            @PathVariable String id,
            HttpSession session, RedirectAttributes redirectAttributes) {

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
        Categoria categoria =
                categoriaService.buscarCategoria(id);

        if(categoria == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Categoría no encontrada."
            );

            return "redirect:/Categoria";
        }
        try{

            categoriaService.cambiarEstadoCategoria(id);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Estado de la categoría se ha actualizado correctamente."
            );
        }
        catch(RuntimeException e){

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/Categoria";
    }
    @GetMapping("/VerMasCategoria/{id}")
    public String verMasCategoria(
            @PathVariable String id,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if(AuthUtil.noLogueado(session)){

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
        Categoria categoria =
                categoriaService.buscarCategoria(id);

        if(categoria == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Categoría no encontrada."
            );

            return "redirect:/Categoria";
        }

        model.addAttribute(
                "categoria",
                categoria
        );

        return "VerMasCategoria";
    }
}