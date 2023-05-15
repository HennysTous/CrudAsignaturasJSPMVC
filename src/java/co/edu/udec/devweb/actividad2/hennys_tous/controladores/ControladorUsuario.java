/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.udec.devweb.actividad2.hennys_tous.controladores;

import Config.ConexionBDJPA;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao.UsuariosJpaController;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/usuario"})
public class ControladorUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            recuperarAccion(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void recuperarAccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Ingresar" -> {
                
                iniciarSesion(request, response);
                
            }
            default ->
                response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
    
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("txtemail");
        String pass = request.getParameter("txtpass");
        
        EntityManagerFactory conBD
                = ConexionBDJPA.getConexion();
        UsuariosJpaController crudUsuario = new UsuariosJpaController(conBD);
        Usuario usuario = crudUsuario.findUsuariosByUserLog(email);
        if (usuario != null || !usuario.getPass().equals(pass)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?msj=Usuario no encontrado");
            return;
        }
        request.getSession().setAttribute("usuario", usuario);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
}
