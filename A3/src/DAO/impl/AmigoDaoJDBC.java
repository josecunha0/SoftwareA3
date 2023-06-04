package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.AmigoDao;
import Model.Amigo;
import db.DB;
import db.DbException;

public class AmigoDaoJDBC implements AmigoDao {

	private Connection conn;
	
	public AmigoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Amigo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Amigo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Amigo findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement ("SELECT * FROM Amigo WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Amigo obj = new Amigo();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				return obj;
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Amigo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
