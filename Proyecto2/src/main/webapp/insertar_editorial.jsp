<%@ page import="java.util.ArrayList" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial" %>
<%@ page import="cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.EditorialXMLDao" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    EditorialXMLDao editorialXMLDAO = null;
    try {
        editorialXMLDAO = EditorialXMLDao.abrirDocumento("editoriales.xml");
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
        <title>Formulario Editorial</title>
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

            .table-container {
                width: 50%;
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

            .form-container input[type="text"] {
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

            .table-container table {
                width: 100%;
                border-collapse: collapse;
            }

            .table-container th, .table-container td {
                padding: 8px;
                border: 1px solid gray;
            }

            .table-container th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            .table-container tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .table-container tr:hover {
                background-color: #ddd;
            }

            .back-link {
                margin-top: 20px;
            }
        </style>
        <script>
            function validateForm() {
                var nombreEditorial = document.getElementById("nombreEditorial").value;

                if (nombreEditorial === "") {
                    alert("Por favor, complete el campo Nombre de la editorial.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="form-container">
                <h1>Formulario de Ingreso de Editorial</h1>
                <form action="/Proyecto2/editoriales" method="post" onsubmit="return validateForm();">
                    <label for="nombreEditorial">Nombre de la editorial:</label>
                    <input type="text" name="nombreEditorial" id="nombreEditorial">
                    <input type="submit" value="Agregar editorial">
                </form>
                <br>
                <a href="index.html" class="back-link">Volver al inicio</a>
            </div>
            <div class="table-container">
                <h1>Editoriales</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                    </tr>
                    <% 
                    ArrayList<Editorial> editoriales = editorialXMLDAO.getEditoriales();
                    for (Editorial editorial : editoriales) {
                    %>
                    <tr>
                        <td><%= editorial.getIdEditorial() %></td>
                        <td><%= editorial.getNombreEditorial() %></td>
                    </tr>
                    <% } %>
                </table>

            </div>
        </div>
    </body>
</html>
