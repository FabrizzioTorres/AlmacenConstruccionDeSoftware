package com.example.ALMACENMULTIPRO.repository.jpaimpl;

import com.example.ALMACENMULTIPRO.entity.DestinoEntity;
import com.example.ALMACENMULTIPRO.mapper.DestinoMapper;
import com.example.ALMACENMULTIPRO.model.Destino;
import com.example.ALMACENMULTIPRO.repository.DestinoRepository;
import com.example.ALMACENMULTIPRO.repository.jpa.DestinoJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DestinoRepositoryJpaImpl
        implements DestinoRepository {

    private final DestinoJpaRepository
            destinoJpaRepository;

    public DestinoRepositoryJpaImpl(
            DestinoJpaRepository destinoJpaRepository) {

        this.destinoJpaRepository =
                destinoJpaRepository;
    }

    @Override
    public void guardarDestino(
            Destino destino) {

        DestinoEntity entity =
                DestinoMapper.toEntity(
                        destino
                );

        destinoJpaRepository.save(
                entity
        );
    }

    @Override
    public List<Destino> listarDestinos() {

        return destinoJpaRepository
                .findAll()
                .stream()
                .map(
                        DestinoMapper::toModel
                )
                .toList();
    }

    @Override
    public Destino buscarDestino(
            String id) {

        return destinoJpaRepository
                .findById(id)
                .map(
                        DestinoMapper::toModel
                )
                .orElse(null);
    }

    @Override
    public void actualizarDestino(
            Destino destino) {

        DestinoEntity entity =
                destinoJpaRepository
                        .findById(
                                destino.getIdDestino()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Destino no encontrado."
                                )
                        );

        entity.setDesNombre(
                destino.getDesNombre()
        );

        entity.setDesDireccion(
                destino.getDesDireccion()
        );

        entity.setDesContacto(
                destino.getDesContacto()
        );

        entity.setDesDescripcion(
                destino.getDesDescripcion()
        );

        destinoJpaRepository.save(
                entity
        );
    }

    @Override
    public void cambiarEstadoDestino(
            String id) {

        DestinoEntity entity =
                destinoJpaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Destino no encontrado."
                                )
                        );

        if(entity.getDesEstado()
                .equalsIgnoreCase(
                        "Activo"
                )){

            entity.setDesEstado(
                    "Inactivo"
            );

        }else{

            entity.setDesEstado(
                    "Activo"
            );
        }

        destinoJpaRepository.save(
                entity
        );
    }
}