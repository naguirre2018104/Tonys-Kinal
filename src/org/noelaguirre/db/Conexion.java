package org.noelaguirre.db;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conexion;
    private static Conexion instancia;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTonysKinal2018104?useSSL=false","root","root");
        }catch(ClassNotFoundException e){ // Por si la clase no existe.
            e.printStackTrace();
        }catch(InstantiationException e){ // Por si no existe la instancia.
            e.printStackTrace();
        }catch(IllegalAccessException e){ // Por si el usario o contraseña son incorrectos.
            e.printStackTrace();
        }catch(SQLException e){ // Por si no puede usar algo de MySQL.
            e.printStackTrace();
        }
    }
    
    public static Conexion getInstance(){
        if (instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
}
