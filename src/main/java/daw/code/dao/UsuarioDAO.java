package daw.code.dao;

import daw.code.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void guardar(Usuario usuario);
    List<Usuario> cargar();
}
