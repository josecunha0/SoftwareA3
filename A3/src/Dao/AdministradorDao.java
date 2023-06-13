package Dao;

import db.DB;
import db.DbException;
import Model.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdministradorDao {
    private Connection conn;

    public AdministradorDao() {
        conn = DB.getConnection();
    }

    public Administrador findByID(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM administrador WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setSenha(rs.getString("senha"));
                return administrador;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }
    
    public void insert(Administrador administrador) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO administrador (id, senha) VALUES (?, ?)";
            st = conn.prepareStatement(sql);
            st.setInt(1, administrador.getId());
            st.setString(2, administrador.getSenha());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void deleteById(int id) {
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM administrador WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void update(Administrador administrador) {
        PreparedStatement st = null;

        try {
            String sql = "UPDATE administrador SET senha = ? WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, administrador.getSenha());
            st.setInt(2, administrador.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public List<Administrador> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM administrador";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Administrador> administradores = new ArrayList<>();

            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setSenha(rs.getString("senha"));
                administradores.add(administrador);
            }

            return administradores;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
