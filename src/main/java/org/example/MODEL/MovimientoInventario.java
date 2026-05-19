package org.example.MODEL;

public class MovimientoInventario {

    private int idMovimiento;
    private int idProducto;
    private String tipoMovimiento;
    private int cantidad;
    private String fechaMovimiento;
    private String descripcion;

    public MovimientoInventario() {
    }

    public MovimientoInventario(int idProducto,
                                String tipoMovimiento,
                                int cantidad,
                                String fechaMovimiento,
                                String descripcion) {

        this.idProducto = idProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "MovimientoInventario{" +
                "idMovimiento=" + idMovimiento +
                ", idProducto=" + idProducto +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", cantidad=" + cantidad +
                ", fechaMovimiento='" + fechaMovimiento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}