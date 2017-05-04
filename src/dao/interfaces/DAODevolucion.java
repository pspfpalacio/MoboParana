package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.Devolucione;
import model.entity.Producto;
import model.entity.UnidadMovil;

public interface DAODevolucion {
	
	public int insertar(Devolucione devolucione);
	
	public int update(Devolucione devolucione);
	
	public Devolucione get(int id);
	
	public Devolucione get(String imei);
	
	public Devolucione get(String imei, boolean estado);
	
	public Devolucione get(UnidadMovil unidadMovil);
	
	public List<Devolucione> getLista();
	
	public List<Devolucione> getLista(boolean estado);
	
	public List<Devolucione> getLista(Cliente cliente);
	
	public List<Devolucione> getLista(Producto producto);
	
	public List<Devolucione> getLista(Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(boolean estado, Cliente cliente);
	
	public List<Devolucione> getLista(boolean estado, Producto producto);
	
	public List<Devolucione> getLista(boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(Cliente cliente, Producto producto);
	
	public List<Devolucione> getLista(Cliente cliente, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(Producto producto, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(boolean estado, Cliente cliente, Producto producto);
	
	public List<Devolucione> getLista(boolean estado, Cliente cliente, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(boolean estado, Producto producto, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(Cliente cliente, Producto producto, Date fechaInicio, Date fechaFin);
	
	public List<Devolucione> getLista(boolean estado, Cliente cliente, Producto producto, Date fechaInicio, Date fechaFin);

}
