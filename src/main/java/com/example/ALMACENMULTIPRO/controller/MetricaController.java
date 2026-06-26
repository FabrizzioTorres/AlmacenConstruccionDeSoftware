package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.MetricaService;
import com.example.ALMACENMULTIPRO.service.UsuarioService;
import com.example.ALMACENMULTIPRO.utils.AuthUtil;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MetricaController {

    private final UsuarioService usuarioService;
    private final MetricaService metricaService;

    public MetricaController(
            UsuarioService usuarioService,
            MetricaService metricaService) {

        this.usuarioService = usuarioService;
        this.metricaService = metricaService;
    }

    @GetMapping("/Metrica")
    public String metrica(
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (AuthUtil.noLogueado(session)) {

            return "redirect:/";
        }

        if (AuthUtil.usuarioInactivo(
                session,
                usuarioService)) {

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

        if (usuarioLogueado == null
                ||
                AuthUtil.esAsistente(
                        usuarioLogueado
                )) {

            redirectAttributes.addFlashAttribute(
                    "errorPermiso",
                    "No tienes los permisos suficientes para acceder a esta sección."
            );

            return "redirect:/Main";
        }

        model.addAttribute(
                "resumen",
                metricaService.obtenerResumen()
        );

        model.addAttribute(
                "metricasMensuales",
                metricaService.listarMetricasMensuales()
        );

        return "Metrica";
    }
}