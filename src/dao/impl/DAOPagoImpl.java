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
import model.entity.PagosCliente;
import model.entity.PagosProveedore;
import model.entity.Proveedore;
import dao.interfaces.DAOPago;

public class DAOPagoImpl implements Serializable, DAOPago {

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

	public int insertar(PagosCliente pagosCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pagosCliente);
			tx.commit();
			return pagosCliente.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(PagosCliente pagosCliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE PagosCliente p SET p.cliente = :pCliente, p.concepto = :pConcepto, "
					+ "p.fecha = :pFecha, p.fechaAlta = :pFechaAlta, p.monto = :pMonto, p.usuario1 = :pUsuarioAlta, "
					+ "p.usuario2 = :pUsuarioBaja, p.fechaBaja = :pFechaBaja, p.enabled = :pEnabled "
					+ "WHERE p.id = :pId");
			locQuery.setParameter("pCliente", pagosCliente.getCliente());
			locQuery.setParameter("pConcepto", pagosCliente.getConcepto());
			locQuery.setParameter("pFecha", pagosCliente.getFecha());
			locQuery.setParameter("pFechaAlta", pagosCliente.getFechaAlta());
			locQuery.setParameter("pMonto", pagosCliente.getMonto());
			locQuery.setParameter("pUsuarioAlta", pagosCliente.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", pagosCliente.getUsuario2());
			locQuery.setParameter("pFechaBaja", pagosCliente.getFechaBaja());
			locQuery.setParameter("pEnabled", pagosCliente.getEnabled());
			locQuery.setParameter("pId", pagosCliente.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return pagosCliente.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public PagosCliente getPagoCliente(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.id = :pId", PagosCliente.class);
		locQuery.setParameter("pId", id);
		PagosCliente pagosCliente = new PagosCliente();
		try{
			pagosCliente = (PagosCliente) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			pagosCliente = new PagosCliente();
		}
		return pagosCliente;
	}

	public List<PagosCliente> getListaPagosCliente() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p", PagosCliente.class);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCliente> getListaPagosCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.cliente = :pCliente", PagosCliente.class);
		locQuery.setParameter("pCliente", cliente);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<PagosCliente> getListaPagosCliente(Cliente cliente, boolean enabled) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.cliente = :pCliente "
				+ "AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pEnabled", enabled);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCliente> getListaPagosCliente(Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.fecha BETWEEN :pInicio AND :pFin", PagosCliente.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCliente> getListaPagosCliente(Cliente cliente,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.cliente = :pCliente "
				+ "AND p.fecha BETWEEN :pInicio AND :pFin", PagosCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<PagosCliente> getListaPagosCliente(Cliente cliente,
			Date fechaInicio, Date fechaFin, boolean enabled) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCliente p WHERE p.cliente = :pCliente "
				+ "AND p.enabled = :pEnabled AND p.fecha BETWEEN :pInicio AND :pFin "
				+ "ORDER BY p.fecha DESC", PagosCliente.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pEnabled", enabled);
		List<PagosCliente> lista = locQuery.getResultList();
		return lista;
	}

	public int insertar(PagosProveedore pagosProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pagosProveedore);
			tx.commit();
			return pagosProveedore.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(PagosProveedore pagosProveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE PagosProveedore p SET p.proveedore = :pProveedor, p.concepto = :pConcepto, "
					+ "p.fecha = :pFecha, p.fechaAlta = :pFechaAlta, p.monto = :pMonto, p.usuario = :pUsuario "
					+ "WHERE p.id = :pId");
			locQuery.setParameter("pProveedor", pagosProveedore.getProveedore());
			locQuery.setParameter("pConcepto", pagosProveedore.getConcepto());
			locQuery.setParameter("pFecha", pagosProveedore.getFecha());
			locQuery.setParameter("pFechaAlta", pagosProveedore.getFechaAlta());
			locQuery.setParameter("pMonto", pagosProveedore.getMonto());
			locQuery.setParameter("pUsuario", pagosProveedore.getUsuario());
			locQuery.setParameter("pId", pagosProveedore.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return pagosProveedore.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public PagosProveedore getPagoProveedore(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosProveedore p WHERE p.id = :pId", PagosProveedore.class);
		locQuery.setParameter("pId", id);
		PagosProveedore pagosProveedor = new PagosProveedore();
		try{
			pagosProveedor = (PagosProveedore) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			pagosProveedor = new PagosProveedore();
		}
		return pagosProveedor;
	}

	public List<PagosProveedore> getListaPagosProveedore() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosProveedore p", PagosProveedore.class);
		List<PagosProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosProveedore> getListaPagosProveedore(Proveedore proveedore) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosProveedore p WHERE p.proveedore = :pProveedor ", PagosProveedore.class);
		locQuery.setParameter("pProveedor", proveedore);
		List<PagosProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosProveedore> getListaPagosProveedore(Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosProveedore p WHERE p.fecha BETWEEN :pInicio AND :pFin", PagosProveedore.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<PagosProveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosProveedore> getListaPagosProveedore(Proveedore proveedore,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosProveedore p WHERE p.proveedore = :pProveedor "
				+ "AND p.fecha BETWEEN :pInicio AND :pFin", PagosProveedore.class);
		locQuery.setParameter("pProveedor", proveedore);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<PagosProveedore> lista = locQuery.getResultList();
		return lista;
	}

}
