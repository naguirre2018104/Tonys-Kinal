package org.noelaguirre.bean;

public class TipoPlato {
    
    private int codigoTipoPlato;
    private String descripcionTipoPlato;

    public TipoPlato() {
    }

    public TipoPlato(int codigoTipoPlato, String descripcionTipoPlato) {
        this.codigoTipoPlato = codigoTipoPlato;
        this.descripcionTipoPlato = descripcionTipoPlato;
    }

    public int getCodigoTipoPlato() {
        return codigoTipoPlato;
    }

    public void setCodigoTipoPlato(int codigoTipoPlato) {
        this.codigoTipoPlato = codigoTipoPlato;
    }

    public String getDescripcionTipoPlato() {
        return descripcionTipoPlato;
    }

    public void setDescripcionTipoPlato(String descripcionTipoPlato) {
        this.descripcionTipoPlato = descripcionTipoPlato;
    }
    
    public String toString(){
        return getCodigoTipoPlato() + " | " + getDescripcionTipoPlato();
    }
}
