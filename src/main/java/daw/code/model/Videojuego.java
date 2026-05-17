package daw.code.model;

public class Videojuego {
    private int id;
    private String nombre;
    private String categoria;
    private int precio;
    private String estado; // Nuevo campo

    // Constructor para nuevos juegos (sin ID)
    public Videojuego(String nombre, String categoria, int precio, String estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
    }

    // Constructor completo (con ID para cargar de la DB)
    public Videojuego(int id, String nombre, String categoria, int precio, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
