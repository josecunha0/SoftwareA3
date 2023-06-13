package Dao;

import db.DB;
import db.DbException;
import Model.Ferramenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FerramentaDao {
    private Connection conn;

    public FerramentaDao() {
        conn = DB.getConnection();
    }

    public List<Ferramenta> findAll() {
        List<Ferramenta> ferramentas = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM ferramenta";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setCustoDeAquisicao(rs.getFloat("custo_aquisicao"));
                ferramenta.setDisponivel(rs.getBoolean("disponivel"));
                ferramentas.add(ferramenta);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return ferramentas;
    }

    public Ferramenta findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM ferramenta WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setCustoDeAquisicao(rs.getFloat("custo_aquisicao"));
                ferramenta.setDisponivel(rs.getBoolean("disponivel"));
                return ferramenta;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }

    public void insert(Ferramenta ferramenta) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO ferramenta (nome, marca, custo_aquisicao, disponivel) " +
                    "VALUES (?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, ferramenta.getNome());
            st.setString(2, ferramenta.getMarca());
            st.setDouble(3, ferramenta.getCustoDeAquisicao());
            st.setBoolean(4, ferramenta.getDisponivel());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void update(Ferramenta ferramenta) {
        PreparedStatement st = null;

        try {
            String sql = "UPDATE ferramenta SET nome = ?, marca = ?, custo_aquisicao = ?, disponivel = ? WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, ferramenta.getNome());
            st.setString(2, ferramenta.getMarca());
            st.setDouble(3, ferramenta.getCustoDeAquisicao());
            st.setBoolean(4, ferramenta.getDisponivel());
            st.setInt(5, ferramenta.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void delete(int id) {
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM ferramenta WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
