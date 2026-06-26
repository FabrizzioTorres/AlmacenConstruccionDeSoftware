package com.example.ALMACENMULTIPRO.repository;

import com.example.ALMACENMULTIPRO.model.Transaccion;

import java.util.List;

public interface TransaccionRepository {

    void guardarTransaccion(
            Transaccion transaccion);

    List<Transaccion> listarTransacciones();

    Transaccion buscarTransaccion(
            String id);
}