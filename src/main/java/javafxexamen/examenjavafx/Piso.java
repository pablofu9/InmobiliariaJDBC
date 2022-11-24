package javafxexamen.examenjavafx;


public class Piso extends Zona {

    public int id_piso;
    public int id_zona;
    public String tipo_operacion;
    public int metros;
    public String nombre_prop;
    public String telefono_prop;
    public int precio;
    public boolean reservado;

    public Piso(int id_zona, String tipo_operacion, int metros, String nombre_prop, String telefono_prop, int precio,
            boolean reservado) {
        super();
        this.id_zona = id_zona;
        this.tipo_operacion = tipo_operacion;
        this.metros = metros;
        this.nombre_prop = nombre_prop;
        this.telefono_prop = telefono_prop;
        this.precio = precio;
        this.reservado = reservado;
    }

    public Piso(int id_piso, int id_zona, String tipo_operacion, int metros, String nombre_prop, String telefono_prop, int precio,
            boolean reservado) {
        super();
        this.id_piso = id_piso;
        this.id_zona = id_zona;
        this.tipo_operacion = tipo_operacion;
        this.metros = metros;
        this.nombre_prop = nombre_prop;
        this.telefono_prop = telefono_prop;
        this.precio = precio;
        this.reservado = reservado;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getNombre_prop() {
        return nombre_prop;
    }

    public void setNombre_prop(String nombre_prop) {
        this.nombre_prop = nombre_prop;
    }

    public String getTelefono_prop() {
        return telefono_prop;
    }

    public void setTelefono_prop(String telefono_prop) {
        this.telefono_prop = telefono_prop;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

}
