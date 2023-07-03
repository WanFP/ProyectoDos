
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.TematicaXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TematicaXMLDAOTest {

    private TematicaXMLDao tematicaXMLDao;

    @Before
    public void init() throws IOException, JDOMException {
        TematicaXMLDao.crearDocumento("tematicas.xml");
        tematicaXMLDao = TematicaXMLDao.abrirDocumento("tematicas.xml");

        Tematica tematica1 = new Tematica();
        tematica1.setIdTematica(100);
        tematica1.setNombreTematica("Tematica 1");
        tematicaXMLDao.insertarTematica(tematica1);

        Tematica tematica2 = new Tematica();
        tematica2.setIdTematica(200);
        tematica2.setNombreTematica("Tematica 2");
        tematicaXMLDao.insertarTematica(tematica2);

        Tematica tematica3 = new Tematica();
        tematica3.setIdTematica(300);
        tematica3.setNombreTematica("Tematica 3");
        tematicaXMLDao.insertarTematica(tematica3);

        System.out.println("Impresión de las temáticas insertadas");
        ArrayList<Tematica> tematicas = tematicaXMLDao.getTematicas();
        for (Iterator<Tematica> tematicaActual = tematicas.iterator(); tematicaActual.hasNext();) {
            Tematica tematica = tematicaActual.next();
            System.out.printf("\nID: %d - Nombre: %s", tematica.getIdTematica(), tematica.getNombreTematica());
        }
    }

    @Test
    public void probarXML() {
        // Aquí puedes agregar pruebas adicionales relacionadas con el archivo XML de temáticas
    }

}
