package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Role;
import model.entity.RolesVista;
import model.entity.Vista;
import dao.interfaces.DAORoleVista;

public class DAORoleVistaImpl implements Serializable, DAORoleVista {

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

	public int insertar(RolesVista roleVista) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(roleVista);
			tx.commit();
			return roleVista.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(RolesVista roleVista) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE RolesVista r SET r.role = :pRole, r.vista = :pVista "
					+ "WHERE r.id = :pId");
			locQuery.setParameter("pRole", roleVista.getRole());
			locQuery.setParameter("pVista", roleVista.getVista());
			locQuery.setParameter("pId", roleVista.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return roleVista.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public RolesVista get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.id = :pId", RolesVista.class);
		locQuery.setParameter("pId", id);
		RolesVista rolesVista = new RolesVista();
		try{
			rolesVista = (RolesVista) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			rolesVista = new RolesVista();
		}
		return rolesVista;
	}

	public List<RolesVista> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r", RolesVista.class);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

	public List<RolesVista> getLista(Role role) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.role = :pRole", RolesVista.class);
		locQuery.setParameter("pRole", role);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

	public List<RolesVista> getLista(Vista vista) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.vista = :pVista", RolesVista.class);
		locQuery.setParameter("pVista", vista);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

	public int deleteVistasPorRol(Role role) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM RolesVista r "
					+ "WHERE r.role = :pRol");
			locQuery.setParameter("pRol", role);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			//cerrarInstancia();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			//cerrarInstancia();
			return 0;
		}
	}

}
