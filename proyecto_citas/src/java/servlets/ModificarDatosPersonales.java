/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
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
 * @author Eric
 */
@WebServlet(name = "ModificarDatosPersonales", urlPatterns = {"/ModificarDatosPersonales"})
public class ModificarDatosPersonales extends HttpServlet {

    @EJB
    ConexionEJB conEJB;
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
            out.println("<title>Modificar datos personales</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>MODIFICAR DATOS PERSONALES</h1>");
            
            
            String dni = request.getParameter("dni");
            Usuario us = conEJB.findUsuarioByDni(dni);
            
            if(us==null){
                out.println("<h2>No existen datos registrados de este usuario</h2>");
            }else{
                out.println("<form action=\"ModificarDatosPersonalesAccept\" method=\"POST\">");
                out.println("<br>Nombre: <input type=\"text\" name=\"nombre\" value=\"" + us.getNombre() + "\" required/>" + "</br>");
                out.println("<br>Apellidos: <input type=\"text\" name=\"apellidos\" value=\"" + us.getApellidos()+ "\" required/>" + "</br>");
                out.println("<br>DNI: <input type=\"text\" name=\"dni\" value=\"" + us.getDni() + "\" readonly/>" + "</br>");
                out.println("<br>Fecha de nacimiento: <input type=\"text\" name=\"fecnac\" value=\"" + us.getFechaNacimiento() + "\" required/>" + "</br>");
                out.println("<br>Dirección: <input type=\"text\" name=\"direc\" value=\"" + us.getDireccion() + "\" required/>" + "</br>");
                out.println("<br>Cuenta bancaria: <input type=\"text\" name=\"cuenta\" value=\"" + us.getCuentaBancaria() + "\" size=\"21\" maxlength=\"20\" minlength=\"20\" required/>" + "</br>");
                out.println("<br>Estado: <input type=\"text\" name=\"estado\" value=\"" + us.getEstado() + "\" readonly/>" + "</br>");
                out.println("<br>Password: <input type=\"password\" name=\"password\" required/></br>");            
                out.println("<br><input type=\"submit\" name=\"modificar\" value=\"Modificar\" /></br>" + "</form>");
            }
            out.println("<form action=\"MenuSupervisorTramites\" method=\"POST\">"
                + "<p><input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                + "</form>");
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
