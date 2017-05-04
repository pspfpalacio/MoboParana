package dao.interfaces;

import java.util.List;

import model.entity.Rubro;

public interface DAORubro {
	
	public int insertar(Rubro rubro);
	
	public int update(Rubro rubro);
	
	public Rubro get(int id);
	
	public List<Rubro> getLista();
	
	public List<Rubro> getLista(boolean estado);

}
