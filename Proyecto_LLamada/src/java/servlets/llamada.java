/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.llamadaEJB;
import entidades_POJO.Citas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
@WebServlet(name = "llamada", urlPatterns = {"/llamada"})
public class llamada extends HttpServlet {
@EJB
llamadaEJB llamaEJB;
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
      response.setIntHeader("refresh", 1);
       response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
           Calendar cal= new GregorianCalendar();
           String noon;
           int hour=cal.get(Calendar.HOUR);
           int minute = cal.get(Calendar.MINUTE);
           int second=cal.get(Calendar.SECOND);
           if(cal.get(Calendar.AM_PM)==0){
               noon="AM";
           }
           else{
               noon="PM";
           }
           String ct=hour+":"+minute+":"+second+" "+noon;
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html class='main'>");
            out.println("<head>");
            out.println("<title>Servlet Formulario</title>");
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/></link>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>"
                + "<h1>Turnos</h1>"
                + "<h2>Hora: "+ct+"</h2>"
                + "<h3>Compruebe su código y acerquese a la mesa correspondiente</h3>"
                + "<table cellspacing='20' class='tabla'>"
                    + "<thead>"
                        + "<th> Código </th>"
                        + "<th> Mesa </th>"
                    + "</thead>"
                    + "<tbody>");
            List<Citas> c=new ArrayList<Citas>();
            c=llamaEJB.DameCitas();
                        for (int i=0; i<c.size();i++){
                            out.println("<tr><td class='center'>"+c.get(i).getCodigoCita()+"</td><td class='center'>"+c.get(i).getTipoTramite().getId()+"</td></tr>");    
                        }
                    out.println("</tbody>"
                + "</table>"
            + "</div>");
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
