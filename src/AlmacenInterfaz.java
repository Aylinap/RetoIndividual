
/**
 * AlmacenInterfaz
 */

import java.sql.SQLException;
import java.util.List;

public interface AlmacenInterfaz {
    void añadirProductos(List<Producto> productos) throws SQLException;

    void añadirProducto(Producto productoNuevo) throws SQLException;

    void modificarProducto(int idProducto, Producto productoModificado) throws SQLException;

    void eliminarProducto(int idProducto) throws SQLException;

    void obtenerProductoConMasStock() throws SQLException;

    void obtenerProductosAgotados() throws SQLException;

    void obtenerProductosSinDonaciones() throws SQLException;

    void añadirMovimiento(Movimiento movimientoNuevo) throws SQLException;
}
