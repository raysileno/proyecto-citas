/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.UsuarioEJB;
import entidades_POJO.Citas;
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
@WebServlet(name = "GestionCita", urlPatterns = {"/GestionCita"})
public class GestionCita extends HttpServlet {
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
            String tramite = request.getParameter("tipo");
            Citas cita= usuEJB.dameCita(tramite);
            if(cita.getTipoTramite().getId() == 5){
                out.println("<h2>Usted ya está dado de alta</h2>");
                out.println("<br>Seleccione otra cita.</br>");
                out.println("<form action=\"Menu_usuario.html\" method=\"POST\">"
                            + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                            + "</form>");
            }else if(cita.getTipoTramite().getId() == 6){
                out.println("<h2>Usted no está dado de alta</h2>");
                out.println("<br>Seleccione otra cita.</br>");
                out.println("<form action=\"Menu_usuario.html\" method=\"POST\">"
                            + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                            + "</form>");
            }else if(cita.getTipoTramite().getId() == 7){
                out.println("<h2>Usted no está dado de alta</h2>");
                out.println("<br>Seleccione otra cita.</br>");
                out.println("<form action=\"Menu_usuario.html\" method=\"POST\">"
                            + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                            + "</form>");
            }else{
            usuEJB.insertarCita(cita);
                        out.println("<h1>Recuerde su cita:</h1>");
                        out.println("<table cellspacing='20' class='tabla'>"
                            + "<thead>"
                                + "<th>Código</th>"
                                + "<th>Hora</th>"
                                + "<th>Fecha</th>"
                            + "</thead>"
                            + "<tbody>"
                                + "<tr>"
                                    + "<td>"+cita.getCodigoCita()+"</td>"
                                    + "<td>"+cita.getHora()+"</td>"
                                    + "<td>"+cita.getFecha()+"</td>"
                                + "</tr>"
                            + "</tbody>"
                        + "</table><br>");
                        out.println("<form action=\"index.jsp\" method=\"POST\">"
                            + "<input type=\"submit\" name=\"Salir\" value=\"Salir\" />"
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
