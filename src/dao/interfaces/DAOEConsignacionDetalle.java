package dao.interfaces;

import java.util.List;

import model.entity.EConsignacion;
import model.entity.EConsignacionsDetalle;
import model.entity.Producto;

public interface DAOEConsignacionDetalle {
	
	public int insertar(EConsignacionsDetalle eConsignacionsDetalle);
	
	public int update(EConsignacionsDetalle eConsignacionsDetalle);
	
	public EConsignacionsDetalle get(int id);
	
	public EConsignacionsDetalle get(EConsignacion eConsignacion, Producto producto);
	
	public List<EConsignacionsDetalle> getLista();
	
	public List<EConsignacionsDetalle> getLista(EConsignacion eConsignacion);
	
	public List<EConsignacionsDetalle> getLista(Producto producto);
	
}
