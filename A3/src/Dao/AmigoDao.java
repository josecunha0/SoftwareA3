package Dao;

import db.DB;
import db.DbException;
import Model.Amigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmigoDao {
    private Connection conn;

    public AmigoDao() {
        conn = DB.getConnection();
    }

    public Amigo findByEmail(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM amigo WHERE email = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                Amigo amigo = new Amigo();
                amigo.setEmail(rs.getString("email"));
                amigo.setNome(rs.getString("nome"));
                amigo.setTelefone(rs.getString("telefone"));
                amigo.setDevedor(rs.getBoolean("devedor"));
                return amigo;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }

    public void insert(Amigo amigo) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO amigo (email, nome, telefone, devedor) VALUES (?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, amigo.getEmail());
            st.setString(2, amigo.getNome());
            st.setString(3, amigo.getTelefone());
            st.setBoolean(4, amigo.getDevedor());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void update(Amigo amigo) {
        PreparedStatement st = null;

        try {
            String sql = "UPDATE amigo SET nome = ?, telefone = ?, devedor = ? WHERE email = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, amigo.getNome());
            st.setString(2, amigo.getTelefone());
            st.setBoolean(3, amigo.getDevedor());
            st.setString(4, amigo.getEmail());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void delete(String email) {
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM amigo WHERE email = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, email);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public List<Amigo> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM amigo";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Amigo> amigos = new ArrayList<>();

            while (rs.next()) {
                Amigo amigo = new Amigo();
                amigo.setEmail(rs.getString("email"));
                amigo.setNome(rs.getString("nome"));
                amigo.setTelefone(rs.getString("telefone"));
                amigo.setDevedor(rs.getBoolean("devedor"));
                amigos.add(amigo);
            }

            return amigos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
