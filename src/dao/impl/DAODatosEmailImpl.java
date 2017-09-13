package dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.interfaces.DAODatosEmail;
import model.entity.DatosEmail;

public class DAODatosEmailImpl implements Serializable, DAODatosEmail {
	
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
	
	public DatosEmail get() {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM DatosEmail d WHERE d.id = :pId", DatosEmail.class);
		locQuery.setParameter("pId", 1);
		DatosEmail datosEmail = new DatosEmail();
		try {
			datosEmail = (DatosEmail) locQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
			datosEmail = new DatosEmail();
		}
		return datosEmail;
	}

}
