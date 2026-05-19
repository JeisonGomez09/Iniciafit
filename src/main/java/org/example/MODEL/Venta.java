package org.example.MODEL;

public class Venta {

    private int idVenta;
    private int idCliente;
    private String fechaVenta;
    private String horaVenta;
    private double total;

    public Venta() {
    }

    public Venta(int idCliente, String fechaVenta,
                 String horaVenta, double total) {

        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.total = total;
    }

    public Venta(int idVenta, int idCliente,
                 String fechaVenta, String horaVenta,
                 double total) {

        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public String getHoraVenta() {
        return horaVenta;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", idCliente=" + idCliente +
                ", fechaVenta='" + fechaVenta + '\'' +
                ", horaVenta='" + horaVenta + '\'' +
                ", total=" + total +
                '}';
    }
}