package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Consignacion;
import model.entity.CuotasDetalle;
import model.entity.EntregaConsignacion;
import dao.interfaces.DAOEntregaConsignacion;

public class DAOEntregaConsignacionImpl implements Serializable,
		DAOEntregaConsignacion {

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

	public int insertar(EntregaConsignacion entregaConsignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(entregaConsignacion);
			tx.commit();
			return entregaConsignacion.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(EntregaConsignacion entregaConsignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE EntregaConsignacion e SET e.concepto = :pConcepto, e.consignacion = :pConsignacion, e.estado = :pEstado, "
					+ "e.fecha = :pFecha, e.monto = :pMonto, e.cuotasDetalle = :pCuotasDetalle, e.cuota = :pCuota "
					+ "WHERE e.id = :pId");
			locQuery.setParameter("pConcepto", entregaConsignacion.getConcepto());
			locQuery.setParameter("pConsignacion", entregaConsignacion.getConsignacion());
			locQuery.setParameter("pEstado", entregaConsignacion.getEstado());
			locQuery.setParameter("pFecha", entregaConsignacion.getFecha());
			locQuery.setParameter("pMonto", entregaConsignacion.getMonto());
			locQuery.setParameter("pCuotasDetalle", entregaConsignacion.getCuotasDetalle());
			locQuery.setParameter("pCuota", entregaConsignacion.getCuota());
			locQuery.setParameter("pId", entregaConsignacion.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return entregaConsignacion.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public EntregaConsignacion get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.id = :pId", EntregaConsignacion.class);
		locQuery.setParameter("pId", id);
		EntregaConsignacion entregaConsignacion = new EntregaConsignacion();
		try {
			entregaConsignacion = (EntregaConsignacion) locQuery.getSingleResult();
		} catch (Exception e) {
			entregaConsignacion = new EntregaConsignacion();
		}
		return entregaConsignacion;
	}
	
	public EntregaConsignacion get(CuotasDetalle cuotasDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.cuotasDetalle = :pCuotasDetalle "
				+ "AND e.estado = :pEstado", EntregaConsignacion.class);
		locQuery.setParameter("pCuotasDetalle", cuotasDetalle);
		locQuery.setParameter("pEstado", true);
		EntregaConsignacion entregaConsignacion = new EntregaConsignacion();
		try {
			entregaConsignacion = (EntregaConsignacion) locQuery.getSingleResult();
		} catch (Exception e) {
			entregaConsignacion = new EntregaConsignacion();
		}
		return entregaConsignacion;
	}

	public List<EntregaConsignacion> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e", EntregaConsignacion.class);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.estado = :pEstado", EntregaConsignacion.class);
		locQuery.setParameter("pEstado", estado);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.fecha BETWEEN :pInicio AND :pFin", EntregaConsignacion.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.consignacion = :pConsignacion", EntregaConsignacion.class);
		locQuery.setParameter("pConsignacion", consignacion);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(boolean estado, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.estado = :pEstado "
				+ "AND e.fecha BETWEEN :pInicio AND :pFin", EntregaConsignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(boolean estado,
			Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.estado = :pEstado "
				+ "AND e.consignacion = :pConsignacion ORDER BY e.fecha DESC", EntregaConsignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pConsignacion", consignacion);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(Date fechaInicio, Date fechaFin,
			Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.fecha BETWEEN :pInicio AND :pFin "
				+ "AND e.consignacion = :pConsignacion", EntregaConsignacion.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pConsignacion", consignacion);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EntregaConsignacion> getLista(boolean estado, Date fechaInicio,
			Date fechaFin, Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EntregaConsignacion e WHERE e.estado = :pEstado "
				+ "AND e.fecha BETWEEN :pInicio AND :pFin AND e.consignacion = :pConsignacion", EntregaConsignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pConsignacion", consignacion);
		List<EntregaConsignacion> lista = locQuery.getResultList();
		return lista;
	}

}
