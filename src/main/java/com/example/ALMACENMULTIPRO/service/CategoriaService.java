package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    void guardarCategoria(Categoria categoria);

    Categoria buscarCategoria(String id);

    void actualizarCategoria(Categoria categoria);

    void cambiarEstadoCategoria(String id);
}