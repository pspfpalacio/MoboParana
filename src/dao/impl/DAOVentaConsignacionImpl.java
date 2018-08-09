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
import model.entity.Consignacion;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.Usuario;
import model.entity.VentasCon;
import dao.interfaces.DAOVentaConsignacion;

public class DAOVentaConsignacionImpl implements Serializable,
		DAOVentaConsignacion {

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

	public int insertar(VentasCon ventasCon) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(ventasCon);
			tx.commit();
			return ventasCon.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(VentasCon ventasCon) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE VentasCon v SET v.cliente = :pCliente, v.consignacion = :pConsignacion, v.estado = :pEstado, "
					+ "v.fecha = :pFecha, v.fechaAlta = :pFechaAlta, v.fechaBaja = :pFechaBaja, v.fechaMod = :pFechaMod, v.monto = :pMonto, "
					+ "v.saldoCliente = :pSaldoCliente, v.tipo = :pTipo, v.usuario1 = :pUsuarioAlta, v.usuario2 = :pUsuarioBaja, v.usuario3 = :pUsuarioMod "
					+ "WHERE v.id = :pId");
			locQuery.setParameter("pCliente", ventasCon.getCliente());
			locQuery.setParameter("pConsignacion", ventasCon.getConsignacion());
			locQuery.setParameter("pEstado", ventasCon.getEstado());
			locQuery.setParameter("pFecha", ventasCon.getFecha());
			locQuery.setParameter("pFechaAlta", ventasCon.getFechaAlta());
			locQuery.setParameter("pFechaBaja", ventasCon.getFechaBaja());
			locQuery.setParameter("pFechaMod", ventasCon.getFechaMod());
			locQuery.setParameter("pMonto", ventasCon.getMonto());
			locQuery.setParameter("pSaldoCliente", ventasCon.getSaldoCliente());
			locQuery.setParameter("pTipo", ventasCon.getTipo());
			locQuery.setParameter("pUsuarioAlta", ventasCon.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", ventasCon.getUsuario2());
			locQuery.setParameter("pUsuarioMod", ventasCon.getUsuario3());
			locQuery.setParameter("pId", ventasCon.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return ventasCon.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public VentasCon get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.id = :pId", VentasCon.class);
		locQuery.setParameter("pId", id);
		VentasCon venta = new VentasCon();
		try{
			venta = (VentasCon) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			venta = new VentasCon();
		}
		return venta;
	}
	
	public Integer getMaxId() {
		Integer resultado = 0;
			try {
			inicializar();
			Query locQuery = em.createQuery("SELECT MAX(v.id) FROM VentasCon", Integer.class);
			
			try {
				resultado = (Integer) locQuery.getSingleResult();
			} catch (Exception e) {
				resultado = 0;
			}
		}catch (Exception ex) {
			resultado = 0;
		}
		return resultado;
	}

	public List<VentasCon> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v ORDER BY v.fecha DESC", VentasCon.class);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.cliente = :pCliente ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pCliente", cliente);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.consignacion = :pConsignacion ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pConsignacion", consignacion);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(boolean estado, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.cliente = :pCliente ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(boolean estado, Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.consignacion = :pConsignacion ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pConsignacion", consignacion);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion, Usuario usuario) {
		try {
			inicializar();
			Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.consignacion = :pConsignacion "
					+ "AND v.usuario1 = :pUsuario ORDER BY v.fecha DESC", VentasCon.class);
			locQuery.setParameter("pEstado", estado);
			locQuery.setParameter("pConsignacion", consignacion);
			locQuery.setParameter("pUsuario", usuario);
			List<VentasCon> lista = locQuery.getResultList();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public List<VentasCon> getLista(boolean estado, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(Cliente cliente, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.cliente = :pCliente AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(Consignacion consignacion, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.consignacion = :pConsignacion AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(boolean estado, Cliente cliente,
			Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.cliente = :pCliente "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

	public List<VentasCon> getLista(boolean estado, Consignacion consignacion,
			Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.consignacion = :pConsignacion "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getLista(boolean estado, Consignacion consignacion,
			Date fechaDesde, Date fechaHasta, Usuario usuario) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado AND v.consignacion = :pConsignacion "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin AND v.usuario1 = :pUsuario ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pConsignacion", consignacion);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		locQuery.setParameter("pUsuario", usuario);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getLista(Producto producto, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT DISTINCT v.ventasCon FROM VentasConsDetalle v WHERE v.eliminado = :pEliminado "
				+ "AND v.ventasCon.estado = :pEstado AND v.producto = :pProducto AND v.ventasCon.cliente = :pCliente", VentasCon.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pCliente", cliente);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderFecha(boolean estado, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderMonto(boolean estado, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.monto DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderFecha(boolean estado, List<Cliente> clientes, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado "
				+ "AND v.cliente IN :pClientes "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderMonto(boolean estado, List<Cliente> clientes, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM VentasCon v WHERE v.estado = :pEstado "
				+ "AND v.cliente IN :pClientes "
				+ "AND v.fecha BETWEEN :pInicio AND :pFin ORDER BY v.monto DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderFecha(boolean estado, Producto producto, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.ventasCon FROM VentasConsDetalle v WHERE v.ventasCon.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado "
				+ "AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin ORDER BY v.ventasCon.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderMonto(boolean estado, Producto producto, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.ventasCon FROM VentasConsDetalle v WHERE v.ventasCon.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado "
				+ "AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin ORDER BY v.ventasCon.monto DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderFecha(boolean estado, List<Cliente> clientes, Producto producto, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.ventasCon FROM VentasConsDetalle v WHERE v.ventasCon.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.ventasCon.cliente IN :pClientes "
				+ "AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin ORDER BY v.ventasCon.fecha DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getListaOrderMonto(boolean estado, List<Cliente> clientes, Producto producto, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.ventasCon FROM VentasConsDetalle v WHERE v.ventasCon.estado = :pEstado "
				+ "AND v.producto = :pProducto AND v.eliminado = :pEliminado AND v.ventasCon.cliente IN :pClientes "
				+ "AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin ORDER BY v.ventasCon.monto DESC", VentasCon.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pClientes", clientes);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<VentasCon> getLista(Rubro rubro, boolean estado, Date fechaDesde,
			Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v.ventasCon FROM VentasConsDetalle v WHERE v.ventasCon.estado = :pEstado "
				+ "AND v.ventasCon.fecha BETWEEN :pInicio AND :pFin AND v.producto.rubro = :pRubro "
				+ "ORDER BY v.ventasCon.fecha DESC", VentasCon.class);
		locQuery.setParameter("pRubro", rubro);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<VentasCon> lista = locQuery.getResultList();
		return lista;
	}

}
