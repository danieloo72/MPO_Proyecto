package daw.code.dao;

import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;

import java.util.List;

/**
 * Interfaz para las operaciones de acceso a datos de los videojuegos
 */
public interface VideojuegoDAO {
    /**
     * Metodo que guarda un videojuego
     *
     * @param videojuego el videojuego que se guarda
     * @throws VideojuegoException ocurre si hay un error cuando se guarda en la base de datos
     */
    void guardar(Videojuego videojuego) throws VideojuegoException;

    /**
     * Metodo que carga la lista de videojuegos de la base de datos
     * @return la lista de videojuegos guardados en la base de datos
     */
    List<Videojuego> cargar();

    /**
     * Metodo que elimina un videojuego guardado de la base de datos porque ha sido comprado
     * @param id el videojuego eliminado
     * @throws VideojuegoException ocuure si hay un error cunado se elimina de la base de datos
     */
    void eliminar(int id) throws VideojuegoException;
}