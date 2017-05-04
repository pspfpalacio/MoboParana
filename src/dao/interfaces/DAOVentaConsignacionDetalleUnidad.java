package dao.interfaces;

import java.util.List;

import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;


public interface DAOVentaConsignacionDetalleUnidad {
	
public int insertar(VentasConsDetalleUnidad ventasConsDetalleUnidad);
	
	public int update(VentasConsDetalleUnidad ventasConsDetalleUnidad);
	
	public VentasConsDetalleUnidad get(int id);
	
	public VentasConsDetalleUnidad get(String imei);
	
	public int deleteUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad);
	
	public int deletePorDetalle(VentasConsDetalle ventasConsDetalle);
	
	public List<VentasConsDetalleUnidad> getLista();
	
	public List<VentasConsDetalleUnidad> getLista(VentasConsDetalle ventasConsDetalle);
	
	public List<VentasConsDetalleUnidad> getLista(VentasCon ventasCon);

}
