package dao.interfaces;

import java.util.List;

import model.entity.Producto;
import model.entity.Stock;

public interface DAOStock {
	
	public int insertar(Stock stock);
	
	public int update(Stock stock);
	
	public List<Stock> getLista();
	
	public List<Stock> getLista(Producto producto);
	
	public Stock get(Producto producto, float precio);
	
	public List<Stock> getListaEnStock(boolean estado);

}
