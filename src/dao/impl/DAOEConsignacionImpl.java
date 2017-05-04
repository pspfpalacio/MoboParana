package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.EConsignacion;
import dao.interfaces.DAOEConsignacion;

public class DAOEConsignacionImpl implements Serializable, DAOEConsignacion {

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

	public int insertar(EConsignacion eConsignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(eConsignacion);
			tx.commit();
			return eConsignacion.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(EConsignacion eConsignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE EConsignacion e SET e.cliente = :pCliente, e.consignacion = :pConsignacion, "
					+ "e.fecha = :pFecha, e.fechaAlta = :pFechaAlta, e.monto = :pMonto, e.usuario = :pUsuario "
					+ "WHERE e.id = :pId");
			locQuery.setParameter("pCliente", eConsignacion.getCliente());
			locQuery.setParameter("pConsignacion", eConsignacion.getConsignacion());
			locQuery.setParameter("pFecha", eConsignacion.getFecha());
			locQuery.setParameter("pFechaAlta", eConsignacion.getFechaAlta());
			locQuery.setParameter("pMonto", eConsignacion.getMonto());
			locQuery.setParameter("pUsuario", eConsignacion.getUsuario());			
			locQuery.setParameter("pId", eConsignacion.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return eConsignacion.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public EConsignacion get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.id = :pId", EConsignacion.class);
		locQuery.setParameter("pId", id);
		EConsignacion eConsignacion = new EConsignacion();
		try{
			eConsignacion = (EConsignacion) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			eConsignacion = new EConsignacion();
		}
		return eConsignacion;
	}

	public List<EConsignacion> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e ORDER BY e.fecha DESC", EConsignacion.class);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacion> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.cliente = :pCliente ORDER BY e.fecha DESC", EConsignacion.class);
		locQuery.setParameter("pCliente", cliente);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacion> getLista(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.fecha BETWEEN :pInicio AND :pFin ORDER BY e.fecha DESC", EConsignacion.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacion> getLista(Cliente cliente, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.cliente = :pCliente "
				+ "AND e.fecha BETWEEN :pInicio AND :pFin ORDER BY e.fecha DESC", EConsignacion.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacion> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.consignacion = :pConsignacion ORDER BY e.fecha DESC", EConsignacion.class);
		locQuery.setParameter("pConsignacion", consignacion);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacion> getLista(Consignacion consignacion,
			Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacion e WHERE e.consignacion = :pConsignacion "
				+ "AND e.fecha BETWEEN :pInicio AND :pFin ORDER BY e.fecha DESC", EConsignacion.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<EConsignacion> lista = locQuery.getResultList();
		return lista;
	}

}
