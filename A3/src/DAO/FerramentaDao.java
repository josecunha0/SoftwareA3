package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Ferramenta;
import db.DB;
import db.DbException;

public class FerramentaDao {

	private Connection conn;
	
	public FerramentaDao() {
		this.conn = DB.getConnection();
	}
	
	public void insert(Ferramenta ferramenta) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Ferramenta (nome, marca, custo_aquisicao) VALUES (?, ?, ?)");
			st.setString(1, ferramenta.getNome());
			st.setString(2, ferramenta.getMarca());
			st.setDouble(3, ferramenta.getCustoDeAquisicao());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Ferramenta inserida com sucesso!");
			} else {
				System.out.println("Erro ao inserir ferramenta!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void update(Ferramenta ferramenta) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Ferramenta SET nome = ?, marca = ?, custo_aquisicao = ? WHERE id = ?");
			st.setString(1, ferramenta.getNome());
			st.setString(2, ferramenta.getMarca());
			st.setDouble(3, ferramenta.getCustoDeAquisicao());
			st.setInt(4, ferramenta.getId());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Ferramenta atualizada com sucesso!");
			} else {
				System.out.println("Erro ao atualizar ferramenta!");
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
			st = conn.prepareStatement("DELETE FROM Ferramenta WHERE id = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Ferramenta removida com sucesso!");
			} else {
				System.out.println("Erro ao remover ferramenta!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public Ferramenta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Ferramenta WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Ferramenta ferramenta = new Ferramenta();
				ferramenta.setId(rs.getInt("id"));
				ferramenta.setNome(rs.getString("nome"));
				ferramenta.setMarca(rs.getString("marca"));
				ferramenta.setCustoDeAquisicao(rs.getDouble("custo_aquisicao"));
				return ferramenta;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public List<Ferramenta> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Ferramenta");
			rs = st.executeQuery();

			List<Ferramenta> ferramentas = new ArrayList<>();

			while (rs.next()) {
				Ferramenta ferramenta = new Ferramenta();
				ferramenta.setId(rs.getInt("id"));
				ferramenta.setNome(rs.getString("nome"));
				ferramenta.setMarca(rs.getString("marca"));
				ferramenta.setCustoDeAquisicao(rs.getDouble("custo_aquisicao"));
				ferramentas.add(ferramenta);
			}

			return ferramentas;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
