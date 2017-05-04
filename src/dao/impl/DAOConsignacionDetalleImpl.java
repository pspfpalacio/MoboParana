package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.Producto;
import dao.interfaces.DAOConsignacionDetalle;

public class DAOConsignacionDetalleImpl implements Serializable,
		DAOConsignacionDetalle {

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

	public int insertar(ConsignacionsDetalle consignacionsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(consignacionsDetalle);
			tx.commit();
			return consignacionsDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(ConsignacionsDetalle consignacionsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalle c SET c.cantidad = :pCantidad, c.consignacion = :pConsignacion, c.eliminado =:pEliminado, "
					+ "c.precioVenta = :pPrecioVenta, c.producto = :pProducto, c.subtotal = :pSubtotal, c.tipoVenta = :pTipoVenta, c.vendido = :pVendido "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCantidad", consignacionsDetalle.getCantidad());
			locQuery.setParameter("pConsignacion", consignacionsDetalle.getConsignacion());
			locQuery.setParameter("pEliminado", consignacionsDetalle.getEliminado());
			locQuery.setParameter("pPrecioVenta", consignacionsDetalle.getPrecioVenta());
			locQuery.setParameter("pProducto", consignacionsDetalle.getProducto());
			locQuery.setParameter("pSubtotal", consignacionsDetalle.getSubtotal());
			locQuery.setParameter("pTipoVenta", consignacionsDetalle.getTipoVenta());
			locQuery.setParameter("pVendido", consignacionsDetalle.getVendido());
			locQuery.setParameter("pId", consignacionsDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return consignacionsDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ConsignacionsDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalle c WHERE c.id = :pId "
				+ "AND c.eliminado = :pEliminado", ConsignacionsDetalle.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		ConsignacionsDetalle consignacionsDetalle = new ConsignacionsDetalle();
		try{
			consignacionsDetalle = (ConsignacionsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			consignacionsDetalle = new ConsignacionsDetalle();
		}
		return consignacionsDetalle;
	}

	public ConsignacionsDetalle get(Consignacion consignacion, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalle c WHERE c.consignacion = :pConsignacion AND c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado", ConsignacionsDetalle.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pProducto", producto);		
		locQuery.setParameter("pEliminado", false);
		ConsignacionsDetalle consignacionsDetalle = new ConsignacionsDetalle();
		try{
			consignacionsDetalle = (ConsignacionsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			consignacionsDetalle = new ConsignacionsDetalle();
		}
		return consignacionsDetalle;
	}

	public int delete(Consignacion consignacion) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalle c SET c.eliminado = :pEliminado "
					+ "WHERE c.consignacion = :pConsignacion");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pConsignacion", consignacion);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int delete(ConsignacionsDetalle consignacionDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ConsignacionsDetalle c SET c.eliminado = :pEliminado "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", consignacionDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<ConsignacionsDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalle c WHERE c.eliminado = :pEliminado", ConsignacionsDetalle.class);
		locQuery.setParameter("pEliminado", false);
		List<ConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalle> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalle c WHERE c.consignacion = :pConsignacion "
				+ "AND c.eliminado = :pEliminado", ConsignacionsDetalle.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pEliminado", false);
		List<ConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<ConsignacionsDetalle> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ConsignacionsDetalle c WHERE c.producto = :pProducto "
				+ "AND c.eliminado = :pEliminado", ConsignacionsDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<ConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

}
