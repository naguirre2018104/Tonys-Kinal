package org.noelaguirre.bean;

public class ServicioPlato {
    
    private int Servicios_codigoServicio;
    private int Platos_codigoPlato;

    public ServicioPlato() {
    }

    public ServicioPlato(int Servicios_codigoServicio, int Platos_codigoPlato) {
        this.Servicios_codigoServicio = Servicios_codigoServicio;
        this.Platos_codigoPlato = Platos_codigoPlato;
    }

    public int getServicios_codigoServicio() {
        return Servicios_codigoServicio;
    }

    public void setServicios_codigoServicio(int Servicios_codigoServicio) {
        this.Servicios_codigoServicio = Servicios_codigoServicio;
    }

    public int getPlatos_codigoPlato() {
        return Platos_codigoPlato;
    }

    public void setPlatos_codigoPlato(int Platos_codigoPlato) {
        this.Platos_codigoPlato = Platos_codigoPlato;
    }
}
