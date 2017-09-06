package dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.EquipoPendientePago;
import dao.interfaces.DAOEquipoPendientePago;

public class DAOEquipoPendientePagoImpl  implements Serializable, DAOEquipoPendientePago {
	
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
	
	public int insert(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(epp);
			tx.commit();
			return epp.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

}
