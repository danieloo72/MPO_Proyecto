package daw.code.service;

import daw.code.dao.VideojuegoDAO;
import daw.code.dao.impl.VideojuegoDAOImpl;
import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Service que gestiona la logica de negocio de los videojuegos
 */
public class VideojuegoService {

    private final VideojuegoDAO dao;

    public VideojuegoService() {
        this.dao = new VideojuegoDAOImpl();
    }

    /**
     * Metodo que registra un nuevo videojuego en el sistema validandolo por el DAO
     *
     * @param videojuego el videojuego que se registra
     * @throws VideojuegoException ocurre cuando hay un error en el guardado del videojuego
     */
    public void registrar(Videojuego videojuego) throws VideojuegoException {
        dao.guardar(videojuego);
    }

    /**
     * Metodo que recoge todos los videojuegos de la base de datos
     *
     * @return la lista de videojuegos guardados
     */
    public ObservableList<Videojuego> getVideojuegos() {
        return FXCollections.observableArrayList(dao.cargar());
    }

    /**
     * Metodo que elimina un videojuego mediante su id
     *
     * @param id el identificador del videojuego
     * @throws VideojuegoException ocurre cuando hay un error en la eliminacion de un videojuego
     */
    public void eliminar(int id) throws VideojuegoException {
        dao.eliminar(id);
    }
}