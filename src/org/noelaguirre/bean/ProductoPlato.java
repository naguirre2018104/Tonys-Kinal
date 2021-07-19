package org.noelaguirre.bean;

public class ProductoPlato {
    
    private int Productos_codigoProducto;
    private int Platos_codigoPlato;

    public ProductoPlato() {
    }

    public ProductoPlato(int Productos_codigoProducto, int Platos_codigoPlato) {
        this.Productos_codigoProducto = Productos_codigoProducto;
        this.Platos_codigoPlato = Platos_codigoPlato;
    }

    public int getProductos_codigoProducto() {
        return Productos_codigoProducto;
    }

    public void setProductos_codigoProducto(int Productos_codigoProducto) {
        this.Productos_codigoProducto = Productos_codigoProducto;
    }

    public int getPlatos_codigoPlato() {
        return Platos_codigoPlato;
    }

    public void setPlatos_codigoPlato(int Platos_codigoPlato) {
        this.Platos_codigoPlato = Platos_codigoPlato;
    }
}
