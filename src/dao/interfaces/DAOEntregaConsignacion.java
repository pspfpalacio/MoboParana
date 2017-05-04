package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Consignacion;
import model.entity.CuotasDetalle;
import model.entity.EntregaConsignacion;

public interface DAOEntregaConsignacion {
	
	public int insertar(EntregaConsignacion entregaConsignacion);
	
	public int update(EntregaConsignacion entregaConsignacion);
	
	public EntregaConsignacion get(int id);
	
	public EntregaConsignacion get(CuotasDetalle cuotasDetalle);
	
	public List<EntregaConsignacion> getLista();
	
	public List<EntregaConsignacion> getLista(boolean estado);
	
	public List<EntregaConsignacion> getLista(Date fechaInicio, Date fechaFin);
	
	public List<EntregaConsignacion> getLista(Consignacion consignacion);
	
	public List<EntregaConsignacion> getLista(boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<EntregaConsignacion> getLista(boolean estado, Consignacion consignacion);
	
	public List<EntregaConsignacion> getLista(Date fechaInicio, Date fechaFin, Consignacion consignacion);
	
	public List<EntregaConsignacion> getLista(boolean estado, Date fechaInicio, Date fechaFin, Consignacion consignacion);

}
