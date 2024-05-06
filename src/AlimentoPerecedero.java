import java.util.Date;

public class AlimentoPerecedero extends Producto {
    private Date fechaCaducidad;
    private boolean mantenerRefrigerado;

    public AlimentoPerecedero(int idProducto, String referencia, String nombre, String empresaManufacturera,
            int codAlmacen, int numero_pasillo, int cod_ubicacion, int stock_producto, Date fechaCaducidad,
            boolean mantenerRefrigerado) {
        super(idProducto, referencia, nombre, empresaManufacturera, codAlmacen, numero_pasillo, cod_ubicacion,
                stock_producto);
        this.fechaCaducidad = fechaCaducidad;
        this.mantenerRefrigerado = mantenerRefrigerado;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean isMantenerRefrigerado() {
        return mantenerRefrigerado;
    }

    public void setMantenerRefrigerado(boolean mantenerRefrigerado) {
        this.mantenerRefrigerado = mantenerRefrigerado;
    }

}