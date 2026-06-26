package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Categoria;
import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.repository.CategoriaRepository;
import com.example.ALMACENMULTIPRO.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl
        implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaServiceImpl(
            CategoriaRepository categoriaRepository,
            ProductoRepository productoRepository) {

        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Categoria> listarCategorias() {

        return categoriaRepository.listarCategorias();
    }

    @Override
    public void guardarCategoria(
            Categoria categoria) {
        if(categoria == null){
            throw new RuntimeException(
                    "Categoría inválida."
            );
        }

        if(categoria.getCatNombre() == null ||
                categoria.getCatNombre().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        boolean existe =
                listarCategorias()
                        .stream()
                        .anyMatch(
                                c -> c.getCatNombre()
                                        .equalsIgnoreCase(
                                                categoria.getCatNombre()
                                        )
                        );

        if(existe){

            throw new RuntimeException(
                    "Ya existe una categoría con ese nombre."
            );
        }
        if(categoria.getCatEstado() == null
                ||
                categoria.getCatEstado().isBlank()){

            categoria.setCatEstado(
                    "Activo"
            );
        }
        categoriaRepository.guardarCategoria(
                categoria
        );
    }

    @Override
    public Categoria buscarCategoria(String id) {

        return categoriaRepository.buscarCategoria(id);
    }

    @Override
    public void actualizarCategoria(
            Categoria categoria) {
        if(categoria == null){

            throw new RuntimeException(
                    "Categoría inválida."
            );
        }

        Categoria existente =
                buscarCategoria(
                        categoria.getIdCategoria()
                );

        if(existente == null){

            throw new RuntimeException(
                    "Categoría no encontrada."
            );
        }
        if(categoria.getCatDescripcion() == null
                ||
                categoria.getCatDescripcion().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar una descripción."
            );
        }

        categoriaRepository.actualizarCategoria(
                categoria
        );
    }

    @Override
    public void cambiarEstadoCategoria(String id) {
        Categoria categoria =
                buscarCategoria(id);
        if(categoria == null){

            throw new RuntimeException(
                    "Categoría no encontrada."
            );
        }
        List<Producto> asociados =
                productoRepository.listarProductos()
                        .stream()
                        .filter(
                                p ->
                                        p.getProdCategoria()
                                                .equalsIgnoreCase(
                                                        categoria.getIdCategoria()
                                                )
                        )
                        .toList();
        if(categoria.getCatEstado()
                .equalsIgnoreCase("Activo")){

            categoriaRepository.cambiarEstadoCategoria(id);

            for(Producto p : asociados){

                if(p.getProdEstado()
                        .equalsIgnoreCase("Activo")){

                    productoRepository.cambiarEstadoProducto(
                            p.getIdProducto()
                    );
                }
            }

            return;
        }
        categoriaRepository.cambiarEstadoCategoria(id);
    }
}