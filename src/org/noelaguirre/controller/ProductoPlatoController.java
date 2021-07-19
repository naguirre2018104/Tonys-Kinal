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
import org.noelaguirre.bean.Producto;
import org.noelaguirre.bean.ProductoPlato;
import org.noelaguirre.db.Conexion;
import org.noelaguirre.system.Principal;

public class ProductoPlatoController implements Initializable{

    private Principal escenarioPrincipal;
    private ObservableList <Producto> listaProducto;
    private ObservableList <Plato> listaPlato;
    private ObservableList <ProductoPlato> listaProductoPlato;
    
    @FXML private ComboBox cmbCodigoProducto;
    @FXML private ComboBox cmbCodigoPlato;
    @FXML private TableView tblProductoPlato;
    @FXML private TableColumn colCodigoProducto;
    @FXML private TableColumn colCodigoPlato;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cmbCodigoProducto.setItems(getProductos());
        cmbCodigoPlato.setItems(getPlato());
    }
    
    public void cargarDatos(){
        tblProductoPlato.setItems(getProductoPlato());
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<ProductoPlato, Integer>("Productos_codigoProducto"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<ProductoPlato, Integer>("Platos_codigoPlato"));
    }
    
    public void seleccionarElemento(){
        cmbCodigoProducto.getSelectionModel().select(buscarProducto(((ProductoPlato)tblProductoPlato.getSelectionModel().getSelectedItem()).getProductos_codigoProducto()));
        cmbCodigoPlato.getSelectionModel().select(buscarPlato(((ProductoPlato)tblProductoPlato.getSelectionModel().getSelectedItem()).getPlatos_codigoPlato()));
    }
    
    public Producto buscarProducto(int codigoProducto){
        Producto resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarProducto(?)}");
            procedimiento.setInt(1,codigoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Producto(
                    registro.getInt("codigoProducto"),
                    registro.getString("nombreProducto"),
                    registro.getInt("cantidad"));
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
    
    public ObservableList<ProductoPlato> getProductoPlato(){
        ArrayList<ProductoPlato> lista = new ArrayList<ProductoPlato>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarProductos_has_Platos}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new ProductoPlato(
                    resultado.getInt("Productos_codigoProducto"),
                    resultado.getInt("Platos_codigoPlato")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProductoPlato = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Producto> getProductos(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarProductos}");
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                lista.add(new Producto(
                        registro.getInt("codigoProducto"),
                        registro.getString("nombreProducto"),
                        registro.getInt("cantidad")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProducto = FXCollections.observableList(lista);
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
