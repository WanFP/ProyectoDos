package cr.ac.ucr.paraiso.ie.progra2.proyecto2.data;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Autor;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutorXMLDao {

    private static int contadorAutores = 1;
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public AutorXMLDao(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public AutorXMLDao(String rutaDocumento) throws IOException, JDOMException {
        this.rutaDocumento = rutaDocumento;
        cargarDocumento();
    }

    private void cargarDocumento() throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);

        this.document = saxBuilder.build(this.rutaDocumento);
        this.raiz = this.document.getRootElement();
    }

    public static AutorXMLDao crearDocumento(String rutaDocumento) throws IOException {
        return new AutorXMLDao(rutaDocumento, "autores");
    }

//    private AutorXMLDao(String rutaDocumento) throws IOException, JDOMException {
//        SAXBuilder saBuilder = new SAXBuilder();
//        saBuilder.setIgnoringElementContentWhitespace(true);
//
//        this.document = saBuilder.build(rutaDocumento);
//        this.raiz = document.getRootElement();
//        this.rutaDocumento = rutaDocumento;
//    }
    public static AutorXMLDao abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new AutorXMLDao(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));

        // Extra para revisar el archivo
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarAutor(Autor autor) throws IOException {
        Element eAutor = new Element("autor");
        eAutor.setAttribute("id", String.valueOf(autor.getIdAutor()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(autor.getNombre());
        eAutor.addContent(eNombre);

        Element eApellido = new Element("apellido");
        eApellido.addContent(autor.getApellido());
        eAutor.addContent(eApellido);

        // Agrega más elementos del autor según sea necesario
        raiz.addContent(eAutor);
        guardar();
    }

    public ArrayList<Autor> getAutores() throws DataConversionException {
        List eListaAutores = raiz.getChildren();
        ArrayList<Autor> autores = new ArrayList<>();

        for (Object obj : eListaAutores) {
            Element eAutor = (Element) obj;
            Autor autorActual = new Autor();

            autorActual.setIdAutor(eAutor.getAttribute("id").getIntValue());
            autorActual.setNombre(eAutor.getChildText("nombre"));
            autorActual.setApellido(eAutor.getChildText("apellido"));

            // Agrega más atributos y elementos del autor según sea necesario
            autores.add(autorActual);
        }

        return autores;
    }

    public int generarCodigoAutor() {
        int codigo = contadorAutores;
        contadorAutores++;
        return codigo;
    }

    public Autor getAutorPorId(int idAutor) throws DataConversionException {
        List<Element> eListaAutores = raiz.getChildren();

        for (Element eAutor : eListaAutores) {
            int id = Integer.parseInt(eAutor.getAttributeValue("id"));
            if (id == idAutor) {
                Autor autor = new Autor();
                autor.setIdAutor(id);
                autor.setNombre(eAutor.getChildText("nombre"));
                autor.setApellido(eAutor.getChildText("apellido"));

                // Agrega más atributos y elementos del autor según sea necesario
                return autor;
            }
        }

        return null; // Si no se encuentra el autor con el ID especificado
    }

}
