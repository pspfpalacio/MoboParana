package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Caja;
import dao.interfaces.DAOCaja;

public class DAOCajaImpl implements Serializable, DAOCaja {

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

	public int insertar(Caja caja) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(caja);
			tx.commit();
			return caja.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Caja caja) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE Caja c SET c.concepto = :pConcepto, c.fecha = :pFecha, c.idMovimiento = :pIdMovimiento, "
					+ "c.monto = :pMonto, c.nombreTabla = :pNombreTabla, c.saldo = :pSaldo, c.usuario = :pUsuario "
					+ "WHERE c.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pConcepto", caja.getConcepto());
			locQuery.setParameter("pFecha", caja.getFecha());
			locQuery.setParameter("pIdMovimiento", caja.getIdMovimiento());
			locQuery.setParameter("pMonto", caja.getMonto());
			locQuery.setParameter("pNombreTabla", caja.getNombreTabla());
			locQuery.setParameter("pSaldo", caja.getSaldo());
			locQuery.setParameter("pUsuario", caja.getUsuario());
			locQuery.setParameter("pId", caja.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return caja.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public Caja get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.id = :pId", Caja.class);
		locQuery.setParameter("pId", id);
		Caja caja = new Caja();
		try{
			caja = (Caja) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			caja = new Caja();
		}
		return caja;
	}

	public List<Caja> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Caja> getListaSinOrden() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c", Caja.class);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public List<Caja> getLista(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public List<Caja> getListaOrdenadaPorFecha(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha DESC", Caja.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Caja> getListaOrdenadaParaInsercion(Date fechaDesde, Date fechaHasta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha BETWEEN :pInicio AND :pFin ORDER BY c.fecha", Caja.class);
		locQuery.setParameter("pInicio", fechaDesde);
		locQuery.setParameter("pFin", fechaHasta);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public Caja get(int idMovimiento, String nombreTabla) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.idMovimiento = :pIdMovimiento AND c.nombreTabla = :pNombreTabla", Caja.class);
		locQuery.setParameter("pIdMovimiento", idMovimiento);
		locQuery.setParameter("pNombreTabla", nombreTabla);
		Caja caja = new Caja();
		try{
			caja = (Caja) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			caja = new Caja();
		}
		return caja;
	}

	public int delete(Caja caja) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM Caja c "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pId", caja.getId());
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

}
