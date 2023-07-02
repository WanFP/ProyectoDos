package cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio;

public class Tematica {

    private int idTematica;
    private String nombreTematica;

    public Tematica() {
    }

    public Tematica(int idTematica, String nombreTematica) {
        this.idTematica = idTematica;
        this.nombreTematica = nombreTematica;
    }

    
    public int getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(int idTematica) {
        this.idTematica = idTematica;
    }

    public String getNombreTematica() {
        return nombreTematica;
    }

    public void setNombreTematica(String nombreTematica) {
        this.nombreTematica = nombreTematica;
    }

}
