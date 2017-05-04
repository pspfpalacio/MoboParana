package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.EConsignacion;
import model.entity.EConsignacionsDetalle;
import model.entity.Producto;
import dao.interfaces.DAOEConsignacionDetalle;

public class DAOEConsignacionDetalleImpl implements Serializable,
		DAOEConsignacionDetalle {

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

	public int insertar(EConsignacionsDetalle eConsignacionsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(eConsignacionsDetalle);
			tx.commit();
			return eConsignacionsDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(EConsignacionsDetalle eConsignacionsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE EConsignacionsDetalle e SET e.cantidad = :pCantidad, e.EConsignacion = :pEConsignacion, "
					+ "e.precioVenta = :pPrecioVenta, e.producto = :pProducto, e.subtotal = :pSubtotal "
					+ "WHERE e.id = :pId");
			locQuery.setParameter("pCantidad", eConsignacionsDetalle.getCantidad());
			locQuery.setParameter("pEConsignacion", eConsignacionsDetalle.getEConsignacion());
			locQuery.setParameter("pPrecioVenta", eConsignacionsDetalle.getPrecioVenta());
			locQuery.setParameter("pProducto", eConsignacionsDetalle.getProducto());
			locQuery.setParameter("pSubtotal", eConsignacionsDetalle.getSubtotal());
			locQuery.setParameter("pId", eConsignacionsDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return eConsignacionsDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public EConsignacionsDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalle e WHERE e.id = :pId", EConsignacionsDetalle.class);
		locQuery.setParameter("pId", id);
		EConsignacionsDetalle eConsignacionsDetalle = new EConsignacionsDetalle();
		try{
			eConsignacionsDetalle = (EConsignacionsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			eConsignacionsDetalle = new EConsignacionsDetalle();
		}
		return eConsignacionsDetalle;
	}

	public EConsignacionsDetalle get(EConsignacion eConsignacion,
			Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalle e WHERE e.EConsignacion = :pEConsignacion AND e.producto = :pProducto ", EConsignacionsDetalle.class);
		locQuery.setParameter("pEConsignacion", eConsignacion);
		locQuery.setParameter("pProducto", producto);
		EConsignacionsDetalle eConsignacionsDetalle = new EConsignacionsDetalle();
		try{
			eConsignacionsDetalle = (EConsignacionsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			eConsignacionsDetalle = new EConsignacionsDetalle();
		}
		return eConsignacionsDetalle;
	}

	public List<EConsignacionsDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalle e", EConsignacionsDetalle.class);
		List<EConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacionsDetalle> getLista(EConsignacion eConsignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalle e WHERE e.EConsignacion = :pEConsignacion", EConsignacionsDetalle.class);
		locQuery.setParameter("pEConsignacion", eConsignacion);
		List<EConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacionsDetalle> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalle e WHERE e.producto = :pProducto", EConsignacionsDetalle.class);
		locQuery.setParameter("pProducto", producto);
		List<EConsignacionsDetalle> lista = locQuery.getResultList();
		return lista;
	}

}
