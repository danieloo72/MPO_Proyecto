package daw.code.dao;

import daw.code.model.Videojuego;

import java.util.List;

public interface VideojuegoDAO {

    void guardar(Videojuego videojuego);
    List<Videojuego> cargar();
}
