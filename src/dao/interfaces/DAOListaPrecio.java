package dao.interfaces;

import java.util.List;

import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;

public interface DAOListaPrecio {
	
	public int insertar(ListaPrecio listaPrecio);
	
	public int update(ListaPrecio listaPrecio);
	
	public ListaPrecio get(int id);
	
	public ListaPrecio getBase();
	
	public List<ListaPrecio> getLista();
	
	public List<ListaPrecio> getLista(boolean estado);
	
	public List<ListaPrecio> getLista(boolean estado, boolean relacionBase);
	
	public int insertar(ListaPrecioProducto listaPrecioProducto);
	
	public int update(ListaPrecioProducto listaPrecioProducto);
	
	public ListaPrecioProducto getItemProducto(int id);
	
	public ListaPrecioProducto getItemProducto(ListaPrecio listaPrecio, Producto producto);
	
	public List<ListaPrecioProducto> getListaPrecioProducto();
	
	public List<ListaPrecioProducto> getListaPrecioProducto(ListaPrecio listaPrecio);
	
	public List<ListaPrecioProducto> getListaPrecioProducto(Producto producto);
	
	public int deleteProductosPorLista(ListaPrecio listaPrecio);

}
