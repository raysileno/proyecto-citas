

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="main">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='resource/index.css'/>
        <title>INICIO DE SESIÓN</title>
    </head>
    <body>
        <div class="center">
            <h1>CONECTAR</h1>
            <form action="IniciarSesion" method="POST">
                <p>Introduzca su DNI: <input type="text" name="dni" required></p>
                <p>Introduzca su password: <input type="password" name="password" required></p>            
                <input type="submit" value="Iniciar">
            </form>
        </div>
    </body>
</html>
