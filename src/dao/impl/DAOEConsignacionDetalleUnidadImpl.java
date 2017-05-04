package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.ConsignacionsDetalleUnidad;
import model.entity.EConsignacion;
import model.entity.EConsignacionsDetalle;
import model.entity.EConsignacionsDetalleUnidad;
import model.entity.Producto;
import dao.interfaces.DAOEConsignacionDetalleUnidad;

public class DAOEConsignacionDetalleUnidadImpl implements Serializable,
		DAOEConsignacionDetalleUnidad {

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

	public int insertar(EConsignacionsDetalleUnidad eConsignacionsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(eConsignacionsDetalleUnidad);
			tx.commit();
			return eConsignacionsDetalleUnidad.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(EConsignacionsDetalleUnidad eConsignacionsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE EConsignacionsDetalleUnidad e SET e.EConsignacionsDetalle = :pEConsignacionDetalle, "
					+ "e.listaPrecio = :pListaPrecio, e.nroImei = :pNroImei, e.precioVenta = :pPrecioVenta, e.producto = :pProducto "
					+ "WHERE e.id = :pId");
			locQuery.setParameter("pEConsignacionDetalle", eConsignacionsDetalleUnidad.getEConsignacionsDetalle());
			locQuery.setParameter("pListaPrecio", eConsignacionsDetalleUnidad.getListaPrecio());
			locQuery.setParameter("pNroImei", eConsignacionsDetalleUnidad.getNroImei());
			locQuery.setParameter("pPrecioVenta", eConsignacionsDetalleUnidad.getPrecioVenta());
			locQuery.setParameter("pProducto", eConsignacionsDetalleUnidad.getProducto());
			locQuery.setParameter("pId", eConsignacionsDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return eConsignacionsDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public EConsignacionsDetalleUnidad get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalleUnidad e WHERE e.id = :pId", EConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pId", id);
		EConsignacionsDetalleUnidad eConsignacionsDetalleUnidad = new EConsignacionsDetalleUnidad();
		try{
			eConsignacionsDetalleUnidad = (EConsignacionsDetalleUnidad) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			eConsignacionsDetalleUnidad = new EConsignacionsDetalleUnidad();
		}
		return eConsignacionsDetalleUnidad;
	}

	public List<EConsignacionsDetalleUnidad> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalleUnidad e", EConsignacionsDetalleUnidad.class);
		List<EConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacionsDetalleUnidad> getLista(
			EConsignacionsDetalle eConsignacionsDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalleUnidad e WHERE e.EConsignacionsDetalle = :pEConsignacionDetalle", EConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pEConsignacionDetalle", eConsignacionsDetalle);
		List<EConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacionsDetalleUnidad> getLista(
			EConsignacion eConsignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalleUnidad e WHERE e.EConsignacionsDetalle.EConsignacion = :pEConsignacion", EConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pEConsignacion", eConsignacion);
		List<EConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<EConsignacionsDetalleUnidad> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EConsignacionsDetalleUnidad e WHERE e.producto = :pProducto", EConsignacionsDetalleUnidad.class);
		locQuery.setParameter("pProducto", producto);
		List<EConsignacionsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

}
