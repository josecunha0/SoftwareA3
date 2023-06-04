package DAO;

import java.util.List;

import Model.Ferramenta;

public interface FerramentaDao {
	
	void insert(Ferramenta obj);
	void update (Ferramenta obj);
	void deleteById(Integer id);
	Ferramenta findById(Integer id);
	List<Ferramenta> findAll();
	
}
