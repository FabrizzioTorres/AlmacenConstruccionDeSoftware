package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Categoria;
import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final CategoriaService categoriaService;

    public ProductoServiceImpl(
            ProductoRepository productoRepository,
            CategoriaService categoriaService) {

        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }

    @Override
    public void guardarProducto(Producto producto) {
        if(producto == null){
            throw new RuntimeException(
                    "Producto inválido."
            );
        }
        Categoria categoria =
                categoriaService
                        .listarCategorias()
                        .stream()
                        .filter(
                                c ->
                                        c.getIdCategoria()
                                                .equalsIgnoreCase(
                                                        producto.getProdCategoria()
                                                )
                        )
                        .findFirst()
                        .orElse(null);
        if(categoria == null){

            throw new RuntimeException(
                    "La categoría no existe."
            );
        }
        if(!categoria.getCatEstado()
                .equalsIgnoreCase("Activo")){

            throw new RuntimeException(
                    "No se puede asignar una categoría inactiva."
            );
        }
        if(producto.getProdNombre() == null ||
                producto.getProdNombre().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        if(producto.getProdCategoria() == null ||
                producto.getProdCategoria().isBlank()){

            throw new RuntimeException(
                    "Debe seleccionar una categoría."
            );
        }
        boolean existe =
                listarProductos()
                        .stream()
                        .anyMatch(
                                p -> p.getProdNombre()
                                        .equalsIgnoreCase(
                                                producto.getProdNombre()
                                        )
                        );
        if (existe) {

            throw new RuntimeException(
                    "Ya existe un producto con ese nombre."
            );
        }
        if (producto.getProdEstado() == null ||
                producto.getProdEstado().isBlank()) {
            producto.setProdEstado("Activo");
        }
        productoRepository.guardarProducto(producto);
    }

    @Override
    public List<Producto> listarProductos() {

        return productoRepository.listarProductos();
    }

    @Override
    public void cambiarEstadoProducto(String id) {
        Producto producto =
                buscarProducto(id);
        if(producto == null){

            throw new RuntimeException(
                    "Producto no encontrado."
            );
        }
        if(producto.getProdEstado()
                .equalsIgnoreCase("Inactivo")){
            Categoria categoria =
                    categoriaService
                            .listarCategorias()
                            .stream()
                            .filter(
                                    c -> c.getIdCategoria()
                                            .equalsIgnoreCase(
                                                    producto.getProdCategoria()
                                            )
                            )
                            .findFirst()
                            .orElse(null);
            if(categoria != null
                    &&
                    categoria.getCatEstado()
                            .equalsIgnoreCase("Inactivo")){

                throw new RuntimeException(
                        "No se puede activar un producto cuya categoría está inactiva."
                );
            }
        }
        System.out.println(
                "Producto: " + producto.getProdNombre()
        );

        System.out.println(
                "Estado producto: " + producto.getProdEstado()
        );

        System.out.println(
                "Categoria producto: " + producto.getProdCategoria()
        );
        System.out.println(
                "Categorias existentes:"
        );

        categoriaService.listarCategorias()
                .forEach(
                        c -> System.out.println(
                                c.getCatNombre()
                                        + " - "
                                        + c.getCatEstado()
                        )
                );
        productoRepository.cambiarEstadoProducto(id);
    }

    @Override
    public Producto buscarProducto(String id) {

        return productoRepository.buscarProducto(id);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        if(producto == null){
            throw new RuntimeException(
                    "Producto inválido."
            );
        }
        Producto existente =
                buscarProducto(
                        producto.getIdProducto()
                );

        if(existente == null){

            throw new RuntimeException(
                    "Producto no encontrado."
            );
        }
        Categoria categoria =
                categoriaService
                        .listarCategorias()
                        .stream()
                        .filter(
                                c ->
                                        c.getIdCategoria()
                                                .equalsIgnoreCase(
                                                        producto.getProdCategoria()
                                                )
                        )
                        .findFirst()
                        .orElse(null);
        if(categoria == null){

            throw new RuntimeException(
                    "La categoría no existe."
            );
        }
        if(!categoria.getCatEstado()
                .equalsIgnoreCase("Activo")){

            throw new RuntimeException(
                    "No se puede asignar una categoría inactiva."
            );
        }
        productoRepository.actualizarProducto(producto);
    }
}