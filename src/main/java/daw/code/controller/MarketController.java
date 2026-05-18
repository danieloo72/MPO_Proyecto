package daw.code.controller;

import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;
import daw.code.service.VideojuegoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para gestionar las operaciones relacionadas con el catalogo y compra de videojuegos
 */
public class MarketController {

    @FXML
    private TableView<Videojuego> tablaVideojuegos;
    @FXML
    private TableColumn<Videojuego, String> colNombre, colCategoria, colEstado, colUsuario;
    @FXML
    private TableColumn<Videojuego, Integer> colPrecio;

    @FXML
    private TextField txtBuscarNombre, txtBuscarPrecio;
    @FXML
    private ComboBox<String> cmbFiltrarEstado;

    private VideojuegoService service = new VideojuegoService();
    private ObservableList<Videojuego> masterData;
    private FilteredList<Videojuego> listaFiltrada;

    /**
     * Metodo que inicializa el FXML
     */
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        masterData = service.getVideojuegos();
        listaFiltrada = new FilteredList<>(masterData, p -> true);

        cmbFiltrarEstado.setItems(FXCollections.observableArrayList(
                "Cualquiera", "nuevo", "seminuevo", "usado", "muy usado"
        ));

        txtBuscarNombre.textProperty().addListener((obs, oldV, newV) -> aplicarFiltros());
        txtBuscarPrecio.textProperty().addListener((obs, oldV, newV) -> aplicarFiltros());
        cmbFiltrarEstado.valueProperty().addListener((obs, oldV, newV) -> aplicarFiltros());

        tablaVideojuegos.setItems(listaFiltrada);
    }

    /**
     * Metodo para comprar un videojuego
     *
     * @param event evento de compra de videojuego
     */
    @FXML
    private void comprarJuego(ActionEvent event) {
        Videojuego seleccionado = tablaVideojuegos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            try {
                service.eliminar(seleccionado.getId());

                masterData.setAll(service.getVideojuegos());

                mostrarAlerta("Compra Completada", "¡Has comprado '" + seleccionado.getNombre() + "' con éxito!", Alert.AlertType.INFORMATION);

            } catch (VideojuegoException e) {
                mostrarAlerta("Error de Base de Datos", e.getMessage(), Alert.AlertType.ERROR);
            } catch (Exception e) {
                mostrarAlerta("Error Inesperado", "Ocurrió un error: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Ninguna Selección", "Por favor, selecciona un videojuego de la tabla para poder comprarlo.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Metodo que muestra alertas
     *
     * @param titulo  muestra el titulo de la alerta
     * @param mensaje muestra el mensaje de la alerta
     * @param tipo    señala el tipo de la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Metodo que resetea los filtros
     */
    @FXML
    private void limpiarFiltros() {
        txtBuscarNombre.clear();
        txtBuscarPrecio.clear();
        cmbFiltrarEstado.setValue("Cualquiera");
    }

    /**
     * Metodo para ir a la pagina de venta de videojuegos
     *
     * @param event evento de cambio de pagina
     * @throws IOException ocurre si hay un error en la entrada o salida de datos
     */
    @FXML
    private void irAVender(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sell-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    /**
     * Metodo que filtra los videjuegos por los distintos buscadores
     */
    private void aplicarFiltros() {
        listaFiltrada.setPredicate(juego -> {
            String nombre = txtBuscarNombre.getText();
            if (nombre != null && !nombre.isEmpty() && !juego.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return false;
            }
            String precioMax = txtBuscarPrecio.getText();
            if (precioMax != null && !precioMax.isEmpty()) {
                try {
                    if (juego.getPrecio() > Integer.parseInt(precioMax)) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Precio incorrecto");
                }
            }
            String estado = cmbFiltrarEstado.getValue();
            if (estado != null && !estado.equals("Cualquiera") && !juego.getEstado().equals(estado)) {
                return false;
            }
            return true;
        });
    }
}