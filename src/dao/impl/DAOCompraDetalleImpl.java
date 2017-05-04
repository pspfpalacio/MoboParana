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
import dao.interfaces.DAOCompraDetalle;

public class DAOCompraDetalleImpl implements Serializable, DAOCompraDetalle {

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

	public int insertar(ComprasDetalle comprasDetalle) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(comprasDetalle);
			tx.commit();
			return comprasDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(ComprasDetalle comprasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalle c SET c.cantidad = :pCantidad, c.compra = :pCompra, "
					+ "c.precioCompra = :pPrecioCompra, c.producto = :pProducto, c.subtotal = :pSubtotal, c.accesorio = :pAccesorio "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCantidad", comprasDetalle.getCantidad());
			locQuery.setParameter("pCompra", comprasDetalle.getCompra());
			locQuery.setParameter("pPrecioCompra", comprasDetalle.getPrecioCompra());
			locQuery.setParameter("pProducto", comprasDetalle.getProducto());
			locQuery.setParameter("pSubtotal", comprasDetalle.getSubtotal());
			locQuery.setParameter("pAccesorio", comprasDetalle.getAccesorio());
			locQuery.setParameter("pId", comprasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return comprasDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ComprasDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalle c WHERE c.id = :pId "
				+ "AND c.eliminado = :pEliminado", ComprasDetalle.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		ComprasDetalle comprasDetalle = new ComprasDetalle();
		try{
			comprasDetalle = (ComprasDetalle) locQuery.getSingleResult();
		} catch (Exception e){
			System.out.println(e.getMessage());
			comprasDetalle = new ComprasDetalle();
		}
		return comprasDetalle;
	}

	public List<ComprasDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalle c WHERE c.eliminado = :pEliminado", ComprasDetalle.class);
		locQuery.setParameter("pEliminado", false);
		List<ComprasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<ComprasDetalle> getLista(Compra compra) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalle c WHERE c.compra = :pCompra "
				+ "AND c.eliminado = :pEliminado", ComprasDetalle.class);
		locQuery.setParameter("pCompra", compra);
		locQuery.setParameter("pEliminado", false);
		List<ComprasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<ComprasDetalle> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado", ComprasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<ComprasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public ComprasDetalle get(Compra compra, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalle c WHERE c.compra = :pCompra AND c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado", ComprasDetalle.class);
		locQuery.setParameter("pCompra", compra);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		ComprasDetalle comprasDetalle = new ComprasDetalle();
		try{
			comprasDetalle = (ComprasDetalle) locQuery.getSingleResult();
		} catch(Exception e){
			System.out.println(e.getMessage());
			comprasDetalle = new ComprasDetalle();
		}
		return comprasDetalle;
	}

	public int deleteDetallePorCompra(Compra compra) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalle c SET c.eliminado = :pEliminado "
					+ "WHERE c.compra = :pCompra");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pCompra", compra);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteDetalleCompra(ComprasDetalle comprasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalle c SET c.eliminado = :pEliminado "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", comprasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public List<Float> getListaPorRango(Producto producto, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT c.precioCompra FROM ComprasDetalle c WHERE c.producto = :pProducto "
				+ "AND c.compra.fecha BETWEEN :pFechaInicio AND :pFechaFin AND c.compra.estado = :pEstado "
				+ "AND c.eliminado = :pEliminado AND c.precioCompra <> 0", Float.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		locQuery.setParameter("pFechaFin", fechaFin);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pEliminado", false);
		List<Float> lista = locQuery.getResultList();
		return lista;
	}

}
