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
    String driver = "com.mysql.cj.jdbc.Driver";
    public Conexion() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/asignaturascrud", "root", "");
        } catch (Exception e) {
            System.err.println("Conexion Fallida, error" + e);
        }
        
    }
    public Connection getConnection(){
        return con;
    }
    
}
