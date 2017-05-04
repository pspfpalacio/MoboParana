package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.Producto;
import model.entity.Usuario;
import model.entity.VentasCon;

public interface DAOVentaConsignacion {
	
	public int insertar(VentasCon ventasCon);
	
	public int update(VentasCon ventasCon);
	
	public VentasCon get(int id);
	
	public Integer getMaxId();
	
	public List<VentasCon> getLista();
	
	public List<VentasCon> getLista(boolean estado);
	
	public List<VentasCon> getLista(Cliente cliente);
	
	public List<VentasCon> getLista(Consignacion consignacion);
	
	public List<VentasCon> getLista(Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(boolean estado, Cliente cliente);
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion);
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion, Usuario usuario);
	
	public List<VentasCon> getLista(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(Consignacion consignacion, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(boolean estado, Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion, Date fechaDesde, Date fechaHasta, Usuario usuario);
	
	public List<VentasCon> getLista(Producto producto, Cliente cliente);
	
	public List<VentasCon> getListaOrderFecha(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderMonto(boolean estado, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderFecha(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderMonto(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderFecha(boolean estado, Producto producto, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderMonto(boolean estado, Producto producto, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderFecha(boolean estado, List<Cliente> clientes, Producto producto, Date fechaDesde, Date fechaHasta);
	
	public List<VentasCon> getListaOrderMonto(boolean estado, List<Cliente> clientes, Producto producto, Date fechaDesde, Date fechaHasta);

}
