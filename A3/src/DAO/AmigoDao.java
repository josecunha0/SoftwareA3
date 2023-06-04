package DAO;

import java.util.List;
import Model.Amigo;

public interface AmigoDao {
	
	void insert(Amigo obj);
	void update (Amigo obj);
	void deleteById(Integer id);
	Amigo findById(Integer id);
	List<Amigo> findAll();
	
}
