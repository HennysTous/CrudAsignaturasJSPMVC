/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;


import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class Conexion {

    Connection con;
    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jbdc:mysql://localhost:3308/asignaturascrud", "root", "1234");
        } catch (Exception e) {
            System.err.println("Conexion Fallida, error" + e);
        }
        return con;
    }
    
    
}
