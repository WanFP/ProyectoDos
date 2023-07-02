package cr.ac.ucr.paraiso.ie.progra2.proyecto2.data;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Libro;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LibroXMLDao {

    private String rutaDocumento;
    private Element raiz;
    private Document documento;

    private LibroXMLDao(String rutaDocumento) throws IOException, JDOMException {
        File file = new File(rutaDocumento);
        if (!file.exists()) {
            this.rutaDocumento = rutaDocumento;
            this.raiz = new Element("catalogo");
            this.documento = new Document(raiz);
            guardar();
        } else {
            SAXBuilder saBuilder = new SAXBuilder();
            saBuilder.setIgnoringElementContentWhitespace(true);
            this.documento = saBuilder.build(new File(rutaDocumento));
            this.raiz = documento.getRootElement();
            this.rutaDocumento = rutaDocumento;
        }
    }

    public void guardar() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.documento, new PrintWriter(this.rutaDocumento));
        xmlOutputter.output(this.documento, System.out);
    }

    public static LibroXMLDao abrirDocumento(String rutaDocumento) throws JDOMException, IOException {
        return new LibroXMLDao(rutaDocumento);
    }

    public void insertarLibro(Libro libro) throws IOException {
        Element eLibro = new Element("libro");
        eLibro.setAttribute("idLibro", String.valueOf(libro.getIdLibro()));
        eLibro.setAttribute("isbn", String.valueOf(libro.getIsbn()));

        Element eTitulo = new Element("titulo");
        eTitulo.addContent(libro.getTitulo());

        Element eYear = new Element("year");
        eYear.addContent(String.valueOf(libro.getYear()));

        Element eEditorial = new Element("editorial");
        eEditorial.addContent(new Element("idEditorial").addContent(String.valueOf(libro.getEditorial().getIdEditorial())));
        eEditorial.addContent(new Element("nombre").addContent(libro.getEditorial().getNombreEditorial()));

        for (Autor autor : libro.getAutores()) {
            Element eAutor = new Element("autor");
            eAutor.addContent(new Element("idAutor").addContent(String.valueOf(autor.getIdAutor())));
            eAutor.addContent(new Element("nombre").addContent(autor.getNombre()));
            eLibro.addContent(eAutor);
        }

        eLibro.addContent(eTitulo);
        eLibro.addContent(eYear);
        eLibro.addContent(eEditorial);

        raiz.addContent(eLibro);
        guardar();
    }

    public List<Libro> buscarLibros(String titulo, String autor, String tema, String editorial) {
        List<Element> eLibros = raiz.getChildren("libro");
        List<Libro> librosEncontrados = new ArrayList<>();

        for (Element eLibro : eLibros) {
            int libroId = Integer.parseInt(eLibro.getAttributeValue("idLibro"));
            int libroIsbn = Integer.parseInt(eLibro.getAttributeValue("isbn"));
            String libroTitulo = eLibro.getChildText("titulo");
            int libroYear = Integer.parseInt(eLibro.getChildText("year"));

            if (titulo == null || titulo.isEmpty() || libroTitulo.contains(titulo)) {
                boolean autorEncontrado = false;
                List<Element> eAutores = eLibro.getChildren("autor");
                for (Element eAutor : eAutores) {
                    String nombreAutor = eAutor.getChildText("nombre");
                    if (autor == null || autor.isEmpty() || nombreAutor.contains(autor)) {
                        autorEncontrado = true;
                        break;
                    }
                }

                if (autorEncontrado) {
                    boolean temaEncontrado = false;
                    // Verificar tema aquí si es necesario

                    if (temaEncontrado) {
                        boolean editorialEncontrada = false;
                        Element eEditorial = eLibro.getChild("editorial");
                        if (eEditorial != null) {
                            String nombreEditorial = eEditorial.getChildText("nombre");
                            if (editorial == null || editorial.isEmpty() || nombreEditorial.contains(editorial)) {
                                editorialEncontrada = true;
                            }
                        } else {
                            editorialEncontrada = true;
                        }

                        if (editorialEncontrada) {
                            Libro libro = new Libro();
                            libro.setIdLibro(libroId);
                            libro.setIsbn(libroIsbn);
                            libro.setTitulo(libroTitulo);
                            libro.setYear(libroYear);

                            // Obtener la información adicional del libro, como la editorial y los autores,
                            // y asignarla al objeto Libro

                            librosEncontrados.add(libro);
                        }
                    }
                }
            }
        }

        return librosEncontrados;
    }

    public void insertarAutor(Autor autor) throws IOException {
        Element eAutor = new Element("autor");
        eAutor.setAttribute("idAutor", String.valueOf(autor.getIdAutor()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(autor.getNombre());

        Element eApellido = new Element("apellido");
        eApellido.addContent(autor.getApellido());
        
        eAutor.addContent(eNombre);
        eAutor.addContent(eApellido);
        
        raiz.addContent(eAutor);
        guardar();
    }

    public List<Autor> getAutores() {
        List<Element> eAutores = raiz.getChildren("autor");
        List<Autor> autores = new ArrayList<>();

        for (Element eAutor : eAutores) {
            int autorId = Integer.parseInt(eAutor.getAttributeValue("idAutor"));
            String autorNombre = eAutor.getChildText("nombre");
            String autorApellido = eAutor.getChildText("apellido");

            Autor autor = new Autor();
            autor.setIdAutor(autorId);
            autor.setNombre(autorNombre);
            autor.setApellido(autorApellido);

            // Agrega más atributos y elementos del autor según sea necesario

            autores.add(autor);
        }

        return autores;
    }

    public void insertarEditorial(Editorial editorial) throws IOException {
        Element eEditorial = new Element("editorial");
        eEditorial.setAttribute("idEditorial", String.valueOf(editorial.getIdEditorial()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(editorial.getNombreEditorial());

        // Agrega más elementos de la editorial según sea necesario

        eEditorial.addContent(eNombre);

        raiz.addContent(eEditorial);
        guardar();
    }

    public List<Editorial> getEditoriales() {
        List<Element> eEditoriales = raiz.getChildren("editorial");
        List<Editorial> editoriales = new ArrayList<>();

        for (Element eEditorial : eEditoriales) {
            int editorialId = Integer.parseInt(eEditorial.getAttributeValue("idEditorial"));
            String editorialNombre = eEditorial.getChildText("nombre");

            Editorial editorial = new Editorial();
            editorial.setIdEditorial(editorialId);
            editorial.setNombreEditorial(editorialNombre);

            // Agrega más atributos y elementos de la editorial según sea necesario

            editoriales.add(editorial);
        }

        return editoriales;
    }
}
