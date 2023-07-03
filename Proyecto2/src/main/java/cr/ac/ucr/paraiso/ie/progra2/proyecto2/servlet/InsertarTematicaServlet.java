package cr.ac.ucr.paraiso.ie.progra2.proyecto2.servlet;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.TematicaXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jdom2.JDOMException;

@WebServlet("/tematicas")
public class InsertarTematicaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TematicaXMLDao tematicaDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            tematicaDao = TematicaXMLDao.abrirDocumento("tematicas.xml");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            throw new ServletException("Error al abrir el documento XML de temáticas.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el nombre de la temática ingresado por el usuario
        String nombreTematica = request.getParameter("nombreTematica");

        Tematica tematica = new Tematica();
        tematica.setNombreTematica(nombreTematica);
        tematica.setIdTematica(tematicaDao.generarCodigoTematica());

        try {
            tematicaDao.insertarTematica(tematica);
            response.sendRedirect("index.html"); // Redireccionar a una página de éxito
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redireccionar a una página de error
        }
    }
}
