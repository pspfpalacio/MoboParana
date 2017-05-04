package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.EConsignacion;

public interface DAOEConsignacion {
	
	public int insertar(EConsignacion eConsignacion);
	
	public int update(EConsignacion eConsignacion);
	
	public EConsignacion get(int id);
	
	public List<EConsignacion> getLista();
	
	public List<EConsignacion> getLista(Cliente cliente);
	
	public List<EConsignacion> getLista(Date fechaDesde, Date fechaHasta);
	
	public List<EConsignacion> getLista(Cliente cliente, Date fechaDesde, Date fechaHasta);
	
	public List<EConsignacion> getLista(Consignacion consignacion);
	
	public List<EConsignacion> getLista(Consignacion consignacion, Date fechaDesde, Date fechaHasta);

}
