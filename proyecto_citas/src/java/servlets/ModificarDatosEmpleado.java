/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.Empleado;
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
@WebServlet(name = "ModificarDatosEmpleado", urlPatterns = {"/ModificarDatosEmpleado"})
public class ModificarDatosEmpleado extends HttpServlet {

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
            out.println("<title>Modificar datos del empleado</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            Empleado e = new Empleado(request.getParameter("dni"));
            if(conEJB.existeEmpleado(e)){
                Empleado em = conEJB.findEmpleadoByDni(e.getDni());
                out.println("<h1>DATOS DEL EMPLEADO</h1>");
                out.println("<form action=\"ModificarEmpleado\" method=\"POST\">");
                out.println("<p>DNI: <input type=\"text\" name=\"dni\" value=\"" + em.getDni() + "\" readonly/></p>");
                out.println("<p>Nombre: <input type=\"text\" name=\"nombre\" value=\"" + em.getNombre() + "\" required/></p>");
                out.println("<p>Apellidos: <input type=\"text\" name=\"apellidos\" value=\"" + em.getApellidos() + "\" required/></p>");
                out.println("<p>Password: <input type=\"password\" name=\"pass\" value=\"" + em.getPassword() + "\" required/></p>");
                out.println("<p>Tipo de trámite: <select name=\"tiptra\">");
                out.println("<option value=\"1\">1</option>");
                out.println("<option value=\"2\">2</option>");
                out.println("<option value=\"3\">3</option>");
                out.println("<option value=\"4\">4</option>");             
                out.println("</select>");             
                out.println("<br><input type=\"submit\" name=\"modificar\" value=\"Modificar\"/>");
                out.println("</form>");
            }else{
                out.println("<h1>Este empleado no existe en la base de datos</h1>");
                out.println("<form action=\"Formulario_crear_empleado.html\" method=\"POST\">");
                out.println("Pinche aquí para crear uno nuevo <input type=\"submit\" name=\"crear\" value=\"Crear nuevo\"/>");
                out.println("</form>");
            }            
            out.println("<form action=\"Menu_gestion_empleados.html\" method=\"POST\">"
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
