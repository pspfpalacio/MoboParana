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
import model.entity.Venta;
import model.entity.VentasDetalle;
import dao.interfaces.DAOVentaDetalle;

public class DAOVentaDetalleImpl implements Serializable, DAOVentaDetalle {

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

	public int insertar(VentasDetalle ventasDetalle) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(ventasDetalle);
			tx.commit();
			return ventasDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(VentasDetalle ventasDetalle) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalle v SET v.cantidad = :pCantidad, v.eliminado = :pEliminado, v.listaPrecio = :pListaPrecio, "
					+ "v.nroImei = :pNroImei, v.precioCompra = :pPrecioCompra, v.precioVenta = :pPrecioVenta, v.producto = :pProducto, v.subtotal = :pSubtotal, "
					+ "v.venta = :pVenta, v.accesorio = :pAccesorio "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pCantidad", ventasDetalle.getCantidad());
			locQuery.setParameter("pEliminado", ventasDetalle.getEliminado());
			locQuery.setParameter("pListaPrecio", ventasDetalle.getListaPrecio());
			locQuery.setParameter("pNroImei", ventasDetalle.getNroImei());
			locQuery.setParameter("pPrecioCompra", ventasDetalle.getPrecioCompra());
			locQuery.setParameter("pPrecioVenta", ventasDetalle.getPrecioVenta());
			locQuery.setParameter("pProducto", ventasDetalle.getProducto());
			locQuery.setParameter("pSubtotal", ventasDetalle.getSubtotal());
			locQuery.setParameter("pVenta", ventasDetalle.getVenta());
			locQuery.setParameter("pAccesorio", ventasDetalle.getAccesorio());
			locQuery.setParameter("pId", ventasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public VentasDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.id = :pId AND v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		VentasDetalle ventasDetalle = new VentasDetalle();
		try{
			ventasDetalle = (VentasDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			ventasDetalle = new VentasDetalle();
		}
		return ventasDetalle;
	}

	public VentasDetalle get(Venta venta, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.venta = :pVenta AND v.producto = :pProducto "
				+ "AND v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pVenta", venta);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		VentasDetalle ventasDetalle = new VentasDetalle();
		try{
			ventasDetalle = (VentasDetalle) locQuery.getSingleResult();
		}catch(Exception e){
//			System.out.println(e.getMessage());
			ventasDetalle = new VentasDetalle();
		}
		return ventasDetalle;
	}

	public int delete(Venta venta) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalle v SET v.eliminado = :pEliminado "
					+ "WHERE v.venta = :pVenta");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pVenta", venta);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int delete(VentasDetalle ventasDetalle){
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasDetalle v SET v.eliminado = :pEliminado "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pId", ventasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<VentasDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasDetalle> getLista(Venta venta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.venta = :pVenta "
				+ "AND v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pVenta", venta);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasDetalle> getListaOrderByProducto(Venta venta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.venta = :pVenta "
				+ "AND v.eliminado = :pEliminado ORDER BY v.producto", VentasDetalle.class);
		locQuery.setParameter("pVenta", venta);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasDetalle> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.producto = :pProducto "
				+ "AND v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasDetalle> getLista(Producto producto, boolean estadoVenta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.producto = :pProducto AND v.venta.estado = :pEstado "
				+ "AND v.eliminado = :pEliminado", VentasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEstado", estadoVenta);
		locQuery.setParameter("pEliminado", false);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasDetalle> getLista(Producto producto, boolean estadoVenta, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasDetalle v WHERE v.producto = :pProducto AND v.venta.estado = :pEstado "
				+ "AND v.eliminado = :pEliminado AND v.venta.fecha BETWEEN :pInicio AND :pFin", VentasDetalle.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEstado", estadoVenta);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	

}
