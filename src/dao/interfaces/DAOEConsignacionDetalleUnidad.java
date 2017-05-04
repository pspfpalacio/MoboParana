package dao.interfaces;

import java.util.List;

import model.entity.EConsignacion;
import model.entity.EConsignacionsDetalle;
import model.entity.EConsignacionsDetalleUnidad;
import model.entity.Producto;

public interface DAOEConsignacionDetalleUnidad {
	
	public int insertar(EConsignacionsDetalleUnidad eConsignacionsDetalleUnidad);
	
	public int update(EConsignacionsDetalleUnidad eConsignacionsDetalleUnidad);
	
	public EConsignacionsDetalleUnidad get(int id);
	
	public List<EConsignacionsDetalleUnidad> getLista();
	
	public List<EConsignacionsDetalleUnidad> getLista(EConsignacionsDetalle eConsignacionsDetalle);
	
	public List<EConsignacionsDetalleUnidad> getLista(EConsignacion eConsignacion);
	
	public List<EConsignacionsDetalleUnidad> getLista(Producto producto);

}
