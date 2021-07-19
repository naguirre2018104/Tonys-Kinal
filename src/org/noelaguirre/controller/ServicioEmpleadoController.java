package org.noelaguirre.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.noelaguirre.bean.Empleado;
import org.noelaguirre.bean.Servicio;
import org.noelaguirre.bean.ServicioEmpleado;
import org.noelaguirre.db.Conexion;
import org.noelaguirre.system.Principal;

public class ServicioEmpleadoController implements Initializable {

    private Principal escenarioPrincipal;
    private DatePicker fecha;
    private ObservableList <Servicio> listaServicio;
    private ObservableList <Empleado> listaEmpleado;
    private ObservableList <ServicioEmpleado> listaServicioEmpleado;
    private enum operaciones{NUEVO,GUARDAR,ACTUALIZAR,NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    
    @FXML private TextField txtCodigoServicioEmpleado;
    @FXML private TextField txtHoraEvento;
    @FXML private TextField txtLugarEvento;
    @FXML private ComboBox cmbServicio;
    @FXML private ComboBox cmbEmpleado;
    @FXML private GridPane grpFechaEvento;
    @FXML private TableView tblServicioEmpleado;
    @FXML private TableColumn colCodigoServicioEmpleado;
    @FXML private TableColumn colFechaEvento;
    @FXML private TableColumn colHoraEvento;
    @FXML private TableColumn colLugarEvento;
    @FXML private TableColumn colCodigoServicio;
    @FXML private TableColumn colCodigoEmpleado;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        calendario();
        cmbServicio.setItems(getServicio());
        cmbEmpleado.setItems(getEmpleado());
    }
    
