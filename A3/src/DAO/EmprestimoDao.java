package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Emprestimo;
import db.DB;
import db.DbException;

public class EmprestimoDao {

    private Connection conn;

    public EmprestimoDao() {
        this.conn = DB.getConnection();
    }

    public void insert(Emprestimo obj) {
    	PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO emprestimo (id_ferramenta, id_amigo, data_do_emprestimo) VALUES (?, ?, ?)");
            st.setInt(1, obj.getFerramentaId());
            st.setInt(2, obj.getAmigoId());
            st.setDate(3, java.sql.Date.valueOf(LocalDate.now())); // Obtém a data atual do sistema
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void update(Emprestimo obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE Emprestimo SET data_emprestimo = ?, data_devolucao = ?, amigo_id = ?, ferramenta_id = ? WHERE id = ?");
            st.setString(1, obj.getDataInicio());
            st.setString(2, obj.getDataDevolucao());
            st.setInt(3, obj.getAmigoId());
            st.setInt(4, obj.getFerramentaId());
            st.setInt(5, obj.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM Emprestimo WHERE id = ?");
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deletado com sucesso!");
            } else {
                System.out.println("Erro ao deletar!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public Emprestimo findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Emprestimo WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Emprestimo obj = new Emprestimo();
                obj.setId(rs.getInt("id"));
                obj.setDataInicio(rs.getString("data_emprestimo"));
                obj.setDataDevolucao(rs.getString("data_devolucao"));
                obj.setAmigoId(rs.getInt("amigo_id"));
                obj.setFerramentaId(rs.getInt("ferramenta_id"));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    public List<Emprestimo> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Emprestimo");
            rs = st.executeQuery();

            List<Emprestimo> emprestimos = new ArrayList<>();

            while (rs.next()) {
                Emprestimo obj = new Emprestimo();
                obj.setId(rs.getInt("id"));
                obj.setDataInicio(rs.getString("data_emprestimo"));
                obj.setDataDevolucao(rs.getString("data_devolucao"));
                obj.setAmigoId(rs.getInt("amigo_id"));
                obj.setFerramentaId(rs.getInt("ferramenta_id"));
                emprestimos.add(obj);
            }

            return emprestimos;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}