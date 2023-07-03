<%@ page import="java.util.ArrayList" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.EditorialXMLDao" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.AutorXMLDao" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.LibroXMLDao" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.TematicaXMLDao" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    EditorialXMLDao editorialXMLDAO = null;
    AutorXMLDao autorXMLDao = null;
    LibroXMLDao libroXMLDao = null;
    TematicaXMLDao tematicaXMLDao = null;
    try {
        editorialXMLDAO = EditorialXMLDao.abrirDocumento("editoriales.xml");
        autorXMLDao = AutorXMLDao.abrirDocumento("autores.xml");
        tematicaXMLDao= TematicaXMLDao.abrirDocumento("tematicas.xml");
        libroXMLDao = LibroXMLDao.abrirDocumento("libros.xml");
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JDOMException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Formulario de Ingreso de Libro</title>
        <style>
            body {
                background-color: white;
                font-family: Arial, sans-serif;
            }

            .container {
                display: flex;
                align-items: flex-start;
                justify-content: space-between;
                height: 100vh;
                padding: 20px;
            }

            .form-container {
                width: 40%;
            }

            .form-container h1 {
                color: black;
                margin-bottom: 20px;
                font-size: 24px;
                font-weight: bold;
            }

            .form-container form {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }

            .form-container label {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .form-container input[type="text"], .form-container select {
                padding: 5px;
                margin-bottom: 10px;
                width: 200px;
                border: 1px solid gray;
                border-radius: 4px;
            }

            .form-container input[type="submit"] {
                background-color: black;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .back-link {
                margin-top: 20px;
            }

            .table-container {
                width: 60%;
            }

            .table-container h1 {
                color: black;
                margin-bottom: 20px;
                font-size: 24px;
                font-weight: bold;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }
        </style>
        <script>
            function validateForm() {
                var isbn = document.getElementById("isbn").value;
                var titulo = document.getElementById("titulo").value;
                var year = document.getElementById("year").value;
                var idEditorial = document.getElementById("idEditorial").value;
                var idAutores = document.getElementsByName("idAutores");
                var idTematica = document.getElementById("idTematica").value;

                if (isbn.trim() === "") {
                    alert("Por favor, complete el campo ISBN.");
                    return false;
                }

                if (titulo.trim() === "") {
                    alert("Por favor, complete el campo Título.");
                    return false;
                }

                if (year.trim() === "") {
                    alert("Por favor, complete el campo Año.");
                    return false;
                }

                if (idEditorial.trim() === "") {
                    alert("Por favor, seleccione una editorial.");
                    return false;
                }

                var selectedAutores = Array.from(idAutores).filter(function (checkbox) {
                    return checkbox.checked;
                });

                if (selectedAutores.length === 0) {
                    alert("Por favor, seleccione al menos un autor.");
                    return false;
                }

                if (idTematica.trim() === "") {
                    alert("Por favor seleccione una temática.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="form-container">
                <h1>Formulario de Ingreso de Libro</h1>
                <form action="/Proyecto2/libros" method="post" onsubmit="return validateForm();">
                    <label for="isbn">ISBN:</label>
                    <input type="text" name="isbn" id="isbn">

                    <label for="titulo">Título:</label>
                    <input type="text" name="titulo" id="titulo">

                    <label for="year">Año:</label>
                    <input type="text" name="year" id="year">

                    <label for="idEditorial">Editorial:</label>
                    <select name="idEditorial" id="idEditorial">
                        <option value="">Seleccione una editorial</option>
                        <% 
                        ArrayList<Editorial> editoriales = editorialXMLDAO.getEditoriales();
                        for (Editorial editorial : editoriales) {
                        %>
                        <option value="<%= editorial.getIdEditorial() %>"><%= editorial.getNombreEditorial() %></option>
                        <% } %>
                    </select>

                    <label for="idAutores">Autores:</label>
                    <%
                    ArrayList<Autor> autores = autorXMLDao.getAutores();
                    for (Autor autor : autores) {
                    %>
                    <label>
                        <input type="checkbox" name="idAutores" value="<%= autor.getIdAutor() %>">
                        <%= autor.getNombre() + " " + autor.getApellido() %>
                    </label>
                    <br>
                    <% } %>

                    <label for="idTematica">Tematica:</label>
                    <select name="idTematica" id="idTematica">
                        <option value="">Seleccione una tematica</option>
                        <% 
                        ArrayList<Tematica> tematicas = tematicaXMLDao.getTematicas();
                        for (Tematica tematica : tematicas) {
                        %>
                        <option value="<%= tematica.getIdTematica() %>"><%= tematica.getNombreTematica() %></option>
                        <% } %>
                    </select>

                    <input type="submit" value="Agregar libro">
                </form>
                <br>
                <a href="index.html" class="back-link">Volver al inicio</a>
            </div>
            <div class="table-container">
                <h1>Libros existentes</h1>
                <table>
                    <tr>
                        <th>ISBN</th>
                        <th>Título</th>
                        <th>Año</th>
                        <th>Editorial</th>
                        <th>Autores</th>
                        <th>Tematica</th>
                    </tr>
                    <% 
                    ArrayList<Libro> libros = libroXMLDao.getLibros();
                    for (Libro libro : libros) {
                    %>
                    <tr>
                        <td><%= libro.getIsbn() %></td>
                        <td><%= libro.getTitulo() %></td>
                        <td><%= libro.getYear() %></td>
                        <%
                            Editorial editorialLibro = libro.getEditorial();
                            String nombreEditorial = (editorialLibro != null) ? editorialLibro.getNombreEditorial() : "";
                            int idEditorial = (editorialLibro != null) ? editorialLibro.getIdEditorial() : 0;
                        %>
                        <td><%=nombreEditorial %></td>
                        <td>
                            <ul>
                                <%
                                List<Autor> autoresLibro = libro.getAutores();
                                if (autoresLibro != null && !autoresLibro.isEmpty()) {
                                    for (Autor autor : autoresLibro) {
                                        if (autor != null) {
                                %>
                                <li><%= autor.getNombre() + " " + autor.getApellido() %></li>
                                    <% 
                                            }
                                        }
                                    } else {
                                    %>
                                <li>No hay autores registrados para este libro</li>
                                    <% } %>

                            </ul>
                        </td>
                        <%
                            Tematica tematicaLibro = libro.getTematica();
                            String nombreTematica = (tematicaLibro != null) ? tematicaLibro.getNombreTematica() : "";
                            int idTematica = (tematicaLibro != null) ? tematicaLibro.getIdTematica() : 0;
                        %>
                        <td><%=nombreTematica %></td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>
