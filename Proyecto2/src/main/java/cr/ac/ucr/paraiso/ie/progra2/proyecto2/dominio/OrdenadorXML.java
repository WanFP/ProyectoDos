package cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenadorXML {

    public static void ordenarLibrosPorTitulo(List<Libro> libros) {
        Collections.sort(libros, Comparator.comparing(Libro::getTitulo));
    }

    public static void ordenarLibrosPorAutor(List<Libro> libros) {
        Collections.sort(libros, Comparator.comparing(Libro::getYear));
    }

    // Otros métodos de ordenación para distintos archivos XML y criterios
}
