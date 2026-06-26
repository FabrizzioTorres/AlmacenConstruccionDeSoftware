package com.example.ALMACENMULTIPRO.model;

public class MetricaResumen {

    private int stockTotal;
    private long productosActivos;
    private long usuariosActivos;
    private long proveedoresActivos;
    private long transaccionesHoy;

    public MetricaResumen() {
    }

    public MetricaResumen(
            int stockTotal,
            long productosActivos,
            long usuariosActivos,
            long proveedoresActivos,
            long transaccionesHoy) {

        this.stockTotal = stockTotal;
        this.productosActivos = productosActivos;
        this.usuariosActivos = usuariosActivos;
        this.proveedoresActivos = proveedoresActivos;
        this.transaccionesHoy = transaccionesHoy;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public long getProductosActivos() {
        return productosActivos;
    }

    public void setProductosActivos(long productosActivos) {
        this.productosActivos = productosActivos;
    }

    public long getUsuariosActivos() {
        return usuariosActivos;
    }

    public void setUsuariosActivos(long usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    public long getProveedoresActivos() {
        return proveedoresActivos;
    }

    public void setProveedoresActivos(long proveedoresActivos) {
        this.proveedoresActivos = proveedoresActivos;
    }

    public long getTransaccionesHoy() {
        return transaccionesHoy;
    }

    public void setTransaccionesHoy(long transaccionesHoy) {
        this.transaccionesHoy = transaccionesHoy;
    }
}