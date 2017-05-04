package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.CuotasVenta;
import model.entity.CuotasVentasDetalle;
import model.entity.Venta;

public interface DAOCuotaVentaDetalle {
	
	public int insertar(CuotasVentasDetalle cuotasVentasDetalle);
	
	public int update(CuotasVentasDetalle cuotasVentasDetalle);
	
	public CuotasVentasDetalle get(int id);
	
	public List<CuotasVentasDetalle> getLista();
	
	public List<CuotasVentasDetalle> getLista(boolean estado);
	
	public List<CuotasVentasDetalle> getLista(CuotasVenta cuotasVenta);
	
	public List<CuotasVentasDetalle> getLista(boolean estado, CuotasVenta cuotasVenta);
	
	public List<CuotasVentasDetalle> getListaPorVencer(Date fecha);
	
	public List<CuotasVentasDetalle> getListaPorVencer(CuotasVenta cuotasVenta, Date fecha);
	
	public List<CuotasVentasDetalle> getListaPorVencer(Venta venta, Date fecha);

}
