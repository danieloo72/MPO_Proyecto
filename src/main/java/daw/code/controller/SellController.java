package daw.code.controller;

import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;
import daw.code.service.VideojuegoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para gestionar las operaciones relacionadas con la venta de videojuegos
 */
public class SellController {

    @FXML
    private TextField txtUsuario, txtNombre, txtPrecio;

    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private ComboBox<String> cmbEstado;

    private VideojuegoService service = new VideojuegoService();

    /**
     * Metodo que inicializa el FXML
     */
    @FXML
    public void initialize() {
        cmbEstado.setItems(FXCollections.observableArrayList("nuevo", "seminuevo", "usado", "muy usado"));

        cmbCategoria.setItems(FXCollections.observableArrayList(
                "Acción",
                "Aventura",
                "RPG",
                "Shooter",
                "Deportes",
                "Lucha",
                "Terror",
                "Metroidvania",
                "Simulación",
                "Plataformas",
                "Sandbox",
                "MOBA"
        ));
    }

    /**
     * Metodo que permite vender/publicar un videojuego nuevo
     * @param event evento de añadir un nuevo videojuego
     */
    @FXML
    private void publicarJuego(ActionEvent event) {
        try {

            if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || cmbCategoria.getValue() == null || cmbEstado.getValue() == null || txtPrecio.getText().isEmpty()) {
                mostrarAlerta("Campos Incompletos", "Por favor, rellena todos los campos antes de publicar.", Alert.AlertType.WARNING);
                return;
            }

            int precio = Integer.parseInt(txtPrecio.getText());

            if (precio < 0) {
                mostrarAlerta("Precio Inválido", "El precio del videojuego no puede ser inferior a 0 €.", Alert.AlertType.WARNING);
                return;
            }

            Videojuego nuevo = new Videojuego(
                    txtNombre.getText(),
                    cmbCategoria.getValue(),
                    precio,
                    cmbEstado.getValue(),
                    txtUsuario.getText()
            );

            service.registrar(nuevo);

            mostrarAlerta("Juego Publicado", "¡Tu videojuego se ha puesto a la venta correctamente!", Alert.AlertType.INFORMATION);
            volverTienda(event);

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Incorrecto", "El campo Precio debe contener únicamente números enteros.", Alert.AlertType.ERROR);
        } catch (VideojuegoException e) {
            mostrarAlerta("Error de Registro", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error Inesperado", "No se pudo publicar el juego: " + e.getMessage(), Alert.AlertType.ERROR);
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
     * Metodo que lleva a la pagina de compra
     * @param event evento de ir a la pagina de compra
     * @throws IOException ocurre si hay un error en la entrada o salida de datos
     */
    @FXML
    private void volverTienda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/market-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}