package DAO;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Ferramenta;

public class FerramentaDAO {

    public static ArrayList<Ferramenta> MinhaLista = new ArrayList<Ferramenta>();
    
    public FerramentaDAO(){
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
    
    public void criarFerramenta(int id, String nome, String marca, double custo_de_aquisicao) {
        Connection conexao = getConexao();
        String sql = "INSERT INTO ferramenta (id, nome, marca, custo_de_aquisicao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, marca);
            stmt.setDouble(4, custo_de_aquisicao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Ferramenta lerFerramenta(int id) {
        Connection conexao = getConexao();
        String sql = "SELECT * FROM ferramenta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String marca = rs.getString("marca");
                    double custo_de_aquisicao = rs.getDouble("custo_de_aquisicao");
                    return new Ferramenta(id, nome, marca, custo_de_aquisicao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizarFerramenta(int id, String novoNome, String novaMarca, double novoCustoDeAquisicao) {
        Connection conexao = getConexao();
        String sql = "UPDATE amigo SET nome = ?, marca = ?, custo_de_aquisicao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novaMarca);
            stmt.setDouble(3, novoCustoDeAquisicao);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluirFerramenta(int id) {
        Connection conexao = getConexao();
        String sql = "DELETE FROM ferramenta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
