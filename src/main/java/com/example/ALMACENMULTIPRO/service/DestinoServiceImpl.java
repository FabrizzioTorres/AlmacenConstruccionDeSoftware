package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Destino;
import com.example.ALMACENMULTIPRO.repository.DestinoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinoServiceImpl
        implements DestinoService {

    private final DestinoRepository destinoRepository;

    public DestinoServiceImpl(
            DestinoRepository destinoRepository) {

        this.destinoRepository =
                destinoRepository;
    }

    @Override
    public List<Destino> listarDestinos() {

        return destinoRepository.listarDestinos();
    }

    @Override
    public void guardarDestino(
            Destino destino) {
        if (destino == null) {
            throw new RuntimeException(
                    "Destino inválido."
            );
        }
        if (!destino.getIdDestino()
                .matches("\\d{11}")) {

            throw new RuntimeException(
                    "El RUC debe tener 11 dígitos"
            );
        }
        if (destino.getDesNombre() == null ||
                destino.getDesNombre().isBlank()) {

            throw new RuntimeException(
                    "Debe ingresar un nombre."
            );
        }
        if (destino.getDesDireccion() == null ||
                destino.getDesDireccion().isBlank()) {

            throw new RuntimeException(
                    "Debe ingresar una dirección."
            );
        }
        Destino existente =
                buscarDestino(
                        destino.getIdDestino()
                );

        if (existente != null) {

            throw new RuntimeException(
                    "Ya existe un proveedor con ese RUC"
            );
        }
        if(destino.getDesContacto() == null
                || destino.getDesContacto().isBlank()){

            throw new RuntimeException(
                    "Debe ingresar un contacto."
            );
        }
        boolean existe =
                listarDestinos()
                        .stream()
                        .anyMatch(
                                d -> d.getDesNombre()
                                        .equalsIgnoreCase(
                                                destino.getDesNombre()
                                        )
                        );
        if (existe) {

            throw new RuntimeException(
                    "Ya existe un destino con ese nombre."
            );
        }
        if (destino.getDesEstado() == null ||
                destino.getDesEstado().isBlank()) {
            destino.setDesEstado("Activo");
        }
        destinoRepository.guardarDestino(
                destino
        );
    }

    @Override
    public Destino buscarDestino(
            String id) {

        return destinoRepository.buscarDestino(
                id
        );
    }

    @Override
    public void actualizarDestino(
            Destino destino) {
        if(destino == null){
            throw new RuntimeException(
                    "Destino inválido."
            );
        }
        Destino existente =
                buscarDestino(
                        destino.getIdDestino()
                );

        if(existente == null){

            throw new RuntimeException(
                    "Destino no encontrado."
            );
        }
        destinoRepository.actualizarDestino(
                destino
        );
    }

    @Override
    public void cambiarEstadoDestino(
            String id) {
        Destino destino =
                buscarDestino(id);

        if(destino == null){

            throw new RuntimeException(
                    "Destino no encontrado."
            );
        }
        destinoRepository.cambiarEstadoDestino(
                id
        );
    }
}