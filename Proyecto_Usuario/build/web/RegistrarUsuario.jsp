<%-- 
    Document   : RegistrarUsuario
    Created on : 12-may-2020, 13:04:45
    Author     : jluis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="main">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='resource/index.css'/></link>
        <title>Registrate</title>
    </head>
    <body>
        <div class="center">
            <h1>Insertar empleado</h1>
            <form action="insertarUsuario" method="post">
                <table class="tabla">
                    <tr>
                        <td><label for="nombre">Nombre: </label></td>
                        <td><input type="text" name="nombre" id="nombre"></td>
                    </tr>
                    <tr>
                        <td><label for="apellidos">Apellidos: </label></td>
                        <td><input type="text" name="apellidos" id="apellidos"></td>
                    </tr>
                    <tr>
                        <td><label for="dni">DNI: </label></td>
                        <td><input type="text" name="dni" id="dni"></td>
                    </tr>
                    <tr>
                        <td><label for="password">Contraseña: </label></td>
                        <td><input type="password" name="password" id="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" name="button" id="button" value="Enviar"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
