package org.example.MODEL;

public class Producto {

    private int idProducto;
    private int idProveedor;
    private String nombre;
    private String marca;
    private String categoria;
    private String sabor;
    private String presentacion;
    private double precioCompra;
    private double precioVenta;
    private int stock;

    public Producto() {
    }

    public Producto(int idProveedor, String nombre, String marca,
                    String categoria, String sabor,
                    String presentacion, double precioCompra,
                    double precioVenta, int stock) {

        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.sabor = sabor;
        this.presentacion = presentacion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public Producto(int idProducto, int idProveedor, String nombre,
                    String marca, String categoria,
                    String sabor, String presentacion,
                    double precioCompra, double precioVenta,
                    int stock) {

        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.sabor = sabor;
        this.presentacion = presentacion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getSabor() {
        return sabor;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", sabor='" + sabor + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                '}';
    }
}