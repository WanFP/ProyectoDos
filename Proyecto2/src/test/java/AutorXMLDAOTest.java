
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.AutorXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AutorXMLDAOTest {

    private AutorXMLDao autorXMLDao;

    @Before
    public void init() throws IOException, JDOMException {
        AutorXMLDao.crearDocumento("autores.xml");
        autorXMLDao = AutorXMLDao.abrirDocumento("autores.xml");

        Autor autor1 = new Autor();
        autor1.setIdAutor(100);
        autor1.setNombre("Autor 1");
        autor1.setApellido("Apellido 1");
        autorXMLDao.insertarAutor(autor1);

        Autor autor2 = new Autor();
        autor2.setIdAutor(200);
        autor2.setNombre("Autor 2");
        autor2.setApellido("Apellido 2");
        autorXMLDao.insertarAutor(autor2);

        Autor autor3 = new Autor();
        autor3.setIdAutor(300);
        autor3.setNombre("Autor 3");
        autor3.setApellido("Apellido 3");
        autorXMLDao.insertarAutor(autor3);

        System.out.println("Impresión de los autores insertados");
        ArrayList<Autor> autores = autorXMLDao.getAutores();
        for (Iterator<Autor> autorActual = autores.iterator(); autorActual.hasNext();) {
            Autor autor = autorActual.next();
            System.out.printf("\nID: %d - Nombre: %s - Apellido: %s", autor.getIdAutor(), autor.getNombre(), autor.getApellido());
        }
    }

    @Test
    public void probarXML() {
        // Aquí puedes agregar pruebas adicionales relacionadas con el archivo XML de autores
    }

}
