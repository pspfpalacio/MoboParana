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
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Proveedore;
import dao.interfaces.DAOCuentaCorriente;

public class DAOCuentaCorrienteImpl implements Serializable, DAOCuentaCorriente {

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

	public int insertar(CuentasCorrientesCliente cuentasCorrientesCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuentasCorrientesCliente);
			tx.commit();
			return cuentasCorrientesCliente.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(CuentasCorrientesCliente cuentasCorrientesCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE CuentasCorrientesCliente c SET c.cliente = :pCliente, c.debe = :pDebe, "
					+ "c.detalle = :pDetalle, c.fecha = :pFecha, c.haber = :pHaber, c.idMovimiento = :pIdMovimiento, "
					+ "c.monto = :pMonto, c.nombreTabla = :pNombreTabla, c.saldo = :pSaldo, c.usuario = :pUsuario "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCliente", cuentasCorrientesCliente.getCliente());
			locQuery.setParameter("pDebe", cuentasCorrientesCliente.getDebe());
			locQuery.setParameter("pDetalle", cuentasCorrientesCliente.getDetalle());
			locQuery.setParameter("pFecha", cuentasCorrientesCliente.getFecha());
			locQuery.setParameter("pHaber", cuentasCorrientesCliente.getHaber());
			locQuery.setParameter("pIdMovimiento", cuentasCorrientesCliente.getIdMovimiento());
			locQuery.setParameter("pMonto", cuentasCorrientesCliente.getMonto());
			locQuery.setParameter("pNombreTabla", cuentasCorrientesCliente.getNombreTabla());
			locQuery.setParameter("pSaldo", cuentasCorrientesCliente.getSaldo());
			locQuery.setParameter("pUsuario", cuentasCorrientesCliente.getUsuario());
			locQuery.setParameter("pId", cuentasCorrientesCliente.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuentasCorrientesCliente.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
//	public int delete(int id) {
//		inicializar();
//		EntityTransaction tx = em.getTransaction();
//		try {
//			Query locQuery = em.createQuery("DELETE FROM CuentasCorrientesCliente c "
//					+ "WHERE c.id = :pId");
//			locQuery.setParameter("pId", id);
//			tx.begin();
//			locQuery.executeUpdate();
//			tx.commit();
//			//cerrarInstancia();
//			return 1;
//		} catch (Exception e) {
//			tx.rollback();
//			//cerrarInstancia();
//			return 0;
//		}
//	}

	public CuentasCorrientesCliente get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c WHERE c.id = :pId", CuentasCorrientesCliente.class);
		locQuery.setParameter("pId", id);
		CuentasCorrientesCliente cuentasCorrientesCliente = new CuentasCorrientesCliente();
		try{
			cuentasCorrientesCliente = (CuentasCorrientesCliente) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			cuentasCorrientesCliente = new CuentasCorrientesCliente();
		}
		return cuentasCorrientesCliente;
	}

	public List<CuentasCorrientesCliente> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesCliente.class);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuentasCorrientesCliente> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c "
				+ "WHERE c.cliente = :pCliente "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesCliente.class);
		locQuery.setParameter("pCliente", cliente);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuentasCorrientesCliente> getLista(Cliente cliente,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c "
				+ "WHERE c.cliente = :pCliente AND c.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

	public int insertar(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuentasCorrientesProveedore);
			tx.commit();
			return cuentasCorrientesProveedore.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE CuentasCorrientesProveedore c SET c.proveedore = :pProveedor, c.debe = :pDebe, "
					+ "c.detalle = :pDetalle, c.fecha = :pFecha, c.haber = :pHaber, c.idMovimiento = :pIdMovimiento, "
					+ "c.monto = :pMonto, c.nombreTabla = :pNombreTabla, c.saldo = :pSaldo, c.usuario = :pUsuario "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pProveedor", cuentasCorrientesProveedore.getProveedore());
			locQuery.setParameter("pDebe", cuentasCorrientesProveedore.getDebe());
			locQuery.setParameter("pDetalle", cuentasCorrientesProveedore.getDetalle());
			locQuery.setParameter("pFecha", cuentasCorrientesProveedore.getFecha());
			locQuery.setParameter("pHaber", cuentasCorrientesProveedore.getHaber());
			locQuery.setParameter("pIdMovimiento", cuentasCorrientesProveedore.getIdMovimiento());
			locQuery.setParameter("pMonto", cuentasCorrientesProveedore.getMonto());
			locQuery.setParameter("pNombreTabla", cuentasCorrientesProveedore.getNombreTabla());
			locQuery.setParameter("pSaldo", cuentasCorrientesProveedore.getSaldo());
			locQuery.setParameter("pUsuario", cuentasCorrientesProveedore.getUsuario());
			locQuery.setParameter("pId", cuentasCorrientesProveedore.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuentasCorrientesProveedore.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public CuentasCorrientesProveedore getProveedor(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c WHERE c.id = :pId", CuentasCorrientesProveedore.class);
		locQuery.setParameter("pId", id);
		CuentasCorrientesProveedore cuentasCorrientesProveedor = new CuentasCorrientesProveedore();
		try{
			cuentasCorrientesProveedor = (CuentasCorrientesProveedore) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			cuentasCorrientesProveedor = new CuentasCorrientesProveedore();
		}
		return cuentasCorrientesProveedor;
	}

	public List<CuentasCorrientesProveedore> getListaProveedor() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesProveedore.class);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuentasCorrientesProveedore> getListaProveedor(
			Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c "
				+ "WHERE c.proveedore = :pProveedor "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesProveedore.class);
		locQuery.setParameter("pProveedor", proveedore);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuentasCorrientesProveedore> getListaProveedor(
			Proveedore proveedore, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c "
				+ "WHERE c.proveedore = :pProveedor AND c.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesProveedore.class);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<CuentasCorrientesCliente> getListaOrdenadaPorFechaCliente(Date fechaInicio, Date fechaFin, Cliente cliente){
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c WHERE c.cliente = :pCliente AND c.fecha BETWEEN :pFechaInicio "
				+ "AND :pFechaFin ORDER BY c.fecha", CuentasCorrientesCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		locQuery.setParameter("pFechaFin", fechaFin);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<CuentasCorrientesCliente> getListaOrdenadaPorFechaCliente(Date fechaInicio, Cliente cliente){
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c WHERE c.cliente = :pCliente AND c.fecha >= :pFechaInicio "
				+ "ORDER BY c.fecha, c.id", CuentasCorrientesCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuentasCorrientesProveedore> getListaOrdenadaPorFechaProveedor(
			Date fechaInicio, Date fechaFin, Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c WHERE c.proveedore = :pProveedor AND c.fecha BETWEEN :pFechaInicio "
				+ "AND :pFechaFin ORDER BY c.fecha", CuentasCorrientesCliente.class);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		locQuery.setParameter("pFechaFin", fechaFin);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<CuentasCorrientesProveedore> getListaOrdenadaPorFechaProveedor(
			Date fechaInicio, Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c WHERE c.proveedore = :pProveedor AND c.fecha >= :pFechaInicio "
				+ "ORDER BY c.fecha, c.id", CuentasCorrientesCliente.class);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public CuentasCorrientesCliente get(int idMovimiento,
			String nombreTabla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla", CuentasCorrientesCliente.class);
		locQuery.setParameter("pIdMovimiento", idMovimiento);
		locQuery.setParameter("pNombreTabla", nombreTabla);
		CuentasCorrientesCliente cuenta = new CuentasCorrientesCliente();
		try{
			cuenta = (CuentasCorrientesCliente) locQuery.getSingleResult();
		}catch(Exception e){
			cuenta = new CuentasCorrientesCliente();
		}
		return cuenta;
	}
	
	public List<CuentasCorrientesCliente> getLista(int idMovimiento, String nombreTabla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla "
				+ "ORDER BY c.fecha DESC, c.id DESC", CuentasCorrientesCliente.class);
		locQuery.setParameter("pIdMovimiento", idMovimiento);
		locQuery.setParameter("pNombreTabla", nombreTabla);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

	public CuentasCorrientesProveedore getProveedor(
			int idMovimiento, String nombreTabla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla", CuentasCorrientesProveedore.class);
		locQuery.setParameter("pIdMovimiento", idMovimiento);
		locQuery.setParameter("pNombreTabla", nombreTabla);
		CuentasCorrientesProveedore cuenta = new CuentasCorrientesProveedore();
		try{
			cuenta = (CuentasCorrientesProveedore) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			cuenta = new CuentasCorrientesProveedore();
		}
		return cuenta;
	}

	public int deletePorMovimientoCliente(int idMovimiento, String nombreTabla, Cliente cliente) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM CuentasCorrientesCliente c "
					+ "WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla AND c.cliente = :pCliente");
			locQuery.setParameter("pIdMovimiento", idMovimiento);
			locQuery.setParameter("pNombreTabla", nombreTabla);
			locQuery.setParameter("pCliente", cliente);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			//cerrarInstancia();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			//cerrarInstancia();
			return 0;
		}
	}

	public int deletePorMovimientoProveedor(int idMovimiento, String nombreTabla, Proveedore proveedor) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM CuentasCorrientesProveedore c "
					+ "WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla AND c.proveedore = :pProveedor");
			locQuery.setParameter("pIdMovimiento", idMovimiento);
			locQuery.setParameter("pNombreTabla", nombreTabla);
			locQuery.setParameter("pProveedor", proveedor);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			//cerrarInstancia();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			//cerrarInstancia();
			return 0;
		}
	}
	
	
	public List<CuentasCorrientesProveedore> getListaProveedorOrdenadaPorFecha(
			Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesProveedore c "
				+ "WHERE c.proveedore = :pProveedor "
				+ "ORDER BY c.fecha, c.id", CuentasCorrientesProveedore.class);
		locQuery.setParameter("pProveedor", proveedore);
		List<CuentasCorrientesProveedore> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<CuentasCorrientesCliente> getListaClienteOrdenadaPorFecha(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuentasCorrientesCliente c "
				+ "WHERE c.cliente = :pCliente "
				+ "ORDER BY c.fecha, c.id", CuentasCorrientesCliente.class);
		locQuery.setParameter("pCliente", cliente);
		List<CuentasCorrientesCliente> lista = locQuery.getResultList();
		return lista;
	}

}
