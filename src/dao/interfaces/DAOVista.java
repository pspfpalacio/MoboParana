package dao.interfaces;

import java.util.List;

import model.entity.Vista;

public interface DAOVista {
	
	public int insertar(Vista vista);
	
	public int update(Vista vista);
	
	public Vista get(int id);
	
	public List<Vista> getLista();

}
