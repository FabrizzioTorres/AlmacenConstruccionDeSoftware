package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.CategoriaService;
import com.example.ALMACENMULTIPRO.service.ProductoService;

import com.example.ALMACENMULTIPRO.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public ProductoController(
            ProductoService productoService,
            CategoriaService categoriaService, UsuarioService usuarioService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Gestion")
    public String listarProductos(
            Model model,
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

        if (AuthUtil.esAsistente(
                usuarioLogueado)) {

            model.addAttribute(
                    "productos",
                    productoService.listarProductos()
                            .stream()
                            .filter(
                                    p ->
                                            p.getProdEstado()
                                                    .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );
        }
        else{

            model.addAttribute(
                    "productos",
                    productoService.listarProductos()
            );
        }
        return "Gestion";
    }

    @GetMapping("/Producto")
    public String mostrarFormularioProducto(
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

            return "redirect:/Gestion";
        }
        model.addAttribute(
                "producto",
                new Producto()
        );

        model.addAttribute(
                "categorias",
                categoriaService.listarCategorias()
                        .stream()
                        .filter(
                                c ->
                                        c.getCatEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );

        return "Producto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(
            @ModelAttribute Producto producto,
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
        try {

            productoService.guardarProducto(
                    producto
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Producto registrado correctamente."
            );

            return "redirect:/Gestion";

        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "producto",
                    producto
            );

            model.addAttribute(
                    "categorias",
                    categoriaService.listarCategorias()
                            .stream()
                            .filter(
                                    c ->
                                            c.getCatEstado()
                                                    .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            return "Producto";
        }
    }

    @GetMapping("/editarProducto/{id}")
    public String editarProducto(
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

            return "redirect:/Gestion";
        }
        Producto producto =
                productoService.buscarProducto(id);

        if(producto == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Producto no encontrado."
            );

            return "redirect:/Gestion";
        }
        model.addAttribute(
                "producto",
                producto
        );

        model.addAttribute(
                "categorias",
                categoriaService.listarCategorias()
                        .stream()
                        .filter(
                                c ->
                                        c.getCatEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );

        return "EditarProducto";
    }

    @GetMapping("/cambiarEstadoProducto/{id}")
    public String cambiarEstadoProducto(
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
        Producto producto =
                productoService.buscarProducto(id);

        if(producto == null){

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Producto no encontrado."
            );

            return "redirect:/Gestion";
        }
        try{

            productoService.cambiarEstadoProducto(id);
            Producto actualizado =
                    productoService.buscarProducto(id);

            String mensaje =
                    actualizado.getProdEstado()
                            .equalsIgnoreCase("Activo")

                            ?

                            "Producto activado correctamente."

                            :

                            "Producto desactivado correctamente.";

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

        return "redirect:/Gestion";
    }

	@PostMapping("/actualizarProducto")
    	public String actualizarProducto(
            @ModelAttribute Producto producto,
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
        try {

            productoService.actualizarProducto(
                    producto
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Producto actualizado correctamente."
            );

            return "redirect:/Gestion";

        }
        catch(RuntimeException e){

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "producto",
                    producto
            );

            model.addAttribute(
                    "categorias",
                    categoriaService.listarCategorias()
                            .stream()
                            .filter(
                                    c ->
                                            c.getCatEstado()
                                                    .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            return "EditarProducto";
        }
    }
    @GetMapping("/VerMasProducto/{id}")
    public String verMasProducto(
            @PathVariable String id,
            Model model) {

        Producto producto =
                productoService.buscarProducto(id);

        model.addAttribute(
                "producto",
                producto
        );

        return "VerMasProducto";
    }
}