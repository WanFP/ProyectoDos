
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.LibroXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibroXMLDAOTest {

    private LibroXMLDao libroXMLDao;

    @Before
    public void init() throws IOException, JDOMException {
        libroXMLDao = new LibroXMLDao("libros.xml");

        // Crear algunos libros de ejemplo
        Libro libro1 = new Libro();
        libro1.setIdLibro(1);
        libro1.setIsbn(123456789);
        libro1.setTitulo("Libro 1");
        libro1.setYear(2021);

        Editorial editorial1 = new Editorial();
        editorial1.setIdEditorial(100);
        editorial1.setNombreEditorial("Editorial 1");

        libro1.setEditorial(editorial1);

        Autor autor1 = new Autor();
        autor1.setIdAutor(1);
        autor1.setNombre("Autor 1");

        Autor autor2 = new Autor();
        autor2.setIdAutor(2);
        autor2.setNombre("Autor 2");

        List<Autor> autores1 = new ArrayList<>();
        autores1.add(autor1);
        autores1.add(autor2);

        libro1.setAutores(autores1);

        Tematica tematica1 = new Tematica();
        tematica1.setIdTematica(100);
        tematica1.setNombreTematica("Tematica 1");

        libro1.setTematica(tematica1);

        libroXMLDao.insertarLibro(libro1);

        System.out.println("Impresión de los libros insertados");
        ArrayList<Libro> libros = libroXMLDao.getLibros();
        for (Iterator<Libro> libroActual = libros.iterator(); libroActual.hasNext();) {
            Libro libro = libroActual.next();
            System.out.printf("\nID: %d - ISBN: %d - Título: %s", libro.getIdLibro(), libro.getIsbn(), libro.getTitulo());
        }
    }

    @Test
    public void probarXML() {
        // Aquí puedes agregar pruebas adicionales relacionadas con el archivo XML de libros
    }
}
