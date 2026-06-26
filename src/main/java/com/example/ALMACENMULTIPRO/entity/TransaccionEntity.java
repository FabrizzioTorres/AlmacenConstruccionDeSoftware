package com.example.ALMACENMULTIPRO.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    private LocalDate fecha;

    private LocalTime hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private ProveedorEntity proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id")
    private DestinoEntity destino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    private UsuarioEntity responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_id")
    private UsuarioEntity asignado;

    @OneToMany(
            mappedBy = "transaccion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DetalleTransaccionEntity> detalles;

    public TransaccionEntity(){

    }
    public TransaccionEntity(Long id,
                             String tipo,
                             LocalDate fecha,
                             LocalTime hora,
                             ProveedorEntity proveedor,
                             DestinoEntity destino,
                             UsuarioEntity responsable,
                             UsuarioEntity asignado,
                             List<DetalleTransaccionEntity> detalles) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.proveedor = proveedor;
        this.destino = destino;
        this.responsable = responsable;
        this.asignado = asignado;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public DestinoEntity getDestino() {
        return destino;
    }

    public UsuarioEntity getResponsable() {
        return responsable;
    }

    public UsuarioEntity getAsignado() {
        return asignado;
    }

    public List<DetalleTransaccionEntity> getDetalles() {
        return detalles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public void setDestino(DestinoEntity destino) {
        this.destino = destino;
    }

    public void setResponsable(UsuarioEntity responsable) {
        this.responsable = responsable;
    }

    public void setAsignado(UsuarioEntity asignado) {
        this.asignado = asignado;
    }

    public void setDetalles(List<DetalleTransaccionEntity> detalles) {
        this.detalles = detalles;
    }
}
