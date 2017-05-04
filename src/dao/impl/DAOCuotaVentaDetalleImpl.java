package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.CuotasVenta;
import model.entity.CuotasVentasDetalle;
import model.entity.Venta;
import dao.interfaces.DAOCuotaVentaDetalle;

public class DAOCuotaVentaDetalleImpl implements DAOCuotaVentaDetalle,
		Serializable {

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

	public int insertar(CuotasVentasDetalle cuotasVentasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuotasVentasDetalle);
			tx.commit();
			return cuotasVentasDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(CuotasVentasDetalle cuotasVentasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE CuotasVentasDetalle c SET c.cuotasVenta = :pCuotasVenta, c.descripcion = :pDescripcion, "
					+ "c.estado = :pEstado, c.fechaVencimiento = :pFechaVencimiento, c.monto = :pMonto, c.pago = :pPago, "
					+ "c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCuotasVenta", cuotasVentasDetalle.getCuotasVenta());
			locQuery.setParameter("pDescripcion", cuotasVentasDetalle.getDescripcion());
			locQuery.setParameter("pEstado", cuotasVentasDetalle.getEstado());
			locQuery.setParameter("pFechaVencimiento", cuotasVentasDetalle.getFechaVencimiento());
			locQuery.setParameter("pMonto", cuotasVentasDetalle.getMonto());
			locQuery.setParameter("pPago", cuotasVentasDetalle.getPago());
			locQuery.setParameter("pFechaAlta", cuotasVentasDetalle.getFechaAlta());
			locQuery.setParameter("pFechaBaja", cuotasVentasDetalle.getFechaBaja());
			locQuery.setParameter("pUsuarioAlta", cuotasVentasDetalle.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", cuotasVentasDetalle.getUsuario2());
			locQuery.setParameter("pId", cuotasVentasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuotasVentasDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public CuotasVentasDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.id = :pId", CuotasVentasDetalle.class);
		locQuery.setParameter("pId", id);
		CuotasVentasDetalle cuotasDetalle = new CuotasVentasDetalle();
		try {
			cuotasDetalle = (CuotasVentasDetalle) locQuery.getSingleResult();
		} catch(Exception e) {
			cuotasDetalle = new CuotasVentasDetalle();
		}
		return cuotasDetalle;
	}

	public List<CuotasVentasDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c", CuotasVentasDetalle.class);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.estado = :pEstado", CuotasVentasDetalle.class);
		locQuery.setParameter("pEstado", estado);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getLista(CuotasVenta cuotasVenta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.cuotasVenta = :pCuotasVenta", CuotasVentasDetalle.class);
		locQuery.setParameter("pCuotasVenta", cuotasVenta);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getLista(boolean estado,
			CuotasVenta cuotasVenta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.estado = :pEstado AND c.cuotasVenta = :pCuotasVenta", CuotasVentasDetalle.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCuotasVenta", cuotasVenta);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getListaPorVencer(Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.fechaVencimiento <= :pFecha "
				+ "AND c.pago = :pPago AND c.estado = :pEstado", CuotasVentasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getListaPorVencer(CuotasVenta cuotasVenta,
			Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.fechaVencimiento <= :pFecha "
				+ "AND c.cuotasVenta = :pCuotasVenta AND c.pago = :pPago AND c.estado = :pEstado", CuotasVentasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pCuotasVenta", cuotasVenta);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVentasDetalle> getListaPorVencer(Venta venta, Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVentasDetalle c WHERE c.fechaVencimiento < :pFecha "
				+ "AND c.cuotasVenta.venta = :pVenta AND c.pago = :pPago AND c.estado = :pEstado", CuotasVentasDetalle.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pVenta", venta);
		locQuery.setParameter("pPago", false);
		locQuery.setParameter("pEstado", true);
		List<CuotasVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

}
