/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.UsuarioEJB;
import entidades_POJO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jluis
 */
@WebServlet(name = "insertarUsuario", urlPatterns = {"/insertarUsuario"})
public class insertarUsuario extends HttpServlet {
@EJB
 UsuarioEJB usuEJB;
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
            out.println("<!DOCTYPE html>");
            out.println("<html class='main'>");
            out.println("<head>");
            out.println("<title>Servlet IniciarSesion</title>");       
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/></link>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            String nombre=request.getParameter("nombre");
            String apellidos=request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String password = request.getParameter("password");
            Usuario usu = new Usuario(dni);
            if(usuEJB.existeUsuario(usu)){
             out.println("<form action=\"index.jsp\" method=\"POST\">"
                            + "<h1>Usuario ya existe. Si no recuerda la contraseña pongase en contacto: att@gym.com</h1> <br><br>"                     
                            + "<input type=\"submit\" name=\"Volver\" value=\"Volver\" />"
                            + "</form>");
             
            }
             else{
                    Usuario nuevoUsu= new Usuario(dni,nombre,apellidos,password);
                    usuEJB.nuevoUsuario(nuevoUsu);
                    out.println("<form action=\"index.jsp\" method=\"POST\">"
                            + "<h1>Usuario registrado con existo </h1><br><br>"                     
                            + "<input type=\"submit\" name=\"Volver\" value=\"Volver\" />"
                            + "</form>");
                    
                }
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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

}
