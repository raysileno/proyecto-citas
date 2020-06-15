/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.TramiteAltas;
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
@WebServlet(name = "ConsultaFichaAlta", urlPatterns = {"/ConsultaFichaAlta"})
public class ConsultaFichaAlta extends HttpServlet {

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
            out.println("<title>Consulta de ficha alta</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>DATOS DE LA FICHA DE ALTA</h1>");
            
            String dni = request.getParameter("dni");
            Usuario us = conEJB.findUsuarioByDni(dni);            
            
            if(us == null){
                out.println("<h2>ESTE USUARIO NO SE ENCUENTRA EN LA BASE DE DATOS</h2>");
            }else{
                TramiteAltas tra = conEJB.findAltaByDni(us);
                if(tra == null){
                    out.println("<h2>NO SE ENCUENTRA EL TRÁMITE DE ALTA DE ESTE USUARIO EN LA BASE DE DATOS</h2>");
                }else{
                    out.println("<br>Id: <b>" + tra.getIdAlta() + "</b></br>");
                    out.println("<br>DNI usuario: <b>" + tra.getDniUsuario().getDni()+ "</b></br>");
                    out.println("<br>Nombre y apellidos del usuario: <b>" + tra.getDniUsuario().getNombre() + " " + tra.getDniUsuario().getApellidos() + "</b></br>");
                    out.println("<br>DNI empleado: <b>" + tra.getDniEmpleado().getDni() + "</b></br>");
                    out.println("<br>Nombre y apellidos del empleado: <b>" + tra.getDniEmpleado().getNombre() + " " + tra.getDniEmpleado().getApellidos() + "</b></br>");
                    out.println("<br>Fecha de alta: <b>" + tra.getFechaAlta() + "</b></br>");
                    out.println("<br>Tipo de plan: <b>" + tra.getTipoPlan() + "</b></br>");
                    out.println("<br>Precio: <b>" + tra.getPrecio() + "</b></br>");
                    out.println("<br>Tipo de trámite: <b>" + tra.getTipoTramite().getId() + "</b></br>");
                }
            }
            out.println("<br><form action=\"MenuSupervisorTramites\" method=\"POST\">"
                + "<p><input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                + "</form></br>");
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
