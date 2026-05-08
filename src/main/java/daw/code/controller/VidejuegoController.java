package daw.code.controller;

import com.sun.javafx.scene.control.IntegerField;
import daw.code.model.Videojuego;
import daw.code.service.VideojuegoService;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.SimpleStyleableIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VidejuegoController {

    private final VideojuegoService service = new VideojuegoService();

    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private IntegerField txtPrecio;

    @FXML private TableView<Videojuego> tablaVideojuego;
    @FXML private TableColumn<Videojuego, String> colNombre;
    @FXML private TableColumn<Videojuego, String> colCategoria;
    @FXML private TableColumn<Videojuego, Integer> colPrecio;

    @FXML
    private void initialize(){
        prefWidthColumns();
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tablaVideojuego.setItems(service.getVideojuegos());
    }

    private void prefWidthColumns() {
        tablaVideojuego.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        colNombre.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.4));
        colCategoria.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.3));
        colPrecio.prefWidthProperty().bind(tablaVideojuego.widthProperty().multiply(0.3));
    }

    @FXML
    public void addVideojuego() {
        Videojuego videojuego = new Videojuego(
                txtNombre.getText(),
                txtCategoria.getText(),
                txtPrecio.getValue()
        );
        service.registrar(videojuego);
        limpiarCampos();
        tablaVideojuego.setItems(service.getVideojuegos());
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.setValue(0);
    }
}
