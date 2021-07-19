package org.noelaguirre.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.noelaguirre.controller.EmpleadoController;
import org.noelaguirre.controller.EmpresaController;
import org.noelaguirre.controller.MenuPrincipalController;
import org.noelaguirre.controller.PlatoController;
import org.noelaguirre.controller.PresupuestoController;
import org.noelaguirre.controller.ProductoController;
import org.noelaguirre.controller.ProductoPlatoController;
import org.noelaguirre.controller.ProgramadorController;
import org.noelaguirre.controller.ServicioController;
import org.noelaguirre.controller.ServicioEmpleadoController;
import org.noelaguirre.controller.ServicioPlatoController;
import org.noelaguirre.controller.TipoEmpleadoController;
import org.noelaguirre.controller.TipoPlatoController;

/**
 *
 * @author lesbi
 */
public class Principal extends Application {
    
    private Stage escenarioPrincipal;
    private Scene escena; 
    private final String PAQUETE_VISTA = "/org/noelaguirre/view/";
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Tony's Kinal App");
        escenarioPrincipal.getIcons().add(new Image("/org/noelaguirre/image/Icono Tony's Kinal.png"));
        //Parent root = FXMLLoader.load(getClass().getResource("/org/noelaguirre/view/MenuPrincipalView.fxml"));
        //Scene escena = new Scene(root);
        //escenarioPrincipal.setScene(escena);
        menuPrincipal();
        escenarioPrincipal.show();
    }
    
    public void menuPrincipal(){
        try{
            MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml",600,399);
            menuPrincipal.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProgramador(){
        try{
            ProgramadorController ventanaProgramador = (ProgramadorController)cambiarEscena("ProgramadorView.fxml",600,400);
            ventanaProgramador.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpresas(){
        try{
            EmpresaController ventanaEmpresas = (EmpresaController)cambiarEscena("EmpresasView.fxml",700,500);
            ventanaEmpresas.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPresupuesto(){
        try{
            PresupuestoController ventanaPresupuesto = (PresupuestoController)cambiarEscena("PresupuestoView.fxml",700,500);
            ventanaPresupuesto.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoEmpleado(){
        try{
            TipoEmpleadoController ventanaTipoEmpleado = (TipoEmpleadoController)cambiarEscena("TipoEmpleadoView.fxml",700,500);
            ventanaTipoEmpleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleados(){
        try{
            EmpleadoController ventanaEmpleado = (EmpleadoController)cambiarEscena("EmpleadosView.fxml",800,500);
            ventanaEmpleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaServicios(){
        try{
            ServicioController ventanaServicio = (ServicioController)cambiarEscena("ServiciosView.fxml",700,500);
            ventanaServicio.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProductos(){
        try{
            ProductoController ventanaProducto = (ProductoController)cambiarEscena("ProductosView.fxml",700,450);
            ventanaProducto.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoPlato(){
        try{
            TipoPlatoController ventanaTipoPlato = (TipoPlatoController)cambiarEscena("TipoPlatoView.fxml",700,500);
            ventanaTipoPlato.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPlato(){
        try{
            PlatoController ventanaPlato = (PlatoController)cambiarEscena("PlatosView.fxml",800,550);
            ventanaPlato.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaServicioEmpleado(){
        try{
            ServicioEmpleadoController ventanaServicioEmpleado = (ServicioEmpleadoController)cambiarEscena("ServicioshasEmpleadosView.fxml",800,550);
            ventanaServicioEmpleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaServicioPlato(){
        try{
            ServicioPlatoController ventanaServicioPlato = (ServicioPlatoController)cambiarEscena("ServicioshasPlatosView.fxml",600,400);
            ventanaServicioPlato.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProductoPlato(){
        try{
            ProductoPlatoController ventanaProductoPlato = (ProductoPlatoController)cambiarEscena("ProductoshasPlatosView.fxml",600,400);
            ventanaProductoPlato.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA + fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        
        return resultado;
    }
    
}