package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Destino;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.model.Proveedor;

import com.example.ALMACENMULTIPRO.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransaccionController {

    private final TransaccionService transaccionService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final ProveedorService proveedorService;
    private final DestinoService destinoService;


    public TransaccionController(
            TransaccionService transaccionService,
            ProductoService productoService,
            UsuarioService usuarioService,
            ProveedorService proveedorService,
            DestinoService destinoService) {

        this.transaccionService =
                transaccionService;

        this.productoService =
                productoService;

        this.usuarioService =
                usuarioService;

        this.proveedorService =
                proveedorService;

        this.destinoService =
                destinoService;
    }
    @GetMapping("/Transacciones")
    public String listarTransacciones(

            @RequestParam(required = false)
            String fechaInicio,

            @RequestParam(required = false)
            String fechaFin,
            @RequestParam(required = false) String responsable,
            @RequestParam(required = false) String asignado,

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

        Map<String,String> responsables =
                new HashMap<>();

        Map<String,String> asignados =
                new HashMap<>();

        Map<String,String> origenDestino =
                new HashMap<>();

        List<Transaccion> transacciones =
                transaccionService
                        .listarTransacciones();

        if (responsable != null &&
                !responsable.isBlank()) {

            transacciones =
                    transacciones.stream()
                            .filter(
                                    t -> t.getResponsable()
                                            .equals(responsable)
                            )
                            .toList();
        }

        if (asignado != null &&
                !asignado.isBlank()) {

            transacciones =
                    transacciones.stream()
                            .filter(
                                    t -> t.getAsignado()
                                            .equals(asignado)
                            )
                            .toList();
        }

        // FILTRO POR ROL

        if (AuthUtil.esAsistente(
                usuarioLogueado)) {

            transacciones =
                    transacciones.stream()
                            .filter(
                                    t -> t.getAsignado()
                                            .equals(
                                                    usuarioLogueado
                                                            .getIdUsuario()
                                            )
                            )
                            .toList();
        }

        // FILTRO FECHA INICIO

        if (fechaInicio != null &&
                !fechaInicio.isBlank()) {

            LocalDate inicio =
                    LocalDate.parse(
                            fechaInicio
                    );

            transacciones =
                    transacciones.stream()
                            .filter(
                                    t -> !t.getFecha()
                                            .isBefore(inicio)
                            )
                            .toList();
        }

        // FILTRO FECHA FIN

        if (fechaFin != null &&
                !fechaFin.isBlank()) {

            LocalDate fin =
                    LocalDate.parse(
                            fechaFin
                    );

            transacciones =
                    transacciones.stream()
                            .filter(
                                    t -> !t.getFecha()
                                            .isAfter(fin)
                            )
                            .toList();
        }

        // MAPAS PARA LA VISTA

        for (Transaccion t : transacciones) {

            Usuario usuarioResponsable =
                    usuarioService.buscarUsuario(
                            t.getResponsable()
                    );

            Usuario usuarioAsignado =
                    usuarioService.buscarUsuario(
                            t.getAsignado()
                    );

            responsables.put(
                    t.getIdTransaccion(),
                    usuarioResponsable.getUsuNombre()
            );

            asignados.put(
                    t.getIdTransaccion(),
                    usuarioAsignado.getUsuNombre()
            );

            if (t.getTipo()
                    .equalsIgnoreCase("Ingreso")) {

                Proveedor proveedor =
                        proveedorService.buscarProveedor(
                                t.getProveedor()
                        );

                origenDestino.put(
                        t.getIdTransaccion(),
                        proveedor.getProvNombre()
                );
            }

            else {

                Destino destino =
                        destinoService.buscarDestino(
                                t.getDestino()
                        );

                origenDestino.put(
                        t.getIdTransaccion(),
                        destino.getDesNombre()
                );
            }
        }
        List<Usuario> responsablesDisponibles =
                usuarioService.listarUsuarios()
                        .stream()
                        .filter(
                                u ->

                                        (
                                                u.getUsuRol().equalsIgnoreCase("Administrador")
                                                        ||
                                                        u.getUsuRol().equalsIgnoreCase("Dueño")
                                        )

                                                &&

                                                u.getUsuEstado().equalsIgnoreCase("Activo")
                        )
                        .toList();

        List<Usuario> asistentes =
                usuarioService.listarUsuarios()
                        .stream()
                        .filter(
                                u ->

                                        u.getUsuRol()
                                                .equalsIgnoreCase("Asistente")

                                                &&

                                                u.getUsuEstado()
                                                        .equalsIgnoreCase("Activo")
                        )
                        .toList();
        model.addAttribute(
                "responsablesDisponibles",
                responsablesDisponibles
        );

        model.addAttribute(
                "asistentes",
                asistentes
        );
        model.addAttribute(
                "transacciones",
                transacciones
        );

        model.addAttribute(
                "fechaInicio",
                fechaInicio
        );

        model.addAttribute(
                "fechaFin",
                fechaFin
        );

        model.addAttribute(
                "responsables",
                responsables
        );

        model.addAttribute(
                "asignados",
                asignados
        );

        model.addAttribute(
                "origenDestino",
                origenDestino
        );


        model.addAttribute(
                "responsable",
                responsable
        );

        model.addAttribute(
                "asignado",
                asignado
        );

        return "Transacciones";
    }

    @GetMapping("/NuevaTransaccion")
    public String nuevaTransaccion(Model model,
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

            return "redirect:/Transacciones";
        }
        model.addAttribute(
                "transaccion",
                new Transaccion()
        );

        model.addAttribute(
                "productos",

                productoService.listarProductos()
                        .stream()
                        .filter(
                                p -> p.getProdEstado()
                                        .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );

        if (AuthUtil.esDueño(usuarioLogueado)) {

            model.addAttribute(
                    "responsables",

                    usuarioService
                            .listarUsuarios()
                            .stream()
                            .filter(
                                    u ->

                                            (
                                                    u.getUsuRol()
                                                            .equalsIgnoreCase("Dueño")

                                                            ||

                                                            u.getUsuRol()
                                                                    .equalsIgnoreCase("Administrador")
                                            )

                                                    &&

                                                    u.getUsuEstado()
                                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

        }
        else if (AuthUtil.esAdministrador(usuarioLogueado)) {

            model.addAttribute(
                    "responsables",

                    usuarioService
                            .listarUsuarios()
                            .stream()
                            .filter(
                                    u ->

                                            u.getIdUsuario()
                                                    .equals(
                                                            usuarioLogueado.getIdUsuario()
                                                    )

                                                    &&

                                                    u.getUsuEstado()
                                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

        }

        model.addAttribute(
                "asistentes",

                usuarioService
                        .listarUsuarios()
                        .stream()
                        .filter(
                                u -> u.getUsuRol()
                                        .equalsIgnoreCase(
                                                "Asistente"
                                        )
                                        &&

                                        u.getUsuEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );

        model.addAttribute(
                "proveedores",

                proveedorService.listarProveedores()
                        .stream()
                        .filter(
                                p -> p.getProvEstado()
                                        .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );
        model.addAttribute(
                "destinos",

                destinoService.listarDestinos()
                        .stream()
                        .filter(
                                d -> d.getDesEstado()
                                        .equalsIgnoreCase("Activo")
                        )
                        .toList()
        );
        return "NuevaTransaccion";
    }

    @PostMapping("/guardarTransaccion")
    public String guardarTransaccion(
            @ModelAttribute Transaccion transaccion,
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

        if (usuarioLogueado == null ||
                AuthUtil.esAsistente(
                        usuarioLogueado)) {

            return "redirect:/Main";
        }
        try {

            transaccionService.guardarTransaccion(
                    transaccion
            );

            return "redirect:/Transacciones";

        }

        catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "transaccion",
                    transaccion
            );

            model.addAttribute(
                    "productos",

                    productoService.listarProductos()
                            .stream()
                            .filter(
                                    p -> p.getProdEstado()
                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            model.addAttribute(
                    "responsables",

                    usuarioService
                            .listarUsuarios()
                            .stream()
                            .filter(
                                    u ->

                                            (
                                                    u.getUsuRol()
                                                            .equalsIgnoreCase("Administrador")

                                                            ||

                                                            u.getUsuRol()
                                                                    .equalsIgnoreCase("Dueño")
                                            )

                                                    &&

                                                    u.getUsuEstado()
                                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            model.addAttribute(
                    "asistentes",

                    usuarioService
                            .listarUsuarios()
                            .stream()
                            .filter(
                                    u -> u.getUsuRol()
                                            .equalsIgnoreCase(
                                                    "Asistente"
                                            )
                                            &&

                                            u.getUsuEstado()
                                                    .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            model.addAttribute(
                    "proveedores",

                    proveedorService.listarProveedores()
                            .stream()
                            .filter(
                                    p -> p.getProvEstado()
                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );
            model.addAttribute(
                    "destinos",

                    destinoService.listarDestinos()
                            .stream()
                            .filter(
                                    d -> d.getDesEstado()
                                            .equalsIgnoreCase("Activo")
                            )
                            .toList()
            );

            return "NuevaTransaccion";
        }
    }

    @GetMapping("/VerMasTransaccion/{id}")
    public String verTransaccion(
            @PathVariable String id,
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
        Transaccion transaccion =
                transaccionService.buscarTransaccion(id);

        Usuario usuarioLogueado =
                AuthUtil.obtenerUsuarioLogueado(
                        session
                );

        if (AuthUtil.esAsistente(
                usuarioLogueado)

                &&

                !transaccion.getAsignado()
                        .equals(
                                usuarioLogueado
                                        .getIdUsuario()
                        )) {

            return "redirect:/Transacciones";
        }

        Usuario usuario =
                usuarioService.buscarUsuario(
                        transaccion.getResponsable()
                );


        Usuario asignado =
                usuarioService.buscarUsuario(
                        transaccion.getAsignado()
                );

        if (transaccion.getTipo()
                .equalsIgnoreCase("Ingreso")) {

            Proveedor proveedor =
                    proveedorService.buscarProveedor(
                            transaccion.getProveedor()
                    );

            model.addAttribute(
                    "nombreProveedor",
                    proveedor.getProvNombre()
            );
        }

        if (transaccion.getTipo()
                .equalsIgnoreCase("Salida")) {

            Destino destino =
                    destinoService.buscarDestino(
                            transaccion.getDestino()
                    );

            model.addAttribute(
                    "nombreDestino",
                    destino.getDesNombre()
            );
        }
        model.addAttribute(
                "nombreAsignado",
                asignado.getUsuNombre()
        );

        model.addAttribute(
                "transaccion",
                transaccion
        );

        model.addAttribute(
                "nombreResponsable",
                usuario.getUsuNombre()
        );



        return "VerMasTransaccion";
    }
}