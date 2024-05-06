import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MovimientoDAO {
    // llamo los metodos del almacen, llamo al dao para hacer la conexion
    private static final String insert_movimiento = "insert into movimientos (tipo_movimiento, id_producto, cantidad, detalle, fecha, beneficiario, donador) values (?, ?, ?, ?, ?, ?,?)";
    private static final String producto_mas_stock = "select *, stock_producto, nombre_producto " +
            "from inventario " +
            "inner join productos on productos.id_producto = inventario.id_producto " +
            "order by stock_producto desc " +
            "limit 1;"; // usar un max con subconsulta, sino demora mucho en buscar

    private static final String producto_sin_stock = "select *, stock_producto, nombre_producto "
            +
            "from inventario " +
            "join productos  on productos.id_producto = inventario.id_producto " +
            "where inventario.stock_producto = 0";

    private static final String producto_sin_donaciones = "select p.*" +
            "from productos p " +
            "where p.id_producto not in (select distinct id_producto from movimientos where tipo_movimiento = 'entrada')";

    private static final String id_producto = "select count(*) as count from inventario where id_producto = ?";

    // uso las consultas que necesito para movimiento

    // necesito un metodo para guardar datos en la tabla de movimientos
    public void añadirMovimiento(Movimiento movimientoNuevo) throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(insert_movimiento);

        if (movimientoNuevo.getMovimiento().equalsIgnoreCase("entrada")) {
            pstmt.setString(1, "entrada");
        } else if (movimientoNuevo.getMovimiento().equalsIgnoreCase("salida")) {
            pstmt.setString(1, "salida");
        } else {

            System.out.println("Error: Tipo de movimiento inválido.");
            return;
        }
        pstmt.setInt(2, movimientoNuevo.getIdProducto());
        pstmt.setInt(3, movimientoNuevo.getCantidad());
        pstmt.setString(4, movimientoNuevo.getDetalle());
        pstmt.setDate(5, new java.sql.Date(movimientoNuevo.getFecha().getTime()));
        pstmt.setString(6, movimientoNuevo.getBeneficiario());
        pstmt.setString(7, movimientoNuevo.getDonador());
        System.out.println("Sentencia SQL para Inventario: " + pstmt.toString());

        pstmt.executeUpdate();

        pstmt.close();
        c.close();
    }

    // metodo obtener producto con mas stock(listo)

    public void obtenerProductoConMasStock() throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(producto_mas_stock);
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()) {
            System.out.println(
                    "\n Producto con más stock: " + rset.getString("nombre_producto")
                            + "\n Id del producto: " + rset.getInt("id_producto")
                            + "\n Unidades disponibles: " + rset.getInt("stock_producto"));
        }

        pstmt.close();
        c.close();

    }

    // productos sin stock (listo)

    public void obtenerProductosAgotados() throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(producto_sin_stock);
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()) {
            System.out.println("\n Producto sin stock: " + rset.getString("nombre_producto")
                    + "\n Id del producto: " + rset.getInt("id_producto")
                    + "\n Número de referencia del producto: " + rset.getString("referencia")
                    + "\n Unidades disponibles: " + rset.getInt("stock_producto"));
        }
        pstmt.close();
        c.close();

    }

    // metodo sin donaciones (entrada)(listo)
    public void obtenerProductosSinDonaciones() throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(producto_sin_donaciones);
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()) {
            System.out.println("\n Producto sin donaciones: " + rset.getString("nombre_producto")
                    + "\n Id del producto: " + rset.getInt("id_producto"));
        }
    }

    // si el producto existe
    public boolean verificarExistenciaProducto(int idProducto) {
        try {
            Connection c = DAO.openConnection();
            PreparedStatement pstmt = c.prepareStatement(id_producto);
            pstmt.setInt(1, idProducto);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    int count = rset.getInt("count");
                    return count > 0;
                }
            }
            pstmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia del producto: " + e.getMessage());
        }
        return false;
    }

}
