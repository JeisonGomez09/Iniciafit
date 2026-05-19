package org.example.DAO;

import org.example.MODEL.Cliente;

public interface ClienteDAO {

    void agregar(Cliente cliente);

    void listar();

    void actualizar(Cliente cliente);

    void eliminar(int idCliente);

    Cliente buscarPorId(int idCliente);
}