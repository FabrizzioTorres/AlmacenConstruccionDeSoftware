package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.MetricaMensual;
import com.example.ALMACENMULTIPRO.model.MetricaResumen;

import java.util.List;

public interface MetricaService {

    MetricaResumen obtenerResumen();

    List<MetricaMensual> listarMetricasMensuales();
}