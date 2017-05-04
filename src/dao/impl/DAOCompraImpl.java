package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.Producto;
import model.entity.Proveedore;
import dao.interfaces.DAOCompra;

public class DAOCompraImpl implements Serializable, DAOCompra {

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

	public int insertar(Compra compra) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(compra);
			tx.commit();
			return compra.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Compra compra) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Compra c SET c.estado = :pEstado, c.fecha = :pFecha, c.tipo = :pTipo, "
					+ "c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.fechaMod = :pFechaMod, c.monto = :pMonto, "
					+ "c.proveedore = :pProveedore, c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja, c.usuario3 = :pUsuarioMod "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pEstado", compra.getEstado());
			locQuery.setParameter("pFecha", compra.getFecha());
			locQuery.setParameter("pTipo", compra.getTipo());
			locQuery.setParameter("pFechaAlta", compra.getFechaAlta());
			locQuery.setParameter("pFechaBaja", compra.getFechaBaja());
			locQuery.setParameter("pFechaMod", compra.getFechaMod());
			locQuery.setParameter("pMonto", compra.getMonto());
			locQuery.setParameter("pProveedore", compra.getProveedore());
			locQuery.setParameter("pUsuarioAlta", compra.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", compra.getUsuario2());
			locQuery.setParameter("pUsuarioMod", compra.getUsuario3());
			locQuery.setParameter("pId", compra.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return compra.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Compra get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.id = :pId", Compra.class);
		locQuery.setParameter("pId", id);
		Compra compra = new Compra();
		try{
			compra = (Compra) locQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			compra = new Compra();
		}
		return compra;
	}
	
	public List<Compra> getListaSinOrden() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c", Compra.class);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c ORDER BY c.fecha DESC", Compra.class);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(Proveedore proveedor) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.proveedore = :pProveedor ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pProveedor", proveedor);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(boolean estado, Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.proveedore = :pProveedor ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProveedor", proveedore);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(Proveedore proveedore, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.proveedore = :pProveedor "
				+ "AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

	public List<Compra> getLista(boolean estado, Proveedore proveedore,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado AND c.proveedore = :pProveedor "
				+ "AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderFecha(boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderMonto(boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.monto DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderFecha(boolean estado, List<Proveedore> proveedores, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.proveedore IN :pProveedores AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProveedores", proveedores);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderMonto(boolean estado, List<Proveedore> proveedores, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Compra c WHERE c.estado = :pEstado "
				+ "AND c.proveedore IN :pProveedores AND c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.monto DESC", Compra.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProveedores", proveedores);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderFecha(Producto producto, boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c.compra FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado AND c.compra.estado = :pEstado "
				+ "AND c.compra.fecha BETWEEN :pInicio AND :pFin ORDER BY c.compra.fecha DESC", ComprasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderMonto(Producto producto, boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c.compra FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado AND c.compra.estado = :pEstado "
				+ "AND c.compra.fecha BETWEEN :pInicio AND :pFin ORDER BY c.compra.monto DESC", ComprasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}	
	
	public List<Compra> getListaOrderFecha(Producto producto, List<Proveedore> proveedores, boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c.compra FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado AND c.compra.estado = :pEstado AND c.compra.proveedore IN :pProveedores "
				+ "AND c.compra.fecha BETWEEN :pInicio AND :pFin ORDER BY c.compra.fecha DESC", ComprasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pProveedores", proveedores);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Compra> getListaOrderMonto(Producto producto, List<Proveedore> proveedores, boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c.compra FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado AND c.compra.estado = :pEstado AND c.compra.proveedore IN :pProveedores "
				+ "AND c.compra.fecha BETWEEN :pInicio AND :pFin ORDER BY c.compra.monto DESC", ComprasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pProveedores", proveedores);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Compra> lista = locQuery.getResultList();
		return lista;
	}

}
