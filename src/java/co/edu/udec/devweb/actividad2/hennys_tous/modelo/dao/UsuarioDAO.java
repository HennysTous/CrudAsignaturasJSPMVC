/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao;

import Config.Conexion;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.entidades.Usuario;
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class UsuarioDAO {

    /* método void agregar(EntidadXyz e) throws Exception
       método EntidadXyx buscarPorId( Integer Id) throws Exception
       método void editar(EntidadXyz e) throws Exception
       método void eliminarPorId(Integer id) throws Exception
       método List<EntidadXyz> listarTodo() 
       método List<EntidadXyz> buscarPorPropiedadX( Object propiedadX) 
       método int contar()
    
       metodo iniciarSesion(email, pass)
       metodo cerrarSesion()
       metodo recordar_pass()
       metodo cambiar_pass()
     */
    Conexion con = new Conexion();
    Connection conexion = con.Conexion();

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO usuarios (cc, pass, nombre, apellido, genero, email, respuesta) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, usuario.getCc());
            ps.setString(2, usuario.getPass());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getGenero());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getRespuesta());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios "
                + "WHERE email='?'");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setCc(rs.getInt("cc"));
            usuario.setPass(rs.getString("pass"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setGenero(rs.getString("genero"));
            usuario.setEmail(rs.getString("email"));
            usuario.setRespuesta(rs.getString("respuesta"));

        }
        return usuario;
    }

    public boolean iniciarSesion(String email, String pass, HttpSession sesion) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario != null && usuario.getPass().equals(pass)) {
            sesion.setAttribute("usuario", usuario);
            return true;
        } else {
            sesion.removeAttribute("usuario");
            return false;
        }
    }

    public void cerrarSesion(HttpSession sesion) throws SQLException {
        sesion.removeAttribute("usuario");
    }

    public String recordarPassword(String email, String respuesta) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario != null && usuario.getRespuesta().equals(respuesta)) {
            return usuario.getPass();
        } else {
            return null;
        }

    }
}
