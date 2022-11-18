/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.actdatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bod.actdatos.bean.BeanActDatos;

/**
 *
 * @author ecolina
 */
public class procesoConsultaActDatos {
    
    public BeanActDatos consultaEstatusCliente(String urlDB, String usuarioDB, String passDB, String coreID, String driver){
        
        
        String conectar;
        Connection conector = null;
        Statement stmt = null;
        ResultSet rs = null;
        String consulta;
        
        String codigoEDICT;
        
        conectar = urlDB;
        codigoEDICT = String.format("%09d", Integer.parseInt(coreID));
        
        final BeanActDatos beanRespuesta = new BeanActDatos();
        try {
                //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //conector = DriverManager.getConnection(conectar, "administrador", "Bod12345");
                
                conector = getConnection(driver, conectar, usuarioDB, passDB);
                String SQL = "SELECT CodCliente, DesEstatus FROM TblEstatusAct WHERE CodCliente = '"+codigoEDICT+"'";
                stmt = conector.createStatement();
                rs = stmt.executeQuery(SQL);

                if(rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                    beanRespuesta.setConsultaActDatos(rs.getString(2));
                }else {
                    //System.out.println("No tiene registros en BD");
                    beanRespuesta.setConsultaActDatos("0");
                }
        }
        catch (Exception e) {
            //System.out.println("No ha podido Conectar");
            e.printStackTrace();
            beanRespuesta.setConsultaActDatos("-1");
        }
        
        try {
            rs.close();
            stmt.close();
            conector.close();
        } catch (SQLException ex) {
            Logger.getLogger(procesoConsultaActDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beanRespuesta;
    }
    
    public Connection getConnection(String driver, String conectar, String user, String pass) throws Exception {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(conectar, user, pass);        
        } catch (NullPointerException npe){
            System.out.println("Ocurrio un Error inesperado " + npe.getMessage());
        }

        return conn;
    }
    
}
