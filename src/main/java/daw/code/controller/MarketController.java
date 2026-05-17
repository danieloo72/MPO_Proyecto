package daw.code.controller;

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

    @FXML private TableView<Videojuego> tablaVideojuegos;
    @FXML private TableColumn<Videojuego, String> colNombre, colCategoria, colEstado;
    @FXML private TableColumn<Videojuego, Integer> colPrecio;

    @FXML private TextField txtBuscarNombre;
    @FXML private TextField txtBuscarPrecio;
    @FXML private ComboBox<String> cmbFiltrarEstado;

    private VideojuegoService service = new VideojuegoService();
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

        ObservableList<Videojuego> masterData = service.getVideojuegos();
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
     * Metodo que filtra los videjuegos por los distintos buscadores
     */
    private void aplicarFiltros() {
        listaFiltrada.setPredicate(juego -> {
            String nombre = txtBuscarNombre.getText().toLowerCase();
            if (nombre != null && !nombre.isEmpty() && !juego.getNombre().toLowerCase().contains(nombre)) {
                return false;
            }

            String precioMax = txtBuscarPrecio.getText();
            if (precioMax != null && !precioMax.isEmpty()) {
                try {
                    if (juego.getPrecio() > Integer.parseInt(precioMax)) return false;
                } catch (NumberFormatException e) {

                }
            }

            String estado = cmbFiltrarEstado.getValue();
            if (estado != null && !estado.equals("Cualquiera") && !juego.getEstado().equals(estado)) {
                return false;
            }

            return true;
        });
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
     * Metodo para comprar un videojuego
     * @param event evento de compra de videojuego
     */
    @FXML
    private void comprarJuego(ActionEvent event) {
        Videojuego seleccionado = tablaVideojuegos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.eliminar(seleccionado.getId());
            tablaVideojuegos.setItems(new FilteredList<>(service.getVideojuegos(), p -> true));
        }
    }

    /**
     * Metodo para ir a la pagina de venta de videojuegos
     * @param event evento de cambio de pagina
     * @throws IOException ocurre si hay un error en la entrada o salida de datos
     */
    @FXML
    private void irAVender(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sell-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}