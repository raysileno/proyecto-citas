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
@WebServlet(name = "MenuFormulario", urlPatterns = {"/MenuFormulario"})
public class MenuFormulario extends HttpServlet {

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
            out.println("<title>Menú principal</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            out.println("<h1>Menú de formulario</h1>");
            
            
            Empleado empl = ConexionEJB.empleado;
            List<Citas> listaCitas = conEJB.findCitaByTipo(empl);
            out.println("<br>Usted tiene " + listaCitas.size() + " citas pendientes</br>");
            
            if(listaCitas.isEmpty()){
                out.println("<h1>No hay usuarios de este tipo de cita a la espera</h1>");
            }else{
                ConexionEJB.cit = listaCitas.get(0);
                Usuario usu = listaCitas.get(0).getDniUsuario();
                ConexionEJB.usu = usu;
                ConexionEJB.cit.setLlamado(true);
                conEJB.modificarCita();
                
                switch(empl.getTipoTramite()){
                    case 1:
                        out.println("<h1>Rellenar formulario de Altas</h1>");
                        out.println("<form action=\"Formulario1\" method=\"POST\">"
                                + "<p>Ir al formulario de alta."
                                + "<input type=\"submit\" name=\"ir\" value=\"Ir\" />"
                                + "</form>");
                        break;
                    case 2:
                        out.println("<h1>Rellenar formulario de Bajas</h1>");
                        out.println("<form action=\"Formulario2\" method=\"POST\">"
                                + "<p>Ir al formulario de baja."
                                + "<input type=\"submit\" name=\"ir\" value=\"Ir\" />"
                                + "</form>");
                        break;
                    case 3:
                        out.println("<h1>Rellenar formulario de Suspensiones</h1>");
                        out.println("<form action=\"Formulario3\" method=\"POST\">"
                                + "<p>Ir al formulario de suspensión "
                                + "<input type=\"submit\" name=\"ir\" value=\"Ir\" />"
                                + "</form>");
                        break;
                }
                out.println("<form action=\"NoPresentado\" method=\"POST\">"
                            + "<p><input type=\"submit\" name=\"pasar\" value=\"No presentado\" />"
                            + "</form>");
            }    
            out.println("<form action=\"Menu_empleado.html\" method=\"POST\">"
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
