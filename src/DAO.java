import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    // La dirección de la base de datos en el servidor
    // junto con el usuario de pleno acceso y su respectiva contraseña
    private static String url = "jdbc:mysql://localhost:3306/almacen_cruz_roja";
    private static String user = "root";
    private static String pass = "Sanm1919.";

    // La conexción con la BBDD
    public static Connection con = openConnection();

    // metodo abrir conexion
    public static Connection openConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     
     public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // getters/setters

    public static ProductoDao getProductoDao() {
        ProductoDao productoDao = new ProductoDao();
        return productoDao;
    }

    public static MovimientoDAO getMovimientoDAO() {
        MovimientoDAO movimientoDAO = new MovimientoDAO();
        return movimientoDAO;
    }

}
