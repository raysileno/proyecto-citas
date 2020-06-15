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
@WebServlet(name = "ModificarCorrientePago", urlPatterns = {"/ModificarCorrientePago"})
public class ModificarCorrientePago extends HttpServlet {

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
            out.println("<title>Modificar corriente de pago</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>MODIFICAR SI ESTÁ AL CORRIENTE DE PAGO O NO</h1>");
            
            String dni = request.getParameter("dni");            
            
            if(!conEJB.existeUsuario(new Usuario(dni))){
                out.println("<h2>ESTE USUARIO NO SE ENCUENTRA REGISTRADO EN LA BASE DE DATOS</h2>");
            }else{
                Usuario us = conEJB.findUsuarioByDni(dni);                
                if(!conEJB.existeAlta(us)){
                   out.println("<h2>NO SE ENCUENTRA EL TRÁMITE DE ALTA DE ESTE USUARIO EN LA BASE DE DATOS</h2>"); 
                }else{
                    TramiteAltas tra = conEJB.findAltaByDni(us);
                    if(tra.getCorrientePago()){
                        out.println("<br>Este usuario <b>ESTÁ AL CORRIENTE DE PAGO</b></br>");                        
                    }else{
                        out.println("<br>Este usuario <b>NO ESTÁ AL CORRIENTE DE PAGO</b></br>");    
                    }
                    out.println("<br>¿Desea cambiarlo para este usuario?</br>");
                    out.println("<form action=\"ModificarCorrientePagoAccept\" method=\"POST\">");
                    out.println("<br><input type=\"text\" name=\"dni\" value=\"" + us.getDni() + "\" readonly></br>");//en vez de hacer esto, modificar el usuario conectado para cogerlo de ahi??
                    out.println("<p><input type=\"submit\" name=\"confirmar\" value=\"Confirmar\" />"
                        + "</form>");                    
                }
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
