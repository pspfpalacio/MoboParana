package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Venta;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;
import dao.interfaces.DAOVentaDetalleUnidad;

public class DAOVentaDetalleUnidadImpl implements Serializable,
		DAOVentaDetalleUnidad {

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

	public int insertar(VentasDetalleUnidad ventasDetalleUnidad) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(ventasDetalleUnidad);
			tx.commit();
			return ventasDetalleUnidad.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(VentasDetalleUnidad ventasDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalleUnidad v SET v.eliminado = :pEliminado, v.listaPrecio = :pListaPrecio, v.nroImei = :pNroImei, "
					+ "v.precioCompra = :pPrecioCompra, v.precioVenta = :pPrecioVenta, v.unidadMovil = :pUnidadMovil, v.ventasDetalle = :pVentasDetalle "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pEliminado", ventasDetalleUnidad.getEliminado());
			locQuery.setParameter("pListaPrecio", ventasDetalleUnidad.getListaPrecio());
			locQuery.setParameter("pNroImei", ventasDetalleUnidad.getNroImei());
			locQuery.setParameter("pPrecioCompra", ventasDetalleUnidad.getPrecioCompra());
			locQuery.setParameter("pPrecioVenta", ventasDetalleUnidad.getPrecioVenta());
			locQuery.setParameter("pUnidadMovil", ventasDetalleUnidad.getUnidadMovil());
			locQuery.setParameter("pVentasDetalle", ventasDetalleUnidad.getVentasDetalle());
			locQuery.setParameter("pId", ventasDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public VentasDetalleUnidad get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalleUnidad v WHERE v.id = :pId "
				+ "AND v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		VentasDetalleUnidad ventasDetalleUnidad = new VentasDetalleUnidad();
		try{
			ventasDetalleUnidad = (VentasDetalleUnidad) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			ventasDetalleUnidad = new VentasDetalleUnidad();
		}
		return ventasDetalleUnidad;
	}

	public VentasDetalleUnidad get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalleUnidad v WHERE v.nroImei = :pNroImei "
				+ "AND v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEliminado", false);
		VentasDetalleUnidad ventasDetalleUnidad = new VentasDetalleUnidad();
		try{
			ventasDetalleUnidad = (VentasDetalleUnidad) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			ventasDetalleUnidad = new VentasDetalleUnidad();
		}
		return ventasDetalleUnidad;
	}

	public int deleteUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalleUnidad v SET v.eliminado = :pEliminado "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", ventasDetalleUnidad.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasDetalleUnidad.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int deletePorDetalle(VentasDetalle ventasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalleUnidad v SET v.eliminado = :pEliminado "
					+ "WHERE v.ventasDetalle = :pVentasDetalle");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pVentasDetalle", ventasDetalle);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<VentasDetalleUnidad> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalleUnidad v WHERE v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasDetalleUnidad> getLista(VentasDetalle ventasDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalleUnidad v WHERE v.ventasDetalle = :pVentaDetalle "
				+ "AND v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pVentaDetalle", ventasDetalle);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasDetalleUnidad> getLista(Venta venta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalleUnidad v WHERE v.ventasDetalle.venta = :pVenta "
				+ "AND v.eliminado = :pEliminado", VentasDetalleUnidad.class);
		locQuery.setParameter("pVenta", venta);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalleUnidad> lista = locQuery.getResultList();
		return lista;
	}

}
