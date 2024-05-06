public abstract class Producto {
    
    protected int idProducto;
    protected String referencia;
    protected String nombre;
    protected String empresaManufacturera;
    protected int codAlmacen;
    protected int numero_pasillo;
    protected int cod_ubicacion;
    protected int stock_producto;

    public Producto(int idProducto, String referencia, String nombre, String empresaManufacturera, int codAlmacen,
            int numero_pasillo, int cod_ubicacion, int stock_producto) {
        this.idProducto = idProducto;
        this.referencia = referencia;
        this.nombre = nombre;
        this.empresaManufacturera = empresaManufacturera;
        this.codAlmacen = codAlmacen;
        this.numero_pasillo = numero_pasillo;
        this.cod_ubicacion = cod_ubicacion;
        this.stock_producto = stock_producto;

    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(int codAlmacen) {
        this.codAlmacen = codAlmacen;
    }

    public int getNumero_pasillo() {
        return numero_pasillo;
    }

    public void setNumero_pasillo(int numero_pasillo) {
        this.numero_pasillo = numero_pasillo;
    }

    public int getCod_ubicacion() {
        return cod_ubicacion;
    }

    public void setCod_ubicacion(int cod_ubicacion) {
        this.cod_ubicacion = cod_ubicacion;
    }

    public int getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) {
        this.stock_producto = stock_producto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresaManufacturera() {
        return empresaManufacturera;
    }

    public void setEmpresaManufacturera(String empresaManufacturera) {
        this.empresaManufacturera = empresaManufacturera;
    }

    

}
