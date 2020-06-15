/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.TramiteSuspensiones;
import entidades_POJO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "ModificarFechaSuspensionAccept", urlPatterns = {"/ModificarFechaSuspensionAccept"})
public class ModificarFechaSuspensionAccept extends HttpServlet {

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
            out.println("<title>Modificar suspensión</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
                        
            String dni = request.getParameter("dni");
            Usuario us = conEJB.findUsuarioByDni(dni);
            TramiteSuspensiones tra = conEJB.findSuspensionByDni(us);
            if(request.getParameter("finsus").equalsIgnoreCase("0")){
                conEJB.eliminarSuspension(tra);
                out.println("<h1>Suspensión cancelada con éxito</h1>");
            }else{
                int dias = Integer.parseInt(request.getParameter("finsus"));
                String fechaAnt = tra.getFechaFinSus();
                DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
                Date fechDat = null;
                try{
                    fechDat = fecha.parse(fechaAnt);
                }catch(ParseException ex){
                    ex.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(fechDat);
                cal.add(Calendar.DAY_OF_YEAR, dias);

                Date ffin = cal.getTime();
                String ffinal = fecha.format(ffin);
                tra.setFechaFinSus(ffinal);
                conEJB.modificarFechaFinSuspension(tra);
                out.println("<h1>Cambio de fecha final realizada con éxito</h1>");
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
