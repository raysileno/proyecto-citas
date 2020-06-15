/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.Citas;
import entidades_POJO.Empleado;
import entidades_POJO.TramiteAltas;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
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
@WebServlet(name = "Formulario1", urlPatterns = {"/Formulario1"})
public class Formulario1 extends HttpServlet {

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
            out.println("<title>Formulario de alta</title>");
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>FORMULARIO DE ALTA</h1>");
            
            
            
            List<TramiteAltas> listaTraAlt = conEJB.findAllTramiteAltas();
            int id;
            if(listaTraAlt.isEmpty()){
                id = 1;
            }else{
                id = (listaTraAlt.get(listaTraAlt.size() - 1).getIdAlta()) + 1;
            }            
            String fecha = conEJB.obtenerFecha();
            TramiteAltas nueAlt = new TramiteAltas(id);
            nueAlt.setDniEmpleado(ConexionEJB.empleado);
            nueAlt.setFechaAlta(fecha);
            nueAlt.setTipoTramite(new Tramites(1));
            ConexionEJB.traAlt = nueAlt;
            ConexionEJB.inicio = conEJB.obtenerHora();
            
            String ruta;
            if(ConexionEJB.empleado.getTipoTramite()==4){
                ruta="MenuSupervisorTramites";
            }else{
                ruta="Menu_empleado.html";
            }
            String readonly;
            if(ConexionEJB.usu == null){
                ConexionEJB.usu = new Usuario("", "", "", "");
                readonly = "";
            }else{
                readonly = "readonly";
            }
            
            out.println("<form name=\"formulario1\" action=\"FormularioAltaEnviado\" method=\"POST\">"
                    + "<fieldset>"
                    + "<legend><h3>Datos del registro</h3></legend>"
                    + "<p><label>Nombre: <input type=\"text\" name=\"nombre\" value=\"" + ConexionEJB.usu.getNombre() + "\" " + readonly +">"
                    + "<p><label>Apellidos: <input type=\"text\" name=\"apellidos\" value=\"" + ConexionEJB.usu.getApellidos()+ "\" " + readonly +">"
                    + "<p><label>DNI: <input type=\"text\" name=\"dni\" value=\"" + ConexionEJB.usu.getDni()+ "\" " + readonly +">"
                    + "<p><label>Password: <input type=\"password\" name=\"password\" value=\"" + ConexionEJB.usu.getPassword()+ "\" " + readonly +">"
                    + "</fieldset>"
                    + "<fieldset>"
                    + "<legend><h3>Datos personales</h3></legend>"
                    + "<p><label>Fecha de nacimiento: <input type=\"text\" name=\"fecnac\" required>"
                    + "<p><label>Dirección postal: <input type=\"text\" name=\"direc\" required>"
                    + "<p><label>Cuenta bancaria: <input type=\"text\" name=\"cuenta\" size=\"21\" maxlength=\"20\" minlength=\"20\" required>"
                    + "</fieldset>"
                    + "<fieldset>"
                    + "<legend><h3>Otros datos</h3></legend>"
                    + "<p><label>Tipo de plan: <select name=\"plan\">"
                    + "<option value=\"A\">Matrícula estándar</option>"
                    + "<option value=\"B\">Matrícula reducida</option>" 
                    + "<option value=\"C\">Matrícula premium</option>" 
                    + "</select>"
                    + "<p><label>Precio: <select name=\"precio\">"
                    + "<option value=\"30\">30</option>"
                    + "<option value=\"25\">25</option>"
                    + "<option value=\"50\">50</option>"
                    + "</select>"
                    + "</fieldset>"
                    + "<p><input type=\"submit\" value=\"Finalizar\"></p>"
                    + "</form>");
            
          /*  out.println("<form action=\"" + ruta + "\" method=\"POST\">"
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
