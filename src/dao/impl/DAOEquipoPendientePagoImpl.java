package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Caja;
import model.entity.Cliente;
import model.entity.EquipoPendientePago;
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
	
	public int insert(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(epp);
			tx.commit();
			return epp.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public int update(EquipoPendientePago epp) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE EquipoPendientePago e SET e.pagado = :pPagado, e.usuarioMod = :pUsuarioMod, e.fechaMod = :pFechaMod "
					+ "WHERE e.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pPagado", epp.getPagado());
			locQuery.setParameter("pFechaMod", epp.getFechaMod());
			locQuery.setParameter("pUsuarioMod", epp.getUsuario2());
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
	
	public List<EquipoPendientePago> getListaPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente.getId());
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<EquipoPendientePago> getListaNoPagosPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente and e.pago = 0", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente.getId());
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<EquipoPendientePago> getListaPagosPorCliente(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT e FROM EquipoPendientePago e WHERE e.cliente = :pCliente and e.pago = 1", EquipoPendientePago.class);
		locQuery.setParameter("pCliente", cliente.getId());
		List<EquipoPendientePago> lista = locQuery.getResultList();
		return lista;
	}

}
