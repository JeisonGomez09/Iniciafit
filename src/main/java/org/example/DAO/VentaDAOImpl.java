package org.example.DAO;

import org.example.MODEL.Producto;
import org.example.UTIL.ConexionBD;

import java.sql.*;

public class VentaDAOImpl implements VentaDAO {

    ProductoDAO productoDAO = new ProductoDAOImpl();

    @Override
    public void registrarVenta(int idCliente,
                               int idProducto,
                               int cantidad) {

        Producto producto = productoDAO.buscarPorId(idProducto);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        if (producto.getStock() < cantidad) {
            System.out.println("Stock insuficiente.");
            return;
        }

        double precioUnitario = producto.getPrecioVenta();

        double subtotal = precioUnitario * cantidad;

        String fecha = java.time.LocalDate.now().toString();

        String hora = java.time.LocalTime.now()
                .withNano(0)
                .toString();

        Connection conn = null;

        try {

            conn = ConexionBD.obtenerConexion();

            conn.setAutoCommit(false);

            // INSERTE VENTA

            String sqlVenta = """
                    INSERT INTO ventas
                    (id_cliente, fecha_venta,
                     hora_venta, total)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement psVenta =
                    conn.prepareStatement(
                            sqlVenta,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psVenta.setInt(1, idCliente);
            psVenta.setString(2, fecha);
            psVenta.setString(3, hora);
            psVenta.setDouble(4, subtotal);

            psVenta.executeUpdate();

            ResultSet rs =
                    psVenta.getGeneratedKeys();

            int idVenta = 0;

            if (rs.next()) {
                idVenta = rs.getInt(1);
            }

            //DETALLE

            String sqlDetalle = """
                    INSERT INTO detalle_ventas
                    (id_venta, id_producto,
                     cantidad, precio_unitario,
                     subtotal)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement psDetalle =
                    conn.prepareStatement(sqlDetalle);

            psDetalle.setInt(1, idVenta);
            psDetalle.setInt(2, idProducto);
            psDetalle.setInt(3, cantidad);
            psDetalle.setDouble(4, precioUnitario);
            psDetalle.setDouble(5, subtotal);

            psDetalle.executeUpdate();

            //ACTUALIZAR STOCK

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

            System.out.println("Venta registrada correctamente.");
            System.out.println("ID venta generada: " + idVenta);

        } catch (SQLException e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println("Error al registrar venta: "
                    + e.getMessage());

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
    public void listarVentas() {

        String sql = """
                SELECT v.id_venta,
                       v.fecha_venta,
                       v.hora_venta,
                       v.total,
                       c.nombre,
                       c.apellido
                FROM ventas v
                INNER JOIN clientes c
                ON v.id_cliente = c.id_cliente
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println("\n      VENTAS ");

            while (rs.next()) {

                System.out.println("---------------------------");

                System.out.println("ID Venta: "
                        + rs.getInt("id_venta"));

                System.out.println("Cliente: "
                        + rs.getString("nombre")
                        + " "
                        + rs.getString("apellido"));

                System.out.println("Fecha: "
                        + rs.getString("fecha_venta"));

                System.out.println("Hora: "
                        + rs.getString("hora_venta"));

                System.out.println("Total: $"
                        + rs.getDouble("total"));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar ventas: "
                            + e.getMessage()
            );
        }
    }

    @Override
    public void generarFactura(int idVenta) {

        String sql = """
                SELECT
                v.id_venta,
                v.fecha_venta,
                v.hora_venta,
                v.total,

                c.id_cliente,
                c.nombre,
                c.apellido,

                p.nombre AS producto,
                p.marca,

                d.cantidad,
                d.precio_unitario,
                d.subtotal

                FROM ventas v

                INNER JOIN clientes c
                ON v.id_cliente = c.id_cliente

                INNER JOIN detalle_ventas d
                ON v.id_venta = d.id_venta

                INNER JOIN productos p
                ON d.id_producto = p.id_producto

                WHERE v.id_venta = ?
                """;

        try (Connection conn =
                     ConexionBD.obtenerConexion();

             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, idVenta);

            ResultSet rs = ps.executeQuery();

            boolean existe = false;

            System.out.println(
                    "\n_____________________________________");

            System.out.println(
                    "        FACTURA DE VENTA");

            System.out.println(
                    "_______________________________________");

            while (rs.next()) {

                existe = true;

                System.out.println(
                        "Factura #: "
                                + rs.getInt("id_venta"));

                System.out.println(
                        "Fecha: "
                                + rs.getString("fecha_venta"));

                System.out.println(
                        "Hora: "
                                + rs.getString("hora_venta"));

                System.out.println("--------------------------------------");

                System.out.println(
                        "Cliente: "
                                + rs.getString("nombre")
                                + " "
                                + rs.getString("apellido"));

                System.out.println(
                        "Documento: "
                                + rs.getInt("id_cliente"));

                System.out.println("--------------------------------------");

                System.out.println(
                        "Producto: "
                                + rs.getString("producto"));

                System.out.println(
                        "Marca: "
                                + rs.getString("marca"));

                System.out.println(
                        "Cantidad: "
                                + rs.getInt("cantidad"));

                System.out.println(
                        "Precio unitario: $"
                                + rs.getDouble("precio_unitario"));

                System.out.println(
                        "Subtotal: $"
                                + rs.getDouble("subtotal"));

                System.out.println("--------------------------------------");

                System.out.println(
                        "TOTAL PAGADO: $"
                                + rs.getDouble("total"));

                System.out.println(
                        "___________________________________________");
            }

            if (!existe) {
                System.out.println(
                        "No existe una venta con ese ID.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al generar factura: "
                            + e.getMessage());
        }
    }
}