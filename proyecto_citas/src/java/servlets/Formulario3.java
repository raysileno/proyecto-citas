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
@WebServlet(name = "Formulario3", urlPatterns = {"/Formulario3"})
public class Formulario3 extends HttpServlet {

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
            out.println("<title>Formulario de suspensión</title>");
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>FORMULARIO DE SUSPENSIÓN</h1>");
            
            ConexionEJB.inicio = conEJB.obtenerHora();      
            
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
            
            String ruta;
            if(ConexionEJB.empleado.getTipoTramite()==4){
                ruta="MenuSupervisorTramites";
            }else{
                ruta="Menu_empleado.html";
            }
            
            out.println("<form action=\"FormularioSuspensionEnviado\" method=\"POST\">"
                    + "<fieldset>"
                        + "<legend>Datos personales</legend>"                    
                        + "<p><label>DNI: <input type=\"text\" name=\"dni\" value=\"" + ConexionEJB.usu.getDni() + "\"" + readonly + ">"
                        + "<p " + style + "><label>Corriente de pago: " + corr + ""
                    + "</fieldset>"
                    + "<fieldset>"
                        + "<legend>Periodo de la suspensión</legend>"
                        + "<p>Inicio de la suspensión: <input type=\"text\" name=\"inisus\" value=\"" + conEJB.obtenerFecha() + "\" readonly>"
                        + "<p>Fin de la suspensión:"
                        + "<p><label><input type=\"radio\" name=\"finsus\" value=\"15\" checked>15</label>"
                        + "<p><label><input type=\"radio\" name=\"finsus\" value=\"30\">30</label>"
                        + "<p><label><input type=\"radio\" name=\"finsus\" value=\"45\">45</label>"
                    + "</fieldset>"       
                    + "<fieldset>"       
                        + "<legend>Seleccione los motivos</legend>"       
                        + "<p><label><input type=\"radio\" name=\"motivo\" value=\"IMP\">Por impago</label>"
                        + "<label><input type=\"radio\" name=\"motivo\" value=\"SOL\">Solicitud del usuario</label>"
                        + "<p><label><input type=\"radio\" name=\"motivo\" value=\"INT\">Causas internas del gimnasio</label>"
                        + "<label><input type=\"radio\" name=\"motivo\" value=\"AJE\">Causas ajenas al gimnasio y al usuario</label>"
                        + "<p><label><input type=\"radio\" name=\"motivo\" value=\"OTR\" checked>Otras causas</label>"
                    + "</fieldset>"
                    + "<p><input type=\"submit\" value=\"Finalizar\"></p>"
                    + "</form>"); 
           /* out.println("<form action=\"" + ruta + "\" method=\"POST\">"
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
