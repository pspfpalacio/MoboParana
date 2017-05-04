package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Producto;
import model.entity.Venta;
import model.entity.VentasDetalle;

public interface DAOVentaDetalle {
	
	public int insertar(VentasDetalle ventasDetalle);
	
	public int update(VentasDetalle ventasDetalle);
	
	public VentasDetalle get(int id);
	
	public VentasDetalle get(Venta venta, Producto producto);
	
	public int delete(Venta venta);
	
	public int delete(VentasDetalle ventasDetalle);
	
	public List<VentasDetalle> getLista();
	
	public List<VentasDetalle> getLista(Venta venta);
	
	public List<VentasDetalle> getLista(Producto producto);
	
	public List<VentasDetalle> getLista(Producto producto, boolean estadoVenta);
	
	public List<VentasDetalle> getLista(Producto producto, boolean estadoVenta, Date fechaDesde, Date fechaHasta);

}
