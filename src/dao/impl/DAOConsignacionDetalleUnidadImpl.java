package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.Producto;
import dao.interfaces.DAOConsignacionDetalleUnidad;

public class DAOConsignacionDetalleUnidadImpl implements Serializable,
		DAOConsignacionDetalleUnidad {

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

	public int insertar(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(consignacionsDetalleUnidad);
			tx.commit();
			return consignacionsDetalleUnidad.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalleUnidad c SET c.consignacionsDetalle = :pConsignacionDetalle, c.nroImei = :pNroImei, "
					+ "c.precioCompra = :pPrecioCompra, c.precioLista = :pPrecioLista, c.tipoVenta = :pTipoVenta, c.unidadMovil = :pUnidadMovil, c.vendido = :pVendido, "
					+ "c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.fechaVenta = :pFechaVenta, c.listaPrecio = :pListaPrecio, c.enabled = :pEnabled, "
					+ "c.producto = :pProducto "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pConsignacionDetalle", consignacionsDetalleUnidad.getConsignacionsDetalle());
			locQuery.setParameter("pNroImei", consignacionsDetalleUnidad.getNroImei());
			locQuery.setParameter("pPrecioCompra", consignacionsDetalleUnidad.getPrecioCompra());
			locQuery.setParameter("pPrecioLista", consignacionsDetalleUnidad.getPrecioLista());
			locQuery.setParameter("pTipoVenta", consignacionsDetalleUnidad.getTipoVenta());
			locQuery.setParameter("pUnidadMovil", consignacionsDetalleUnidad.getUnidadMovil());
			locQuery.setParameter("pVendido", consignacionsDetalleUnidad.getVendido());
			locQuery.setParameter("pFechaAlta", consignacionsDetalleUnidad.getFechaAlta());
			locQuery.setParameter("pFechaBaja", consignacionsDetalleUnidad.getFechaBaja());
			locQuery.setParameter("pFechaVenta", consignacionsDetalleUnidad.getFechaVenta());
			locQuery.setParameter("pListaPrecio", consignacionsDetalleUnidad.getListaPrecio());			
			locQuery.setParameter("pEnabled", consignacionsDetalleUnidad.getEnabled());
			locQuery.setParameter("pProducto", consignacionsDetalleUnidad.getProducto());
			locQuery.setParameter("pId", consignacionsDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return consignacionsDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ConsignacionsDetalleUnidad get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.id = :pId "
				+ "AND c.eliminado = :pEliminado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		ConsignacionsDetalleUnidad consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		try{
			consignacionsDetalleUnidad = (ConsignacionsDetalleUnidad) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		}
		return consignacionsDetalleUnidad;
	}

	public ConsignacionsDetalleUnidad get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.nroImei = :pNroImei "
				+ "AND c.eliminado = :pEliminado AND c.enabled = :pEstado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		ConsignacionsDetalleUnidad consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		try{
			consignacionsDetalleUnidad = (ConsignacionsDetalleUnidad) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		}
		return consignacionsDetalleUnidad;
	}
	
	public ConsignacionsDetalleUnidad getAll(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.nroImei = :pNroImei ", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pNroImei", imei);
		ConsignacionsDetalleUnidad consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		try{
			consignacionsDetalleUnidad = (ConsignacionsDetalleUnidad) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			consignacionsDetalleUnidad = new ConsignacionsDetalleUnidad();
		}
		return consignacionsDetalleUnidad;
	}

	public int deleteUnidad(
			ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalleUnidad c SET c.eliminado = :pEliminado, c.enabled = :pEstado "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pEstado", false);			
			locQuery.setParameter("pId", consignacionsDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return consignacionsDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int deletePorDetalle(ConsignacionsDetalle consignacionsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalleUnidad c SET c.eliminado = :pEliminado, c.enabled = :pEstado "
					+ "WHERE c.consignacionsDetalle = :pConsignacionDetalle");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pEstado", false);
			locQuery.setParameter("pConsignacionDetalle", consignacionsDetalle);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<ConsignacionsDetalleUnidad> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.eliminado = :pEliminado "
				+ "AND c.enabled = :pEstado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalleUnidad> getLista(
			ConsignacionsDetalle consignacionsDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle = :pConsignacionsDetalle "
				+ "AND c.eliminado = :pEliminado AND c.enabled = :pEstado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pConsignacionsDetalle", consignacionsDetalle);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalleUnidad> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle.consignacion = :pConsignacion "
				+ "AND c.eliminado = :pEliminado AND c.enabled = :pEstado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalleUnidad> getLista(Consignacion consignacion, boolean vendido) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle.consignacion = :pConsignacion "
				+ "AND c.vendido = :pVendido AND c.eliminado = :pEliminado AND c.enabled = :pEstado AND c.consignacionsDetalle.eliminado = :pEliminado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pVendido", vendido);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<ConsignacionsDetalle> getListaDetalleVendido(Consignacion consignacion, boolean vendido) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT c.consignacionsDetalle FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle.consignacion = :pConsignacion "
				+ "AND c.vendido = :pVendido AND c.eliminado = :pEliminado AND c.enabled = :pEstado", ConsignacionsDetalle.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pVendido", vendido);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<ConsignacionsDetalleUnidad> getLista(ConsignacionsDetalle consignacionsDetalle, boolean vendido) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle = :pConsignacionDetalle "
				+ "AND c.vendido = :pVendido AND c.eliminado = :pEliminado AND c.enabled = :pEstado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pConsignacionDetalle", consignacionsDetalle);
		locQuery.setParameter("pVendido", vendido);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalleUnidad> getLista(Producto producto,
			boolean vendido) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE "
				+ "c.consignacionsDetalle.producto = :pProducto AND c.enabled = :pEstado "
				+ "AND c.vendido = :pVendido ORDER BY c.consignacionsDetalle.consignacion AND c.eliminado = :pEliminado", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pVendido", vendido);
		locQuery.setParameter("pEliminado", false);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<ConsignacionsDetalleUnidad> getListaOrderFechaVenta(Consignacion consignacion, boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.consignacionsDetalle.consignacion = :pConsignacion "
				+ "AND c.consignacionsDetalle.consignacion.estado = :pEstado AND c.enabled = :pEnabled AND c.fechaVenta is not NULL "
				+ "ORDER BY c.fechaVenta", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnabled", estado);
		List<ConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}
	
	public ConsignacionsDetalleUnidad getUnidad(String nroImei, Consignacion consignacion, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalleUnidad c WHERE c.nroImei = :pNroImei "
				+ "AND c.eliminado = :pEliminado AND c.enabled = :pEstado AND c.vendido = :pVendido "
				+ "AND c.consignacionsDetalle.consignacion.estado = :pEstado AND c.consignacionsDetalle.eliminado = :pEliminado "
				+ "AND c.consignacionsDetalle.consignacion = :pConsignacion "				
				+ "AND c.consignacionsDetalle.consignacion.cliente = :pCliente", ConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pVendido", false);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pCliente", cliente);
		ConsignacionsDetalleUnidad unidad = new ConsignacionsDetalleUnidad();
		try {
			unidad = (ConsignacionsDetalleUnidad) locQuery.getSingleResult();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			unidad = new ConsignacionsDetalleUnidad();
		}
		return unidad;
	}

}
