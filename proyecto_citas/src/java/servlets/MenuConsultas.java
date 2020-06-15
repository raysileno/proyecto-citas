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
@WebServlet(name = "MenuConsultas", urlPatterns = {"/MenuConsultas"})
public class MenuConsultas extends HttpServlet {

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
            out.println("<title>Menu de consultas</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            int numCitas = conEJB.dameCitasTotal();
            int perAtt = conEJB.damePersAtt().size();
            int perAttSu = conEJB.damePersAttSu();
            int perNoPe = conEJB.dameNoPresentados();
            int perNoPeHoy = conEJB.dameNoPresentadosHoy();
            int usuAltHoy = conEJB.dameAltasHoy();
            int usuReg = conEJB.dameUsuRegis();
            List<Empleado> listaEmpleados = conEJB.finAllEmpleado();
            
            out.println("<h1>Menú de consultas</h1>");
            out.println("<p>Número de citas: " + numCitas);
            out.println("<p>Número de personas atendidas hoy: " + perAtt);
            out.println("<p>Número de personas atendidas hoy por el supervisor: " + perAttSu);
            out.println("<p>Número de personas no presentadas: " + perNoPe);
            out.println("<p>Número de personas no presentadas hoy: " + perNoPeHoy);
            out.println("<p>Usuarios dados de alta hoy: " + usuAltHoy);
            out.println("<p>Usuarios registrados en total: " + usuReg);
            out.println("<p><br><br>Seleccione los filtros de búsqueda que desea aplicar de las siguientes opciones:");
            out.println("<form name=\"MenuConsultas\" action=\"ResultadoConsulta\" method=\"POST\">"
                    + "<p><fieldset>"
                    + "<legend>Filtros de búsqueda</legend>"
                    + "<p><label class='marginLeft'><input type=\"checkbox\" name=\"chk\" value=\"1\">Trámites de alta</label>"
                    + "<label class='marginLeft'><input type=\"checkbox\" name=\"chk\" value=\"2\">Trámites de baja</label>"
                    + "<label class='marginLeft'><input type=\"checkbox\" name=\"chk\" value=\"3\">Trámites de suspensión</label>"
                    + "<p>DNI del usuario: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp"
                    + "Empleado:"
                    + "<p><input type=\"text\" name=\"usuario\">&nbsp &nbsp &nbsp &nbsp"
                    + "<select name=\"empleado\">"
                    + "<option value=\"-\">-</option>");
            listaEmpleados.forEach((e) -> {
                out.println("<option value=\"" + e.getDni() + "\">" + e.getNombre() + " " + e.getApellidos() + "</option>");
            });        
            
            out.println("</select>"
                    + "<p><label class='marginLeft'><input type=\"checkbox\" name=\"chkmesa\" value=\"4\">Mesa del supervisor</label>"
                    + "<label class='marginLeft'><input type=\"checkbox\" name=\"chkmesa\" value=\"0\">Mesa del empleado</label>"
                    + "</fieldset>"
                    + "<p><input type=\"submit\" value=\"Realizar consulta\">"
                    + "</form>");
            out.println("<form action=\"Menu_supervisor.html\" method=\"POST\">"                    
                    + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
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
