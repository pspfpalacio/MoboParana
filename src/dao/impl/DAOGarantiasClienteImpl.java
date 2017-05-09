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
import model.entity.GarantiasCliente;
import model.entity.Producto;
import dao.interfaces.DAOGarantiasCliente;

public class DAOGarantiasClienteImpl implements Serializable, DAOGarantiasCliente {

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

	public int insertar(GarantiasCliente garantiasCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(garantiasCliente);
			tx.commit();
			return garantiasCliente.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(GarantiasCliente garantiasCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE GarantiasCliente g SET g.accionRealizar = :pAccionRealizar, g.cliente = :pCliente, g.estado = :pEstado, "
					+ "g.falla = :pFalla, g.fallaDefinitiva = :pFallaDefinitiva, g.fechaAlta = :pFechaAlta, g.fechaAltaConsignacion = :pFechaAltaConsignacion, "
					+ "g.fechaBaja = :pFechaBaja, g.fechaIngreso = :pFechaIngreso, g.fechaMod = :pFechaMod, g.fechaVentaConsignacion = :pFechaVentaConsignacion, "
					+ "g.finalizado = :pFinalizado, g.idConVenta = :pIdConVenta, g.idMovimiento = :pIdMovimiento, g.imeiFalla = :pImeiFalla, g.imeiReintegro = :pImeiReintegro, "
					+ "g.nombreMovimiento = :pNombreMovimiento, g.observaciones = :pObservaciones, g.precioUnidad = :pPrecioUnidad, g.producto1 = :pProductoFalla, "
					+ "g.producto2 = :pProductoReintegro, g.resolucion = :pResolucion, g.tecnico = :pTecnico, g.telefonoFalla = :pTelefonoFalla, "
					+ "g.telefonoReintegro = :pTelefonoReintegro, g.usuario1 = :pUsuarioAlta, g.usuario2 = :pUsuarioBaja, g.usuario3 = :pUsuarioMod, g.vendido = :pVendido "
					+ "WHERE g.id = :pId");
			locQuery.setParameter("pAccionRealizar", garantiasCliente.getAccionRealizar());
			locQuery.setParameter("pCliente", garantiasCliente.getCliente());
			locQuery.setParameter("pEstado", garantiasCliente.getEstado());
			locQuery.setParameter("pFalla", garantiasCliente.getFalla());
			locQuery.setParameter("pFallaDefinitiva", garantiasCliente.getFallaDefinitiva());
			locQuery.setParameter("pFechaAlta", garantiasCliente.getFechaAlta());
			locQuery.setParameter("pFechaAltaConsignacion", garantiasCliente.getFechaAltaConsignacion());
			locQuery.setParameter("pFechaBaja", garantiasCliente.getFechaBaja());
			locQuery.setParameter("pFechaIngreso", garantiasCliente.getFechaIngreso());
			locQuery.setParameter("pFechaMod", garantiasCliente.getFechaMod());
			locQuery.setParameter("pFechaVentaConsignacion", garantiasCliente.getFechaVentaConsignacion());
			locQuery.setParameter("pFinalizado", garantiasCliente.getFinalizado());
			locQuery.setParameter("pIdConVenta", garantiasCliente.getIdConVenta());
			locQuery.setParameter("pIdMovimiento", garantiasCliente.getIdMovimiento());
			locQuery.setParameter("pImeiFalla", garantiasCliente.getImeiFalla());
			locQuery.setParameter("pImeiReintegro", garantiasCliente.getImeiReintegro());
			locQuery.setParameter("pNombreMovimiento", garantiasCliente.getNombreMovimiento());
			locQuery.setParameter("pObservaciones", garantiasCliente.getObservaciones());
			locQuery.setParameter("pPrecioUnidad", garantiasCliente.getPrecioUnidad());
			locQuery.setParameter("pProductoFalla", garantiasCliente.getProducto1());
			locQuery.setParameter("pProductoReintegro", garantiasCliente.getProducto2());
			locQuery.setParameter("pResolucion", garantiasCliente.getResolucion());
			locQuery.setParameter("pTecnico", garantiasCliente.getTecnico());
			locQuery.setParameter("pTelefonoFalla", garantiasCliente.getTelefonoFalla());
			locQuery.setParameter("pTelefonoReintegro", garantiasCliente.getTelefonoReintegro());
			locQuery.setParameter("pUsuarioAlta", garantiasCliente.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", garantiasCliente.getUsuario2());
			locQuery.setParameter("pUsuarioMod", garantiasCliente.getUsuario3());
			locQuery.setParameter("pVendido", garantiasCliente.getVendido());
			locQuery.setParameter("pId", garantiasCliente.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return garantiasCliente.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public GarantiasCliente get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.id = :pId", GarantiasCliente.class);
		locQuery.setParameter("pId", id);
		GarantiasCliente garantia = new GarantiasCliente();
		try{
			garantia = (GarantiasCliente) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasCliente();
		}
		return garantia;
	}
	
	public GarantiasCliente get(String imeiFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.imeiFalla = :pImeiFalla AND g.estado = :pEstado", GarantiasCliente.class);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		locQuery.setParameter("pEstado", true);
		GarantiasCliente garantia = new GarantiasCliente();
		try{
			garantia = (GarantiasCliente) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasCliente();
		}
		return garantia;
	}

	public GarantiasCliente get(String imeiFalla, String imeiReintegro) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.imeiFalla = :pImeiFalla AND "
				+ "g.imeiReintegro = :pImeiReintegro", GarantiasCliente.class);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		locQuery.setParameter("pImeiReintegro", imeiReintegro);
		GarantiasCliente garantia = new GarantiasCliente();
		try{
			garantia = (GarantiasCliente) locQuery.getSingleResult();
		}catch (Exception e){
			garantia = new GarantiasCliente();
		}
		return garantia;
	}

	public List<GarantiasCliente> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado ORDER BY g.fechaIngreso DESC", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(String imeiFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.imeiFalla = :pImeiFalla ORDER BY g.fechaIngreso", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pImeiFalla", imeiFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.cliente = :pCliente", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pCliente", cliente);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.producto1 = :pProductoFalla", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(Cliente cliente, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.cliente = :pCliente AND g.producto1 = :pProductoFalla", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.finalizado = :pFinalizado", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.cliente = :pCliente", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pCliente", cliente);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado "
				+ "AND g.producto1 = :pProductoFalla AND g.finalizado = :pFinalizado", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio,
			Date fechaFin, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.cliente = :pCliente AND g.finalizado = :pFinalizado AND "
				+ "g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pCliente", cliente);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio,
			Date fechaFin, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.producto1 = :pProductoFalla AND "
				+ "g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Cliente cliente, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.cliente = :pCliente AND g.finalizado = :pFinalizado AND "
				+ "g.producto1 = :pProductoFalla", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<GarantiasCliente> getLista(boolean finalizado, Date fechaInicio,
			Date fechaFin, Cliente cliente, Producto productoFalla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM GarantiasCliente g WHERE g.estado = :pEstado AND "
				+ "g.finalizado = :pFinalizado AND g.cliente = :pCliente AND "
				+ "g.producto1 = :pProductoFalla AND g.fechaIngreso BETWEEN :pInicio AND :pFin", GarantiasCliente.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pFinalizado", finalizado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProductoFalla", productoFalla);
		List<GarantiasCliente> lista = locQuery.getResultList();
		return lista;
	}

}
