package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.Venta;

public interface DAOVenta {
	
	public int insertar(Venta venta);
	
	public int update(Venta venta);
	
	public Venta get(int id);
	
	public List<Venta> getListaSinOrden();
	
	public List<Venta> getLista();
	
	public List<Venta> getLista(boolean estado);
	
	public List<Venta> getLista(Cliente cliente);
	
	public List<Venta> getLista(Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getLista(boolean estado, Cliente cliente);
	
	public List<Venta> getLista(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getLista(Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getLista(boolean estado, Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getLista(Producto producto);
	
	public List<Venta> getLista(Producto producto, Cliente cliente);
	
	public List<Venta> getListaOrderFecha(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderFecha(boolean estado, Rubro rubro, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(boolean estado, Rubro rubro, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderFecha(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderFecha(boolean estado, List<Cliente> clientes, Rubro rubro, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(boolean estado, List<Cliente> clientes, Rubro rubro, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderFecha(Producto producto, boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(Producto producto, boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderFecha(Producto producto, List<Cliente> clientes, boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getListaOrderMonto(Producto producto, List<Cliente> clientes, boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<Venta> getLista(Rubro rubro, boolean estado, Date fechaDesde, Date fechaHasta);
	
}
