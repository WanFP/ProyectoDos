package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.EditorialXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jdom2.JDOMException;

@WebServlet("/editoriales")
public class InsertarEditorialServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EditorialXMLDao editorialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            editorialDao = EditorialXMLDao.abrirDocumento("editoriales.xml");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            throw new ServletException("Error al abrir el documento XML de editoriales.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el nombre de la editorial ingresado por el usuario
        String nombreEditorial = request.getParameter("nombreEditorial");

        Editorial editorial = new Editorial();
        editorial.setNombreEditorial(nombreEditorial);
        editorial.setIdEditorial(editorialDao.generarCodigoEditorial());

        try {
            editorialDao.insertarEditorial(editorial);
            response.sendRedirect("index.html"); // Redireccionar a una página de éxito
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redireccionar a una página de error
        }
    }
}
