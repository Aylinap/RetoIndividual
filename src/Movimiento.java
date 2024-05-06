import java.util.Date;

public class Movimiento {
    private int idMovimiento;
    private String movimiento; // tomar como string movimiento, sino ver como hacerlo funcionar con un enum
    private int idProducto;
    private int cantidad;
    private String detalle;
    private Date fecha;
    private String donador;
    private String beneficiario;

    public Movimiento(int idMovimiento, String movimiento, int idProducto, int cantidad, String detalle,
            Date fecha, String donador, String beneficiario) {
        this.idMovimiento = idMovimiento;
        this.movimiento = movimiento; // el enum se hacia distinto --> es cuando se inicializa
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.detalle = detalle;
        this.fecha = fecha;
        this.donador = donador;
        this.beneficiario = beneficiario;

    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDonador() {
        return donador;
    }

    public void setDonador(String donador) {
        this.donador = donador;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

}
