package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Gasto;
import dao.interfaces.DAOGasto;

public class DAOGastoImpl implements Serializable, DAOGasto {

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

	public int insertar(Gasto gasto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(gasto);
			tx.commit();
			return gasto.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Gasto gasto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Gasto g SET g.descripcion = :pDescripcion, g.estado = :pEstado, "
					+ "g.fecha = :pFecha, g.fechaAlta = :pFechaAlta, g.fechaBaja = :pFechaBaja, g.fechaMod = :pFechaMod, "
					+ "g.monto = :pMonto, g.usuario1 = :pUsuarioAlta, g.usuario2 = :pUsuarioBaja, g.usuario3 = :pUsuarioMod "
					+ "WHERE g.id = :pId");
			locQuery.setParameter("pDescripcion", gasto.getDescripcion());
			locQuery.setParameter("pEstado", gasto.getEstado());
			locQuery.setParameter("pFecha", gasto.getFecha());
			locQuery.setParameter("pFechaAlta", gasto.getFechaAlta());
			locQuery.setParameter("pFechaBaja", gasto.getFechaBaja());
			locQuery.setParameter("pFechaMod", gasto.getFechaMod());
			locQuery.setParameter("pMonto", gasto.getMonto());
			locQuery.setParameter("pUsuarioAlta", gasto.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", gasto.getUsuario2());
			locQuery.setParameter("pUsuarioMod", gasto.getUsuario3());
			locQuery.setParameter("pId", gasto.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return gasto.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Gasto get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.id = :pId", Gasto.class);
		locQuery.setParameter("pId", id);
		Gasto gasto = new Gasto();
		try{
			gasto = (Gasto) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			gasto = new Gasto();
		}
		return gasto;
	}

	public List<Gasto> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g", Gasto.class);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.estado = :pEstado", Gasto.class);
		locQuery.setParameter("pEstado", estado);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.fecha BETWEEN :pInicio AND :pFin", Gasto.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.estado = :pEstado AND "
				+ "g.fecha BETWEEN :pInicio AND :pFin", Gasto.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

}
