package cr.ac.ucr.paraiso.ie.progra2.proyecto2.dominio;

public class Editorial {

    private int idEditorial;
    private String nombreEditorial;

    public Editorial() {
    }

    public Editorial(int idEditorial, String nombreEditorial) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
    }
    
    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }
    
}
