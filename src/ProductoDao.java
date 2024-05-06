import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;

public class ProductoDao {
    // producto dao usa la clase dao para hacer la conexion, crea una llamada a la
    // base de datos,
    // para usarla en el metodo del main del almacen
    //

    // si tengo la bbdd con id autoincrement no le pongo el id_producto pero voy a
    // ver que hago
    private static final String insert_producto = "Insert Into productos (referencia, nombre_producto, empresa_manufacturera, fechaCaducidad, mantenerRefrigerado, conserva, humedad_maxima_porcentaje, tipo_alimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String modificar_producto = "Update productos SET referencia=?, nombre_producto=?, empresa_manufacturera=?, fechaCaducidad=?, mantenerRefrigerado=?, conserva=?, humedad_maxima_porcentaje=?, tipo_alimento=? WHERE id_producto=?";
    private static final String eliminar_producto = "Delete from productos WHERE id_producto=?";
    private static final String id_producto = "Select id_producto from productos ORDER BY id_producto DESC LIMIT 1";
    private static final String insert_inventario = "Insert into inventario (cod_almacen, id_producto, numero_pasillo, cod_ubicacion, stock_producto) values (?,?,?,?,?)";
    private static final String cod_almacen = "Select cod_almacen from almacen";
    private static final String produtos_id = "SELECT id_producto FROM productos WHERE id_producto = ? ";
    // private static final String stock_id_producto = " SELECT stock_producto FROM
    // inventario WHERE id_producto = ?";

    // metodo añadir productos

    // metodo insertar un producto a la bbdd

    public void añadirProducto(Producto productoNuevo) throws SQLException {

        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(insert_producto);

        pstmt.setString(1, productoNuevo.getReferencia());
        pstmt.setString(2, productoNuevo.getNombre());
        pstmt.setString(3, productoNuevo.getEmpresaManufacturera());

        if (productoNuevo instanceof AlimentoPerecedero) {
            pstmt.setDate(4, new java.sql.Date(((AlimentoPerecedero) productoNuevo).getFechaCaducidad().getTime()));
            pstmt.setString(5, "si");
            pstmt.setString(6, "no");
            pstmt.setNull(7, Types.INTEGER);
            pstmt.setString(8, "perecedero");

        } else if (productoNuevo instanceof AlimentoSemiPerecedero) {
            pstmt.setDate(4, new java.sql.Date(((AlimentoSemiPerecedero) productoNuevo).getFechaCaducidad().getTime()));
            if (((AlimentoSemiPerecedero) productoNuevo).isMantenerRefrigerado()) {
                pstmt.setString(5, "si");
            } else {
                pstmt.setString(5, "no");
            }
            pstmt.setString(6, "si");
            pstmt.setInt(7, ((AlimentoSemiPerecedero) productoNuevo).getHumedad_maxima_porcentaje());
            pstmt.setString(8, "semiperecedero");
        } else {
            pstmt.setNull(4, Types.DATE);
            pstmt.setString(5, "no");
            pstmt.setString(6, (((AlimentoNoPerecedero) productoNuevo).isConserva()) ? "si" : "no");
            pstmt.setNull(7, Types.INTEGER);
            pstmt.setString(8, "noperecedero");
        }

        System.out.println("Sentencia SQL para productos: " + pstmt.toString());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();

        int id_producto = leerIDProducto();
        // int codAlmacen = leerCodAlmacen();

        c = DAO.openConnection();
        pstmt = c.prepareStatement(insert_inventario);

        pstmt.setInt(1, 1); // asi me tira el cod predeterminado
        pstmt.setInt(2, id_producto); // obtengo el id del producto que se ingreso antes
        pstmt.setInt(3, productoNuevo.getNumero_pasillo());
        pstmt.setInt(4, productoNuevo.getCod_ubicacion());
        pstmt.setInt(5, productoNuevo.getStock_producto());
        System.out.println("Sentencia SQL para Inventario: " + pstmt.toString());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();

    }

