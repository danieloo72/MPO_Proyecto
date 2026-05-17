package daw.code.service;

import daw.code.dao.VideojuegoDAO;
import daw.code.dao.impl.VideojuegoDAOImpl; // Este import ya es correcto
import daw.code.model.Videojuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideojuegoService {

    private final VideojuegoDAO dao;

    public VideojuegoService() {
        // ERROR CORREGIDO: Eliminamos la ruta larga incorrecta
        // Como ya importamos VideojuegoDAOImpl arriba, solo usamos 'new VideojuegoDAOImpl()'
        this.dao = new VideojuegoDAOImpl();
    }

    public void registrar(Videojuego videojuego) {
        dao.guardar(videojuego);
    }

    public ObservableList<Videojuego> getVideojuegos() {
        return FXCollections.observableArrayList(dao.cargar());
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}