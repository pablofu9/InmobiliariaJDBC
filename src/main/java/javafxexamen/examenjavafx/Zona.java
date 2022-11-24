package javafxexamen.examenjavafx;

public class Zona {

    private int id_zona;
    private String nombre_zona;

    public Zona() {
        super();
    }

    public Zona(int id_zona, String nombre_zona) {
        super();
        this.id_zona = id_zona;
        this.nombre_zona = nombre_zona;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public String getNombre_zona() {
        return nombre_zona;
    }

    public void setNombre_zona(String nombre_zona) {
        this.nombre_zona = nombre_zona;
    }

}
