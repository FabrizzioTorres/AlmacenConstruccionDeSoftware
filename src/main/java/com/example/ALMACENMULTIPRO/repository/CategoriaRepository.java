package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Categoria;

import java.util.List;

public interface CategoriaRepository {

    void guardarCategoria(Categoria categoria);

    List<Categoria> listarCategorias();

    Categoria buscarCategoria(String id);

    void cambiarEstadoCategoria(String id);

    void actualizarCategoria(Categoria categoria);

}