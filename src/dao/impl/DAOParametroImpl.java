package dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Parametro;
import dao.interfaces.DAOParametro;

public class DAOParametroImpl implements Serializable, DAOParametro {

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

	public int insertar(Parametro parametro) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(parametro);
			tx.commit();
			return parametro.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Parametro parametro) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE Parametro p SET p.cantMesesCp = :pCantMesesCP "
					+ "WHERE p.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pCantMesesCP", parametro.getCantMesesCp());
			locQuery.setParameter("pId", parametro.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return parametro.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public Parametro get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Parametro p WHERE p.id = :pId", Parametro.class);
		locQuery.setParameter("pId", id);
		Parametro parametro = new Parametro();
		try {
			parametro = (Parametro) locQuery.getSingleResult();
		} catch (Exception e) {
			parametro = new Parametro();
		}
		return parametro;
	}

}
