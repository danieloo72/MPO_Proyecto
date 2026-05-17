package daw.code.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    /**
     * Configuración de la base de datos.
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/MPO_Proyecto";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return la conexión a la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
