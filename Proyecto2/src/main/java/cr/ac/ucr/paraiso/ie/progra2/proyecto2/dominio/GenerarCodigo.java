package cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio;

public class GenerarCodigo {

    private static int contadorLibros = 1; // Contador para los códigos de libros
    private static int contadorEditoriales = 1;

    public static String generarCodigoLibro() {
        String codigo = "LIB" + contadorLibros;
        contadorLibros++;
        return codigo;
    }

    public static String generarCodigoEditorial() {
        String codigo = "EDIT" + contadorEditoriales;
        contadorEditoriales++;
        return codigo;
    }

    // Otros métodos para generar códigos de otras entidades, si es necesario
}
