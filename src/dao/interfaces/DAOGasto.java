package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Gasto;

public interface DAOGasto {
	
	public int insertar(Gasto gasto);
	
	public int update(Gasto gasto);
	
	public Gasto get(int id);
	
	public List<Gasto> getLista();
	
	public List<Gasto> getLista(boolean estado);
	
	public List<Gasto> getLista(Date fechaInicio, Date fechaFin);
	
	public List<Gasto> getLista(boolean estado, Date fechaInicio, Date fechaFin);

}
