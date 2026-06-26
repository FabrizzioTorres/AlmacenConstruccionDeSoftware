package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Destino;

import java.util.List;

public interface DestinoService {

    List<Destino> listarDestinos();

    void guardarDestino(
            Destino destino);

    Destino buscarDestino(
            String id);

    void actualizarDestino(
            Destino destino);

    void cambiarEstadoDestino(
            String id);
}