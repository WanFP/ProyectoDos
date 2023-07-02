<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Formulario Autor</title>
    </head>
    <body>
        <h1>Formulario de Ingreso de Autor</h1>
        <form action="InsertarAutorServlet" method="post">
            <label for="nombreAutor">Nombre del autor:</label>
            <input type="text" name="nombreAutor" id="nombreAutor">
            <label for="apellidoAutor">Apellido del autor:</label>
            <input type="text" name="apellidoAutor" id="apellidoAutor">
            <input type="submit" value="Agregar autor">
        </form>
    </body>
</html>
