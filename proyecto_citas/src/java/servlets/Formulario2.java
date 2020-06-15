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
@WebServlet(name = "Formulario2", urlPatterns = {"/Formulario2"})
public class Formulario2 extends HttpServlet {

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
            out.println("<title>Formulario de baja</title>");
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>FORMULARIO DE BAJA</h1>");            
            
            
            ConexionEJB.inicio = conEJB.obtenerHora();
            String ruta;
            if(ConexionEJB.empleado.getTipoTramite()==4){
                ruta="MenuSupervisorTramites";
            }else{
                ruta="Menu_empleado.html";
            }             
            
            String readonly,style,corr;
            if(ConexionEJB.usu == null){
                ConexionEJB.usu = new Usuario("", "", "", "");
                readonly = "";
                style = "";
                corr = "";
            }else{
                readonly = "readonly";
                TramiteAltas traAlt = conEJB.findAltaByDni(ConexionEJB.usu);
                  corr = "SÍ";
                    style = "style=\"color:black;\"";
               /* if(traAlt.getCorrientePago()){
                    corr = "SÍ";
                    style = "style=\"color:black;\"";
                }else{
                    corr = "NO";
                    style = "style=\"color:red;\"";
                }*/
            }           
            
            out.println("<form action=\"FormularioBajaEnviado\" method=\"POST\">"
                    + "<fieldset>"
                        + "<legend>Datos personales</legend>"                    
                        + "<p><label>DNI: <input type=\"text\" name=\"dni\" value=\"" + ConexionEJB.usu.getDni() + "\"" + readonly +">"
                        + "<p " + style + "><label>Corriente de pago: " + corr + ""
                    + "</fieldset>"
                    + "<fieldset>"
                        + "<legend>Seleccione los motivos</legend>"
                        + "<p><label><input type=\"checkbox\" name=\"chk\" value=\"DIN\">Por cuestiones económicas</label>"
                        + "<label><input type=\"checkbox\" name=\"chk\" value=\"TIE\">Por falta de tiempo</label>"
                        + "<p><label><input type=\"checkbox\" name=\"chk\" value=\"SAT\">Por saturación de gente</label>"
                        + "<label><input type=\"checkbox\" name=\"chk\" value=\"LOC\">Me viene mal su localización</label>"
                        + "<p><label><input type=\"checkbox\" name=\"chk\" value=\"CLA\">No me gustan las clases</label>"
                        + "<label><input type=\"checkbox\" name=\"chk\" value=\"INS\">No me gustan las instalaciones</label>"
                        + "<p><label><input type=\"checkbox\" name=\"chk\" value=\"OTR\">Otros motivos</label>"
                    + "</fieldset>"
                    
                    //PODEMOS AÑADIR OBSERVACIONES
                    
                    + "<p><input type=\"submit\" value=\"Finalizar\"></p>"
                    + "</form>");  
        /*    out.println("<form action=\"" + ruta + "\" method=\"POST\">"
                    + "<p><input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                    + "</form>");*/
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
