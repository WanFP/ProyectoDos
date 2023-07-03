package cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio;

import java.util.List;

public class Libro {

    private int idLibro;
    private int isbn;
    private String titulo;
    private int year;
    private Editorial editorial;
    private List<Autor> autores;
    private Tematica tematica;
    
    public Libro() {
    }

    public Libro(int idLibro, int isbn, String titulo, int year) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.year= year;
    }
    

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
    
    

    
}
