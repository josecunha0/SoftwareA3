package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import Model.Administrador;

public class AdministradorDao {

    private Connection conn;
    
    public AdministradorDao(Connection conn) {
        this.conn = conn;
    }
    
    // Método para buscar o administrador pelo ID
    public Administrador findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Administrador WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setSenha(rs.getString("senha"));
                return admin;
            }
            
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

	public void insert(Administrador administrador) {
		// TODO Auto-generated method stub
		
	}

	public void update(Administrador administradorEncontrado) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int i) {
		// TODO Auto-generated method stub
		
	}

	public List<Administrador> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
