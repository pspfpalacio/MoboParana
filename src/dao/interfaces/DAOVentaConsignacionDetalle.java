package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Producto;
import model.entity.Rubro;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;

public interface DAOVentaConsignacionDetalle {
	
public int insertar(VentasConsDetalle ventasConsDetalle);
	
	public int update(VentasConsDetalle ventasConsDetalle);
	
	public VentasConsDetalle get(int id);
	
	public VentasConsDetalle get(VentasCon ventasCon, Producto producto);
	
	public int delete(VentasCon ventasCon);
	
	public int delete(VentasConsDetalle ventasConsDetalle);
	
	public List<VentasConsDetalle> getLista();
	
	public List<VentasConsDetalle> getLista(VentasCon ventasCon);
	
	public List<VentasConsDetalle> getLista(Producto producto);
	
	public List<VentasConsDetalle> getLista(Producto producto, boolean estadoVenta);
	
	public List<VentasConsDetalle> getLista(Producto producto, boolean estadoVenta, Date fechaDesde, Date fechaHasta);
	
	public List<VentasConsDetalle> getLista(Rubro rubro, boolean estadoVenta, Date fechaDesde, Date fechaHasta);

}
