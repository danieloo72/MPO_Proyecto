package daw.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación.
 */
public class MainApp extends Application {
    /**
     * Inicia y despliega la pagina principal de la tienda
     * @param stage escenario principal proporcionado por la plataforma JavaFX
     * @throws Exception si ocurre un fallo del FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/market-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 400, 400);

        stage.setTitle("Compra/Venta de Videojuegos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}