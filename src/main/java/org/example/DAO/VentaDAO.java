package org.example.DAO;

public interface VentaDAO {

    void registrarVenta(
            int idCliente,
            int idProducto,
            int cantidad
    );

    void listarVentas();

    void generarFactura(int idVenta);
}