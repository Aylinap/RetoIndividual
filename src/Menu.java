import java.util.Scanner;
import java.sql.SQLException;
import java.util.Date;

public class Menu {
    private Almacen almacen;
    private Scanner scanner;
    private ProductoDao ProductoDao;
    private MovimientoDAO MovimientoDAO;

    public Menu() {
        this.almacen = new Almacen();
        this.scanner = new Scanner(System.in);
        this.ProductoDao = new ProductoDao();
        this.MovimientoDAO = new MovimientoDAO();

    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Almacén ---");
            System.out.println("1. Añadir producto");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Obtener producto con más stock");
            System.out.println("5. Obtener productos agotados");
            System.out.println("6. Obtener productos sin donaciones");
            System.out.println("7. Ingresar un tipo de movimiento");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    añadirProducto();
                    break;
                case 2:
                    modificarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    obtenerProductoConMasStock();
                    break;
                case 5:
                    obtenerProductosAgotados();
                    break;
                case 6:
                    obtenerProductosSinDonaciones();
                    break;
                case 7:
                    añadirMovimiento();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);
    }

    // menu añadir producto --- añadir inventario el producto
    private void añadirProducto() {
        System.out.println("\n--- Añadir Producto ---");
        System.out.print("Ingrese la referencia del producto: ");
        String referencia = scanner.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la empresa manufacturera: ");
        String empresaManufacturera = scanner.nextLine();
        System.out.print("Ingrese el tipo de producto (perecedero, semiperecedero, noperecedero): ");
        String tipoProducto = scanner.nextLine();

        Producto productoNuevo;
        switch (tipoProducto.toLowerCase()) {
            case "perecedero":
                productoNuevo = crearAlimentoPerecedero();
                break;
            case "semiperecedero":
                productoNuevo = crearAlimentoSemiPerecedero();
                break;
            case "noperecedero":
                productoNuevo = crearAlimentoNoPerecedero();
                break;
            default:
                System.out.println("Tipo de producto inválido.");
                return;
        }

        System.out.print("Ingrese el número de pasillo del producto: ");
        int numeroPasillo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el código de ubicación del producto: ");
        int codUbicacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el stock del producto: ");
        int stockProducto = scanner.nextInt();
        scanner.nextLine();

        productoNuevo.setReferencia(referencia);
        productoNuevo.setNombre(nombre);
        productoNuevo.setEmpresaManufacturera(empresaManufacturera);
        productoNuevo.setNumero_pasillo(numeroPasillo);
        productoNuevo.setCod_ubicacion(codUbicacion);
        productoNuevo.setStock_producto(stockProducto);

        almacen.añadirProducto(productoNuevo);

    }

    // si elige perecedero:
    private AlimentoPerecedero crearAlimentoPerecedero() {
        System.out.print("Ingrese la fecha de caducidad (yyyy-MM-dd): ");
        String fechaCaducidadStr = scanner.nextLine();
        System.out.print("¿Debe mantenerse refrigerado? (si/no): ");
        String mantenerRefrigeradoStr = scanner.nextLine();
        boolean mantenerRefrigerado = mantenerRefrigeradoStr.equalsIgnoreCase("si");

        java.sql.Date fechaCaducidad = java.sql.Date.valueOf(fechaCaducidadStr);

        return new AlimentoPerecedero(0, "", "", "", 0, 0, 0, 0, fechaCaducidad, mantenerRefrigerado);
    }

    // si elige metodo semiperecedero
    private AlimentoSemiPerecedero crearAlimentoSemiPerecedero() {
        System.out.print("Ingrese la fecha de caducidad (yyyy-MM-dd): ");
        String fechaCaducidadStr = scanner.nextLine();
        System.out.print("¿Debe mantenerse refrigerado? (si/no): ");
        String mantenerRefrigeradoStr = scanner.nextLine();
        boolean mantenerRefrigerado = mantenerRefrigeradoStr.equalsIgnoreCase("si");
        System.out.print("Ingrese el porcentaje de humedad máximo (0-100): ");
        int humedadMaximaPorcentaje = scanner.nextInt();

        scanner.nextLine();

        java.sql.Date fechaCaducidad = java.sql.Date.valueOf(fechaCaducidadStr);

        return new AlimentoSemiPerecedero(0, "", "", "", 0, 0, 0, 0, fechaCaducidad, mantenerRefrigerado,
                humedadMaximaPorcentaje);
    }

    // si elige metodo no perecedero
    private AlimentoNoPerecedero crearAlimentoNoPerecedero() {
        System.out.print("¿Alimento en conserva? (si/no): ");
        String conservaInput = scanner.next();
        boolean conserva = conservaInput.equalsIgnoreCase("si");

        return new AlimentoNoPerecedero(0, "", "", "", 0, 0, 0, 0, conserva);
    }

    private void modificarProducto() {
        System.out.println("\n--- Modificar Producto ---");
        System.out.print("Ingrese el ID del producto a modificar (en formato PNNNNN): ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();
        if (!MovimientoDAO.verificarExistenciaProducto(idProducto)) {
            System.out.println("El producto con ID " + idProducto + " no existe.");
            return;
        }

        Producto productoModificado = productoModificado();

        try {

            ProductoDao.modificarProducto(idProducto, productoModificado);

            System.out.println("¡Producto modificado exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al modificar el producto: " + e.getMessage());
        }
    }

    private Producto productoModificado() {
        System.out.println("Ingrese la nueva referencia del producto: ");
        String referencia = scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nueva empresa manufacturera del producto: ");
        String empresaManufacturera = scanner.nextLine();

        System.out.println("Ingrese el tipo de producto (perecedero, semiperecedero, noperecedero): ");
        String tipoProducto = scanner.nextLine();

        Producto productoModificado;
        switch (tipoProducto.toLowerCase()) {
            case "perecedero":
                productoModificado = crearAlimentoPerecedero();
                break;
            case "semiperecedero":
                productoModificado = crearAlimentoSemiPerecedero();
                break;
            case "noperecedero":
                productoModificado = crearAlimentoNoPerecedero();
                break;
            default:
                System.out.println("Tipo de producto inválido.");
                return null;
        }

        productoModificado.setReferencia(referencia);
        productoModificado.setNombre(nombre);
        productoModificado.setEmpresaManufacturera(empresaManufacturera);

        return productoModificado;
    }

    // eliminar producto por id producto
    private void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int idProducto = scanner.nextInt();
        almacen.eliminarProducto(idProducto);
    }

    // obtener producto con más stock
    private void obtenerProductoConMasStock() {
        almacen.obtenerProductoConMasStock();
    }

    // obtener productos agostados o con stock = 0
    private void obtenerProductosAgotados() {
        almacen.obtenerProductosAgotados();
    }

    // obtener productos que jamas tengan "salida"
    private void obtenerProductosSinDonaciones() {
        almacen.obtenerProductosSinDonaciones();
    }

    public void añadirMovimiento() {
        System.out.println("\n--- Añadir Movimiento ---");

        System.out.println("Ingrese el tipo de Movimiento (entrada-salida): ");
        String tipoMovimiento = scanner.nextLine().toLowerCase();

        if (!tipoMovimiento.equalsIgnoreCase("entrada") && !tipoMovimiento.equalsIgnoreCase("salida")) {
            System.out.println("Error: Tipo de movimiento inválido. Debe ser 'entrada' o 'salida'.");
            return;
        }

        int idProducto;

        do {
            System.out.println("Ingrese el Id del producto: ");
            idProducto = scanner.nextInt();
            scanner.nextLine();

            if (!MovimientoDAO.verificarExistenciaProducto(idProducto)) {
                System.out.println("Error: El ID del producto ingresado no existe en la base de datos.");
            }
        } while (!MovimientoDAO.verificarExistenciaProducto(idProducto));

        try {
            ProductoDao productoDao = new ProductoDao();
            productoDao.obtenerNombreYStockProducto(idProducto);
        } catch (SQLException e) {
            System.out.println("Error al obtener información del producto: " + e.getMessage());
        }

        System.out.println("Ingrese la cantidad de producto a mover: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese una pequeña descripción: ");
        String detalle = scanner.nextLine();

        System.out.println("Ingrese la fecha (yyyy-MM-dd): ");
        String fecha = scanner.nextLine();

        System.out.println("Ingrese el Beneficiario: ");
        String beneficiario = scanner.nextLine();

        System.out.println("Ingresa el donador: ");
        String donador = scanner.nextLine();

        java.sql.Date fechaf = java.sql.Date.valueOf(fecha);

        Movimiento movimientoNuevo = new Movimiento(0, tipoMovimiento, idProducto, cantidad, detalle, fechaf,
                donador,
                beneficiario);

        almacen.añadirMovimiento(movimientoNuevo);
    }
}
