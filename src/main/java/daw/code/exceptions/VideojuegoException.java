package daw.code.exceptions;

/**
 * Excepcion para errores de videojuegos
 */
public class VideojuegoException extends Exception {
    /**
     * Instancia una nueva excepcion de videojuegos
     *
     * @param message el mensaje de error
     */
    public VideojuegoException(String message) {
        super(message);
    }
}
