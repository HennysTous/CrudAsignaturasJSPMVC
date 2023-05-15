/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ADMIN
 */
public class ConexionBDJPA {
    
    public static EntityManagerFactory getConexion(){
    return Persistence.createEntityManagerFactory("CrudAsignaturasJSPMVCPU");
            
    }
}
