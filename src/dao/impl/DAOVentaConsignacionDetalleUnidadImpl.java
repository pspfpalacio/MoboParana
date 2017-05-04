package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import model.entity.VentasDetalleUnidad;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;

public class DAOVentaConsignacionDetalleUnidadImpl implements Serializable,
		DAOVentaConsignacionDetalleUnidad {

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

	public int insertar(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(ventasConsDetalleUnidad);
			tx.commit();
			return ventasConsDetalleUnidad.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalleUnidad v SET v.consignacionsDetalleUnidad = :pConsignacionDetalleUnidad, "
					+ "v.eliminado = :pEliminado, v.nroImei = :pNroImei, v.precioCompra = :pPrecioCompra, v.precioVenta = :pPrecioVenta, v.ventasConsDetalle = :pVentaConDetalle "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pConsignacionDetalleUnidad", ventasConsDetalleUnidad.getConsignacionsDetalleUnidad());
			locQuery.setParameter("pEliminado", ventasConsDetalleUnidad.getEliminado());
			locQuery.setParameter("pNroImei", ventasConsDetalleUnidad.getNroImei());
			locQuery.setParameter("pPrecioCompra", ventasConsDetalleUnidad.getPrecioCompra());
			locQuery.setParameter("pPrecioVenta", ventasConsDetalleUnidad.getPrecioVenta());
			locQuery.setParameter("pVentaConDetalle", ventasConsDetalleUnidad.getVentasConsDetalle());
			locQuery.setParameter("pId", ventasConsDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasConsDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public VentasConsDetalleUnidad get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalleUnidad v WHERE v.id = :pId "
				+ "AND v.eliminado = :pEliminado", VentasConsDetalleUnidad.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		VentasConsDetalleUnidad ventasDetalleUnidad = new VentasConsDetalleUnidad();
		try{
			ventasDetalleUnidad = (VentasConsDetalleUnidad) locQuery.getSingleResult();
		}catch(Exception e){
			ventasDetalleUnidad = new VentasConsDetalleUnidad();
		}
		return ventasDetalleUnidad;
	}

	public VentasConsDetalleUnidad get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalleUnidad v WHERE v.nroImei = :pNroImei "
				+ "AND v.eliminado = :pEliminado AND v.ventasConsDetalle.eliminado = :pEliminado "
				+ "AND v.ventasConsDetalle.ventasCon.estado = :pEstado", VentasConsDetalleUnidad.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		VentasConsDetalleUnidad ventasDetalleUnidad = new VentasConsDetalleUnidad();
		try{
			ventasDetalleUnidad = (VentasConsDetalleUnidad) locQuery.getSingleResult();
		}catch(Exception e){
			ventasDetalleUnidad = new VentasConsDetalleUnidad();
		}
		return ventasDetalleUnidad;
	}

	public int deleteUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalleUnidad v SET v.eliminado = :pEliminado "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", ventasConsDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int deletePorDetalle(VentasConsDetalle ventasConsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalleUnidad v SET v.eliminado = :pEliminado "
					+ "WHERE v.ventasConsDetalle = :pVentasConsDetalle");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pVentasConsDetalle", ventasConsDetalle);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<VentasConsDetalleUnidad> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalleUnidad v WHERE v.eliminado = :pEliminado", VentasConsDetalleUnidad.class);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasConsDetalleUnidad> getLista(
			VentasConsDetalle ventasConsDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalleUnidad v WHERE v.ventasConsDetalle = :pVentasConsDetalle "
				+ "AND v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pVentasConsDetalle", ventasConsDetalle);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasConsDetalleUnidad> getLista(
			VentasCon ventasCon) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalleUnidad v WHERE v.ventasConsDetalle.ventasCon = :pVentasCon "
				+ "AND v.eliminado = :pEliminado AND v.ventasConsDetalle.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pVentasCon", ventasCon);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

}
