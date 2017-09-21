package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.EquipoPendientePago;
import model.entity.PagosCliente;
import dao.interfaces.DAOEquipoPendientePago;

public class DAOEquipoPendientePagoImpl  implements Serializable, DAOEquipoPendientePago {
	
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
	
	public int insert(EquipoPendientePago equipoPendientePago) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(equipoPendientePago);
			tx.commit();
			return equipoPendientePago.getId();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}
	
	public int update(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE EquipoPendientePago e SET e.cliente = :pCliente, e.enabled = :pEnabled, "
					+ "e.fechaAlta = :pFechaAlta, e.fechaBaja = :pFechaBaja, e.fechaMod = :pFechaMod, e.imei = :pImei, "
					+ "e.monto = :pMonto, e.pagado = :pPagado, e.pagosCliente = :pPagosCliente, "
					+ "e.usuario1 = :pUsuarioAlta, e.usuario2 = :pUsuarioMod, e.usuario3 = :pUsuarioBaja "
					+ "WHERE e.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pCliente", epp.getCliente());
			locQuery.setParameter("pEnabled", epp.getEnabled());
			locQuery.setParameter("pFechaAlta", epp.getFechaAlta());
			locQuery.setParameter("pFechaBaja", epp.getFechaBaja());
			locQuery.setParameter("pFechaMod", epp.getFechaMod());
			locQuery.setParameter("pImei", epp.getImei());
			locQuery.setParameter("pMonto", epp.getMonto());
			locQuery.setParameter("pPagado", epp.getPagado());
			locQuery.setParameter("pPagosCliente", epp.getPagosCliente());
			locQuery.setParameter("pUsuarioAlta", epp.getUsuario1());
			locQuery.setParameter("pUsuarioMod", epp.getUsuario2());			
			locQuery.setParameter("pUsuarioBaja", epp.getUsuario3());
			locQuery.setParameter("pId", epp.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return epp.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public int pagar(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE EquipoPendientePago e SET e.pagado = 1, e.usuario2 = :pUsuario2, e.fechaMod = :pFechaMod "
					+ "WHERE e.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pFechaMod", epp.getFechaMod());
			locQuery.setParameter("pUsuario2", epp.getUsuario2());
			locQuery.setParameter("pId", epp.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return epp.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public int baja(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE EquipoPendientePago e SET e.enabled = 0 "
					+ "WHERE e.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pId", epp.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return epp.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public EquipoPendientePago getPorImei(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.imei = :pImei", EquipoPendientePago.class);
		locQuery.setParameter("pImei", imei);
		EquipoPendientePago epp = new EquipoPendientePago();
		try{
			epp = (EquipoPendientePago) locQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println("getCliente " + e.getMessage());
			epp = new EquipoPendientePago();
		}
		return epp;
	}
	
	public EquipoPendientePago get(String imei, boolean pago, boolean enabled) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.imei = :pImei "
				+ "AND e.pagado = :pPago AND e.enabled = :pEnabled", EquipoPendientePago.class);
		locQuery.setParameter("pImei", imei);
		locQuery.setParameter("pPago", pago);
		locQuery.setParameter("pEnabled", enabled);
		EquipoPendientePago equipoPendientePago = new EquipoPendientePago();
		try {
			equipoPendientePago = (EquipoPendientePago) locQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			equipoPendientePago = new EquipoPendientePago();
		}
		return equipoPendientePago;
	}
	
	public List<EquipoPendientePago> getListaPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente and e.enabled = 1", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente);
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<EquipoPendientePago> getListaNoPagosPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente and e.pagado = 0 and e.enabled = 1", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente);
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<EquipoPendientePago> getListaPagosPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente and e.pagado = 1 and e.enabled = 1", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente);
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<EquipoPendientePago> getLista(Cliente cliente, PagosCliente pagosCliente, boolean pago, boolean enabled) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente "
				+ "AND e.pagosCliente = :pPagosCliente AND e.pagado = :pPagado AND e.enabled = :pEnabled", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pPagosCliente", pagosCliente);
		locQuery.setParameter("pPagado", pago);
		locQuery.setParameter("pEnabled", enabled);
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}

}
