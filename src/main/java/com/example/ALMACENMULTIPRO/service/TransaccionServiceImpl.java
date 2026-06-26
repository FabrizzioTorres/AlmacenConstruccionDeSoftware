package com.example.ALMACENMULTIPRO.service;
import com.example.ALMACENMULTIPRO.model.DetalleTransaccion;
import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Transaccion;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.repository.TransaccionRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalTime;
@Service
public class TransaccionServiceImpl
        implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    private final ProductoService productoService;

    private final UsuarioService usuarioService;

    private final ProveedorService proveedorService;

    private final DestinoService destinoService;


    public TransaccionServiceImpl(
            TransaccionRepository transaccionRepository,
            ProductoService productoService, UsuarioService usuarioService,
            ProveedorService proveedorService, DestinoService destinoService) {

        this.transaccionRepository =
                transaccionRepository;

        this.productoService =
                productoService;

        this.usuarioService =
                usuarioService;

        this.proveedorService =
                proveedorService;

        this.destinoService=
                destinoService;
    }

    @Override
    public List<Transaccion> listarTransacciones() {

        return transaccionRepository
                .listarTransacciones();
    }

    @Override
    public Transaccion buscarTransaccion(
            String id) {

        return transaccionRepository
                .buscarTransaccion(id);
    }

    @Override
    public void guardarTransaccion(
            Transaccion transaccion) {

        if (transaccion.getDetalles() == null) {

            transaccion.setDetalles(
                    new ArrayList<>()
            );
        }
        LocalDate hoy = LocalDate.now();
        LocalTime ahora =
                LocalTime.now()
                        .withSecond(0)
                        .withNano(0);
        if (transaccion.getFecha() == null) {

            throw new RuntimeException(
                    "Debe seleccionar una fecha."
            );
        }

        if (transaccion.getHora() == null) {

            throw new RuntimeException(
                    "Debe seleccionar una hora."
            );
        }
        if (transaccion.getFecha().isAfter(hoy)) {

            throw new RuntimeException(
                    "No puede registrar fechas futuras."
            );
        }

        if (transaccion.getFecha().isEqual(hoy)
                &&
                transaccion.getHora().isAfter(ahora)) {

            throw new RuntimeException(
                    "No puede registrar horas futuras."
            );
        }
        if (!transaccion.getFecha().isEqual(hoy)) {

            throw new RuntimeException(
                    "Solo se permiten transacciones de la fecha actual."
            );
        }
        LocalTime inicio =
                LocalTime.of(8,0);

        LocalTime fin =
                LocalTime.of(22,0);

        if (
                transaccion.getHora()
                        .isBefore(inicio)

                        ||

                        transaccion.getHora()
                                .isAfter(fin)
        ) {

            throw new RuntimeException(
                    "La hora debe estar entre 08:00 y 22:00."
            );
        }
        if (transaccion.getHora()
                .getSecond() != 0) {

            throw new RuntimeException(
                    "La hora no debe contener segundos."
            );
        }
        if (!transaccion.getTipo().equalsIgnoreCase("Ingreso")
                &&
                !transaccion.getTipo().equalsIgnoreCase("Salida")) {

            throw new RuntimeException(
                    "Tipo de transacción inválido."
            );
        }
        if (transaccion.getResponsable() == null
                || transaccion.getResponsable().isBlank()) {

            throw new RuntimeException(
                    "Debe seleccionar un responsable"
            );
        }

        if (transaccion.getAsignado() == null
                || transaccion.getAsignado().isBlank()) {

            throw new RuntimeException(
                    "Debe seleccionar un asignado"
            );
        }
        Usuario responsable =
                usuarioService.buscarUsuario(
                        transaccion.getResponsable()
                );
        if (responsable == null) {

            throw new RuntimeException(
                    "Responsable inexistente."
            );
        }
        if(responsable.getUsuEstado()
                .equalsIgnoreCase("Inactivo")){

            throw new RuntimeException(
                    "El responsable se encuentra inactivo."
            );
        }
        if (transaccion.getResponsable()
                .equals(transaccion.getAsignado())) {

            throw new RuntimeException(
                    "Responsable y asignado no pueden ser la misma persona."
            );
        }
        Usuario asignado =
                usuarioService.buscarUsuario(
                        transaccion.getAsignado()
                );
        if (asignado == null) {

            throw new RuntimeException(
                    "Asignado inexistente."
            );
        }

        if(asignado.getUsuEstado()
                .equalsIgnoreCase("Inactivo")) {

            throw new RuntimeException(
                    "El usuario asignado se encuentra inactivo."
            );
        }
        if (transaccion.getTipo()
                .equalsIgnoreCase("Ingreso")

                &&

                (transaccion.getProveedor() == null
                        || transaccion.getProveedor().isBlank())) {

            throw new RuntimeException(
                    "Debe seleccionar un proveedor."
            );
        }
        if (transaccion.getTipo()
                .equalsIgnoreCase("Ingreso")
                &&
                proveedorService.buscarProveedor(
                        transaccion.getProveedor()
                ) == null) {

            throw new RuntimeException(
                    "Proveedor inexistente."
            );
        }
        if(transaccion.getTipo()
                .equalsIgnoreCase("Ingreso")){

            if(proveedorService.buscarProveedor(
                            transaccion.getProveedor()
                    ).getProvEstado()
                    .equalsIgnoreCase("Inactivo")){

                throw new RuntimeException(
                        "No se puede usar un proveedor inactivo."
                );
            }
        }
        if (transaccion.getTipo()
                .equalsIgnoreCase("Salida")

                &&

                (transaccion.getDestino() == null
                        || transaccion.getDestino().isBlank())) {

            throw new RuntimeException(
                    "Debe seleccionar un destino."
            );
        }
        if (transaccion.getTipo()
                .equalsIgnoreCase("Salida")
                &&
                destinoService.buscarDestino(
                        transaccion.getDestino()
                ) == null) {

            throw new RuntimeException(
                    "Destino inexistente."
            );
        }
        if(transaccion.getTipo()
                .equalsIgnoreCase("Salida")){

            if(destinoService.buscarDestino(
                            transaccion.getDestino()
                    ).getDesEstado()
                    .equalsIgnoreCase("Inactivo")){

                throw new RuntimeException(
                        "No se puede usar un destino inactivo."
                );
            }
        }
            if(transaccion.getTipo()
                    .equalsIgnoreCase("Ingreso")
                    &&
                    transaccion.getDestino() != null
                    &&
                    !transaccion.getDestino().isBlank()){

                throw new RuntimeException(
                        "Un ingreso no puede tener destino."
                );
            }
            if(transaccion.getTipo()
                    .equalsIgnoreCase("Salida")
                    &&
                    transaccion.getProveedor() != null
                    &&
                    !transaccion.getProveedor().isBlank()){

                throw new RuntimeException(
                        "Una Salida no puede tener Proveedor."
                );
            }
        if(
                !responsable.getUsuRol()
                        .equalsIgnoreCase("Administrador")

                        &&

                        !responsable.getUsuRol()
                                .equalsIgnoreCase("Dueño")
        ){

            throw new RuntimeException(
                    "El responsable debe ser Administrador o Dueño."
            );
        }
        if(!asignado.getUsuRol()
                .equalsIgnoreCase("Asistente")){

            throw new RuntimeException(
                    "El asignado debe ser un asistente."
            );
        }
        Set<String> productosRegistrados =
                new HashSet<>();
        if (transaccion.getDetalles().isEmpty()) {

            throw new RuntimeException(
                    "Debe agregar al menos un producto."
            );
        }
        for (DetalleTransaccion detalle :
                transaccion.getDetalles()) {

            // Producto seleccionado

            if (detalle.getIdProducto() == null ||
                    detalle.getIdProducto().isBlank()) {

                throw new RuntimeException(
                        "Debe seleccionar un producto"
                );
            }
            // Cantidad válida

            if (detalle.getCantidad() <= 0) {

                throw new RuntimeException(
                        "La cantidad debe ser mayor a cero"
                );
            }

            // Evitar duplicados

            if (!productosRegistrados.add(
                    detalle.getIdProducto())) {

                throw new RuntimeException(
                        "No puede repetir productos"
                );
            }

            Producto producto =
                    productoService.buscarProducto(
                            detalle.getIdProducto()
                    );

            if (producto == null) {

                throw new RuntimeException(
                        "Producto inexistente"
                );
            }
            if(producto.getProdEstado()
                    .equalsIgnoreCase("Inactivo")){

                throw new RuntimeException(
                        "El producto "
                                + producto.getProdNombre()
                                + " se encuentra inactivo."
                );
            }
            int stockAntes = producto.getStock();

            detalle.setStockAnterior(stockAntes);

            detalle.setNombreProducto(
                    producto.getProdNombre()
            );

            // INGRESO

            if (transaccion.getTipo()
                    .equalsIgnoreCase("Ingreso")) {

                producto.setStock(
                        stockAntes + detalle.getCantidad()
                );

                detalle.setStockResultante(
                        producto.getStock()
                );
            }

            // SALIDA

            else {
                if (!existeIngresoPrevio(
                        detalle.getIdProducto(),
                        transaccion.getFecha(),
                        transaccion.getHora()
                )) {

                    throw new RuntimeException(
                            "No existe un ingreso previo para "
                                    + producto.getProdNombre()
                    );
                }
                if (producto.getStock() <= 0) {

                    throw new RuntimeException(
                            "El producto "
                                    + producto.getProdNombre()
                                    + " no tiene stock"
                    );
                }

                if (producto.getStock()
                        < detalle.getCantidad()) {

                    throw new RuntimeException(
                            "Stock insuficiente para "
                                    + producto.getProdNombre()
                    );
                }

                producto.setStock(
                        stockAntes - detalle.getCantidad()
                );

                detalle.setStockResultante(
                        producto.getStock()
                );
            }
            productoService.actualizarProducto(
                    producto
            );

        }
        transaccionRepository.guardarTransaccion(
                transaccion
        );
    }
    @Override
    public boolean existeIngresoPrevio(
            String idProducto,
            LocalDate fecha,
            LocalTime hora) {

        for (Transaccion t : listarTransacciones()) {

            if (!t.getTipo()
                    .equalsIgnoreCase("Ingreso")) {

                continue;
            }

            // validar fecha anterior

            boolean fechaValida =
                    t.getFecha().isBefore(fecha)

                            ||

                            (

                                    t.getFecha().isEqual(fecha)

                                            &&

                                            !t.getHora().isAfter(hora)

                            );

            if (!fechaValida) {

                continue;
            }

            for (DetalleTransaccion d :
                    t.getDetalles()) {

                if (d.getIdProducto()
                        .equals(idProducto)) {

                    return true;
                }
            }
        }

        return false;
    }
}