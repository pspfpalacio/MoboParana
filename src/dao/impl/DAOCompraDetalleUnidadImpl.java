package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.ComprasDetalle;
import model.entity.ComprasDetalleUnidad;
import dao.interfaces.DAOCompraDetalleUnidad;

public class DAOCompraDetalleUnidadImpl implements Serializable,
		DAOCompraDetalleUnidad {

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

	public int insertar(ComprasDetalleUnidad comprasDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(comprasDetalleUnidad);
			tx.commit();
			return comprasDetalleUnidad.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(ComprasDetalleUnidad comprasDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalleUnidad c SET c.conFalla = :pConFalla, c.eliminado = :pEliminado, "
					+ "c.nroImei = :pNroImei, c.precioCompra = :pPrecioCompra "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pConFalla", comprasDetalleUnidad.getConFalla());
			locQuery.setParameter("pEliminado", comprasDetalleUnidad.getEliminado());
			locQuery.setParameter("pNroImei", comprasDetalleUnidad.getNroImei());
			locQuery.setParameter("pPrecioCompra", comprasDetalleUnidad.getPrecioCompra());
			locQuery.setParameter("pId", comprasDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return comprasDetalleUnidad.getId();
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ComprasDetalleUnidad get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalleUnidad c WHERE c.id = :pId "
				+ "AND c.eliminado = :pEliminado", ComprasDetalleUnidad.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		ComprasDetalleUnidad comprasDetalleUnidad = new ComprasDetalleUnidad();
		try{
			comprasDetalleUnidad = (ComprasDetalleUnidad) locQuery.getSingleResult();
		} catch(Exception e){
//			System.out.println(e.getMessage());
			comprasDetalleUnidad = new ComprasDetalleUnidad();
		}
		return comprasDetalleUnidad;
	}

	public ComprasDetalleUnidad get(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalleUnidad c WHERE c.nroImei = :pNroImei "
				+ "AND c.eliminado = :pEliminado", ComprasDetalleUnidad.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pEliminado", false);
		ComprasDetalleUnidad comprasDetalleUnidad = new ComprasDetalleUnidad();
		try{
			comprasDetalleUnidad = (ComprasDetalleUnidad) locQuery.getSingleResult();
		} catch(Exception e){
//			System.out.println(e.getMessage());
			comprasDetalleUnidad = new ComprasDetalleUnidad();
		}
		return comprasDetalleUnidad;
	}
	
	public ComprasDetalleUnidad getAll(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalleUnidad c WHERE c.nroImei = :pNroImei ", ComprasDetalleUnidad.class);
		locQuery.setParameter("pNroImei", nroImei);
		ComprasDetalleUnidad comprasDetalleUnidad = new ComprasDetalleUnidad();
		try{
			comprasDetalleUnidad = (ComprasDetalleUnidad) locQuery.getSingleResult();
		} catch(Exception e){
//			System.out.println(e.getMessage());
			comprasDetalleUnidad = new ComprasDetalleUnidad();
		}
		return comprasDetalleUnidad;
	}

	public List<ComprasDetalleUnidad> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalleUnidad c WHERE c.eliminado = :pEliminado", ComprasDetalleUnidad.class);
		locQuery.setParameter("pEliminado", false);
		List<ComprasDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<ComprasDetalleUnidad> getLista(ComprasDetalle comprasDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM ComprasDetalleUnidad c WHERE c.comprasDetalle = :pCompraDetalle "
				+ "AND c.eliminado = :pEliminado", ComprasDetalleUnidad.class);
		locQuery.setParameter("pCompraDetalle", comprasDetalle);
		locQuery.setParameter("pEliminado", false);
		List<ComprasDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public int deleteDetalleUnidadPorDetalle(ComprasDetalle comprasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalleUnidad c SET c.eliminado = :pEliminado "
					+ "WHERE c.comprasDetalle = :pCompraDetalle");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pCompraDetalle", comprasDetalle);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteDetalleCompraUnidad(ComprasDetalleUnidad comprasUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ComprasDetalleUnidad c SET c.eliminado = :pEliminado "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", comprasUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
