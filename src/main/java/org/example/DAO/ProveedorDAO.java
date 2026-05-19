package org.example.DAO;

import org.example.MODEL.Proveedor;

public interface ProveedorDAO {

    void agregar(Proveedor proveedor);

    void listar();

    void actualizar(Proveedor proveedor);

    void eliminar(int idProveedor);
}