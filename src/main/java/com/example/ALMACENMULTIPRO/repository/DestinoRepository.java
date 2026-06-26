package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Destino;

import java.util.List;

public interface DestinoRepository {

    void guardarDestino(
            Destino destino);

    List<Destino> listarDestinos();

    Destino buscarDestino(
            String id);

    void actualizarDestino(
            Destino destino);

    void cambiarEstadoDestino(
            String id);
}