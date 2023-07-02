<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insertar Libro</title>
    </head>
    <body>
        <h1>Insertar Libro</h1>

        <form action="InsertarLibroServlet" method="post">
            <label for="idLibro">ID del Libro:</label>
            <input type="number" id="idLibro" name="idLibro" required><br>

            <label for="isbn">ISBN:</label>
            <input type="number" id="isbn" name="isbn" required><br>

            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" required><br>

            <label for="year">Año:</label>
            <input type="number" id="year" name="year" required><br>

            <!-- Agrega más campos del libro según sea necesario -->

            <input type="submit" value="Guardar">
        </form>

        <br>

        <a href="index.jsp">Volver al inicio</a>
    </body>
</html>
