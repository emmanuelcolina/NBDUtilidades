/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.actdatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ecolina
 */
public class ConnectActDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        String conectar = "jdbc:sqlserver://bodbdfhack01cal:1433;databaseName=BD_ACTRIC;";
        Connection conector = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String codigoEDICT;
        
        codigoEDICT = String.format("%09d",488506);
        
        //System.out.println(codigoEDICT);
        try {
                // Establish the connection.
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conector = DriverManager.getConnection(conectar, "calidad", "Bod12345");
                //System.out.println("Conexion Exitosa");
                // Create and execute an SQL statement that returns some data.
                String SQL = "SELECT CodCliente, DesEstatus FROM TblEstatusAct WHERE CodCliente = '"+codigoEDICT+"'";
                stmt = conector.createStatement();
                rs = stmt.executeQuery(SQL);

                // Iterate through the data in the result set and display it.
                if(rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                }else {
                    System.out.println("No tiene registros en BD");
                }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            System.out.println("No ha podido Conectar");
            e.printStackTrace();
        }
    }
    
}
