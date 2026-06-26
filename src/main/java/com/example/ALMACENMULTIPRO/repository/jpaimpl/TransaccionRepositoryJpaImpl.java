package com.example.ALMACENMULTIPRO.repository.jpaimpl;

import com.example.ALMACENMULTIPRO.entity.DetalleTransaccionEntity;
import com.example.ALMACENMULTIPRO.entity.ProductoEntity;
import com.example.ALMACENMULTIPRO.entity.TransaccionEntity;
import com.example.ALMACENMULTIPRO.mapper.DetalleTransaccionMapper;
import com.example.ALMACENMULTIPRO.mapper.TransaccionMapper;
import com.example.ALMACENMULTIPRO.model.DetalleTransaccion;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.repository.TransaccionRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransaccionRepositoryJpaImpl
        implements TransaccionRepository {
    private final TransaccionJpaRepository
            transaccionJpaRepository;

    private final ProductoJpaRepository
            productoJpaRepository;

    private final UsuarioJpaRepository
            usuarioJpaRepository;

    private final ProveedorJpaRepository
            proveedorJpaRepository;

    private final DestinoJpaRepository
            destinoJpaRepository;

    public TransaccionRepositoryJpaImpl(
            TransaccionJpaRepository transaccionJpaRepository,
            ProductoJpaRepository productoJpaRepository,
            UsuarioJpaRepository usuarioJpaRepository,
            ProveedorJpaRepository proveedorJpaRepository,
            DestinoJpaRepository destinoJpaRepository) {

        this.transaccionJpaRepository =
                transaccionJpaRepository;

        this.productoJpaRepository =
                productoJpaRepository;

        this.usuarioJpaRepository =
                usuarioJpaRepository;

        this.proveedorJpaRepository =
                proveedorJpaRepository;

        this.destinoJpaRepository =
                destinoJpaRepository;
    }

    @Override
    public void guardarTransaccion(
            Transaccion transaccion) {
        TransaccionEntity entity =
                TransaccionMapper.toEntity(
                        transaccion
                );
        if (transaccion.getProveedor() != null) {

            entity.setProveedor(

                    proveedorJpaRepository
                            .findById(
                                    transaccion.getProveedor()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Transacción no encontrada."
                                    )
                            )
            );
        }
        if (transaccion.getDestino() != null) {

            entity.setDestino(

                    destinoJpaRepository
                            .findById(
                                    transaccion.getDestino()
                            )
                            .orElseThrow()
            );
        }
        entity.setResponsable(

                usuarioJpaRepository
                        .findById(
                                transaccion.getResponsable()
                        )
                        .orElseThrow()
        );
        entity.setAsignado(

                usuarioJpaRepository
                        .findById(
                                transaccion.getAsignado()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transacción no encontrada."
                                )
                        )
        );
        List<DetalleTransaccionEntity>
                detalles =
                new ArrayList<>();
        for (DetalleTransaccion detalle :
                transaccion.getDetalles()) {
            DetalleTransaccionEntity
                    detalleEntity =
                    DetalleTransaccionMapper
                            .toEntity(
                                    detalle
                            );
            Long idProducto =
                    Long.parseLong(

                            detalle.getIdProducto()
                                    .replace("P", "")
                    );
            ProductoEntity producto =

                    productoJpaRepository
                            .findById(
                                    idProducto
                            )
                            .orElseThrow();
            detalleEntity.setProducto(
                    producto
            );

            detalleEntity.setTransaccion(
                    entity
            );
            detalles.add(
                    detalleEntity
            );
            entity.setDetalles(detalles);
        }
        transaccionJpaRepository.save(
                entity
        );
    }

    @Override
    public List<Transaccion> listarTransacciones() {
        return transaccionJpaRepository
                .findAll()
                .stream()
                .map(
                        TransaccionMapper
                                ::toModel
                )
                .toList();
    }

    @Override
    public Transaccion buscarTransaccion(
            String idTransaccion) {
        Long idNumerico =

                Long.parseLong(
                        idTransaccion
                                .replace("TR", "")
                );

        return transaccionJpaRepository
                .findById(idNumerico)
                .map(
                        TransaccionMapper
                                ::toModel
                )
                .orElse(null);
    }
}
