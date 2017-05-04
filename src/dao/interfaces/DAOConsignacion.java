package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.Producto;

public interface DAOConsignacion {
	
	public int insertar(Consignacion consignacion);
	
	public int update(Consignacion consignacion);
	
	public Consignacion get(int id);
	
	public Consignacion get(Cliente cliente, boolean estado);
	
	public List<Consignacion> getLista();
	
	public List<Consignacion> getLista(boolean estado);
	
	public List<Consignacion> getLista(Cliente cliente);
	
	public List<Consignacion> getLista(Date fechaDesde, Date fechaHasta);
	
	public List<Consignacion> getLista(boolean estado, Cliente cliente);
	
	public List<Consignacion> getLista(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Consignacion> getLista(Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<Consignacion> getLista(boolean estado, Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<Consignacion> getLista(Producto producto);
	
	public List<Consignacion> getListaProductoDisponible(Producto producto);

}
