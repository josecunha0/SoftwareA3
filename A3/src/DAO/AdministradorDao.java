package DAO;

import java.util.List;
import Model.Administrador;

public interface AdministradorDao {
	void insert(Administrador obj);
	void update (Administrador obj);
	void deleteById(Integer id);
	Administrador findById(Integer id);
	List<Administrador> findAll();
}
