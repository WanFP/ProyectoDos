package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editoriales")
public class InsertarEditorialServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el nombre de la editorial ingresado por el usuario
        String nombreEditorial = request.getParameter("nombreEditorial");

        // Aquí puedes realizar las operaciones necesarias para agregar la nueva editorial a tu fuente de datos correspondiente
        // Redireccionar a una página de éxito o a donde desees
        response.sendRedirect("exito.jsp");
    }
}
