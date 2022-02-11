/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC-HOGAR
 */
public class Conexion {

    public Conexion() {
    }        
    
    public Connection connect() {
        //Inicializamos la Conexion
        Connection conn = null;
        try {
            //Pasamos el nombre de la base de datos a la cual nos vamos a conectar
            conn = DriverManager.getConnection("jdbc:sqlite:dbCaja.db");
        } catch (SQLException e) {
            //Mandamos un mensaje de error a la consola en caso de que aparezca uno
            System.out.println(e.getMessage());
        }
        //Por ultimo retornamos la Conexion
        return conn;
    }
    
}
