package dam.code.dao;

import dam.code.model.Persona;

import java.util.List;

public interface PersonaDAO {

    void guardar(Persona persona);
    List<Persona> cargar();
}
