/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Config;

import co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao.UsuariosJpaController;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.entidades.Usuario;

/**
 *
 * @author ADMIN
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuariosJpaController crud = new UsuariosJpaController(ConexionBDJPA.getConexion());
        
        Usuario usuario = crud.findUsuarios(4444444);
        
        System.out.println(usuario);
        
    }
    
}
