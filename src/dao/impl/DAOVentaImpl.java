package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Producto;
import model.entity.Venta;
import model.entity.VentasDetalle;
import dao.interfaces.DAOVenta;

public class DAOVentaImpl implements Serializable, DAOVenta {

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

	public int insertar(Venta venta) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(venta);
			tx.commit();
			return venta.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Venta venta) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Venta v SET v.cliente = :pCliente, v.estado = :pEstado, v.fecha = :pFecha, "
					+ "v.fechaAlta = :pFechaAlta, v.fechaBaja = :pFechaBaja, v.fechaMod = :pFechaMod, v.monto = :pMonto, v.consumidorFinal = :pConsumidorFinal, "
					+ "v.tipo = :pTipo, v.usuario1 = :pUsuarioAlta, v.usuario2 = :pUsuarioBaja, v.usuario3 = :pUsuarioMod "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pCliente", venta.getCliente());
			locQuery.setParameter("pEstado", venta.getEstado());
			locQuery.setParameter("pFecha", venta.getFecha());
			locQuery.setParameter("pFechaAlta", venta.getFechaAlta());
			locQuery.setParameter("pFechaBaja", venta.getFechaBaja());
			locQuery.setParameter("pFechaMod", venta.getFechaMod());
			locQuery.setParameter("pMonto", venta.getMonto());
			locQuery.setParameter("pConsumidorFinal", venta.getConsumidorFinal());
			locQuery.setParameter("pTipo", venta.getTipo());
			locQuery.setParameter("pUsuarioAlta", venta.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", venta.getUsuario2());
			locQuery.setParameter("pUsuarioMod", venta.getUsuario3());
			locQuery.setParameter("pId", venta.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return venta.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Venta get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.id = :pId", Venta.class);
		locQuery.setParameter("pId", id);
		Venta venta = new Venta();
		try{
			venta = (Venta) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			venta = new Venta();
		}
		return venta;
	}
	
	public List<Venta> getListaSinOrden() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v", Venta.class);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v ORDER BY v.fecha DESC", Venta.class);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.cliente = :pCliente ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pCliente", cliente);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(boolean estado, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado AND v.cliente = :pCliente ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(Cliente cliente, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.cliente = :pCliente AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

	public List<Venta> getLista(boolean estado, Cliente cliente,
			Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado AND v.cliente = :pCliente "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT v.venta FROM VentasDetalle v WHERE v.eliminado = :pEliminado "
				+ "AND v.venta.estado = :pEstado AND v.producto = :pProducto", Venta.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProducto", producto);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getLista(Producto producto, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT v.venta FROM VentasDetalle v WHERE v.eliminado = :pEliminado "
				+ "AND v.venta.estado = :pEstado AND v.producto = :pProducto AND v.venta.cliente = :pCliente "
				+ "ORDER BY v.venta.fecha DESC", Venta.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pCliente", cliente);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderFecha(boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderMonto(boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.monto DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderFecha(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado "
				+ "AND v.cliente IN :pClientes AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderMonto(boolean estado, List<Cliente> clientes, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Venta v WHERE v.estado = :pEstado "
				+ "AND v.cliente IN :pClientes AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.monto DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderFecha(Producto producto, boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.venta FROM VentasDetalle v WHERE v.venta.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.venta.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY v.venta.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderMonto(Producto producto, boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.venta FROM VentasDetalle v WHERE v.venta.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.venta.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY v.venta.monto DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderFecha(Producto producto, List<Cliente> clientes, boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.venta FROM VentasDetalle v WHERE v.venta.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.venta.cliente IN :pClientes "
				+ "AND v.venta.fecha BETWEEN :pInicio AND :pFin ORDER BY v.venta.fecha DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Venta> getListaOrderMonto(Producto producto, List<Cliente> clientes, boolean estado, Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.venta FROM VentasDetalle v WHERE v.venta.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.venta.cliente IN :pClientes "
				+ "AND v.venta.fecha BETWEEN :pInicio AND :pFin ORDER BY v.venta.monto DESC", Venta.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Venta> lista = locQuery.getResultList();
		return lista;
	}

}
