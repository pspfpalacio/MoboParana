package dao.interfaces;

import java.util.List;

import model.entity.Consignacion;
import model.entity.Cuota;

public interface DAOCuota {
	
	public int insertar(Cuota cuota);
	
	public int update(Cuota cuota);
	
	public Cuota get(int id);
	
	public Cuota get(String imei);
	
	public List<Cuota> getLista();
	
	public List<Cuota> getLista(boolean estado);
	
	public List<Cuota> getLista(Consignacion consignacion);
	
	public List<Cuota> getLista(boolean estado, Consignacion consignacion);

}
