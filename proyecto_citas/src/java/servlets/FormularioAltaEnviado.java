/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ConexionEJB;
import entidades_POJO.Citas;
import entidades_POJO.Empleado;
import entidades_POJO.Historial;
import entidades_POJO.Tramites;
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
@WebServlet(name = "FormularioAltaEnviado", urlPatterns = {"/FormularioAltaEnviado"})
public class FormularioAltaEnviado extends HttpServlet {

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
            out.println("<title>Formulario enviado</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            //if(request.getParameter("dni").equalsIgnoreCase(""))
            ConexionEJB.traAlt.setDniUsuario(ConexionEJB.usu);
            
            ConexionEJB.usu.setDni(request.getParameter("dni"));
            ConexionEJB.usu.setNombre(request.getParameter("nombre"));
            ConexionEJB.usu.setApellidos(request.getParameter("apellidos"));
            ConexionEJB.usu.setPassword(request.getParameter("password"));
            ConexionEJB.usu.setCuentaBancaria(request.getParameter("cuenta"));
            ConexionEJB.usu.setFechaNacimiento(request.getParameter("fecnac"));
            ConexionEJB.usu.setDireccion(request.getParameter("direc"));
            ConexionEJB.usu.setEstado("Alta");
            ConexionEJB.traAlt.setTipoPlan(request.getParameter("plan"));
            ConexionEJB.traAlt.setPrecio(Integer.parseInt(request.getParameter("precio")));
            ConexionEJB.traAlt.setCorrientePago(true);
            
            List<Historial> listaHis = conEJB.findAllHistorial();
            int id;
            if(listaHis.isEmpty()){
                id = 1;
            }else{
                id = (listaHis.get(listaHis.size()-1).getIdHistorial()) + 1;
            }
                        
            Historial hist = new Historial(id, conEJB.obtenerFecha(), ConexionEJB.inicio, conEJB.obtenerHora(), true);
            hist.setDniEmpleado(ConexionEJB.empleado);
            hist.setTipoTramite(new Tramites(ConexionEJB.empleado.getTipoTramite()));
            hist.setDniUsuario(ConexionEJB.usu);
            
            //MODIFICAR DATOS EN LA BASE DE DATOS
            if(!conEJB.existeAlta(ConexionEJB.usu)){
                out.println("<h1>Formulario creado satisfactoriamente</h1>");
                conEJB.insertarHistorial(hist);                 
                conEJB.insertarTramiteAlta();
                conEJB.modificarUsuario(ConexionEJB.usu);
                
                Empleado empl = ConexionEJB.empleado;
                List<Citas> listaCitas = conEJB.findCitaByTipo(empl);
                if(!listaCitas.isEmpty()){
                    //ELIMINAR CITA DE LA TABLA CITAS            
                    conEJB.eliminarCita();
                }                
            }else{
                out.println("<h1>Este usuario ya estaba dado de alta</h1>");
            }          
            
            String ruta;
            if(ConexionEJB.empleado.getTipoTramite()==4){
                ruta="MenuSupervisorTramites";
            }else{
                ruta="Menu_empleado.html";
            }
            out.println("<form action=\"" + ruta + "\" method=\"POST\">"
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
