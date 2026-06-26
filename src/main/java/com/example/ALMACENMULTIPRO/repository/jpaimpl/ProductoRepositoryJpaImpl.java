package com.example.ALMACENMULTIPRO.repository.jpaimpl;

import com.example.ALMACENMULTIPRO.entity.CategoriaEntity;
import com.example.ALMACENMULTIPRO.entity.ProductoEntity;
import com.example.ALMACENMULTIPRO.mapper.ProductoMapper;
import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.repository.ProductoRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.CategoriaJpaRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.ProductoJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoRepositoryJpaImpl
        implements ProductoRepository {

    private final ProductoJpaRepository productoJpaRepository;

    private final CategoriaJpaRepository categoriaJpaRepository;

    public ProductoRepositoryJpaImpl(
            ProductoJpaRepository productoJpaRepository,
            CategoriaJpaRepository categoriaJpaRepository) {

        this.productoJpaRepository =
                productoJpaRepository;

        this.categoriaJpaRepository =
                categoriaJpaRepository;
    }

    @Override
    public void guardarProducto(
            Producto producto) {

        ProductoEntity entity =
                ProductoMapper.toEntity(
                        producto
                );

        if (producto.getProdCategoria() != null) {
            System.out.println(
                    "Categoria recibida: "
                            + producto.getProdCategoria()
            );
            Long idCategoria =
                    Long.parseLong(
                            producto.getProdCategoria()
                                    .replace("C", "")
                    );
            System.out.println(
                    "ID numérico: "
                            + idCategoria
            );
            CategoriaEntity categoria =
                    categoriaJpaRepository
                            .findById(idCategoria)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Categoría no encontrada"
                                    )
                            );

            entity.setCategoria(
                    categoria
            );
        }

        productoJpaRepository.save(
                entity
        );
    }
    @Override
    public List<Producto> listarProductos() {

        return productoJpaRepository
                .findAll()
                .stream()
                .map(
                        ProductoMapper::toModel
                )
                .toList();
    }
    @Override
    public Producto buscarProducto(
            String idProducto) {

        Long idNumerico =
                Long.parseLong(
                        idProducto.replace(
                                "P",
                                ""
                        )
                );

        return productoJpaRepository
                .findById(idNumerico)
                .map(
                        ProductoMapper::toModel
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada"
                        )
                );
    }
    @Override
    public void cambiarEstadoProducto(
            String idProducto) {

        Long idNumerico =
                Long.parseLong(
                        idProducto.replace(
                                "P",
                                ""
                        )
                );

        ProductoEntity entity =
                productoJpaRepository
                        .findById(idNumerico)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Producto no encontrado"
                                )
                        );

        if(entity.getProdEstado()
                .equalsIgnoreCase(
                        "Activo"
                )){

            entity.setProdEstado(
                    "Inactivo"
            );

        }else{

            entity.setProdEstado(
                    "Activo"
            );
        }

        productoJpaRepository.save(
                entity
        );
    }
    @Override
    public void actualizarProducto(
            Producto producto) {

        Long idNumerico =
                Long.parseLong(
                        producto.getIdProducto()
                                .replace("P", "")
                );

        ProductoEntity entity =
                productoJpaRepository
                        .findById(idNumerico)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Producto no encontrado"
                                )
                        );

        entity.setStock(
                producto.getStock()
        );
        entity.setProdDescripcion(
                producto.getProdDescripcion()
        );

        if(producto.getProdCategoria() != null){

            Long idCategoria =
                    Long.parseLong(
                            producto.getProdCategoria()
                                    .replace("C", "")
                    );

            CategoriaEntity categoria =
                    categoriaJpaRepository
                            .findById(idCategoria)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Categoría no encontrada"
                                    )
                            );

            entity.setCategoria(
                    categoria
            );
        }

        productoJpaRepository.save(
                entity
        );
    }
}