package dao.interfaces;

import java.util.List;

import model.entity.ComprasDetalle;
import model.entity.ComprasDetalleUnidad;

public interface DAOCompraDetalleUnidad {
	
	public int insertar(ComprasDetalleUnidad comprasDetalleUnidad);
	
	public int update(ComprasDetalleUnidad comprasDetalleUnidad);
	
	public ComprasDetalleUnidad get(int id);
	
	public ComprasDetalleUnidad get(String nroImei);
	
	public ComprasDetalleUnidad getAll(String nroImei);
	
	public List<ComprasDetalleUnidad> getLista();
	
	public List<ComprasDetalleUnidad> getLista(ComprasDetalle comprasDetalle);
	
	public int deleteDetalleUnidadPorDetalle(ComprasDetalle comprasDetalle);
	
	public int deleteDetalleCompraUnidad(ComprasDetalleUnidad comprasUnidad);

}
