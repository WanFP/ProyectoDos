package cr.ac.ucr.paraiso.ie.progra2.proyecto2.data;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Editorial;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorialXMLDao {

    private static int contadorEditoriales = 1;
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public EditorialXMLDao(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static EditorialXMLDao crearDocumento(String rutaDocumento) throws IOException {
        return new EditorialXMLDao(rutaDocumento, "editoriales");
    }

    public EditorialXMLDao(String rutaDocumento) throws IOException, JDOMException {
        this.rutaDocumento = rutaDocumento;
        cargarDocumento();
    }

    private void cargarDocumento() throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);

        this.document = saxBuilder.build(this.rutaDocumento);
        this.raiz = this.document.getRootElement();
    }

    public static EditorialXMLDao abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new EditorialXMLDao(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));

        // Extra para revisar el archivo
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarEditorial(Editorial editorial) throws IOException {
        Element eEditorial = new Element("editorial");
        eEditorial.setAttribute("id", String.valueOf(editorial.getIdEditorial()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(editorial.getNombreEditorial());
        eEditorial.addContent(eNombre);

        // Agrega más elementos de la editorial según sea necesario
        raiz.addContent(eEditorial);
        guardar();
    }

    public ArrayList<Editorial> getEditoriales() throws DataConversionException {
        List eListaEditoriales = raiz.getChildren();
        ArrayList<Editorial> editoriales = new ArrayList<>();

        for (Object obj : eListaEditoriales) {
            Element eEditorial = (Element) obj;
            Editorial editorialActual = new Editorial();

            editorialActual.setIdEditorial(eEditorial.getAttribute("id").getIntValue());
            editorialActual.setNombreEditorial(eEditorial.getChildText("nombre"));

            // Agrega más atributos y elementos de la editorial según sea necesario
            editoriales.add(editorialActual);
        }

        return editoriales;
    }

    public int generarCodigoEditorial() {
        int codigo = contadorEditoriales;
        contadorEditoriales++;
        return codigo;
    }

    public Editorial getEditorialPorId(int idEditorial) throws DataConversionException {
        List<Element> eListaEditoriales = raiz.getChildren();

        for (Element eEditorial : eListaEditoriales) {
            int id = Integer.parseInt(eEditorial.getAttributeValue("id"));
            if (id == idEditorial) {
                Editorial editorial = new Editorial();
                editorial.setIdEditorial(id);
                editorial.setNombreEditorial(eEditorial.getChildText("nombre"));

                // Agrega más atributos y elementos de la editorial según sea necesario
                return editorial;
            }
        }

        return null; // Si no se encuentra la editorial con el ID especificado
    }

}
