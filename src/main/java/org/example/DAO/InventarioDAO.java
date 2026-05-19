package org.example.DAO;

public interface InventarioDAO {

    void registrarEntrada(
            int idProducto,
            int cantidad,
            String descripcion
    );

    void registrarSalida(
            int idProducto,
            int cantidad,
            String descripcion
    );

    void listarMovimientos();

    void productosPocoStock();

    void gananciasPotenciales();

    void productosMasVendidos();
}