    public void cargarDatos(){
        tblServicioEmpleado.setItems(getServicioEmpleado());
        colCodigoServicioEmpleado.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, Integer>("codigoServicioEmpleado"));
        colFechaEvento.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, Date>("fechaEvento"));
        colHoraEvento.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, String>("horaEvento"));
        colLugarEvento.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, String>("lugarEvento"));
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, Integer>("Servicios_codigoServicio"));
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<ServicioEmpleado, Integer>("Empleados_codigoEmpleado"));
    }
    
    public void calendario(){
        fecha = new DatePicker(Locale.ENGLISH);
        fecha.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        fecha.getCalendarView().todayButtonTextProperty().set("Today");
        fecha.getCalendarView().setShowWeeks(false);
        fecha.getStylesheets().add("org/noelaguirre/resource/DatePicker.css");
        grpFechaEvento.add(fecha,1,1); // Objeto,Columna,Fila.
    }
    
    public void seleccionarElemento(){
        txtCodigoServicioEmpleado.setText(String.valueOf(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getCodigoServicioEmpleado()));
        fecha.selectedDateProperty().set(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getFechaEvento());
        txtHoraEvento.setText(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getHoraEvento());
        txtLugarEvento.setText(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getLugarEvento());
        cmbServicio.getSelectionModel().select(buscarServicio(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
        cmbEmpleado.getSelectionModel().select(buscarEmpleado(((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getEmpleados_codigoEmpleado()));
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
    
    public Empleado buscarEmpleado(int codigoEmpleado){
        Empleado resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarEmpleado(?)}");
            procedimiento.setInt(1,codigoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Empleado(
                    registro.getInt("codigoEmpleado"),
                    registro.getString("numeroEmpleado"),
                    registro.getString("apellidosEmpleado"),
                    registro.getString("nombresEmpleado"),
                    registro.getString("direccionEmpleado"),
                    registro.getString("telefonoContacto"),
                    registro.getString("gradoCocinero"),
                    registro.getInt("codigoTipoEmpleado"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList <ServicioEmpleado> getServicioEmpleado(){
         ArrayList<ServicioEmpleado> lista = new ArrayList<ServicioEmpleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarServicios_has_Empleados}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new ServicioEmpleado(
                    resultado.getInt("codigoServicioEmpleado"),
                    resultado.getInt("Servicios_codigoServicio"),
                    resultado.getInt("Empleados_codigoEmpleado"),
                    resultado.getDate("fechaEvento"),
                    resultado.getString("horaEvento"),
                    resultado.getString("lugarEvento")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicioEmpleado = FXCollections.observableArrayList(lista);
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
     
    public ObservableList<Empleado> getEmpleado(){
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarEmpleados}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new Empleado(
                resultado.getInt("codigoEmpleado"),
                resultado.getString("numeroEmpleado"),
                resultado.getString("apellidosEmpleado"),
                resultado.getString("nombresEmpleado"),
                resultado.getString("direccionEmpleado"),
                resultado.getString("telefonoContacto"),
                resultado.getString("gradoCocinero"),
                resultado.getInt("codigoTipoEmpleado")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaEmpleado = FXCollections.observableList(lista);
    }
     
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                limpiarControles();
                activarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                tipoDeOperacion = operaciones.GUARDAR;
            break;
            case GUARDAR:
                guardar();
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
            break;
        }
    }
     
    public void guardar(){
        ServicioEmpleado registro = new ServicioEmpleado();
        registro.setFechaEvento(fecha.getSelectedDate());
        registro.setHoraEvento(txtHoraEvento.getText());
        registro.setLugarEvento(txtLugarEvento.getText());
        registro.setServicios_codigoServicio(((Servicio)cmbServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        registro.setEmpleados_codigoEmpleado(((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getCodigoEmpleado());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarServicios_has_Empleados(?,?,?,?,?)}");
            procedimiento.setInt(1,registro.getServicios_codigoServicio());
            procedimiento.setInt(2,registro.getEmpleados_codigoEmpleado());
            procedimiento.setDate(3,new java.sql.Date(registro.getFechaEvento().getTime()));
            procedimiento.setString(4,registro.getHoraEvento());
            procedimiento.setString(5,registro.getLugarEvento());
            procedimiento.executeQuery();
            listaServicioEmpleado.add(registro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                desactivarControles();
                limpiarControles();
            break;
            default:
                if(tblServicioEmpleado.getSelectionModel().getSelectedItem() != null){
                    int resultado = JOptionPane.showConfirmDialog(null,"¿Está seguro que desea eliminar el registro?","Eliminar Servicio Empleado",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(resultado == JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarServicios_has_Empleados(?)}");
                            procedimiento.setInt(1,((ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem()).getCodigoServicioEmpleado());
                            procedimiento.executeQuery();
                            listaServicioEmpleado.remove(tblServicioEmpleado.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debe seleccionar un elemento");
                }
            break;
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if(tblServicioEmpleado.getSelectionModel().getSelectedItem() != null){
                    activarControles();
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
            break;
            case ACTUALIZAR:
                actualizar();
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                cargarDatos();
                limpiarControles();
                desactivarControles();
                tipoDeOperacion = operaciones.NINGUNO;
            break;
        }
    }
    
    public void actualizar(){
        ServicioEmpleado registro = (ServicioEmpleado)tblServicioEmpleado.getSelectionModel().getSelectedItem();
        registro.setFechaEvento(fecha.getSelectedDate());
        registro.setHoraEvento(txtHoraEvento.getText());
        registro.setLugarEvento(txtLugarEvento.getText());
        registro.setServicios_codigoServicio(((Servicio)cmbServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        registro.setEmpleados_codigoEmpleado(((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getCodigoEmpleado());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarServicios_has_Empleados(?,?,?,?,?,?)}");
            procedimiento.setInt(1,registro.getCodigoServicioEmpleado());
            procedimiento.setInt(2,registro.getServicios_codigoServicio());
            procedimiento.setInt(3,registro.getEmpleados_codigoEmpleado());
            procedimiento.setDate(4,new java.sql.Date(registro.getFechaEvento().getTime()));
            procedimiento.setString(5,registro.getHoraEvento());
            procedimiento.setString(6,registro.getLugarEvento());
            procedimiento.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void reporte(){
        switch(tipoDeOperacion){
            case ACTUALIZAR:
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                tipoDeOperacion = operaciones.NINGUNO;
                desactivarControles();
                limpiarControles();
            break;
        }
    }
     
    public void activarControles(){
        txtCodigoServicioEmpleado.setEditable(false);
        fecha.setDisable(false);
        txtHoraEvento.setEditable(true);
        txtLugarEvento.setEditable(true);
        cmbServicio.setDisable(false);
        cmbEmpleado.setDisable(false);
    } 
    
    public void desactivarControles(){
        txtCodigoServicioEmpleado.setEditable(false);
        fecha.setDisable(true);
        txtHoraEvento.setEditable(false);
        txtLugarEvento.setEditable(false);
        cmbServicio.setDisable(true);
        cmbEmpleado.setDisable(true);
    }
    
    public void limpiarControles(){
        txtCodigoServicioEmpleado.setText("");
        txtHoraEvento.setText("");
        txtLugarEvento.setText("");
        cmbServicio.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
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
