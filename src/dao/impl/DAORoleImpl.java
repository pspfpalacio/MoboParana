package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Role;
import dao.interfaces.DAORole;

public class DAORoleImpl implements Serializable, DAORole {

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

	public int insertar(Role role) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(role);
			tx.commit();
			return role.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Role role) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Role r SET r.nombre = :pNombre "
					+ "WHERE r.id = :pId");
			locQuery.setParameter("pNombre", role.getNombre());
			locQuery.setParameter("pId", role.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return role.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Role get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Role r WHERE r.id = :pId", Role.class);
		locQuery.setParameter("pId", id);
		Role role = new Role();
		try{
			role = (Role) locQuery.getSingleResult();
		}catch (Exception e){
			role = new Role();
		}
		return role;
	}

	public List<Role> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Role r", Role.class);
		List<Role> lista = locQuery.getResultList();
		return lista;
	}

}
