package daw.code.model;

/**
 * Representa un videojuego en el sistema
 */
public class Videojuego {

    // Atributos
    private int id;
    private String nombre;
    private String categoria;
    private int precio;
    private String estado;
    private String usuario; // Nuevo campo

    //Constructor

    /**
     * Constructor para registrar un videojuego sin ID
     *
     * @param nombre establece el nombre del videojuego
     * @param categoria establece la categoria del videojuego
     * @param precio establece el precio del videojuego
     * @param estado establece el estado del videojuego
     * @param usuario establece el usuario que vende el videojuego
     */
    public Videojuego(String nombre, String categoria, int precio, String estado, String usuario) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.usuario = usuario;
    }

    /**
     * Constructor completo para el almacenamiento persistente de videojuegos
     *
     * @param id establece el identificador del videojuego
     * @param nombre establece el nombre del videojuego
     * @param categoria establece la categoria del videojuego
     * @param precio establece el precio del videojuego
     * @param estado establece el estado del videojuego
     * @param usuario establece el usuario que vendio el videojuego
     */
    public Videojuego(int id, String nombre, String categoria, int precio, String estado, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.usuario = usuario;
    }

    // Getters y Setters

    /**
     * Getter del atributo id
     *
     * @return el id del videojuego
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del atributo id
     *
     * @param id establece el id del videojuego
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del atributo nombre
     *
     * @return el nombre del videojuego
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre establece el nombre del videojuego
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo categoria
     *
     * @return la categoria del videojuego
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Setter del atributo categoria
     *
     * @param categoria establece la categoria del videojuego
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Getter del atributo precio
     *
     * @return el precio del videojuego
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Setter del atributo precio
     *
     * @param precio establece el precio del videojuego
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Getter del atributo estado
     *
     * @return el estado del videojuego
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Setter del atributo estado
     *
     * @param estado establece el estado del videojuego
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Getter del atributo usuario
     *
     * @return el usuario del videojuego
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Setter del atributo usuario
     *
     * @param usuario establece el usuario del videojuego
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
