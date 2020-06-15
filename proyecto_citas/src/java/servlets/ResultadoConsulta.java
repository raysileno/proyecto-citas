/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ResultadoConsulta", urlPatterns = {"/ResultadoConsulta"})
public class ResultadoConsulta extends HttpServlet {

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
            out.println("<title>Resultado de la consulta</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            
            String [] checkTra = request.getParameterValues("chk");
            String usuario = request.getParameter("usuario");
            String empleado = request.getParameter("empleado");
            String [] checkMes = request.getParameterValues("chkmesa");
            ArrayList<String> listadoTramites = conEJB.realizaConsultaTramite(checkTra);
            ArrayList<String> listadoUsuarios = conEJB.realizaConsultaUsuario(usuario);
            ArrayList<String> listadoEmpleado = conEJB.realizaConsultaEmpleado(empleado);
            ArrayList<String> listadoMesas = conEJB.realizaConsultaMesa(checkMes);
            
            out.println("<h1>Resultado:</h1>");
            out.println("<fieldset>");
            out.println("<legend>Listado de trámites</legend>");
            listadoTramites.forEach((tra) -> {
                out.println("<p>" + tra);
            });
            out.println("</fieldset>");
            out.println("<fieldset>");
            out.println("<legend>Datos del usuario</legend>");
            listadoUsuarios.forEach((datos) -> {
                out.println("<p>" + datos);
            });
            out.println("</fieldset>");
            out.println("<fieldset>");
            out.println("<legend>Datos del empleado</legend>");
            listadoEmpleado.forEach((datos) -> {
                out.println("<p>" + datos);
            });
            out.println("</fieldset>");
            out.println("<fieldset>");
            out.println("<legend>Mesas</legend>");
            listadoMesas.forEach((datos) -> {
                out.println("<p>" + datos);
            });
            out.println("</fieldset>");
            out.println("<form action=\"MenuConsultas\" method=\"POST\">"
                    + "<p><input type=\"submit\" name=\"volver\" value=\"Realizar otra consulta\" />"
                    + "</form>");
            out.println("<form action=\"Menu_supervisor.html\" method=\"POST\">"
                    + "<p><input type=\"submit\" name=\"volver2\" value=\"Volver a la página principal\" />"
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
