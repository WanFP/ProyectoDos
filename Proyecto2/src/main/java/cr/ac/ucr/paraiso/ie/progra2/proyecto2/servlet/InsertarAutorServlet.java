package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.AutorXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jdom2.JDOMException;

@WebServlet("/autores")
public class InsertarAutorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AutorXMLDao autorDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            autorDao = AutorXMLDao.abrirDocumento("autores.xml");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            throw new ServletException("Error al abrir el documento XML de autores.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del autor ingresados por el usuario
        String nombreAutor = request.getParameter("nombreAutor");
        String apellidoAutor = request.getParameter("apellidoAutor");

        Autor autor = new Autor();
        autor.setNombre(nombreAutor);
        autor.setApellido(apellidoAutor);
        autor.setIdAutor(autorDao.generarCodigoAutor());

        try {
            autorDao.insertarAutor(autor);
            response.sendRedirect("index.html"); // Redireccionar a una página de éxito
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redireccionar a una página de error
        }
    }
}
