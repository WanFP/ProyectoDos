package cr.ac.ucr.paraiso.ie.progra2.proyecto2.data;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.AutorXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.data.EditorialXMLDao;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.input.SAXBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibroXMLDao {

    private static int contadorLibros = 1;
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public LibroXMLDao(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static LibroXMLDao crearDocumento(String rutaDocumento) throws IOException {
        return new LibroXMLDao(rutaDocumento, "libros");
    }

    public LibroXMLDao(String rutaDocumento) throws IOException, JDOMException {
        this.rutaDocumento = rutaDocumento;
        cargarDocumento();
    }

    private void cargarDocumento() throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);

        this.document = saxBuilder.build(this.rutaDocumento);
        this.raiz = this.document.getRootElement();
    }

    public static LibroXMLDao abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new LibroXMLDao(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));

        // Extra para revisar el archivo
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarLibro(Libro libro) throws IOException {
        Element eLibro = new Element("libro");
        eLibro.setAttribute("id", String.valueOf(libro.getIdLibro()));

        Element eISBN = new Element("isbn");
        eISBN.addContent(String.valueOf(libro.getIsbn()));
        eLibro.addContent(eISBN);

        Element eTitulo = new Element("titulo");
        eTitulo.addContent(libro.getTitulo());
        eLibro.addContent(eTitulo);

        Element eYear = new Element("year");
        eYear.addContent(String.valueOf(libro.getYear()));
        eLibro.addContent(eYear);

        Element eEditorial = new Element("editorial");
        eEditorial.setAttribute("id", String.valueOf(libro.getEditorial().getIdEditorial()));
        eEditorial.addContent(libro.getEditorial().getNombreEditorial());
        eLibro.addContent(eEditorial);

        Element eAutores = new Element("autores");
        for (Autor autor : libro.getAutores()) {
            Element eAutor = new Element("autor");
            eAutor.setAttribute("id", String.valueOf(autor.getIdAutor()));
            eAutor.addContent(autor.getNombre() + " " + autor.getApellido());
            eAutores.addContent(eAutor);
        }
        eLibro.addContent(eAutores);

        Element eTematica = new Element("tematica");
        eTematica.setAttribute("id", String.valueOf(libro.getTematica().getIdTematica()));
        eTematica.addContent(libro.getTematica().getNombreTematica());
        eLibro.addContent(eTematica);

        raiz.addContent(eLibro);
        guardar();
    }

    public ArrayList<Libro> getLibros() throws DataConversionException, IOException, JDOMException {
        List<Element> eListaLibros = raiz.getChildren();
        ArrayList<Libro> libros = new ArrayList<>();

        for (Element eLibro : eListaLibros) {
            Libro libroActual = new Libro();

            libroActual.setIdLibro(eLibro.getAttribute("id").getIntValue());
            libroActual.setIsbn(Integer.parseInt(eLibro.getChildText("isbn")));
            libroActual.setTitulo(eLibro.getChildText("titulo"));
            libroActual.setYear(Integer.parseInt(eLibro.getChildText("year")));

            Element eEditorial = eLibro.getChild("editorial");
            EditorialXMLDao editorialDao = new EditorialXMLDao("editoriales.xml");
            Editorial editorial = editorialDao.getEditorialPorId(eEditorial.getAttribute("id").getIntValue());
            libroActual.setEditorial(editorial);

            Element eAutores = eLibro.getChild("autores");
            List<Element> eListaAutores = eAutores.getChildren();
            ArrayList<Autor> autores = new ArrayList<>();
            for (Element eAutor : eListaAutores) {
                AutorXMLDao autorDao = new AutorXMLDao("autores.xml");
                Autor autor = autorDao.getAutorPorId(eAutor.getAttribute("id").getIntValue());
                autores.add(autor);
            }
            libroActual.setAutores(autores);

            Element eTematica = eLibro.getChild("tematica");
            TematicaXMLDao tematicaDao = new TematicaXMLDao("tematicas.xml");
            Tematica tematica = tematicaDao.getTematicaPorId(eTematica.getAttribute("id").getIntValue());
            libroActual.setTematica(tematica);

            libros.add(libroActual);
        }

        return libros;
    }

    public int generarCodigoLibro() {
        int codigo = contadorLibros;
        contadorLibros++;
        return codigo;
    }

    public ArrayList<Editorial> getEditoriales() throws DataConversionException, IOException, JDOMException {
        EditorialXMLDao editorialDao = new EditorialXMLDao("editoriales.xml");
        return editorialDao.getEditoriales();
    }

    public ArrayList<Autor> getAutores() throws DataConversionException, IOException, JDOMException {
        AutorXMLDao autorDao = new AutorXMLDao("autores.xml");
        return autorDao.getAutores();
    }

    public Editorial getEditorialPorId(int idEditorial) throws DataConversionException, IOException, JDOMException {
        EditorialXMLDao editorialDao = new EditorialXMLDao("editoriales.xml");
        return editorialDao.getEditorialPorId(idEditorial);
    }

    public Tematica getTematicaPorId(int idTematica) throws DataConversionException, IOException, JDOMException {
        TematicaXMLDao tematicaDao = new TematicaXMLDao("tematicas.xml");
        return tematicaDao.getTematicaPorId(idTematica);
    }

    public ArrayList<Tematica> getTematicas() throws DataConversionException, IOException, JDOMException {
        TematicaXMLDao tematicaDao = new TematicaXMLDao("tematicas.xml");
        return tematicaDao.getTematicas();
    }
}
