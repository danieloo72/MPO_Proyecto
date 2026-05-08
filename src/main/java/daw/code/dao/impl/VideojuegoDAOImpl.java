package daw.code.dao.impl;

import daw.code.config.DatabaseConfig;
import daw.code.dao.VideojuegoDAO;
import daw.code.model.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAOImpl implements VideojuegoDAO {

    @Override
    public void guardar(Videojuego videojuego) {
        String sql = "INSERT INTO Usuarios (nombre, apellido) VALUES (?, ?)";

        try (Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getCategoria());
            ps.setInt(3, videojuego.getPrecio());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al insertar videojuego: " + ex.getMessage());
        }
    }

    @Override
    public List<Videojuego> cargar() {
        List<Videojuego> videojuegos = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos";

        try (Connection con = DatabaseConfig.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Videojuego v = new Videojuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("precio")
                );
                videojuegos.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los videojuegos: " + e.getMessage());
        }

        return videojuegos;
    }
}
