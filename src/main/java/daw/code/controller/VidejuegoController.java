package daw.code.controller;

import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;
import daw.code.service.VideojuegoService;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador para gestionar las operaciones relacionadas con los videojuegos
 */
public class VidejuegoController {

    private final VideojuegoService service = new VideojuegoService();

    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtEstado;
    @FXML private TextField txtUsuario;

    @FXML private TableView<Videojuego> tablaVideojuego;
    @FXML private TableColumn<Videojuego, String> colNombre;
    @FXML private TableColumn<Videojuego, String> colCategoria;
    @FXML private TableColumn<Videojuego, Integer> colPrecio;
    @FXML private TableColumn<Videojuego, String> colEstado;

    /**
     * Metodo que inicializa el FXML
     */
    @FXML
    private void initialize(){
        prefWidthColumns();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
         colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaVideojuego.setItems(service.getVideojuegos());
    }

    /**
     * Metodo para insertar las caracteristicas del videojuego nuevo
     * @throws VideojuegoException ocurre si hay un error al registrar el videojuego
     */
    @FXML
    public void addVideojuego() throws VideojuegoException {
        try {
            Videojuego videojuego = new Videojuego(
                    txtNombre.getText(),
                    txtCategoria.getText(),
                    Integer.parseInt(txtPrecio.getText()),
                    txtEstado.getText(),
                    txtUsuario.getText()
            );

            service.registrar(videojuego);
            limpiarCampos();
            tablaVideojuego.setItems(service.getVideojuegos());

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio debe ser un número entero.");
        } catch (VideojuegoException e) {
            throw new VideojuegoException("No se ha podido registrar el videojuego");
        }
    }

    /**
     * Metodo que resetea el filtrado de los videojuegos buscados
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtEstado.clear();
    }

    private void prefWidthColumns() {
        tablaVideojuego.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        colNombre.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.3));
        colCategoria.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.3));
        colPrecio.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.2));
    }
}