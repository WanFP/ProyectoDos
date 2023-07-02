package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.LibroXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.JDOMException;

@WebServlet("/libros")
public class InsertarLibroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String RUTA_DOCUMENTO = "catalogo.xml";  // Reemplaza con la ruta correcta

    private LibroXMLDao libroDao;

    @Override
    public void init() throws ServletException {
        try {
            libroDao = LibroXMLDao.abrirDocumento(getServletContext().getRealPath(RUTA_DOCUMENTO));
        } catch (JDOMException | IOException e) {
            throw new ServletException("Error al inicializar el DAO del libro", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        int idLibro = Integer.parseInt(request.getParameter("idLibro"));
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String titulo = request.getParameter("titulo");
        int year = Integer.parseInt(request.getParameter("year"));
        int idEditorial = Integer.parseInt(request.getParameter("editorial"));
        String[] idAutores = request.getParameterValues("autores");

        // Crear el objeto Libro con los parámetros obtenidos
        Libro libro = new Libro();
        libro.setIdLibro(idLibro);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setYear(year);

        // Obtener la lista de editoriales y autores del archivo XML
        List<Editorial> editoriales = libroDao.getEditoriales();
        List<Autor> autores = libroDao.getAutores();

        // Buscar la editorial seleccionada por su id
        Editorial editorialSeleccionada = null;
        for (Editorial editorial : editoriales) {
            if (editorial.getIdEditorial() == idEditorial) {
                editorialSeleccionada = editorial;
                break;
            }
        }

        // Buscar los autores seleccionados por sus ids
        List<Autor> autoresSeleccionados = new ArrayList<>();
        for (String idAutor : idAutores) {
            int idAutorSeleccionado = Integer.parseInt(idAutor);
            for (Autor autor : autores) {
                if (autor.getIdAutor() == idAutorSeleccionado) {
                    autoresSeleccionados.add(autor);
                    break;
                }
            }
        }

        // Asignar la editorial y los autores al libro
        libro.setEditorial(editorialSeleccionada);
        libro.setAutores(autoresSeleccionados);

        try {
            // Insertar el libro en el archivo XML
            libroDao.insertarLibro(libro);
            response.sendRedirect("exito.jsp"); // Redireccionar a una página de éxito
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redireccionar a una página de error
        }
    }
}
