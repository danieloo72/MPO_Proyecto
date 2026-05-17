package daw.code.controller;

import daw.code.model.Videojuego;
import daw.code.service.VideojuegoService;
import javafx.collections.FXCollections; // Importante
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox; // Importante
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controlador para gestionar las operaciones relacionadas con la venta de videojuegos
 */
public class SellController {

    @FXML private TextField txtNombre, txtCategoria, txtPrecio;

    @FXML private ComboBox<String> cmbEstado;

    private VideojuegoService service = new VideojuegoService();

    /**
     * Metodo que inicializa el FXML
     */
    @FXML
    public void initialize() {
        cmbEstado.setItems(FXCollections.observableArrayList(
                "nuevo",
                "seminuevo",
                "usado",
                "muy usado"
        ));
    }

    /**
     * Metodo que permite vender/publicar un videojuego nuevo
     * @param event evento de añadir un nuevo videojuego
     * @throws IOException ocurre si hay un error en la entrada o salida de datos
     */
    @FXML
    private void publicarJuego(ActionEvent event) throws IOException {
        try {
            String nombre = txtNombre.getText();
            String categoria = txtCategoria.getText();
            int precio = Integer.parseInt(txtPrecio.getText());

            String estado = cmbEstado.getValue();

            if (estado == null) {
                System.out.println("Por favor, selecciona un estado.");
                return;
            }

            Videojuego nuevo = new Videojuego(nombre, categoria, precio, estado);
            service.registrar(nuevo);

            volverTienda(event);

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio debe ser un número.");
        }
    }

    /**
     * Metodo que lleva a la pagina de compra
     * @param event evento de ir a la pagina de compra
     * @throws IOException ocurre si hay un error en la entrada o salida de datos
     */
    @FXML
    private void volverTienda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/market-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}