package dao.interfaces;

import java.util.List;

import model.entity.HistorialMovil;

public interface DAOHistorialMovil {
	
	public List<HistorialMovil> get();
	
	public List<HistorialMovil> getPorImei(String imei);

}
