package daw.code.controller;

import daw.code.model.Usuario;
import daw.code.service.UsuarioService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioController {

    private final UsuarioService service = new UsuarioService();

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colApellido;

    @FXML
    private void initialize(){
        prefWidthColumns();
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        tablaUsuarios.setItems(service.getUsuarios());
    }

    private void prefWidthColumns() {
        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        colNombre.prefWidthProperty().bind(tablaUsuarios.widthProperty().multiply(0.5));
        colApellido.prefWidthProperty().bind(tablaUsuarios.widthProperty().multiply(0.5));
    }

    @FXML
    public void addUsuario() {
        Usuario usuario = new Usuario(
                txtNombre.getText(),
                txtApellido.getText()
        );
        service.registrar(usuario);
        limpiarCampos();
        tablaUsuarios.setItems(service.getUsuarios());
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
    }
}

