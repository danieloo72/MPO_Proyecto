package daw.code.dao.impl;

import daw.code.config.DatabaseConfig;
import daw.code.dao.VideojuegoDAO;
import daw.code.exceptions.VideojuegoException;
import daw.code.model.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la interfaz VideojuegoDAO para interactuar con la base de datos
 */
public class VideojuegoDAOImpl implements VideojuegoDAO {

    /**
     * Metodo que guarda un videojuego en la base de datos
     *
     * @param videojuego el videojuego que se guarda en la base de datos
     * @throws VideojuegoException ocurre cuando hay un error al guardar un videojuego en la base de datos
     */
    @Override
    public void guardar(Videojuego videojuego) throws VideojuegoException {
        try (Connection con = DatabaseConfig.getConnection()) {
            con.setAutoCommit(false);

            int idUsuario = -1;

            String sqlBuscarUsuario = "SELECT id_usuario FROM usuarios WHERE nombre = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlBuscarUsuario)) {
                ps.setString(1, videojuego.getUsuario());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idUsuario = rs.getInt("id_usuario");
                    }
                }
            }

            if (idUsuario == -1) {
                String sqlInsertarUsuario = "INSERT INTO usuarios (nombre) VALUES (?)";
                try (PreparedStatement ps = con.prepareStatement(sqlInsertarUsuario, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, videojuego.getUsuario());
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            idUsuario = rs.getInt(1);
                        }
                    }
                }
            }

            String sqlInsertarJuego = "INSERT INTO videojuegos (id_usuario, nombre, categoria, precio, estado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sqlInsertarJuego)) {
                ps.setInt(1, idUsuario);
                ps.setString(2, videojuego.getNombre());
                ps.setString(3, videojuego.getCategoria());
                ps.setInt(4, videojuego.getPrecio());
                ps.setString(5, videojuego.getEstado());
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException ex) {
            throw new VideojuegoException("Error al guardar el videojuego en la base de datos: " + ex.getMessage());
        }
    }

    /**
     * Metodo que carga los videojuegos desde la base de datos
     * @return la lista de videojuegos de la base de datos
     */
    @Override
    public List<Videojuego> cargar() {
        List<Videojuego> lista = new ArrayList<>();

        String sql = "SELECT v.id_videojuego, v.nombre, v.categoria, v.precio, v.estado, u.nombre AS nombre_usuario " +
                "FROM videojuegos v " +
                "INNER JOIN usuarios u ON v.id_usuario = u.id_usuario";

        try (Connection con = DatabaseConfig.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Videojuego(
                        rs.getInt("id_videojuego"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("precio"),
                        rs.getString("estado"),
                        rs.getString("nombre_usuario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Metodo que elimina un viodejuego de la lista de los videojuegos porque se ha comprado
     * @param id del videojuego que se ha comprado y se ha quitado de la lista
     * @throws VideojuegoException ocurre cuando hay un error en la eliminacion del videojuego
     */
    @Override
    public void eliminar(int id) throws VideojuegoException {
        String sql = "DELETE FROM videojuegos WHERE id_videojuego = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new VideojuegoException("Error al eliminar el videojuego: " + ex.getMessage());
        }
    }
}