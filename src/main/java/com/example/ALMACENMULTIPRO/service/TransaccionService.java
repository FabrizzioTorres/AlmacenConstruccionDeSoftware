package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Transaccion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TransaccionService {

    List<Transaccion> listarTransacciones();

    void guardarTransaccion(
            Transaccion transaccion);

    Transaccion buscarTransaccion(
            String id);

    boolean existeIngresoPrevio(
            String idProducto,
            LocalDate fecha,
            LocalTime hora
    );
}