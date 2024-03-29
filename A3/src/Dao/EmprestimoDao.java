package Dao;

import db.DB;
import db.DbException;
import Model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {
    private Connection conn;

    public EmprestimoDao() {
        conn = DB.getConnection();
    }

    public List<Emprestimo> findAll() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM emprestimo_ativo";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setFerramenta(rs.getInt("id_ferramenta"));
                emprestimo.setNomeAmigo(rs.getString("nome_amigo"));
                emprestimo.setDataInicio(rs.getObject("data_inicio", LocalDate.class));
                emprestimo.setDataDevolucao(rs.getObject("data_devolucao", LocalDate.class));
                emprestimo.setEmailAmigo(rs.getString("email_amigo"));
                emprestimo.setDevolvido(rs.getBoolean("devolvido"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return emprestimos;
    }

    public Emprestimo findById(int idFerramenta, String emailAmigo) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM emprestimo_ativo WHERE id_ferramenta = ? AND email_amigo = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, idFerramenta);
            st.setString(2, emailAmigo);
            rs = st.executeQuery();

            if (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setFerramenta(rs.getInt("id_ferramenta"));
                emprestimo.setNomeAmigo(rs.getString("nome_amigo"));
                emprestimo.setDataInicio(rs.getObject("data_inicio", LocalDate.class));
                emprestimo.setDataDevolucao(rs.getObject("data_devolucao", LocalDate.class));
                emprestimo.setEmailAmigo(rs.getString("email_amigo"));
                emprestimo.setDevolvido(rs.getBoolean("devolvido"));
                return emprestimo;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }

    public String findAmigoComMaisEmprestimos() {
        String amigoComMaisEmprestimos = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT nome_amigo, COUNT(*) AS total_emprestimos " +
                         "FROM emprestimo_ativo " +
                         "GROUP BY nome_amigo " +
                         "ORDER BY total_emprestimos DESC " +
                         "LIMIT 1";

            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            if (rs.next()) {
                amigoComMaisEmprestimos = rs.getString("nome_amigo");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return amigoComMaisEmprestimos;
    }

    public void insert(Emprestimo emprestimo) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO emprestimo_ativo (id_ferramenta, nome_amigo, data_inicio, data_devolucao, email_amigo, devolvido) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setInt(1, emprestimo.getIdFerramenta());
            st.setString(2, emprestimo.getNomeAmigo());
            st.setObject(3, emprestimo.getDataInicio());
            st.setObject(4, emprestimo.getDataDevolucao());
            st.setString(5, emprestimo.getEmailAmigo());
            st.setBoolean(6, emprestimo.getDevolvido());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void update(Emprestimo emprestimo) {
        PreparedStatement st = null;

        try {
            String sql = "UPDATE emprestimo_ativo SET nome_amigo = ?, data_inicio = ?, data_devolucao = ?, email_amigo = ?, devolvido = ? " +
                    "WHERE id_ferramenta = ? AND email_amigo = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, emprestimo.getNomeAmigo());
            st.setObject(2, emprestimo.getDataInicio());
            st.setObject(3, emprestimo.getDataDevolucao());
            st.setString(4, emprestimo.getEmailAmigo());
            st.setBoolean(5, emprestimo.getDevolvido());
            st.setInt(6, emprestimo.getIdFerramenta());
            st.setString(7, emprestimo.getEmailAmigo());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void delete(int idFerramenta, String emailAmigo) {
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM emprestimo_ativo WHERE id_ferramenta = ? AND email_amigo = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, idFerramenta);
            st.setString(2, emailAmigo);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
