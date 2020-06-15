/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.TramiteAltas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "ConsultaCorrientePago", urlPatterns = {"/ConsultaCorrientePago"})
public class ConsultaCorrientePago extends HttpServlet {

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
            out.println("<title>Consulta de corriente de pago</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>RESULTADO DE LA BÚSQUEDA DE CORRIENTES DE PAGO</h1>");
            
            String consulta = request.getParameter("consulta");
            List<TramiteAltas> listaTraAlt = conEJB.findAllTramiteAltas();
            if(consulta.equalsIgnoreCase("todos")){
                for(int i = 0;i<listaTraAlt.size();i++){
                    String corr;
                    if(listaTraAlt.get(i).getCorrientePago()){
                        corr = "SÍ";
                    }else{
                        corr = "NO";
                    }
                    out.println("<p>[DNI del usuario: <b>" +listaTraAlt.get(i).getDniUsuario().getDni()
                    + "</b>; Corriente de pago: <b>" + corr + "</b>]</p>");
                }
            }else if(consulta.equalsIgnoreCase("si")){
                for(int i = 0;i<listaTraAlt.size();i++){
                    String corr;
                    if(listaTraAlt.get(i).getCorrientePago()){
                        corr = "SÍ";
                        out.println("<p>[DNI del usuario: <b>" +listaTraAlt.get(i).getDniUsuario().getDni()
                            + "</b>; Corriente de pago: <b>" + corr + "</b>]</p>");
                    }                    
                }
            }else if(consulta.equalsIgnoreCase("no")){
                for(int i = 0;i<listaTraAlt.size();i++){
                    String corr;
                    if(!listaTraAlt.get(i).getCorrientePago()){
                        corr = "NO";
                        out.println("<p>[DNI del usuario: <b>" +listaTraAlt.get(i).getDniUsuario().getDni()
                            + "</b>; Corriente de pago: <b>" + corr + "</b>]</p>");
                    }                    
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
