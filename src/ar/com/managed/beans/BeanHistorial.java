package ar.com.managed.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import dao.interfaces.DAOHistorialMovil;
import model.entity.HistorialMovil;
import model.entity.UnidadMovil;

@ManagedBean
@SessionScoped
public class BeanHistorial implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanHistorial.class);
	
	@ManagedProperty(value = "#{BeanHistorialMovilDAO}")
	private DAOHistorialMovil historialMovilDAO;
	
	private List<HistorialMovil> historialMovilList;
	private String imei;
	
	public DAOHistorialMovil getHistorialMovilDAO() {
		return historialMovilDAO;
	}

	public void setHistorialMovilDAO(DAOHistorialMovil historialMovilDAO) {
		this.historialMovilDAO = historialMovilDAO;
	}

	public List<HistorialMovil> getHistorialMovilList() {
		return historialMovilList;
	}

	public void setHistorialMovilList(List<HistorialMovil> historialMovilList) {
		this.historialMovilList = historialMovilList;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}



	public String goHistorial(String imeiSelected){
		historialMovilList = historialMovilDAO.getPorImei(imeiSelected);
		return "historial";
	}
	
	public String verHistorial(String imeiSelected) {
		imei = imeiSelected;
		log.info("Imei: " +imei);
		historialMovilList = historialMovilDAO.getPorImei(imei);
		return "historial";
	}

}
