package dao.interfaces;

import java.util.List;

import model.entity.Producto;
import model.entity.Rubro;

public interface DAOProducto {
	
	public int insertar(Producto producto);
	
	public int update(Producto producto);
	
	public Producto get(int id);
	
	public List<Producto> getLista();
	
	public List<Producto> getLista(boolean estado);
	
	public List<Producto> getLista(Rubro rubro);
	
	public List<Producto> getLista(boolean estado, Rubro rubro);
	
	public List<Producto> getListaDebajoMinimo(Rubro rubro);

}
