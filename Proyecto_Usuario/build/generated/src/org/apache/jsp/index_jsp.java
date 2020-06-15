package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html class=\"main\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel='stylesheet' type='text/css' href='resource/index.css'/></link>\n");
      out.write("        <title>INICIO DE SESIÓN</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

          //Valor por defecto
         String dniUsu=" ";
         // Lee la Cookie
         Cookie[] lasCookies=request.getCookies();
         //Buscar Cookie
         if(lasCookies!=null){
             
             for (Cookie cookie_temp: lasCookies){
                 if("usuario.login".equals(cookie_temp.getName()))
                 dniUsu=cookie_temp.getValue();
                 break;
             }
             
         }
        
        
      out.write("\n");
      out.write("        <div class=\"center\">\n");
      out.write("            <h1>CONECTAR</h1>\n");
      out.write("            <form action=\"IniciarSesion\" method=\"POST\">\n");
      out.write("                <div class=\"form\">\n");
      out.write("                    <div>Introduzca su DNI: <div><input type=\"text\" name=\"dni\" value=");
      out.print( dniUsu );
      out.write("></div></div>\n");
      out.write("                    <div>Introduzca su password: <div> <input type=\"password\" name=\"password\"></div></div>\n");
      out.write("                    <div>\n");
      out.write("                        <input type=\"submit\" value=\"Aceptar\">    \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <button class=\"boton\">\n");
      out.write("                    <a href='RegistrarUsuario.jsp'> Registrate </a>\n");
      out.write("                </button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
