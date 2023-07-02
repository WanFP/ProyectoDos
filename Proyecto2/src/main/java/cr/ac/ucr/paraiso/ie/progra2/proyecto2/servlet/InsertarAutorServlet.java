package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/autores")
public class InsertarAutorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del autor ingresados por el usuario
        String nombreAutor = request.getParameter("nombreAutor");
        String apellidoAutor = request.getParameter("apellidoAutor");

        // Aquí puedes realizar las operaciones necesarias para agregar el nuevo autor a tu fuente de datos correspondiente
        // Redireccionar a una página de éxito o a donde desees
        response.sendRedirect("exito.jsp");
    }
}
