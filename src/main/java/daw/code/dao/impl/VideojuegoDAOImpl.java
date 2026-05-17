package daw.code.dao.impl; // Agregamos el paquete correcto

import daw.code.config.DatabaseConfig;
import daw.code.dao.VideojuegoDAO;
import daw.code.model.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAOImpl implements VideojuegoDAO {
    // ... resto del código igual ...
    @Override
    public void guardar(Videojuego videojuego) {
        // Añadimos 'estado' y el cuarto '?'
        String sql = "INSERT INTO videojuegos (nombre, categoria, precio, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getCategoria());
            ps.setInt(3, videojuego.getPrecio());
            ps.setString(4, videojuego.getEstado()); // Enviamos el estado
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Videojuego> cargar() {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos";
        try (Connection con = DatabaseConfig.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // Añadimos rs.getString("estado") al final
                lista.add(new Videojuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("precio"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}