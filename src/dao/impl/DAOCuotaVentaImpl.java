package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.CuotasVenta;
import model.entity.Venta;
import dao.interfaces.DAOCuotaVenta;

public class DAOCuotaVentaImpl implements DAOCuotaVenta, Serializable {

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

	public int insertar(CuotasVenta cuotasVenta) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuotasVenta);
			tx.commit();
			return cuotasVenta.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(CuotasVenta cuotasVenta) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE CuotasVenta c SET c.cantCuotas = :pCantCuotas, c.venta = :pVenta, "
					+ "c.equipo = :pEquipo, c.estado = :pEstado, c.fechaAlta = :pFechaAlta, c.interes = :pInteres, "
					+ "c.montoTotal = :pMontoTotal, c.nroImei = :pNroImei, c.fechaBaja = :pFechaBaja, "
					+ "c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCantCuotas", cuotasVenta.getCantCuotas());
			locQuery.setParameter("pVenta", cuotasVenta.getVenta());
			locQuery.setParameter("pEquipo", cuotasVenta.getEquipo());
			locQuery.setParameter("pEstado", cuotasVenta.getEstado());
			locQuery.setParameter("pFechaAlta", cuotasVenta.getFechaAlta());
			locQuery.setParameter("pInteres", cuotasVenta.getInteres());
			locQuery.setParameter("pMontoTotal", cuotasVenta.getMontoTotal());
			locQuery.setParameter("pNroImei", cuotasVenta.getNroImei());
			locQuery.setParameter("pFechaBaja", cuotasVenta.getFechaBaja());
			locQuery.setParameter("pUsuarioAlta", cuotasVenta.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", cuotasVenta.getUsuario2());
			locQuery.setParameter("pId", cuotasVenta.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuotasVenta.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public CuotasVenta get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c WHERE c.id = :pId", CuotasVenta.class);
		locQuery.setParameter("pId", id);
		CuotasVenta cuotasVenta = new CuotasVenta();
		try {
			cuotasVenta = (CuotasVenta) locQuery.getSingleResult();
		} catch(Exception e) {
			cuotasVenta = new CuotasVenta();
		}
		return cuotasVenta;
	}

	public CuotasVenta get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c WHERE c.nroImei = :pNroImei "
				+ "AND c.estado = :pEstado", CuotasVenta.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEstado", true);
		CuotasVenta cuotasVenta = new CuotasVenta();
		try {
			cuotasVenta = (CuotasVenta) locQuery.getSingleResult();
		} catch(Exception e) {
			cuotasVenta = new CuotasVenta();
		}
		return cuotasVenta;
	}

	public List<CuotasVenta> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c", CuotasVenta.class);
		List<CuotasVenta> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVenta> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c WHERE c.estado = :pEstado", CuotasVenta.class);
		locQuery.setParameter("pEstado", estado);
		List<CuotasVenta> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVenta> getLista(Venta venta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c WHERE c.venta = :pVenta", CuotasVenta.class);
		locQuery.setParameter("pVenta", venta);
		List<CuotasVenta> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotasVenta> getLista(boolean estado, Venta venta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotasVenta c WHERE c.estado = :pEstado AND c.venta = :pVenta", CuotasVenta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pVenta", venta);
		List<CuotasVenta> lista = locQuery.getResultList();
		return lista;
	}

}
