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
import entidades_POJO.TramiteAltas;
import entidades_POJO.TramiteSuspensiones;
import entidades_POJO.Tramites;
import entidades_POJO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "FormularioSuspensionEnviado", urlPatterns = {"/FormularioSuspensionEnviado"})
public class FormularioSuspensionEnviado extends HttpServlet {

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
            
            List<TramiteSuspensiones> lista = conEJB.findAllTramiteSuspensiones();
            int id;
            if(lista.isEmpty()){
                id = 1;
            }else{
                id = (lista.get(lista.size() - 1).getIdSuspension()) + 1;
            }
            String motivo = request.getParameter("motivo");
            
            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
//            Date date = new Date();
//            String convertido = fecha.format(date);
//            String fechIni = fecha.format(request.getParameter("inisus"));
//            String fechFin = fecha.format(request.getParameter("finsus"));
            String fechIni = request.getParameter("inisus");
            int fechFin = Integer.parseInt(request.getParameter("finsus"));
            Date fechaInicio = null;
            try{
                fechaInicio = fecha.parse(fechIni);
            }catch(ParseException ex){
                ex.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaInicio);
            cal.add(Calendar.DAY_OF_YEAR, fechFin);
            
            Date ffin = cal.getTime();
            String ffinal = fecha.format(ffin);
            List<Historial> listaHis = conEJB.findAllHistorial();
            int idHis;
            if(listaHis.isEmpty()){
                idHis = 1;
            }else{
                idHis = (listaHis.get(listaHis.size()-1).getIdHistorial()) + 1;
            }
            Historial hist = new Historial(idHis, conEJB.obtenerFecha(), ConexionEJB.inicio, conEJB.obtenerHora(), true);
            
            hist.setDniEmpleado(ConexionEJB.empleado);
            if(conEJB.existeUsuario(new Usuario(request.getParameter("dni")))){
                Usuario usu = conEJB.findUsuarioByDni(request.getParameter("dni"));
                usu.setEstado("Suspendido");
                hist.setDniUsuario(usu);
                hist.setTipoTramite(new Tramites(ConexionEJB.empleado.getTipoTramite()));
                TramiteAltas traAlt = conEJB.findAltaByDni(ConexionEJB.usu);
                TramiteSuspensiones traSu = new TramiteSuspensiones(id, fechIni, ffinal, motivo, traAlt.getCorrientePago());
                traSu.setDniEmpleado(ConexionEJB.empleado);
                traSu.setDniUsuario(usu);
                traSu.setTipoTramite(new Tramites(3));

                //MODIFICAR DATOS EN LA BASE DE DATOS
                if(conEJB.existeAlta(ConexionEJB.usu)){
                    conEJB.insertarHistorial(hist);
                    conEJB.insertarSuspension(traSu);
                    conEJB.modificarUsuario(usu);
                    
                    Empleado empl = ConexionEJB.empleado;
                    List<Citas> listaCitas = conEJB.findCitaByTipo(empl);
                    if(!listaCitas.isEmpty()){
                        //ELIMINAR CITA DE LA TABLA CITAS            
                        conEJB.eliminarCita();
                    }
                    out.println("<h1>SUSPENSIÓN INICIADA CORRECTAMENTE</h1>");
                }else{
                    out.println("<h1>Este usuario no está dado de alta</h1>");
                }
            }else{
                out.println("<h1>Este usuario no está registrado en la base de datos</h1>");
            }       
            
            String ruta;
            if(ConexionEJB.empleado.getTipoTramite()==4){
                ruta="MenuSupervisorTramites";
            }else{
                ruta="Menu_empleado.html";
            }
            out.println("<form action=\"" + ruta + "\" method=\"POST\">"
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
