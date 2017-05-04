package dao.interfaces;

import java.util.List;

import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.Producto;

public interface DAOConsignacionDetalle {
	
	public int insertar(ConsignacionsDetalle consignacionsDetalle);
	
	public int update(ConsignacionsDetalle consignacionsDetalle);
	
	public ConsignacionsDetalle get(int id);
	
	public ConsignacionsDetalle get(Consignacion consignacion, Producto producto);
	
	public int delete(Consignacion consignacion);
	
	public int delete(ConsignacionsDetalle consignacionsDetalle);
	
	public List<ConsignacionsDetalle> getLista();
	
	public List<ConsignacionsDetalle> getLista(Consignacion consignacion);
	
	public List<ConsignacionsDetalle> getLista(Producto producto);

}
