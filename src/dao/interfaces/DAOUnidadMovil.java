package dao.interfaces;

import java.util.List;

import model.entity.Producto;
import model.entity.Rubro;
import model.entity.UnidadMovil;

public interface DAOUnidadMovil {
	
	public int insertar(UnidadMovil unidadMovil);
	
	public int update(UnidadMovil unidadMovil);
	
	public UnidadMovil get(int id);
	
	public UnidadMovil get(String nroImei);
	
	public UnidadMovil getBajaStock(String nroImei);
	
	public UnidadMovil get(String nroImei, boolean estado, boolean enVenta);
	
	public List<UnidadMovil> getLista();
	
	public List<UnidadMovil> getLista(boolean estado);
	
	public List<UnidadMovil> getLista(Producto producto);
	
	public List<UnidadMovil> getLista(boolean estado, Producto producto);
	
	public List<UnidadMovil> getListaEnStock(boolean enStock);
	
	public List<UnidadMovil> getListaEnStock(boolean enStock, Producto producto);
	
	public List<UnidadMovil> getListaEnStock(boolean enStock, Producto producto, boolean enConsignacion, boolean estado);
	
	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock);
	
	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock, boolean enConsignacion, Rubro rubro);
	
	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock, Producto producto);
	
	public List<UnidadMovil> getListaEnStockOrdenPrecio(boolean estado, boolean enStock, Producto producto);
	
	public int deletePorProducto(Producto producto);
	
	public int deletePorImei(String imei);
	
	public List<UnidadMovil> getLike(String nroImei);
	
	public List<UnidadMovil> getLike(boolean estado, boolean enStock, String nroImei);
	
	public List<UnidadMovil> getLike(boolean estado, boolean enStock, Producto producto, String nroImei);
	
	public List<String> getListaImeis(int idProducto);
	
	public List<UnidadMovil> getLista(String nroImei);

}
