package org.noelaguirre.bean;

import java.util.Date;

public class ServicioEmpleado {
    
    private int codigoServicioEmpleado;
    private int Servicios_codigoServicio;
    private int Empleados_codigoEmpleado;
    private Date fechaEvento;
    private String horaEvento;
    private String lugarEvento;

    public ServicioEmpleado() {
    }

    public ServicioEmpleado(int codigoServicioEmpleado, int Servicios_codigoServicio, int Empleados_codigoEmpleado, Date fechaEvento, String horaEvento, String lugarEvento) {
        this.codigoServicioEmpleado = codigoServicioEmpleado;
        this.Servicios_codigoServicio = Servicios_codigoServicio;
        this.Empleados_codigoEmpleado = Empleados_codigoEmpleado;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.lugarEvento = lugarEvento;
    }

    public int getCodigoServicioEmpleado() {
        return codigoServicioEmpleado;
    }

    public void setCodigoServicioEmpleado(int codigoServicioEmpleado) {
        this.codigoServicioEmpleado = codigoServicioEmpleado;
    }

    public int getServicios_codigoServicio() {
        return Servicios_codigoServicio;
    }

    public void setServicios_codigoServicio(int Servicios_codigoServicio) {
        this.Servicios_codigoServicio = Servicios_codigoServicio;
    }

    public int getEmpleados_codigoEmpleado() {
        return Empleados_codigoEmpleado;
    }

    public void setEmpleados_codigoEmpleado(int Empleados_codigoEmpleado) {
        this.Empleados_codigoEmpleado = Empleados_codigoEmpleado;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }
    
    
}
