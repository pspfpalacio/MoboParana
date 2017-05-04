package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Consignacion;
import model.entity.Cuota;
import model.entity.CuotasDetalle;

public interface DAOCuotaDetalle {
	
	public int insertar(CuotasDetalle cuotasDetalle);
	
	public int update(CuotasDetalle cuotasDetalle);
	
	public CuotasDetalle get(int id);
	
	public List<CuotasDetalle> getLista();
	
	public List<CuotasDetalle> getLista(boolean estado);
	
	public List<CuotasDetalle> getLista(Cuota cuota);
	
	public List<CuotasDetalle> getLista(boolean estado, Cuota cuota);
	
	public List<CuotasDetalle> getListaPorVencer(Date fecha);
	
	public List<CuotasDetalle> getListaPorVencer(Cuota cuota, Date fecha);
	
	public List<CuotasDetalle> getListaPorVencer(Consignacion consignacion, Date fecha);

}
