package dao.interfaces;

import java.util.List;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.Producto;

public interface DAOConsignacionDetalleUnidad {
	
	public int insertar(ConsignacionsDetalleUnidad consignacionsDetalleUnidad);
	
	public int update(ConsignacionsDetalleUnidad consignacionsDetalleUnidad);
	
	public ConsignacionsDetalleUnidad get(int id);
	
	public ConsignacionsDetalleUnidad get(String imei);
	
	public ConsignacionsDetalleUnidad getAll(String imei);
	
	public int deleteUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad);
	
	public int deletePorDetalle(ConsignacionsDetalle consignacionsDetalle);
	
	public List<ConsignacionsDetalleUnidad> getLista();
	
	public List<ConsignacionsDetalleUnidad> getLista(ConsignacionsDetalle consignacionsDetalle);
	
	public List<ConsignacionsDetalleUnidad> getLista(Consignacion consignacion);
	
	public List<ConsignacionsDetalleUnidad> getLista(Consignacion consignacion, boolean vendido);
	
	public List<ConsignacionsDetalle> getListaDetalleVendido(Consignacion consignacion, boolean vendido);
	
	public List<ConsignacionsDetalleUnidad> getLista(ConsignacionsDetalle consignacionsDetalle, boolean vendido);
	
	public List<ConsignacionsDetalleUnidad> getLista(Producto producto, boolean vendido);
	
	public List<ConsignacionsDetalleUnidad> getListaOrderFechaVenta(Consignacion consignacion, boolean estado);
	
	public ConsignacionsDetalleUnidad getUnidad(String nroImei, Consignacion consignacion, Cliente cliente);

}
