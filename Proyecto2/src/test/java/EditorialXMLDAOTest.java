
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.EditorialXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class EditorialXMLDAOTest {

    private EditorialXMLDao editorialXMLDao;

    @Before
    public void init() throws IOException, JDOMException {
        EditorialXMLDao.crearDocumento("editoriales.xml");
        editorialXMLDao = EditorialXMLDao.abrirDocumento("editoriales.xml");

        Editorial editorial1 = new Editorial();
        editorial1.setIdEditorial(100);
        editorial1.setNombreEditorial("Editorial 1");
        editorialXMLDao.insertarEditorial(editorial1);

        Editorial editorial2 = new Editorial();
        editorial2.setIdEditorial(200);
        editorial2.setNombreEditorial("Editorial 2");
        editorialXMLDao.insertarEditorial(editorial2);

        Editorial editorial3 = new Editorial();
        editorial3.setIdEditorial(300);
        editorial3.setNombreEditorial("Editorial 3");
        editorialXMLDao.insertarEditorial(editorial3);

        System.out.println("Impresión de las editoriales insertadas");
        ArrayList<Editorial> editoriales = editorialXMLDao.getEditoriales();
        for (Iterator<Editorial> editorialActual = editoriales.iterator(); editorialActual.hasNext();) {
            Editorial editorial = editorialActual.next();
            System.out.printf("\nID: %d - Nombre: %s", editorial.getIdEditorial(), editorial.getNombreEditorial());
        }
    }

    @Test
    public void probarXML() {
        // Aquí puedes agregar pruebas adicionales relacionadas con el archivo XML de editoriales
    }

}
