public class AlimentoNoPerecedero extends Producto {
    private boolean conserva;

    public AlimentoNoPerecedero(int idProducto, String referencia, String nombre, String empresaManufacturera,
            int codAlmacen, int numero_pasillo, int cod_ubicacion, int stock_producto, boolean conserva) {
        super(idProducto, referencia, nombre, empresaManufacturera, codAlmacen, numero_pasillo, cod_ubicacion,
                stock_producto);
        this.conserva = conserva;

    }

    public boolean isConserva() {
        return conserva;
    }

    public void setConserva(boolean conserva) {
        this.conserva = conserva;
    }
}
