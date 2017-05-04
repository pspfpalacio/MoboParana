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
import model.entity.Cuota;
import model.entity.CuotasDetalle;
import dao.interfaces.DAOCuotaDetalle;

public class DAOCuotaDetalleImpl implements Serializable, DAOCuotaDetalle {

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

	public int insertar(CuotasDetalle cuotasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuotasDetalle);
			tx.commit();
			return cuotasDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(CuotasDetalle cuotasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE CuotasDetalle c SET c.cuota = :pCuota, c.descripcion = :pDescripcion, "
					+ "c.estado = :pEstado, c.fechaVencimiento = :pFechaVencimiento, c.monto = :pMonto, c.pago = :pPago, "
					+ "c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCuota", cuotasDetalle.getCuota());
			locQuery.setParameter("pDescripcion", cuotasDetalle.getDescripcion());
			locQuery.setParameter("pEstado", cuotasDetalle.getEstado());
			locQuery.setParameter("pFechaVencimiento", cuotasDetalle.getFechaVencimiento());
			locQuery.setParameter("pMonto", cuotasDetalle.getMonto());
			locQuery.setParameter("pPago", cuotasDetalle.getPago());
			locQuery.setParameter("pFechaAlta", cuotasDetalle.getFechaAlta());
			locQuery.setParameter("pFechaBaja", cuotasDetalle.getFechaBaja());
			locQuery.setParameter("pUsuarioAlta", cuotasDetalle.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", cuotasDetalle.getUsuario2());
			locQuery.setParameter("pId", cuotasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuotasDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public CuotasDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.id = :pId", CuotasDetalle.class);
		locQuery.setParameter("pId", id);
		CuotasDetalle cuotasDetalle = new CuotasDetalle();
		try {
			cuotasDetalle = (CuotasDetalle) locQuery.getSingleResult();
		} catch(Exception e) {
			cuotasDetalle = new CuotasDetalle();
		}
		return cuotasDetalle;
	}

	public List<CuotasDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c", CuotasDetalle.class);
		List<CuotasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasDetalle> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.estado = :pEstado", CuotasDetalle.class);
		locQuery.setParameter("pEstado", estado);
		List<CuotasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasDetalle> getLista(Cuota cuota) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.cuota = :pCuota", CuotasDetalle.class);
		locQuery.setParameter("pCuota", cuota);
		List<CuotasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasDetalle> getLista(boolean estado, Cuota cuota) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.estado = :pEstado AND c.cuota = :pCuota", CuotasDetalle.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCuota", cuota);
		return locQuery.getResultList();
	}
	
	public List<CuotasDetalle> getListaPorVencer(Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.fechaVencimiento <= :pFecha "
				+ "AND c.pago = :pPago AND c.estado = :pEstado", CuotasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		List<CuotasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasDetalle> getListaPorVencer(Cuota cuota, Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.fechaVencimiento <= :pFecha "
				+ "AND c.cuota = :pCuota AND c.pago = :pPago AND c.estado = :pEstado", CuotasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pCuota", cuota);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		List<CuotasDetalle> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<CuotasDetalle> getListaPorVencer(Consignacion consignacion, Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasDetalle c WHERE c.fechaVencimiento < :pFecha "
				+ "AND c.cuota.consignacion = :pConsignacion AND c.pago = :pPago AND c.estado = :pEstado", CuotasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		return locQuery.getResultList();
	}

}
