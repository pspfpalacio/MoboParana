package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.interfaces.DAOHistorialMovil;
import model.entity.HistorialMovil;

public class DAOHistorialMovilImpl implements Serializable, DAOHistorialMovil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public void inicializar(){
		emf = Persistence.createEntityManagerFactory("MoboParana");
		em = emf.createEntityManager();
	}
	
	public void cerrarInstancia() {
		em.close();
		emf.close();
	}
	
	public List<HistorialMovil> get() {
		inicializar();
		Query locQuery = em.createQuery("SELECT h FROM HistorialMovil h ORDER BY h.fecha DESC", HistorialMovil.class);
		List<HistorialMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<HistorialMovil> getPorImei(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT h FROM HistorialMovil h WHERE h.imei = :pImei ORDER BY h.fecha DESC", HistorialMovil.class);
		locQuery.setParameter("pImei", imei);
		List<HistorialMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	
}
