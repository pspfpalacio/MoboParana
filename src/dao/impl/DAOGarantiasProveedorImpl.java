package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.GarantiasProveedore;
import model.entity.Producto;
import model.entity.Proveedore;
import dao.interfaces.DAOGarantiasProveedor;

public class DAOGarantiasProveedorImpl implements Serializable,
		DAOGarantiasProveedor {

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

	public int insertar(GarantiasProveedore garantiasProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(garantiasProveedore);
			tx.commit();
			return garantiasProveedore.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(GarantiasProveedore garantiasProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE GarantiasProveedore g SET g.accionRealizar = :pAccionRealizar, g.concepto = :pConcepto, g.costo = :pCosto, g.estado = :pEstado, "
					+ "g.falla = :pFalla, g.fallaDefinitiva = :pFallaDefinitiva, g.fechaAlta = :pFechaAlta, g.fechaBaja = :pFechaBaja, g.fechaFin = :pFechaFin, "
					+ "g.fechaIngreso = :pFechaIngreso, g.fechaMod = :pFechaMod, g.finalizado = :pFinalizado, g.idMovimiento = :pIdMovimiento, g.imeiFalla = :pImeiFalla, "
					+ "g.imeiReintegro = :pImeiReintegro, g.nombreMovimiento = :pNombreMovimiento, g.observaciones = :pObservaciones, g.precioUnidad = :pPrecioUnidad, g.producto1 = :pProductoFalla, "
					+ "g.producto2 = :pProductoReintegro, g.proveedore = :pProveedor, g.resolucion = :pResolucion, g.telefonoFalla = :pTelefonoFalla, g.telefonoReintegro = :pTelefonoReintegro, "
					+ "g.usuario1 = :pUsuarioAlta, g.usuario2 = :pUsuarioBaja, g.usuario3 = :pUsuarioMod "
					+ "WHERE g.id = :pId");
			locQuery.setParameter("pAccionRealizar", garantiasProveedore.getAccionRealizar());
			locQuery.setParameter("pConcepto", garantiasProveedore.getConcepto());
			locQuery.setParameter("pCosto", garantiasProveedore.getCosto());
			locQuery.setParameter("pEstado", garantiasProveedore.getEstado());
			locQuery.setParameter("pFalla", garantiasProveedore.getFalla());
			locQuery.setParameter("pFallaDefinitiva", garantiasProveedore.getFallaDefinitiva());
			locQuery.setParameter("pFechaAlta", garantiasProveedore.getFechaAlta());
			locQuery.setParameter("pFechaBaja", garantiasProveedore.getFechaBaja());
			locQuery.setParameter("pFechaFin", garantiasProveedore.getFechaFin());
			locQuery.setParameter("pFechaIngreso", garantiasProveedore.getFechaIngreso());
			locQuery.setParameter("pFechaMod", garantiasProveedore.getFechaMod());
			locQuery.setParameter("pFinalizado", garantiasProveedore.getFinalizado());		
			locQuery.setParameter("pIdMovimiento", garantiasProveedore.getIdMovimiento());
			locQuery.setParameter("pImeiFalla", garantiasProveedore.getImeiFalla());
			locQuery.setParameter("pImeiReintegro", garantiasProveedore.getImeiReintegro());
			locQuery.setParameter("pNombreMovimiento", garantiasProveedore.getNombreMovimiento());
			locQuery.setParameter("pObservaciones", garantiasProveedore.getObservaciones());
			locQuery.setParameter("pPrecioUnidad", garantiasProveedore.getPrecioUnidad());
			locQuery.setParameter("pProductoFalla", garantiasProveedore.getProducto1());
			locQuery.setParameter("pProductoReintegro", garantiasProveedore.getProducto2());
			locQuery.setParameter("pProveedor", garantiasProveedore.getProveedore());
			locQuery.setParameter("pResolucion", garantiasProveedore.getResolucion());
			locQuery.setParameter("pTelefonoFalla", garantiasProveedore.getTelefonoFalla());
			locQuery.setParameter("pTelefonoReintegro", garantiasProveedore.getTelefonoReintegro());
			locQuery.setParameter("pUsuarioAlta", garantiasProveedore.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", garantiasProveedore.getUsuario2());
			locQuery.setParameter("pUsuarioMod", garantiasProveedore.getUsuario3());
			locQuery.setParameter("pId", garantiasProveedore.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return garantiasProveedore.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public GarantiasProveedore get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.id = :pId", GarantiasProveedore.class);
		locQuery.setParameter("pId", id);
		GarantiasProveedore garantia = new GarantiasProveedore();
		try{
			garantia = (GarantiasProveedore) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasProveedore();
		}
		return garantia;
	}

	public GarantiasProveedore get(String imeiFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.imeiFalla = :pImeiFalla AND g.estado = :pEstado", GarantiasProveedore.class);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		locQuery.setParameter("pEstado", true);
		GarantiasProveedore garantia = new GarantiasProveedore();
		try{
			garantia = (GarantiasProveedore) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasProveedore();
		}
		return garantia;
	}

	public GarantiasProveedore get(String imeiFalla, String imeiReintegro) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.imeiFalla = :pImeiFalla AND "
				+ "g.imeiReintegro = :pImeiReintegro", GarantiasProveedore.class);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		locQuery.setParameter("pImeiReintegro", imeiReintegro);
		GarantiasProveedore garantia = new GarantiasProveedore();
		try{
			garantia = (GarantiasProveedore) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasProveedore();
		}
		return garantia;
	}

	public List<GarantiasProveedore> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado ORDER BY g.fechaIngreso DESC", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(String imeiFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.imeiFalla = :pImeiFalla ORDER BY g.fechaIngreso", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.proveedore = :pProveedor", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProveedor", proveedore);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.producto1 = :pProductoFalla", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(Proveedore proveedore,
			Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.proveedore = :pProveedor AND g.producto1 = :pProductoFalla", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.finalizado = :pFinalizado", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.proveedore = :pProveedor", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pProveedor", proveedore);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado "
				+ "AND g.producto1 = :pProductoFalla AND g.finalizado = :pFinalizado", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Date fechaInicio, Date fechaFin, Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.proveedore = :pProveedor AND g.finalizado = :pFinalizado AND "
				+ "g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pProveedor", proveedore);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Date fechaInicio, Date fechaFin, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.producto1 = :pProductoFalla AND "
				+ "g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Proveedore proveedore, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.proveedore = :pProveedor AND g.finalizado = :pFinalizado AND "
				+ "g.producto1 = :pProductoFalla", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<GarantiasProveedore> getLista(boolean finalizado,
			Date fechaInicio, Date fechaFin, Proveedore proveedore,
			Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasProveedore g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.proveedore = :pProveedor AND "
				+ "g.producto1 = :pProductoFalla AND g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasProveedore.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasProveedore> lista = locQuery.getResultList();
		return lista;
	}

}
