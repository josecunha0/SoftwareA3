package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Amigo;
import db.DB;
import db.DbException;

public class AmigoDao {

	private Connection conn;
	
	public AmigoDao() {
		this.conn = DB.getConnection();
	}
	
	// Insere um novo amigo no banco de dados
	public void insert(Amigo obj) {
		PreparedStatement st = null;
		try {
			// Prepara a consulta SQL para inserção de um amigo
			st = conn.prepareStatement("INSERT INTO Amigo (nome, email, telefone) VALUES (?, ?, ?)");
			
			// Define os valores dos parâmetros da consulta
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			
			// Executa a consulta
			int rowsAffected = st.executeUpdate();
			
			// Verifica se algum registro foi afetado
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

	// Atualiza os dados de um amigo no banco de dados
	public void update(Amigo obj) {
		PreparedStatement st = null;
		try {
			// Prepara a consulta SQL para atualização de um amigo
			st = conn.prepareStatement("UPDATE Amigo SET nome = ?, email = ?, telefone = ? WHERE id = ?");
			
			// Define os valores dos parâmetros da consulta
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			st.setInt(4, obj.getId());
			
			// Executa a consulta
			int rowsAffected = st.executeUpdate();
			
			// Verifica se algum registro foi afetado
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

	// Exclui um amigo do banco de dados pelo ID
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			// Prepara a consulta SQL para exclusão de um amigo
			st = conn.prepareStatement("DELETE FROM Amigo WHERE id = ?");
			
			// Define o valor do parâmetro da consulta
			st.setInt(1, id);
			
			// Executa a consulta
			int rowsAffected = st.executeUpdate();
			
			// Verifica se algum registro foi afetado
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

	// Busca um amigo pelo ID no banco de dados
	public Amigo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Prepara a consulta SQL para buscar um amigo pelo ID
			st = conn.prepareStatement("SELECT * FROM Amigo WHERE id = ?");
			
			// Define o valor do parâmetro da consulta
			st.setInt(1, id);
			
			// Executa a consulta
			rs = st.executeQuery();
			
			// Verifica se o resultado contém algum registro
			if (rs.next()) {
				Amigo obj = new Amigo();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				return obj;
			}
			
			// Retorna null se nenhum registro for encontrado
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	// Retorna uma lista com todos os amigos do banco de dados
	public List<Amigo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Prepara a consulta SQL para buscar todos os amigos
			st = conn.prepareStatement("SELECT * FROM Amigo");
			
			// Executa a consulta
			rs = st.executeQuery();
			
			// Cria uma lista para armazenar os amigos encontrados
			List<Amigo> amigos = new ArrayList<>();
			
			// Itera sobre o resultado e cria objetos Amigo para cada registro encontrado
			while (rs.next()) {
				Amigo obj = new Amigo();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				amigos.add(obj);
			}
			
			// Retorna a lista de amigos
			return amigos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
