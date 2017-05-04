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
import model.entity.Producto;
import dao.interfaces.DAOConsignacion;

public class DAOConsignacionImpl implements Serializable, DAOConsignacion {

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

	public int insertar(Consignacion consignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(consignacion);
			tx.commit();
			return consignacion.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Consignacion consignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Consignacion c SET c.cliente = :pCliente, c.estado = :pEstado, "
					+ "c.fecha = :pFecha, c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.fechaMod = :pFechaMod, "
					+ "c.monto = :pMonto, c.tipoVenta = :pTipoVenta, c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja, "
					+ "c.usuario3 = :pUsuarioMod, c.vendido = :pVendido "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCliente", consignacion.getCliente());
			locQuery.setParameter("pEstado", consignacion.getEstado());
			locQuery.setParameter("pFecha", consignacion.getFecha());
			locQuery.setParameter("pFechaAlta", consignacion.getFechaAlta());
			locQuery.setParameter("pFechaBaja", consignacion.getFechaBaja());
			locQuery.setParameter("pFechaMod", consignacion.getFechaMod());
			locQuery.setParameter("pMonto", consignacion.getMonto());
			locQuery.setParameter("pTipoVenta", consignacion.getTipoVenta());
			locQuery.setParameter("pUsuarioAlta", consignacion.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", consignacion.getUsuario2());
			locQuery.setParameter("pUsuarioMod", consignacion.getUsuario3());
			locQuery.setParameter("pVendido", consignacion.getVendido());
			locQuery.setParameter("pId", consignacion.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return consignacion.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Consignacion get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.id = :pId", Consignacion.class);
		locQuery.setParameter("pId", id);
		Consignacion consignacion = new Consignacion();
		try{
			consignacion = (Consignacion) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			consignacion = new Consignacion();
		}
		return consignacion;
	}
	
	public Consignacion get(Cliente cliente, boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.estado = :pEstado "
				+ "AND c.cliente = :pCliente", Consignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		Consignacion consignacion = new Consignacion();
		try{
			consignacion = (Consignacion) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			consignacion = new Consignacion();
		}
		return consignacion;
	}

	public List<Consignacion> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c ORDER BY c.fecha DESC", Consignacion.class);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.estado = :pEstado ORDER BY c.cliente.nombreNegocio", Consignacion.class);
		locQuery.setParameter("pEstado", estado);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.cliente = :pCliente ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pCliente", cliente);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(boolean estado, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.estado = :pEstado AND c.cliente = :pCliente ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(boolean estado, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.estado = :pEstado AND c.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(Cliente cliente, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.cliente = :pCliente AND c.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(boolean estado, Cliente cliente,
			Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Consignacion c WHERE c.cliente = :pCliente AND c.estado = :pEstado AND c.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY c.fecha DESC", Consignacion.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<Consignacion> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT c.consignacionsDetalle.consignacion "
				+ "FROM ConsignacionsDetalleUnidad c "
				+ "WHERE c.consignacionsDetalle.producto = :pProducto AND c.vendido = :pVendido AND c.eliminado = :pEliminado "
				+ "AND c.consignacionsDetalle.eliminado = :pEliminado AND c.consignacionsDetalle.vendido = :pVendido "
				+ "AND c.consignacionsDetalle.consignacion.estado = :pEstado", Consignacion.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pVendido", false);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Consignacion> getListaProductoDisponible(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT c.consignacionsDetalle.consignacion "
				+ "FROM ConsignacionsDetalleUnidad c "
				+ "WHERE c.producto = :pProducto AND c.vendido = :pVendido AND c.eliminado = :pEliminado AND c.enabled = :pEstado "
				+ "AND c.consignacionsDetalle.eliminado = :pEliminado AND c.consignacionsDetalle.vendido = :pVendido "
				+ "AND c.consignacionsDetalle.consignacion.estado = :pEstado", Consignacion.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pVendido", false);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<Consignacion> lista = locQuery.getResultList();
		return lista;
	}

}
