package daw.code.service;

import daw.code.dao.UsuarioDAO;
import daw.code.dao.impl.UsuarioDAOImpl;
import daw.code.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioService {

    private final UsuarioDAO dao;

    public UsuarioService() {
        dao = new UsuarioDAOImpl();
    }

    public void registrar(Usuario usuario) {
        dao.guardar(usuario);
    }

    public ObservableList<Usuario> getUsuarios() {
        return FXCollections.observableArrayList(dao.cargar());
    }
}
