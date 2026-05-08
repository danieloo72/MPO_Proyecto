package daw.code.service;

import daw.code.dao.VideojuegoDAO;
import daw.code.dao.impl.VideojuegoDAOImpl;
import daw.code.model.Videojuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideojuegoService {

    private final VideojuegoDAO dao;

    public VideojuegoService() {
        dao = new VideojuegoDAOImpl();
    }

    public void registrar(Videojuego videojuego) {
        dao.guardar(videojuego);
    }

    public ObservableList<Videojuego> getVideojuegos() {
        return FXCollections.observableArrayList(dao.cargar());
    }
}

