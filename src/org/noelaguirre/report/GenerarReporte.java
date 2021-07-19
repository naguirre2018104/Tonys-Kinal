package org.noelaguirre.report;

import java.io.InputStream;
import java.util.Map;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import org.noelaguirre.db.Conexion;

public class GenerarReporte {
    
    public static void mostrarReporte(String nombreReporte, String titulo, Map parametros){
        InputStream reporte = GenerarReporte.class.getResourceAsStream(nombreReporte); // Es para saber cuál reporte levantar.
        try{
            JasperReport reporteMaestro = (JasperReport)JRLoader.loadObject(reporte);
                                                                                    // Reporte Maestro, Parámetros del HashMap, Nuestra conexión.
            JasperPrint reporteImpreso = JasperFillManager.fillReport(reporteMaestro,parametros,Conexion.getInstance().getConexion());
            JasperViewer visor = new JasperViewer(reporteImpreso, false);
            visor.setTitle(titulo);
            visor.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
