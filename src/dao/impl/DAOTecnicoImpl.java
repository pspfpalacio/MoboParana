package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Caja;
import model.entity.Tecnico;
import dao.interfaces.DAOTecnico;

public class DAOTecnicoImpl implements DAOTecnico, Serializable {

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

	public int insertar(Tecnico tecnico) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(tecnico);
			tx.commit();
			return tecnico.getId();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}
	
	public int update(Tecnico tecnico) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE Tecnico t SET t.apellidoNombre = :pApellidoNombre, t.direccion = :pDireccion, t.email = :pEmail, "
					+ "t.estado = :pEstado, t.fechaAlta = :pFechaAlta, t.fechaBaja = :pFechaBaja, t.fechaMod = :pFechaMod, t.telefonoCel = :pTelefonoCel, "
					+ "t.telefonoFijo = :pTelefonoFijo, t.usuario1 = :pUsuarioAlta, t.usuario2 = :pUsuarioBaja, t.usuario3 = :pUsuarioMod "
					+ "WHERE t.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pApellidoNombre", tecnico.getApellidoNombre());
			locQuery.setParameter("pDireccion", tecnico.getDireccion());
			locQuery.setParameter("pEmail", tecnico.getEmail());
			locQuery.setParameter("pEstado", tecnico.getEstado());
			locQuery.setParameter("pFechaAlta", tecnico.getFechaAlta());
			locQuery.setParameter("pFechaBaja", tecnico.getFechaBaja());
			locQuery.setParameter("pFechaMod", tecnico.getFechaMod());
			locQuery.setParameter("pTelefonoCel", tecnico.getTelefonoCel());
			locQuery.setParameter("pTelefonoFijo", tecnico.getTelefonoFijo());
			locQuery.setParameter("pUsuarioAlta", tecnico.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", tecnico.getUsuario2());
			locQuery.setParameter("pUsuarioMod", tecnico.getUsuario3());
			locQuery.setParameter("pId", tecnico.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return tecnico.getId();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}

	public Tecnico get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT t FROM Tecnico t WHERE t.id = :pId", Tecnico.class);
		locQuery.setParameter("pId", id);
		Tecnico tecnico = new Tecnico();
		try{
			tecnico = (Tecnico) locQuery.getSingleResult();
		}catch (Exception e){
			e.printStackTrace();
			tecnico = new Tecnico();
		}
		return tecnico;
	}

	public List<Tecnico> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT t FROM Tecnico t ORDER BY t.apellidoNombre", Tecnico.class);
		List<Tecnico> lista = locQuery.getResultList();
		return lista;
	}

	public List<Tecnico> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT t FROM Tecnico t WHERE t.estado = :pEstado ORDER BY t.apellidoNombre", Tecnico.class);
		locQuery.setParameter("pEstado", estado);
		List<Tecnico> lista = locQuery.getResultList();
		return lista;
	}

}
