package com.example.ALMACENMULTIPRO.mapper;

import com.example.ALMACENMULTIPRO.entity.CategoriaEntity;
import com.example.ALMACENMULTIPRO.model.Categoria;

public class CategoriaMapper {
    public static CategoriaEntity toEntity(
            Categoria categoria){

        CategoriaEntity entity =
                new CategoriaEntity();

        if(categoria.getIdCategoria() != null){

            String codigo =
                    categoria.getIdCategoria()
                            .replace("C","");

            entity.setCatId(
                    Long.valueOf(codigo)
            );
        }

        entity.setCatNombre(
                categoria.getCatNombre()
        );

        entity.setCatEstado(
                categoria.getCatEstado()
        );

        entity.setCatDescripcion(
                categoria.getCatDescripcion()
        );

        return entity;
    }

    public static Categoria toModel(
            CategoriaEntity entity){

        Categoria categoria =
                new Categoria();

        categoria.setIdCategoria(
                "C" + String.format(
                        "%04d",
                        entity.getCatId()
                )
        );

        categoria.setCatNombre(
                entity.getCatNombre()
        );

        categoria.setCatEstado(
                entity.getCatEstado()
        );

        categoria.setCatDescripcion(
                entity.getCatDescripcion()
        );

        return categoria;
    }
}
