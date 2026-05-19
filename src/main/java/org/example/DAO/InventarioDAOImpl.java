package org.example.DAO;

import org.example.UTIL.ConexionBD;

import java.sql.*;

public class InventarioDAOImpl implements InventarioDAO {

    @Override
    public void registrarEntrada(int idProducto,
                                 int cantidad,
                                 String descripcion) {

        Connection conn = null;

        try {

            conn = ConexionBD.obtenerConexion();

            conn.setAutoCommit(false);

            String fecha =
                    java.time.LocalDate.now().toString();

            // ===== MOVIMIENTO =====

            String sqlMovimiento = """
                    INSERT INTO movimientos_inventario
                    (id_producto,
                     tipo_movimiento,
                     cantidad,
                     fecha_movimiento,
                     descripcion)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement psMovimiento =
                    conn.prepareStatement(sqlMovimiento);

            psMovimiento.setInt(1, idProducto);
            psMovimiento.setString(2, "ENTRADA");
            psMovimiento.setInt(3, cantidad);
            psMovimiento.setString(4, fecha);
            psMovimiento.setString(5, descripcion);

            psMovimiento.executeUpdate();

            //STOCK

            String sqlStock = """
                    UPDATE productos
                    SET stock = stock + ?
                    WHERE id_producto = ?
                    """;

            PreparedStatement psStock =
                    conn.prepareStatement(sqlStock);

            psStock.setInt(1, cantidad);
            psStock.setInt(2, idProducto);

            psStock.executeUpdate();

            conn.commit();

            System.out.println(
                    "Entrada de inventario registrada.");

        } catch (SQLException e) {

            try {

                if (conn != null) {
                    conn.rollback();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(
                    "Error: " + e.getMessage());

        } finally {

            try {

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void registrarSalida(int idProducto,
                                int cantidad,
                                String descripcion) {

        Connection conn = null;

        try {

            conn = ConexionBD.obtenerConexion();

            conn.setAutoCommit(false);

            String fecha =
                    java.time.LocalDate.now().toString();

            // ===== MOVIMIENTO =====

            String sqlMovimiento = """
                    INSERT INTO movimientos_inventario
                    (id_producto,
                     tipo_movimiento,
                     cantidad,
                     fecha_movimiento,
                     descripcion)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement psMovimiento =
                    conn.prepareStatement(sqlMovimiento);

            psMovimiento.setInt(1, idProducto);
            psMovimiento.setString(2, "SALIDA");
            psMovimiento.setInt(3, cantidad);
            psMovimiento.setString(4, fecha);
            psMovimiento.setString(5, descripcion);

            psMovimiento.executeUpdate();

            // ===== STOCK =====

            String sqlStock = """
                    UPDATE productos
                    SET stock = stock - ?
                    WHERE id_producto = ?
                    """;

            PreparedStatement psStock =
                    conn.prepareStatement(sqlStock);

            psStock.setInt(1, cantidad);
            psStock.setInt(2, idProducto);

            psStock.executeUpdate();

            conn.commit();

            System.out.println(
                    "Salida registrada correctamente.");

        } catch (SQLException e) {

            try {

                if (conn != null) {
                    conn.rollback();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(
                    "Error: " + e.getMessage());

        } finally {

            try {

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void listarMovimientos() {

        String sql = """
                SELECT
                m.id_movimiento,
                p.nombre,
                m.tipo_movimiento,
                m.cantidad,
                m.fecha_movimiento,
                m.descripcion

                FROM movimientos_inventario m

                INNER JOIN productos p
                ON m.id_producto = p.id_producto
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println(
                    "\n===== MOVIMIENTOS INVENTARIO =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println(
                        "ID Movimiento: "
                                + rs.getInt("id_movimiento"));

                System.out.println(
                        "Producto: "
                                + rs.getString("nombre"));

                System.out.println(
                        "Tipo: "
                                + rs.getString("tipo_movimiento"));

                System.out.println(
                        "Cantidad: "
                                + rs.getInt("cantidad"));

                System.out.println(
                        "Fecha: "
                                + rs.getString("fecha_movimiento"));

                System.out.println(
                        "Descripción: "
                                + rs.getString("descripcion"));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    @Override
    public void productosPocoStock() {

        String sql = """
                SELECT *
                FROM productos
                WHERE stock <= 5
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println(
                    "\n===== PRODUCTOS CON POCO STOCK =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println(
                        "ID: "
                                + rs.getInt("id_producto"));

                System.out.println(
                        "Producto: "
                                + rs.getString("nombre"));

                System.out.println(
                        "Stock: "
                                + rs.getInt("stock"));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    @Override
    public void gananciasPotenciales() {

        String sql = """
                SELECT
                SUM(
                    (precio_venta - precio_compra)
                    * stock
                ) AS ganancia
                FROM productos
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                System.out.println(
                        "\nGanancia potencial inventario: $"
                                + rs.getDouble("ganancia"));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    @Override
    public void productosMasVendidos() {

        String sql = """
                SELECT
                p.nombre,
                SUM(d.cantidad) AS total_vendido

                FROM detalle_ventas d

                INNER JOIN productos p
                ON d.id_producto = p.id_producto

                GROUP BY p.nombre

                ORDER BY total_vendido DESC
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println(
                    "\n===== PRODUCTOS MÁS VENDIDOS =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println(
                        "Producto: "
                                + rs.getString("nombre"));

                System.out.println(
                        "Cantidad vendida: "
                                + rs.getInt("total_vendido"));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }
}