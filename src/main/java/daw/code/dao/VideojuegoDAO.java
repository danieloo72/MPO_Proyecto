package daw.code.dao;

import daw.code.model.Videojuego;

import java.util.List;

public interface VideojuegoDAO {
    void guardar(Videojuego videojuego);
    List<Videojuego> cargar();
    void eliminar(int id); // Nuevo método para borrar por ID
}
