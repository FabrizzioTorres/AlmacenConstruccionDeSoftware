package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.DetalleTransaccion;
import com.example.ALMACENMULTIPRO.model.MetricaMensual;
import com.example.ALMACENMULTIPRO.model.MetricaResumen;
import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Proveedor;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.model.Usuario;

import com.example.ALMACENMULTIPRO.repository.MetricaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MetricaServiceImpl implements MetricaService {

    private final MetricaRepository metricaRepository;

    public MetricaServiceImpl(
            MetricaRepository metricaRepository) {

        this.metricaRepository = metricaRepository;
    }

    @Override
    public MetricaResumen obtenerResumen() {

        List<Producto> productos =
                metricaRepository.obtenerProductos();

        List<Usuario> usuarios =
                metricaRepository.obtenerUsuarios();

        List<Proveedor> proveedores =
                metricaRepository.obtenerProveedores();

        List<Transaccion> transacciones =
                metricaRepository.obtenerTransacciones();

        int stockTotal =
                productos.stream()
                        .mapToInt(
                                Producto::getStock
                        )
                        .sum();

        long productosActivos =
                productos.stream()
                        .filter(
                                p -> p.getProdEstado() != null
                                        &&
                                        p.getProdEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .count();

        long usuariosActivos =
                usuarios.stream()
                        .filter(
                                u -> u.getUsuEstado() != null
                                        &&
                                        u.getUsuEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .count();

        long proveedoresActivos =
                proveedores.stream()
                        .filter(
                                p -> p.getProvEstado() != null
                                        &&
                                        p.getProvEstado()
                                                .equalsIgnoreCase("Activo")
                        )
                        .count();

        long transaccionesHoy =
                transacciones.stream()
                        .filter(
                                t -> t.getFecha() != null
                        )
                        .filter(
                                t -> t.getFecha()
                                        .isEqual(
                                                LocalDate.now()
                                        )
                        )
                        .count();

        return new MetricaResumen(
                stockTotal,
                productosActivos,
                usuariosActivos,
                proveedoresActivos,
                transaccionesHoy
        );
    }

    @Override
    public List<MetricaMensual> listarMetricasMensuales() {

        List<Transaccion> transacciones =
                metricaRepository.obtenerTransacciones();

        Map<YearMonth, Integer> ingresosPorMes =
                new HashMap<>();

        Map<YearMonth, Integer> salidasPorMes =
                new HashMap<>();

        Map<YearMonth, Set<String>> productosPorMes =
                new HashMap<>();

        Map<YearMonth, Set<String>> proveedoresPorMes =
                new HashMap<>();

        for (Transaccion transaccion : transacciones) {

            if (transaccion.getFecha() == null) {

                continue;
            }

            YearMonth mes =
                    YearMonth.from(
                            transaccion.getFecha()
                    );

            int cantidadTotal =
                    sumarCantidadDetalles(
                            transaccion
                    );

            if ("Ingreso".equalsIgnoreCase(
                    transaccion.getTipo())) {

                ingresosPorMes.merge(
                        mes,
                        cantidadTotal,
                        Integer::sum
                );

                if (transaccion.getProveedor() != null
                        &&
                        !transaccion.getProveedor().isBlank()) {

                    proveedoresPorMes
                            .computeIfAbsent(
                                    mes,
                                    k -> new HashSet<>()
                            )
                            .add(
                                    transaccion.getProveedor()
                            );
                }
            }

            if ("Salida".equalsIgnoreCase(
                    transaccion.getTipo())) {

                salidasPorMes.merge(
                        mes,
                        cantidadTotal,
                        Integer::sum
                );
            }

            if (transaccion.getDetalles() != null) {

                for (DetalleTransaccion detalle :
                        transaccion.getDetalles()) {

                    if (detalle.getIdProducto() != null
                            &&
                            !detalle.getIdProducto().isBlank()) {

                        productosPorMes
                                .computeIfAbsent(
                                        mes,
                                        k -> new HashSet<>()
                                )
                                .add(
                                        detalle.getIdProducto()
                                );
                    }
                }
            }
        }

        List<YearMonth> mesesOrdenados =
                obtenerMesesOrdenados(
                        transacciones
                );

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "MMM yyyy",
                        new Locale("es", "PE")
                );

        List<MetricaMensual> metricas =
                new ArrayList<>();

        for (YearMonth mes : mesesOrdenados) {

            int ingresos =
                    ingresosPorMes.getOrDefault(
                            mes,
                            0
                    );

            int salidas =
                    salidasPorMes.getOrDefault(
                            mes,
                            0
                    );

            int movimientos =
                    ingresos + salidas;

            int productosDistintos =
                    productosPorMes
                            .getOrDefault(
                                    mes,
                                    Collections.emptySet()
                            )
                            .size();

            int proveedoresUsados =
                    proveedoresPorMes
                            .getOrDefault(
                                    mes,
                                    Collections.emptySet()
                            )
                            .size();

            metricas.add(
                    new MetricaMensual(
                            mes.format(formato),
                            ingresos,
                            salidas,
                            movimientos,
                            productosDistintos,
                            proveedoresUsados
                    )
            );
        }

        return metricas;
    }

    private int sumarCantidadDetalles(
            Transaccion transaccion) {

        if (transaccion.getDetalles() == null) {

            return 0;
        }

        return transaccion.getDetalles()
                .stream()
                .mapToInt(
                        DetalleTransaccion::getCantidad
                )
                .sum();
    }

    private List<YearMonth> obtenerMesesOrdenados(
            List<Transaccion> transacciones) {

        Optional<YearMonth> primerMes =
                transacciones.stream()
                        .filter(
                                t -> t.getFecha() != null
                        )
                        .map(
                                t -> YearMonth.from(
                                        t.getFecha()
                                )
                        )
                        .min(
                                YearMonth::compareTo
                        );

        Optional<YearMonth> ultimoMes =
                transacciones.stream()
                        .filter(
                                t -> t.getFecha() != null
                        )
                        .map(
                                t -> YearMonth.from(
                                        t.getFecha()
                                )
                        )
                        .max(
                                YearMonth::compareTo
                        );

        List<YearMonth> meses =
                new ArrayList<>();

        if (primerMes.isEmpty()
                ||
                ultimoMes.isEmpty()) {

            return meses;
        }

        YearMonth mesActual =
                primerMes.get();

        while (!mesActual.isAfter(
                ultimoMes.get())) {

            meses.add(
                    mesActual
            );

            mesActual =
                    mesActual.plusMonths(
                            1
                    );
        }

        return meses;
    }
}