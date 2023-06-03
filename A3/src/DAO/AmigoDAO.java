package DAO;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Amigo;

public class AmigoDAO {
    
    public static ArrayList<Amigo> MinhaLista = new ArrayList<Amigo>();
    
    public AmigoDAO() {
    }
    
    public Connection getConexao() {
        Connection connection = null;
        try {
            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conexão
            String server = "localhost"; //caminho do MySQL
            String database = "Dev";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "******";

            connection = DriverManager.getConnection(url, user, password);

            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    } 
    
    public void criarAmigo(int id, String nome, String email, String telefone) {
        Connection conexao = getConexao();
        String sql = "INSERT INTO amigo (id, nome, email, telefone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Amigo lerAmigo(int id) {
        Connection conexao = getConexao();
        String sql = "SELECT * FROM amigo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String telefone = rs.getString("telefone");
                    return new Amigo(id, nome, email, telefone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizarAmigo(int id, String novoNome, String novoEmail, String novoTelefone) {
        Connection conexao = getConexao();
        String sql = "UPDATE amigo SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novoEmail);
            stmt.setString(3, novoTelefone);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluirAmigo(int id) {
        Connection conexao = getConexao();
        String sql = "DELETE FROM amigo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
