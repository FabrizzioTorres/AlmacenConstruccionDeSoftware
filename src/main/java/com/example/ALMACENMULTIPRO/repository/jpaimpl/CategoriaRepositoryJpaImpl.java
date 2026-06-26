package com.example.ALMACENMULTIPRO.repository.jpaimpl;

import com.example.ALMACENMULTIPRO.entity.CategoriaEntity;
import com.example.ALMACENMULTIPRO.mapper.CategoriaMapper;
import com.example.ALMACENMULTIPRO.model.Categoria;
import com.example.ALMACENMULTIPRO.repository.CategoriaRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.CategoriaJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepositoryJpaImpl
        implements CategoriaRepository {

    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaRepositoryJpaImpl(
            CategoriaJpaRepository categoriaJpaRepository) {

        this.categoriaJpaRepository =
                categoriaJpaRepository;
    }

    @Override
    public void guardarCategoria(
            Categoria categoria) {

        CategoriaEntity entity =
                CategoriaMapper.toEntity(
                        categoria
                );

        categoriaJpaRepository.save(
                entity
        );
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaJpaRepository
                .findAll()
                .stream()
                .map(
                        CategoriaMapper::toModel
                )
                .toList();
    }

    @Override
    public Categoria buscarCategoria(String idCategoria) {
        Long idNumerico =
                Long.parseLong(
                        idCategoria.replace("C", "")
                );

        return categoriaJpaRepository
                .findById(idNumerico)
                .map(CategoriaMapper::toModel)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada"
                        )
                );
    }

    @Override
    public void cambiarEstadoCategoria(String idCategoria) {
        Long idNumerico =
                Long.parseLong(
                        idCategoria.replace("C", "")
                );

        CategoriaEntity entity =
                categoriaJpaRepository
                        .findById(idNumerico)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoría no encontrada"
                                )
                        );


        if (entity.getCatEstado()
                .equalsIgnoreCase(
                        "Activo")) {

            entity.setCatEstado(
                    "Inactivo"
            );

        } else {

            entity.setCatEstado(
                    "Activo"
            );
        }

        categoriaJpaRepository.save(
                entity
        );

    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        Long idNumerico =
                Long.parseLong(
                        categoria.getIdCategoria()
                                .replace("C", "")
                );

        CategoriaEntity entity =
                categoriaJpaRepository
                        .findById(idNumerico)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoría no encontrada."
                                )
                        );

        entity.setCatDescripcion(
                categoria.getCatDescripcion()
        );

        categoriaJpaRepository.save(
                entity
        );
    }


}
