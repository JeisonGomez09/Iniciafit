package org.example.DAO;

import org.example.MODEL.Proveedor;
import org.example.UTIL.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public void agregar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, telefono, email, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getDireccion());

            ps.executeUpdate();
            System.out.println("Proveedor agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- LISTA DE PROVEEDORES ---");

            while (rs.next()) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + rs.getInt("id_proveedor"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Dirección: " + rs.getString("direccion"));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre=?, telefono=?, email=?, direccion=? WHERE id_proveedor=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getDireccion());
            ps.setInt(5, proveedor.getIdProveedor());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Proveedor actualizado correctamente.");
            } else {
                System.out.println("No se encontró un proveedor con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int idProveedor) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Proveedor eliminado correctamente.");
            } else {
                System.out.println("No se encontró un proveedor con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}