package org.noelaguirre.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.noelaguirre.bean.Plato;
import org.noelaguirre.bean.Servicio;
import org.noelaguirre.bean.ServicioPlato;
import org.noelaguirre.db.Conexion;
import org.noelaguirre.system.Principal;

public class ServicioPlatoController implements Initializable{

    private Principal escenarioPrincipal;
    private ObservableList <Servicio> listaServicio;
    private ObservableList <Plato> listaPlato;
    private ObservableList <ServicioPlato> listaServicioPlato;
    
    @FXML private ComboBox cmbCodigoServicio;
    @FXML private ComboBox cmbCodigoPlato;
    @FXML private TableView tblServicioPlato;
    @FXML private TableColumn colCodigoServicio;
    @FXML private TableColumn colCodigoPlato;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cmbCodigoServicio.setItems(getServicio());
        cmbCodigoPlato.setItems(getPlato());
    }
    
    public void cargarDatos(){
        tblServicioPlato.setItems(getServicioPlato());
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<ServicioPlato, Integer>("Servicios_codigoServicio"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<ServicioPlato, Integer>("Platos_codigoPlato"));
    }
    
    public void seleccionarElemento(){
        cmbCodigoServicio.getSelectionModel().select(buscarServicio(((ServicioPlato)tblServicioPlato.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
        cmbCodigoPlato.getSelectionModel().select(buscarPlato(((ServicioPlato)tblServicioPlato.getSelectionModel().getSelectedItem()).getPlatos_codigoPlato()));
    }
    
    public Servicio buscarServicio(int codigoServicio){
        Servicio resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarServicio(?)}");
            procedimiento.setInt(1,codigoServicio);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Servicio(
                    registro.getInt("codigoServicio"),
                    registro.getDate("fechaServicio"),
                    registro.getString("tipoServicio"),
                    registro.getString("horaServicio"),
                    registro.getString("lugarServicio"),
                    registro.getString("telefonoContacto"),
                    registro.getInt("codigoEmpresa"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public Plato buscarPlato(int codigoPlato){
        Plato resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarPlato(?)}");
            procedimiento.setInt(1,codigoPlato);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Plato(
                    registro.getInt("codigoPlato"),
                    registro.getInt("cantidad"),
                    registro.getString("nombrePlato"),
                    registro.getString("descripcionPlato"),
                    registro.getDouble("precioPlato"),
                    registro.getInt("codigoTipoPlato"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList <ServicioPlato> getServicioPlato(){
        ArrayList<ServicioPlato> lista = new ArrayList<ServicioPlato>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarServicios_has_Platos}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new ServicioPlato(
                    resultado.getInt("Servicios_codigoServicio"),
                    resultado.getInt("Platos_codigoPlato")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicioPlato = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList <Servicio> getServicio(){
        ArrayList<Servicio> lista = new ArrayList<Servicio>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarServicios}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Servicio(
                    resultado.getInt("codigoServicio"),
                    resultado.getDate("fechaServicio"),
                    resultado.getString("tipoServicio"),
                    resultado.getString("horaServicio"),
                    resultado.getString("lugarServicio"),
                    resultado.getString("telefonoContacto"),
                    resultado.getInt("codigoEmpresa")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicio = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Plato> getPlato(){
        ArrayList<Plato> lista = new ArrayList<Plato>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarPlatos}");
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                lista.add(new Plato(
                registro.getInt("codigoPlato"),
                registro.getInt("cantidad"),
                registro.getString("nombrePlato"),
                registro.getString("descripcionPlato"),
                registro.getDouble("precioPlato"),
                registro.getInt("codigoTipoPlato")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaPlato = FXCollections.observableList(lista);
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
}
