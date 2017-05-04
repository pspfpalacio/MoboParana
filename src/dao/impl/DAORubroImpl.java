package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Rubro;
import dao.interfaces.DAORubro;

public class DAORubroImpl implements Serializable, DAORubro {

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

	public int insertar(Rubro rubro) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(rubro);
			tx.commit();
			return rubro.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Rubro rubro) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE Rubro r SET r.estado = :pEstado, r.fechaAlta = :pFechaAlta, r.fechaBaja = :pFechaBaja, "
					+ "r.fechaMod = :pFechaMod, r.nombre = :pNombre, r.usuario1 = :pUsuarioAlta, r.usuario2 = :pUsuarioBaja, "
					+ "r.usuario3 = :pUsuarioMod "
					+ "WHERE r.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pEstado", rubro.getEstado());
			locQuery.setParameter("pFechaAlta", rubro.getFechaAlta());
			locQuery.setParameter("pFechaBaja", rubro.getFechaBaja());
			locQuery.setParameter("pFechaMod", rubro.getFechaMod());
			locQuery.setParameter("pNombre", rubro.getNombre());
			locQuery.setParameter("pUsuarioAlta", rubro.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", rubro.getUsuario2());
			locQuery.setParameter("pUsuarioMod", rubro.getUsuario3());
			locQuery.setParameter("pId", rubro.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return rubro.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public Rubro get(int id){
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Rubro r WHERE r.id = :pId", Rubro.class);
		locQuery.setParameter("pId", id);
		Rubro rubro = new Rubro();
		try{
			rubro = (Rubro) locQuery.getSingleResult();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			rubro = new Rubro();
		}
		return rubro;
	}

	public List<Rubro> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Rubro r", Rubro.class);
		List<Rubro> lista = locQuery.getResultList();
		return lista;
	}

	public List<Rubro> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Rubro r WHERE r.estado = :pEstado", Rubro.class);
		locQuery.setParameter("pEstado", estado);
		List<Rubro> lista = locQuery.getResultList();
		return lista;
	}

}
