package dao.interfaces;

import java.util.List;

import model.entity.CuotasVenta;
import model.entity.Venta;

public interface DAOCuotaVenta {
	
	public int insertar(CuotasVenta cuotasVenta);
	
	public int update(CuotasVenta cuotasVenta);
	
	public CuotasVenta get(int id);
	
	public CuotasVenta get(String imei);
	
	public List<CuotasVenta> getLista();
	
	public List<CuotasVenta> getLista(boolean estado);
	
	public List<CuotasVenta> getLista(Venta venta);
	
	public List<CuotasVenta> getLista(boolean estado, Venta venta);

}
