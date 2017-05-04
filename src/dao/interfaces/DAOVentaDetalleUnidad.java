package dao.interfaces;

import java.util.List;

import model.entity.Venta;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;

public interface DAOVentaDetalleUnidad {
	
	public int insertar(VentasDetalleUnidad ventasDetalleUnidad);
	
	public int update(VentasDetalleUnidad ventasDetalleUnidad);
	
	public VentasDetalleUnidad get(int id);
	
	public VentasDetalleUnidad get(String imei);
	
	public int deleteUnidad(VentasDetalleUnidad ventasDetalleUnidad);
	
	public int deletePorDetalle(VentasDetalle ventasDetalle);
	
	public List<VentasDetalleUnidad> getLista();
	
	public List<VentasDetalleUnidad> getLista(VentasDetalle ventasDetalle);
	
	public List<VentasDetalleUnidad> getLista(Venta venta);

}
