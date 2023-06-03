package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Administrador;

public class AdministradorDAO {
    private Connection conexao;
    
    public AdministradorDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public boolean verificarLogin(String id, String senha) {
        String sql = "SELECT * FROM administrador WHERE id = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void criarAdministrador(String id, String senha) {
        String sql = "INSERT INTO administrador (id, senha) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, senha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Administrador lerAdministrador(String id) {
        String sql = "SELECT * FROM administrador WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senha = rs.getString("senha");
                    return new Administrador(0, id, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizarAdministrador(String id, String novaSenha) {
        String sql = "UPDATE administrador SET senha = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluirAdministrador(String id) {
        String sql = "DELETE FROM administrador WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}