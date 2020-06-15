

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="main">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='resource/index.css'/></link>
        <title>INICIO DE SESIÓN</title>
    </head>
    <body>
        <%
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
        
        %>
        <div class="center">
            <h1>CONECTAR</h1>
            <form action="IniciarSesion" method="POST">
                <div class="form">
                    <div>Introduzca su DNI: <div><input type="text" name="dni" value=<%= dniUsu %>></div></div>
                    <div>Introduzca su password: <div> <input type="password" name="password"></div></div>
                    <div>
                        <input type="submit" value="Aceptar">    
                    </div>
                </div>
                <button class="boton">
                    <a href='RegistrarUsuario.jsp'> Registrate </a>
                </button>
            </form>
        </div>
    </body>
</html>
