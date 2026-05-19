package org.example;

import org.example.MODEL.Cliente;
import org.example.MODEL.Producto;
import org.example.MODEL.Proveedor;

import org.example.DAO.ClienteDAO;
import org.example.DAO.ClienteDAOImpl;
import org.example.DAO.ProductoDAO;
import org.example.DAO.ProductoDAOImpl;
import org.example.DAO.ProveedorDAO;
import org.example.DAO.ProveedorDAOImpl;
import org.example.DAO.VentaDAO;
import org.example.DAO.VentaDAOImpl;
import org.example.DAO.InventarioDAO;
import org.example.DAO.InventarioDAOImpl;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final ProveedorDAO proveedorDAO = new ProveedorDAOImpl();
    private static final ProductoDAO productoDAO = new ProductoDAOImpl();
    private static final ClienteDAO clienteDAO = new ClienteDAOImpl();
    private static final VentaDAO ventaDAO = new VentaDAOImpl();
    private static final InventarioDAO inventarioDAO = new InventarioDAOImpl();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n      TIENDA DE SUPLEMENTOS ");
            System.out.println("1. Proveedores");
            System.out.println("2. Productos");
            System.out.println("3. Clientes");
            System.out.println("4. Ventas");
            System.out.println("5. Inventario");
            System.out.println("6. Salir");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> menuProveedores();
                case 2 -> menuProductos();
                case 3 -> menuClientes();
                case 4 -> menuVentas();
                case 5 -> menuInventario();
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 6);

        sc.close();
    }

    // MENÚ PROVEEDORES

    private static void menuProveedores() {

        int opcion;

        do {
            System.out.println("\n      PROVEEDORES ");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Listar proveedores");
            System.out.println("3. Actualizar proveedor");
            System.out.println("4. Eliminar proveedor");
            System.out.println("5. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre proveedor: ");
                    String nombre = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Dirección: ");
                    String direccion = sc.nextLine();

                    proveedorDAO.agregar(new Proveedor(nombre, telefono, email, direccion));
                }

                case 2 -> proveedorDAO.listar();

                case 3 -> {
                    int id = leerEntero("ID proveedor a actualizar: ");

                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Nuevo teléfono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();

                    System.out.print("Nueva dirección: ");
                    String direccion = sc.nextLine();

                    proveedorDAO.actualizar(new Proveedor(id, nombre, telefono, email, direccion));
                }

                case 4 -> {
                    int id = leerEntero("ID proveedor a eliminar: ");
                    proveedorDAO.eliminar(id);
                }

                case 5 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    // MENÚ PRODUCTOS

    private static void menuProductos() {

        int opcion;

        do {
            System.out.println("\n      PRODUCTOS ");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Buscar producto");
            System.out.println("6. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    int idProveedor = leerEntero("ID proveedor: ");

                    System.out.print("Nombre producto: ");
                    String nombre = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    System.out.print("Sabor: ");
                    String sabor = sc.nextLine();

                    System.out.print("Presentación: ");
                    String presentacion = sc.nextLine();

                    double precioCompra = leerDouble("Precio compra: ");
                    double precioVenta = leerDouble("Precio venta: ");
                    int stock = leerEntero("Stock: ");

                    Producto producto = new Producto(
                            idProveedor,
                            nombre,
                            marca,
                            categoria,
                            sabor,
                            presentacion,
                            precioCompra,
                            precioVenta,
                            stock
                    );

                    productoDAO.agregar(producto);
                }

                case 2 -> productoDAO.listar();

                case 3 -> {
                    int idProducto = leerEntero("ID producto: ");
                    int idProveedor = leerEntero("Nuevo ID proveedor: ");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    System.out.print("Sabor: ");
                    String sabor = sc.nextLine();

                    System.out.print("Presentación: ");
                    String presentacion = sc.nextLine();

                    double precioCompra = leerDouble("Precio compra: ");
                    double precioVenta = leerDouble("Precio venta: ");
                    int stock = leerEntero("Stock: ");

                    Producto producto = new Producto(
                            idProducto,
                            idProveedor,
                            nombre,
                            marca,
                            categoria,
                            sabor,
                            presentacion,
                            precioCompra,
                            precioVenta,
                            stock
                    );

                    productoDAO.actualizar(producto);
                }

                case 4 -> {
                    int id = leerEntero("ID producto a eliminar: ");
                    productoDAO.eliminar(id);
                }

                case 5 -> {
                    int id = leerEntero("ID producto: ");

                    Producto producto = productoDAO.buscarPorId(id);

                    if (producto != null) {
                        System.out.println("\nProducto encontrado:");
                        System.out.println("ID: " + producto.getIdProducto());
                        System.out.println("Nombre: " + producto.getNombre());
                        System.out.println("Marca: " + producto.getMarca());
                        System.out.println("Categoría: " + producto.getCategoria());
                        System.out.println("Sabor: " + producto.getSabor());
                        System.out.println("Presentación: " + producto.getPresentacion());
                        System.out.println("Precio compra: $" + producto.getPrecioCompra());
                        System.out.println("Precio venta: $" + producto.getPrecioVenta());
                        System.out.println("Stock: " + producto.getStock());
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }

                case 6 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    // MENÚ CLIENTES

    private static void menuClientes() {

        int opcion;

        do {
            System.out.println("\n      CLIENTES ");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Buscar cliente");
            System.out.println("6. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    int documento = leerEntero("Documento: ");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Dirección: ");
                    String direccion = sc.nextLine();

                    clienteDAO.agregar(new Cliente(documento, nombre, apellido, telefono, email, direccion));
                }

                case 2 -> clienteDAO.listar();

                case 3 -> {
                    int documento = leerEntero("Documento del cliente: ");

                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Nuevo apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Nuevo teléfono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();

                    System.out.print("Nueva dirección: ");
                    String direccion = sc.nextLine();

                    clienteDAO.actualizar(new Cliente(documento, nombre, apellido, telefono, email, direccion));
                }

                case 4 -> {
                    int documento = leerEntero("Documento del cliente a eliminar: ");
                    clienteDAO.eliminar(documento);
                }

                case 5 -> {
                    int documento = leerEntero("Documento del cliente: ");

                    Cliente cliente = clienteDAO.buscarPorId(documento);

                    if (cliente != null) {
                        System.out.println("\nCliente encontrado:");
                        System.out.println("Documento: " + cliente.getIdCliente());
                        System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
                        System.out.println("Teléfono: " + cliente.getTelefono());
                        System.out.println("Email: " + cliente.getEmail());
                        System.out.println("Dirección: " + cliente.getDireccion());
                    } else {
                        System.out.println("No se encontró el cliente.");
                    }
                }

                case 6 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    // MENÚ VENTAS

    private static void menuVentas() {

        int opcion;

        do {
            System.out.println("\n      VENTAS ");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Generar factura");
            System.out.println("4. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    int idCliente = leerEntero("Documento cliente: ");
                    int idProducto = leerEntero("ID producto: ");
                    int cantidad = leerEntero("Cantidad: ");

                    ventaDAO.registrarVenta(idCliente, idProducto, cantidad);
                }

                case 2 -> ventaDAO.listarVentas();

                case 3 -> {
                    int idVenta = leerEntero("ID venta: ");
                    ventaDAO.generarFactura(idVenta);
                }

                case 4 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    // MENÚ INVENTARIO

    private static void menuInventario() {

        int opcion;

        do {
            System.out.println("\n      INVENTARIO ");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Ver movimientos");
            System.out.println("4. Productos con poco stock");
            System.out.println("5. Ganancia potencial");
            System.out.println("6. Productos más vendidos");
            System.out.println("7. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    int idProducto = leerEntero("ID producto: ");
                    int cantidad = leerEntero("Cantidad entrada: ");

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    inventarioDAO.registrarEntrada(idProducto, cantidad, descripcion);
                }

                case 2 -> {
                    int idProducto = leerEntero("ID producto: ");
                    int cantidad = leerEntero("Cantidad salida: ");

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    inventarioDAO.registrarSalida(idProducto, cantidad, descripcion);
                }

                case 3 -> inventarioDAO.listarMovimientos();

                case 4 -> inventarioDAO.productosPocoStock();

                case 5 -> inventarioDAO.gananciasPotenciales();

                case 6 -> inventarioDAO.productosMasVendidos();

                case 7 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }

    // MÉTODOS AUXILIARES

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un valor válido.");
            }
        }
    }
}