package org.example.MODEL;

public class DetalleVenta {

    private int idDetalle;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(int idVenta, int idProducto,
                        int cantidad,
                        double precioUnitario,
                        double subtotal) {

        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalle=" + idDetalle +
                ", idVenta=" + idVenta +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}