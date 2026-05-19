package org.example.DAO;

import org.example.MODEL.Producto;

public interface ProductoDAO {

    void agregar(Producto producto);

    void listar();

    void actualizar(Producto producto);

    void eliminar(int idProducto);

    Producto buscarPorId(int idProducto);
}