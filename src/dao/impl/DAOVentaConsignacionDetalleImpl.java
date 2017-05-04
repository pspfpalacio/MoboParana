package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Producto;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import dao.interfaces.DAOVentaConsignacionDetalle;

public class DAOVentaConsignacionDetalleImpl implements Serializable,
		DAOVentaConsignacionDetalle {

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

	public int insertar(VentasConsDetalle ventasConsDetalle) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(ventasConsDetalle);
			tx.commit();
			return ventasConsDetalle.getId();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}

	public int update(VentasConsDetalle ventasConsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalle v SET v.cantidad = :pCantidad, v.consignacionsDetalle = :pConsignacionDetalle, "
					+ "v.eliminado = :pEliminado, v.precioVenta = :pPrecioVenta, v.producto = :pProducto, v.subtotal = :pSubtotal, v.ventasCon = :pVentaCon "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pCantidad", ventasConsDetalle.getCantidad());
			locQuery.setParameter("pConsignacionDetalle", ventasConsDetalle.getConsignacionsDetalle());
			locQuery.setParameter("pEliminado", ventasConsDetalle.getEliminado());
			locQuery.setParameter("pPrecioVenta", ventasConsDetalle.getPrecioVenta());
			locQuery.setParameter("pProducto", ventasConsDetalle.getProducto());
			locQuery.setParameter("pSubtotal", ventasConsDetalle.getSubtotal());
			locQuery.setParameter("pVentaCon", ventasConsDetalle.getVentasCon());
			locQuery.setParameter("pId", ventasConsDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasConsDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public VentasConsDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.id = :pId AND v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		VentasConsDetalle ventasDetalle = new VentasConsDetalle();
		try{
			ventasDetalle = (VentasConsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
			ventasDetalle = new VentasConsDetalle();
		}
		return ventasDetalle;
	}

	public VentasConsDetalle get(VentasCon ventasCon, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.ventasCon = :pVentaCon AND v.producto = :pProducto "
				+ "AND v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pVentaCon", ventasCon);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		VentasConsDetalle ventasDetalle = new VentasConsDetalle();
		try{
			ventasDetalle = (VentasConsDetalle) locQuery.getSingleResult();
		}catch(Exception e){
			ventasDetalle = new VentasConsDetalle();
		}
		return ventasDetalle;
	}

	public int delete(VentasCon ventasCon) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalle v SET v.eliminado = :pEliminado "
					+ "WHERE v.ventasCon = :pVentaCon");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pVentaCon", ventasCon);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int delete(VentasConsDetalle ventasConsDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasConsDetalle v SET v.eliminado = :pEliminado "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", ventasConsDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<VentasConsDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasConsDetalle> getLista(VentasCon ventasCon) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.ventasCon = :pVentaCon "
				+ "AND v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pVentaCon", ventasCon);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasConsDetalle> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.producto = :pProducto "
				+ "AND v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasConsDetalle> getLista(Producto producto,
			boolean estadoVenta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.producto = :pProducto AND v.ventasCon.estado = :pEstado "
				+ "AND v.eliminado = :pEliminado", VentasConsDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEstado", estadoVenta);
		locQuery.setParameter("pEliminado", false);
		List<VentasConsDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasConsDetalle> getLista(Producto producto,
			boolean estadoVenta, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasConsDetalle v WHERE v.producto = :pProducto AND v.ventasCon.estado = :pEstado "
				+ "AND v.eliminado = :pEliminado AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin", VentasConsDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEstado", estadoVenta);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasConsDetalle> lista = locQuery.getResultList();
		return lista;
	}

}
