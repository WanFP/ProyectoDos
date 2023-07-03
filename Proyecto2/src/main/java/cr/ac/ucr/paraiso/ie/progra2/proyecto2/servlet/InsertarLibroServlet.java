package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.LibroXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
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
    private LibroXMLDao libroDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            libroDao = LibroXMLDao.abrirDocumento("libros.xml");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            throw new ServletException("Error al abrir el documento XML de libros.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del libro ingresados por el usuario
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String year = request.getParameter("year");
        String idEditorial = request.getParameter("idEditorial");
        String[] idAutores = request.getParameterValues("idAutores");
        String idTematica = request.getParameter("idTematica");

        // Crear una instancia de libro
        Libro libro = new Libro();
        libro.setIsbn(Integer.parseInt(isbn));
        libro.setTitulo(titulo);
        libro.setYear(Integer.parseInt(year));

        try {
            // Obtener la editorial seleccionada
            Editorial editorial = obtenerEditorialPorId(idEditorial);
            libro.setEditorial(editorial);

            // Obtener los autores seleccionados
            List<Autor> autores = obtenerAutoresPorIds(idAutores);
            libro.setAutores(autores);

            Tematica tematica = obtenerTematicaPorId(idTematica);
            libro.setTematica(tematica);
            
            libroDao.insertarLibro(libro);
            response.sendRedirect("index.html"); // Redireccionar a una página de éxito
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redireccionar a una página de error
        }
    }

    private Editorial obtenerEditorialPorId(String idEditorial) throws IOException, JDOMException {
        try {
            int id = Integer.parseInt(idEditorial);
            ArrayList<Editorial> editoriales = libroDao.getEditoriales();
            for (Editorial editorial : editoriales) {
                if (editorial.getIdEditorial() == id) {
                    return editorial;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Tematica obtenerTematicaPorId(String idTematica) throws IOException, JDOMException {
        try {
            int id = Integer.parseInt(idTematica);
            ArrayList<Tematica> tematicas = libroDao.getTematicas();
            for (Tematica tematica : tematicas) {
                if (tematica.getIdTematica() == id) {
                    return tematica;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Autor> obtenerAutoresPorIds(String[] idAutores) throws IOException, JDOMException {
        List<Autor> autores = new ArrayList<>();
        if (idAutores != null) {
            try {
                for (String idAutor : idAutores) {
                    int id = Integer.parseInt(idAutor);
                    ArrayList<Autor> listaAutores = libroDao.getAutores();
                    for (Autor autor : listaAutores) {
                        if (autor.getIdAutor() == id) {
                            autores.add(autor);
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return autores;
    }

}
