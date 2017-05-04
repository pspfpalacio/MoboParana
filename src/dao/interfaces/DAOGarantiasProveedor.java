package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.GarantiasProveedore;
import model.entity.Producto;
import model.entity.Proveedore;

public interface DAOGarantiasProveedor {
	
	public int insertar(GarantiasProveedore garantiasProveedore);
	
	public int update(GarantiasProveedore garantiasProveedore);
	
	public GarantiasProveedore get(int id);
	
	public GarantiasProveedore get(String imeiFalla);
	
	public GarantiasProveedore get(String imeiFalla, String imeiReintegro);
	//Todas las listas realizan la busqueda con estado = true
	public List<GarantiasProveedore> getLista();
	
	public List<GarantiasProveedore> getLista(String imeiFalla);
	
	public List<GarantiasProveedore> getLista(Proveedore proveedore);
	
	public List<GarantiasProveedore> getLista(Producto productoFalla);
	
	public List<GarantiasProveedore> getLista(Proveedore proveedore, Producto productoFalla);
	
	public List<GarantiasProveedore> getLista(boolean finalizado);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Date fechaInicio, Date fechaFin);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Proveedore proveedore);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Producto productoFalla);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Proveedore proveedore);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Producto productoFalla);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Proveedore proveedore, Producto productoFalla);
	
	public List<GarantiasProveedore> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Proveedore proveedore, Producto productoFalla);

}
