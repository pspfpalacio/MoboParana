package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Consignacion;
import model.entity.Cuota;
import dao.interfaces.DAOCuota;

public class DAOCuotaImpl implements Serializable, DAOCuota {

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

	public int insertar(Cuota cuota) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuota);
			tx.commit();
			return cuota.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Cuota cuota) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Cuota c SET c.cantCuotas = :pCantCuotas, c.consignacion = :pConsignacion, "
					+ "c.equipo = :pEquipo, c.estado = :pEstado, c.fechaAlta = :pFechaAlta, c.interes = :pInteres, "
					+ "c.montoTotal = :pMontoTotal, c.nroImei = :pNroImei, c.fechaBaja = :pFechaBaja, "
					+ "c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCantCuotas", cuota.getCantCuotas());
			locQuery.setParameter("pConsignacion", cuota.getConsignacion());
			locQuery.setParameter("pEquipo", cuota.getEquipo());
			locQuery.setParameter("pEstado", cuota.getEstado());
			locQuery.setParameter("pFechaAlta", cuota.getFechaAlta());
			locQuery.setParameter("pInteres", cuota.getInteres());
			locQuery.setParameter("pMontoTotal", cuota.getMontoTotal());
			locQuery.setParameter("pNroImei", cuota.getNroImei());
			locQuery.setParameter("pFechaBaja", cuota.getFechaBaja());
			locQuery.setParameter("pUsuarioAlta", cuota.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", cuota.getUsuario2());
			locQuery.setParameter("pId", cuota.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuota.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Cuota get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.id = :pId", Cuota.class);
		locQuery.setParameter("pId", id);
		Cuota cuota = new Cuota();
		try {
			cuota = (Cuota) locQuery.getSingleResult();
		} catch(Exception e) {
			cuota = new Cuota();
		}
		return cuota;
	}

	public Cuota get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.nroImei = :pNroImei "
				+ "AND c.estado = :pEstado", Cuota.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEstado", true);
		Cuota cuota = new Cuota();
		try {
			cuota = (Cuota) locQuery.getSingleResult();
		} catch(Exception e) {
			cuota = new Cuota();
		}
		return cuota;
	}

	public List<Cuota> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c", Cuota.class);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.estado = :pEstado", Cuota.class);
		locQuery.setParameter("pEstado", estado);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.consignacion = :pConsignacion", Cuota.class);
		locQuery.setParameter("pConsignacion", consignacion);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado, Consignacion consignacion) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.estado = :pEstado AND c.consignacion = :pConsignacion", Cuota.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pConsignacion", consignacion);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

}
