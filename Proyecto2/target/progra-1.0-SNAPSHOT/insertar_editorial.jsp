<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Formulario Editorial</title>
    </head>
    <body>
        <h1>Formulario de Ingreso de Editorial</h1>
        <form action="InsertarEditorialServlet" method="post">
            <label for="nombreEditorial">Nombre de la editorial:</label>
            <input type="text" name="nombreEditorial" id="nombreEditorial">
            <input type="submit" value="Agregar editorial">
        </form>
    </body>
</html>
