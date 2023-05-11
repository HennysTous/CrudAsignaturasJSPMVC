/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.udec.devweb.actividad2.hennys_tous.controladores;

import co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao.UsuarioDAO;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "UsuarioControllador", urlPatterns = {"/UsuarioControllador"})
public class UsuarioControllador extends HttpServlet {

    private UsuarioDAO usuarioDao;
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            }
        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/Vistas/Usuario/index.jsp");
        } else if (action.equals("/cerrar_sesion")) {
            session.removeAttribute("usuario");
            response.sendRedirect(request.getContextPath() + "/iniciar_sesion.jsp");
        } else if (action.equals("/recordar_password")) {
            request.getRequestDispatcher("/recordar_password.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Vistas/Usuario/index.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        
        if (null == action) {
            response.sendRedirect(request.getContextPath() + "/Vistas/Usuario/index.jsp");
        } else switch (action) {
            case "login.jsp" -> {
                try {
                    String email = request.getParameter("txtemail");
                    String password = request.getParameter("password");
                    Usuario usuario = usuarioDao.buscarPorEmail(email);
                    
                    if (usuario != null && usuario.getPass().equals(password)) {
                        session.setAttribute("usuario", usuario);
                        response.sendRedirect(request.getContextPath() + "register.jsp");
                    } else {
                        session.removeAttribute("usuario");
                        request.setAttribute("mensaje", "Los datos ingresados no son correctos.");
                        request.getRequestDispatcher("recoverypass.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioControllador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "/recordar_password" -> {
                try {
                    String cedula = request.getParameter("cedula");
                    String respuesta = request.getParameter("respuesta");
                    Usuario usuario = usuarioDao.buscarPorCedula(cedula);
                    
                    if (usuario != null && usuario.getRespuesta().equals(respuesta)) {
                        request.setAttribute("password", usuario.getPass());
                        request.getRequestDispatcher("/mostrar_password.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "Los datos ingresados no son correctos.");
                        request.getRequestDispatcher("/recordar_password.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioControllador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "/cambiar_password" -> {
                try {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    String nuevaPassword = request.getParameter("nueva_password");
                    usuario.setPass(nuevaPassword);
                    usuarioDao.actualizarUsuario(usuario);
                    session.setAttribute("usuario", usuario);
                    //Aquí podrías enviar un correo electrónico con la nueva contraseña
                    response.sendRedirect(request.getContextPath() + "/Vistas/Usuario/index.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioControllador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            default -> response.sendRedirect(request.getContextPath() + "/Vistas/Usuario/index.jsp");
        }
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
    
