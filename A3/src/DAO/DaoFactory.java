package DAO;

import DAO.impl.AmigoDaoJDBC;
import db.DB;

public class DaoFactory {
	
	public static AmigoDao createAmigoDao() {
		return new AmigoDaoJDBC(DB.getConnection());
	}

}
