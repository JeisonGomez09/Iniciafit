package org.example.DAO;

import org.example.MODEL.Producto;
import org.example.UTIL.ConexionBD;

import java.sql.*;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public void agregar(Producto producto) {

        String sql = """
                INSERT INTO productos
                (id_proveedor, nombre, marca, categoria,
                 sabor, presentacion, precio_compra,
                 precio_venta, stock)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProveedor());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getCategoria());
            ps.setString(5, producto.getSabor());
            ps.setString(6, producto.getPresentacion());
            ps.setDouble(7, producto.getPrecioCompra());
            ps.setDouble(8, producto.getPrecioVenta());
            ps.setInt(9, producto.getStock());

            ps.executeUpdate();

            System.out.println("Producto agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    @Override
    public void listar() {

        String sql = """
                SELECT p.*, pr.nombre AS proveedor
                FROM productos p
                INNER JOIN proveedores pr
                ON p.id_proveedor = pr.id_proveedor
                """;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== PRODUCTOS =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println("ID: " + rs.getInt("id_producto"));
                System.out.println("Proveedor: " + rs.getString("proveedor"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Marca: " + rs.getString("marca"));
                System.out.println("Categoría: " + rs.getString("categoria"));
                System.out.println("Sabor: " + rs.getString("sabor"));
                System.out.println("Presentación: " + rs.getString("presentacion"));
                System.out.println("Precio compra: $" + rs.getDouble("precio_compra"));
                System.out.println("Precio venta: $" + rs.getDouble("precio_venta"));
                System.out.println("Stock: " + rs.getInt("stock"));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Producto producto) {

        String sql = """
                UPDATE productos
                SET id_proveedor=?,
                    nombre=?,
                    marca=?,
                    categoria=?,
                    sabor=?,
                    presentacion=?,
                    precio_compra=?,
                    precio_venta=?,
                    stock=?
                WHERE id_producto=?
                """;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProveedor());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getCategoria());
            ps.setString(5, producto.getSabor());
            ps.setString(6, producto.getPresentacion());
            ps.setDouble(7, producto.getPrecioCompra());
            ps.setDouble(8, producto.getPrecioVenta());
            ps.setInt(9, producto.getStock());

            ps.setInt(10, producto.getIdProducto());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró el producto.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int idProducto) {

        String sql = "DELETE FROM productos WHERE id_producto=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No existe ese producto.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public Producto buscarPorId(int idProducto) {

        String sql = "SELECT * FROM productos WHERE id_producto=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("categoria"),
                        rs.getString("sabor"),
                        rs.getString("presentacion"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }
}