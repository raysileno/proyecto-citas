/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import beans.UsuarioEJB;
import entidades_POJO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eric
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    @EJB
    UsuarioEJB usuEJB;
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
            out.println("<title>Servlet IniciarSesion</title>");       
            out.println("<link rel='stylesheet' type='text/css' href='resource/index.css'/></link>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='center'>");
            String dni = request.getParameter("dni");
            String password = request.getParameter("password");
            Usuario usu = new Usuario(dni);
            
          String dniUsuario=request.getParameter("dni");
          //Creo Cookie
          Cookie laCookie=new Cookie("usuario.login",dniUsuario);
          // Tiempo de la Cookie(1 año)
          laCookie.setMaxAge(365*24*60/60);
          // Graba en el ordenador usuario
          response.addCookie(laCookie);
          
            if(usuEJB.existeUsuario(usu)){
                Usuario u = usuEJB.findUsuarioByDni(dni);
                if(u.getPassword().equals(password)){
                    
                       if(!usuEJB.existeCita()){
                            out.println("<h1>Bienvenido " + u.getNombre() + "</h1>");
                        out.println("<form action=\"Menu_usuario.html\" method=\"POST\">"
                            + "<h3>Ir al menú del usuario.</h3><br>"
                            + "<input type=\"submit\" name=\"ir\" value=\"Ir\" />"
                            + "</form>");
                    
                    
                 }
       
                 else{
                     out.println("<h1>Bienvenido " + u.getNombre() + "</h1>");
                        out.println("<form action=\"verCita\" method=\"POST\">"
                            + "Usted ya tiene una cita. <br>"
                            + "<input type=\"submit\" name=\"VerCita\" value=\"Ver Cita\" />"
                            + "</form>"
                            + "<form action=\"CitaBorrada\" method=\"POST\">"
                            + "<input type=\"submit\" name=\"borrar\" value=\"Borrar Cita\" />"
                            + "</form>");
                       }
                }
            
             else{
                          out.println("<form action=\"index.jsp\" method=\"POST\">"
                            + "<h1>Usuario o contraseña son incorrectos</h1> <br><br>"                     
                            + "<input type=\"submit\" name=\"Volver\" value=\"Volver\" />"
                            + "</form>");
                  
                    
             
                     }     
           
                }
            else{
                      out.println("<form action=\"index.jsp\" method=\"POST\">"
                            + "Usuario o contraseña son incorrectos <br><br>"                     
                            + "<input type=\"submit\" name=\"Volver\" value=\"Volver\" />"
                            + "</form>");
                    }
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