    // metodo para tomar el codigo del almacen de la bbdd y luego insertarla en el
    // inventario, solucion trucha

    public int leerCodAlmacen() throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(cod_almacen);
        ResultSet rset = pstmt.executeQuery();

        int codAlmacen = -1;
        if (rset.next()) {
            codAlmacen = rset.getInt("cod_almacen");
        }

        rset.close();
        pstmt.close();
        c.close();

        return codAlmacen;
    }

    // metodo para tomar el id desde la base de datos,

    public int leerIDProducto() throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(id_producto);
        ResultSet rset = pstmt.executeQuery();

        int idProducto = -1;

        if (rset.next()) {
            idProducto = rset.getInt("id_producto");
        }

        rset.close();
        pstmt.close();
        c.close();

        return idProducto;
    }

    // metodo modificar producto

    public void modificarProducto(int idProducto, Producto productoModificado)
            throws SQLException {

        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(modificar_producto);
        pstmt.setString(1, productoModificado.getReferencia());
        pstmt.setString(2, productoModificado.getNombre());
        pstmt.setString(3, productoModificado.getEmpresaManufacturera());
        if (productoModificado instanceof AlimentoPerecedero) {
            pstmt.setDate(4,
                    new java.sql.Date(((AlimentoPerecedero) productoModificado).getFechaCaducidad().getTime()));
            pstmt.setString(5, "si");
            pstmt.setString(6, "no");
            pstmt.setNull(7, Types.INTEGER);
            pstmt.setString(8, "perecedero");
        } else if (productoModificado instanceof AlimentoSemiPerecedero) {
            pstmt.setDate(4,
                    new java.sql.Date(((AlimentoSemiPerecedero) productoModificado).getFechaCaducidad().getTime()));
            if (((AlimentoSemiPerecedero) productoModificado).isMantenerRefrigerado()) {
                pstmt.setString(5, "si");
            } else {
                pstmt.setString(5, "no");
            }
            pstmt.setString(6, "si");
            pstmt.setInt(7, ((AlimentoSemiPerecedero) productoModificado).getHumedad_maxima_porcentaje());
            pstmt.setString(8, "semiperecedero");
        } else {
            pstmt.setNull(4, Types.DATE);
            pstmt.setString(5, "no");
            pstmt.setString(6, "si");
            pstmt.setNull(7, Types.INTEGER);
            pstmt.setString(8, "noperecedero");
        }

        pstmt.setInt(9, idProducto);

        pstmt.executeUpdate();

        pstmt.close();
        c.close();
    }

    // metodo eliminar producto
    public void eliminarProducto(int idProducto) throws SQLException {

        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(eliminar_producto);

        pstmt.setInt(1, idProducto);

        pstmt.executeUpdate();
        pstmt.close();
        c.close();
    }

    public void obtenerNombreYStockProducto(int idProducto) throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement("SELECT p.nombre_producto, i.stock_producto " +
                "FROM productos p " +
                "JOIN inventario i ON p.id_producto = i.id_producto " +
                "WHERE p.id_producto = ?");
        pstmt.setInt(1, idProducto);
        ResultSet rset = pstmt.executeQuery();

        if (rset.next()) {
            String nombreProducto = rset.getString("nombre_producto");
            int stockProducto = rset.getInt("stock_producto");
            System.out.println("Nombre del producto: " + nombreProducto);
            System.out.println("Stock disponible: " + stockProducto);
        } else {
            System.out.println("No se encontró ningún producto con el ID especificado.");
        }

        pstmt.close();
        c.close();
    }

    public int obtenerIDProducto(int idProducto) throws SQLException {
        Connection c = DAO.openConnection();
        PreparedStatement pstmt = c.prepareStatement(produtos_id);
        ResultSet rset = pstmt.executeQuery();
        int id_productos = 0;

        if (rset.next()) {
            id_productos = rset.getInt("id_producto");
        }

        rset.close();
        pstmt.close();
        c.close();

        return id_productos;
    }

}
