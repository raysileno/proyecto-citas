/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.Citas;
import entidades_POJO.Empleado;
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
@WebServlet(name = "MenuSupervisorTramites", urlPatterns = {"/MenuSupervisorTramites"})
public class MenuSupervisorTramites extends HttpServlet {

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
            out.println("<title>Menú principal del supervisor</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            Empleado empl = ConexionEJB.empleado;
            List<Citas> listaCitas = conEJB.findCitaByTipo(empl);
            out.println("<br>Usted tiene " + listaCitas.size() + " citas pendientes</br>");
            
            out.println("<h1>MENÚ DE TRÁMITES</h1>");            
            
            if(listaCitas.isEmpty()){
                out.println("<h2>NO TIENE CITAS PENDIENTES</h2>");
                ConexionEJB.usu = null;
                
            }else{
                ConexionEJB.cit = listaCitas.get(0);
                Usuario usu = listaCitas.get(0).getDniUsuario();
                ConexionEJB.usu = usu;
                ConexionEJB.cit.setLlamado(true);
                conEJB.modificarCita();
            }
            
            
            out.println("<fieldset>"
                + "<legend><h3>Consulta de datos</h3></legend>"
                + "<form action=\"Consultar_datos_personales.html\" method=\"POST\">"
                + "<p>Consultar datos personales<input class='marginLeft' type=\"submit\" name=\"consultar1\" value=\"Consultar\" />"
                + "</form>"
                + "<form action=\"Consultar_ficha_alta.html\" method=\"POST\">"
                + "<p>Consultar ficha de alta<input class='marginLeft' type=\"submit\" name=\"consultar2\" value=\"Consultar\" />"
                + "</form>"
                + "<form action=\"Consultar_corriente_pago.html\" method=\"POST\">"
                + "<p>Consultar corriente de pago<input class='marginLeft' type=\"submit\" name=\"consultar2\" value=\"Consultar\" />"
                + "</form>"    
                + "<form action=\"Consultar_suspension.html\" method=\"POST\">"
                + "<p>Consultar suspensión<input class='marginLeft' type=\"submit\" name=\"consultar3\" value=\"Consultar\" />"
                + "</form>"
                + "</fieldset>"
                + "<fieldset>"
                + "<legend><h3>Modificación de datos</h3></legend>"
                + "<form action=\"Modificar_datos_personales.html\" method=\"POST\">"
                + "<p>Modificar datos personales<input class='marginLeft' type=\"submit\" name=\"modificar1\" value=\"Modificar\" />"
                + "</form>"
                + "<form action=\"Modificar_plan.html\" method=\"POST\">"
                + "<p>Modificar el tipo de plan<input class='marginLeft' type=\"submit\" name=\"modificar2\" value=\"Modificar\" />"
                + "</form>"    
                + "<form action=\"Modificar_corriente_pago.html\" method=\"POST\">"
                + "<p>Modificar si está o no al corriente de pago<input class='marginLeft' type=\"submit\" name=\"modificar4\" value=\"Modificar\" />"
                + "</form>"        
                + "<form action=\"Modificar_fecha_suspension.html\" method=\"POST\">"
                + "<p>Modificar la fecha del fin de la suspensión<input class='marginLeft' type=\"submit\" name=\"modificar3\" value=\"Modificar\" />"
                + "</form>"
                + "</fieldset>"
                + "<fieldset>"
                + "<legend><h3>Realizar trámite</h3></legend>"
                + "<form action=\"Seleccion_de_tramite.html\" method=\"POST\">"
                + "<p>Realizar un trámite nuevo<input class='marginLeft' type=\"submit\" name=\"ir\" value=\"Ir\" />"
                + "</form>"  
                + "</fieldset>"); 
              if(!listaCitas.isEmpty()){
            out.println("<form action=\"NoPresentado\" method=\"POST\">"
                + "<p><input type=\"submit\" name=\"pasar\" value=\"No presentado\" />"
                + "</form>");
             }
            out.println("<form action=\"Menu_supervisor.html\" method=\"POST\">"
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
