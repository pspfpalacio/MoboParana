package dao.interfaces;

import java.util.List;

import model.entity.Tecnico;

public interface DAOTecnico {
	
	public int insertar(Tecnico tecnico);
	
	public int update(Tecnico tecnico);
	
	public Tecnico get(int id);
	
	public List<Tecnico> getLista();
	
	public List<Tecnico> getLista(boolean estado);

}
