package dao.interfaces;

import java.util.List;

import model.entity.Proveedore;

public interface DAOProveedor {
	
	public int insertar(Proveedore proveedore);
	
	public int update(Proveedore proveedore);
	
	public Proveedore get(int id);
	
	public List<Proveedore> getLista();
	
	public List<Proveedore> getLista(boolean estado);

}
