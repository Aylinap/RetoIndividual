import java.util.Date;

public class AlimentoSemiPerecedero extends Producto {
    private Date fechaCaducidad;
    private boolean mantenerRefrigerado;
    private int humedad_maxima_porcentaje;
    

    public AlimentoSemiPerecedero(int idProducto, String referencia, String nombre, String empresaManufacturera,
            int codAlmacen,
            int numero_pasillo, int cod_ubicacion, int stock_producto, Date fechaCaducidad, boolean mantenerRefrigerado,
            int humedad_maxima_porcentaje) {
        super(idProducto, referencia, nombre, empresaManufacturera, codAlmacen, numero_pasillo, cod_ubicacion,
                stock_producto);
        this.fechaCaducidad = fechaCaducidad;
        this.mantenerRefrigerado = mantenerRefrigerado;
        this.humedad_maxima_porcentaje = humedad_maxima_porcentaje;

    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getHumedad_maxima_porcentaje() {
        return humedad_maxima_porcentaje;
    }

    public void setHumedad_maxima_porcentaje(int humedad_maxima_porcentaje) {
        this.humedad_maxima_porcentaje = humedad_maxima_porcentaje;
    }

    public boolean isMantenerRefrigerado() {
        return mantenerRefrigerado;
    }

    public void setMantenerRefrigerado(boolean mantenerRefrigerado) {
        this.mantenerRefrigerado = mantenerRefrigerado;
    }

}
