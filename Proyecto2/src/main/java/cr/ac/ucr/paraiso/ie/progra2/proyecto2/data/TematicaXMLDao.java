package cr.ac.ucr.paraiso.ie.progra2.proyecto2.data;

import cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio.Tematica;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TematicaXMLDao {

    private static int contadorTematicas = 1;
    private Document document;
    private Element raiz;
    private String rutaDocumento;

    public TematicaXMLDao(String rutaDocumento, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.rutaDocumento = rutaDocumento;
        this.document = new Document(raiz);
        guardar();
    }

    public static TematicaXMLDao crearDocumento(String rutaDocumento) throws IOException {
        return new TematicaXMLDao(rutaDocumento, "tematicas");
    }

    public TematicaXMLDao(String rutaDocumento) throws IOException, JDOMException {
        this.rutaDocumento = rutaDocumento;
        cargarDocumento();
    }

    private void cargarDocumento() throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setIgnoringElementContentWhitespace(true);

        this.document = saxBuilder.build(this.rutaDocumento);
        this.raiz = this.document.getRootElement();
    }

    public static TematicaXMLDao abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        return new TematicaXMLDao(rutaDocumento);
    }

    public void guardar() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(this.document, new FileWriter(this.rutaDocumento));

        // Extra para revisar el archivo
        xmlOutputter.output(this.document, System.out);
    }

    public void insertarTematica(Tematica tematica) throws IOException {
        Element eTematica = new Element("tematica");
        eTematica.setAttribute("id", String.valueOf(tematica.getIdTematica()));

        Element eNombre = new Element("nombre");
        eNombre.addContent(tematica.getNombreTematica());
        eTematica.addContent(eNombre);

        // Agrega más elementos de la temática según sea necesario
        raiz.addContent(eTematica);
        guardar();
    }

    public ArrayList<Tematica> getTematicas() throws DataConversionException {
        List<Element> eListaTematicas = raiz.getChildren();
        ArrayList<Tematica> tematicas = new ArrayList<>();

        for (Element eTematica : eListaTematicas) {
            Tematica tematicaActual = new Tematica();

            tematicaActual.setIdTematica(eTematica.getAttribute("id").getIntValue());
            tematicaActual.setNombreTematica(eTematica.getChildText("nombre"));

            // Agrega más atributos y elementos de la temática según sea necesario
            tematicas.add(tematicaActual);
        }

        return tematicas;
    }

    public int generarCodigoTematica() {
        int codigo = contadorTematicas;
        contadorTematicas++;
        return codigo;
    }

    public Tematica getTematicaPorId(int idTematica) throws DataConversionException {
        List<Element> eListaTematicas = raiz.getChildren();

        for (Element eTematica : eListaTematicas) {
            int id = Integer.parseInt(eTematica.getAttributeValue("id"));
            if (id == idTematica) {
                Tematica tematica = new Tematica();
                tematica.setIdTematica(id);
                tematica.setNombreTematica(eTematica.getChildText("nombre"));

                // Agrega más atributos y elementos de la temática según sea necesario
                return tematica;
            }
        }

        return null; // Si no se encuentra la temática con el ID especificado
    }

}
