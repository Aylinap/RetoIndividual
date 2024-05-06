import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Almacen implements AlmacenInterfaz {
    private ProductoDao ProductoDao;
    private MovimientoDAO MovimientoDAO;

    public Almacen() {
        this.ProductoDao = new ProductoDao();
        this.MovimientoDAO = new MovimientoDAO();
    }

    @Override
    public void añadirProductos(List<Producto> productos) {
        // ProductoDao.añadirProductos(productos);
    }

    @Override
    public void añadirProducto(Producto productoNuevo) {
        try {
            ProductoDao.añadirProducto(productoNuevo);
            System.out.println("Producto añadido correctamente.");
        } catch (SQLException e) {

            System.out.println("Error al añadir el producto: " + e.getMessage());
        }
    }

    @Override
    public void modificarProducto(int idProducto, Producto productoModificado) {
        try {
            ProductoDao.modificarProducto(idProducto, productoModificado);
        } catch (SQLException e) {
            System.err.println("Error al modificar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override

    public void eliminarProducto(int idProducto) {
        try {
            ProductoDao.eliminarProducto(idProducto);
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @Override
    public void obtenerProductoConMasStock() {
        try {
            MovimientoDAO.obtenerProductoConMasStock();
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto con más stock: " + e.getMessage());
        }

    }

    @Override
    public void obtenerProductosAgotados() {
        try {
            MovimientoDAO.obtenerProductosAgotados();
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto con menos stock: " + e.getMessage());
        }
    }

    @Override
    public void obtenerProductosSinDonaciones() {
        try {
            MovimientoDAO.obtenerProductosSinDonaciones();
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto sin donaciones: " + e.getMessage());
        }
    }

    @Override
    public void añadirMovimiento(Movimiento movimientoNuevo) {
        try {
            MovimientoDAO.añadirMovimiento(movimientoNuevo);
            System.out.println("Movimiento agregado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar Movimiento nuevo: " + e.getMessage());
        }
    }

}
