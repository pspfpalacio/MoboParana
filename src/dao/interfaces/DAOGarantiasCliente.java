package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.GarantiasCliente;
import model.entity.Producto;

public interface DAOGarantiasCliente {
	
	public int insertar(GarantiasCliente garantiasCliente);
	
	public int update(GarantiasCliente garantiasCliente);
	
	public GarantiasCliente get(int id);
	
	public GarantiasCliente get(String imeiFalla);
	
	public GarantiasCliente get(String imeiFalla, String imeiReintegro);
	//Todas las listas realizan la busqueda con estado = true
	public List<GarantiasCliente> getLista();
	
	public List<GarantiasCliente> getLista(String imeiFalla);
	
	public List<GarantiasCliente> getLista(Cliente cliente);
	
	public List<GarantiasCliente> getLista(Producto productoFalla);
	
	public List<GarantiasCliente> getLista(Cliente cliente, Producto productoFalla);
	
	public List<GarantiasCliente> getLista(Date fechaInicio, Date fechaFin);
	
	public List<GarantiasCliente> getLista(boolean finalizado);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio, Date fechaFin);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Cliente cliente);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Producto productoFalla);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Cliente cliente);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Producto productoFalla);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Cliente cliente, Producto productoFalla);
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio, Date fechaFin, Cliente cliente, Producto productoFalla);

}
